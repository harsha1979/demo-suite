/*
 *
 * Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 */

package org.wso2.carbon.solution.deployer.iam.impl.sp.oauth2;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.application.common.model.xsd.InboundAuthenticationConfig;
import org.wso2.carbon.identity.application.common.model.xsd.InboundAuthenticationRequestConfig;
import org.wso2.carbon.identity.application.common.model.xsd.Property;
import org.wso2.carbon.identity.application.common.model.xsd.ServiceProvider;
import org.wso2.carbon.identity.oauth.stub.dto.OAuthConsumerAppDTO;
import org.wso2.carbon.solution.CarbonSolutionException;
import org.wso2.carbon.solution.endpoint.iam.IdentityServerAdminClient;
import org.wso2.carbon.solution.endpoint.iam.config.IdentityServer;
import org.wso2.carbon.solution.model.server.Server;
import org.wso2.carbon.solution.model.server.iam.IdentityServerArtifact;
import org.wso2.carbon.solution.util.ApplicationUtility;
import org.wso2.carbon.solution.util.ResourceManager;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * OAuth2 application Deployer..
 */
public class OAuth2ConfigDeployer {

    public static final String PROTOCOL_OAUTH2 = "oauth2";
    private static Log log = LogFactory.getLog(OAuth2ConfigDeployer.class);

    public static void deploy(ServiceProvider serviceProvider,
                              IdentityServerArtifact identityServerArtifact,
                              Server server)
            throws CarbonSolutionException {

        OAuthConsumerAppDTO authConsumerAppDTO_server = null;

        OAuthConsumerAppDTO oAuthConsumerAppDTO = null;
        InboundAuthenticationConfig inboundAuthenticationConfig = serviceProvider.getInboundAuthenticationConfig();
        if (inboundAuthenticationConfig != null) {
            InboundAuthenticationRequestConfig[] inboundAuthenticationRequestConfigs = inboundAuthenticationConfig
                    .getInboundAuthenticationRequestConfigs();
            if (inboundAuthenticationRequestConfigs != null && inboundAuthenticationRequestConfigs.length > 0) {
                for (InboundAuthenticationRequestConfig inboundAuthenticationRequestConfig :
                        inboundAuthenticationRequestConfigs) {
                    if (inboundAuthenticationRequestConfig.getInboundAuthType().equals(PROTOCOL_OAUTH2)) {
                        oAuthConsumerAppDTO = ResourceManager.loadXMLToObject(
                                identityServerArtifact.getAbsoluteResourcePath(), OAuthConsumerAppDTO.class);
                        oAuthConsumerAppDTO.setApplicationName(serviceProvider.getApplicationName());
                        try {
                            authConsumerAppDTO_server =
                                    getOAuthConsumerDTO(serviceProvider.getApplicationName(), server);
                            if (authConsumerAppDTO_server != null) {
                                try {
                                    oAuthConsumerAppDTO
                                            .setOauthConsumerSecret(authConsumerAppDTO_server.getOauthConsumerSecret());
                                    oAuthConsumerAppDTO
                                            .setOauthConsumerKey(authConsumerAppDTO_server.getOauthConsumerKey());
                                    IdentityServerAdminClient.getOAuthAdminService(server)
                                            .updateConsumerApplication(oAuthConsumerAppDTO);
                                } catch (Exception e) {
                                    log.warn("Error occurred while updating oath2 application, " + e.getMessage());
                                    IdentityServerAdminClient.getOAuthAdminService(server)
                                            .removeOAuthApplicationData(
                                                    authConsumerAppDTO_server.getOauthConsumerKey());
                                }
                            } else {
                                IdentityServerAdminClient.getOAuthAdminService(server)
                                        .registerOAuthApplicationData(oAuthConsumerAppDTO);
                            }

                            authConsumerAppDTO_server = IdentityServerAdminClient.getOAuthAdminService(server)
                                    .getOAuthApplicationDataByAppName(serviceProvider.getApplicationName());

                            inboundAuthenticationRequestConfig
                                    .setInboundAuthKey(authConsumerAppDTO_server.getOauthConsumerKey());
                            Property oauthConsumerSecret = new Property();
                            oauthConsumerSecret.setName(OAuth2Config.OAUTH_CONSUMER_KEY.getConfigName());
                            oauthConsumerSecret.setValue(authConsumerAppDTO_server.getOauthConsumerKey());

                            Property[] properties = inboundAuthenticationRequestConfig.getProperties();
                            if (properties != null) {
                                Arrays.fill(properties, oauthConsumerSecret);
                            } else {
                                properties = new Property[1];
                                properties[0] = oauthConsumerSecret;
                                inboundAuthenticationRequestConfig.setProperties(properties);
                            }
                        } catch (Exception e) {
                            String errorMsg = "Error occurred while updating the existing oath2 application.";
                            throw new CarbonSolutionException(errorMsg, e);
                        }
                        break;
                    }
                }
            }
        }

        IdentityServer identityServer = new IdentityServer(server);
        OAuth2Properties oAuth2Properties = new OAuth2Properties(identityServerArtifact.getArtifactFile());
        oAuth2Properties.setServerUrl(identityServer.getHTTPSServerURL());
        oAuth2Properties.setAuthorizeEndpoint(identityServer.getHTTPSServerURL() + "/oauth2/authorize");
        oAuth2Properties.setTokenEndpoint(identityServer.getHTTPSServerURL() + "/oauth2/token");
        oAuth2Properties.setUserInfoEndpoint(identityServer.getHTTPSServerURL() + "/oauth2/userinfo");
        oAuth2Properties.setSloUrl(identityServer.getHTTPSServerURL() + "/oauth2/logout");
        if (authConsumerAppDTO_server != null) {
            oAuth2Properties.setConsumerKey(authConsumerAppDTO_server.getOauthConsumerKey());
            oAuth2Properties.setConsumerSecret(authConsumerAppDTO_server.getOauthConsumerSecret());
            oAuth2Properties.setCallbackUrl(authConsumerAppDTO_server.getCallbackUrl());
        } else {
            log.error("OAuth Consumer app data not available.");
        }
        ApplicationUtility.updateProperty(identityServerArtifact, oAuth2Properties.getOauth2Properties());
    }

    public static OAuthConsumerAppDTO getOAuthConsumerDTO(String applicationName, Server server)
            throws CarbonSolutionException {

        OAuthConsumerAppDTO oAuthConsumerAppDTO = null;
        try {
            OAuthConsumerAppDTO[] allOAuthApplicationData_server = IdentityServerAdminClient
                    .getOAuthAdminService(server)
                    .getAllOAuthApplicationData();
            if (allOAuthApplicationData_server != null) {
                for (OAuthConsumerAppDTO authConsumerAppDTO_server :
                        allOAuthApplicationData_server) {
                    if (authConsumerAppDTO_server.getApplicationName().equals(applicationName)) {
                        oAuthConsumerAppDTO = authConsumerAppDTO_server;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            log.warn("Error occurred while getOAuthConsumerDTO, " + e.getMessage());
        }
        return oAuthConsumerAppDTO;
    }

    /*public static void deploy(org.wso2.carbon.solution.model.iam.sp.ServiceProvider serviceProvider_source,
                              IdentityServerArtifact identityServerArtifact, Server server)
            throws CarbonSolutionException {
        Properties updateProperty = new Properties();

        InboundAuthenticationConfig inboundAuthenticationConfig_source =
                serviceProvider_source.getInboundAuthenticationConfig();
        if (inboundAuthenticationConfig_source != null) {
            List<InboundAuthenticationRequestConfig> inboundAuthenticationRequestConfigs_source
                    = inboundAuthenticationConfig_source.getInboundAuthenticationRequestConfigs();
            if (inboundAuthenticationRequestConfigs_source != null) {
                for (InboundAuthenticationRequestConfig inboundAuthenticationRequestConfig_source :
                        inboundAuthenticationRequestConfigs_source) {

                    if (PROTOCOL_OAUTH2.equals(inboundAuthenticationRequestConfig_source.getInboundAuthType())) {
                        updateSensibleDefaults(inboundAuthenticationRequestConfig_source);
                        OAuthConsumerAppDTO oAuthConsumerAppDTO_dest = getoAuthConsumerAppDTO(
                                inboundAuthenticationRequestConfig_source);

                        try {
                            try {
                                OAuthConsumerAppDTO[] allOAuthApplicationData_server = IdentityServerAdminClient
                                .getOAuthAdminService(server)
                                        .getAllOAuthApplicationData();
                                if (allOAuthApplicationData_server != null) {
                                    for (OAuthConsumerAppDTO authConsumerAppDTO_server :
                                            allOAuthApplicationData_server) {
                                        if (authConsumerAppDTO_server.getApplicationName()
                                                .equals(oAuthConsumerAppDTO_dest.getApplicationName())) {
                                            IdentityServerAdminClient.getOAuthAdminService(server)
                                            .removeOAuthApplicationData(
                                                    authConsumerAppDTO_server.getOauthConsumerKey());
                                            break;
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                throw new CarbonSolutionException("Error occurred while getAllOAuthApplicationData.",e);
                            }
                            IdentityServerAdminClient.getOAuthAdminService(server).registerOAuthApplicationData
                            (oAuthConsumerAppDTO_dest);
                            OAuthConsumerAppDTO[] allOAuthApplicationData_server = IdentityServerAdminClient
                            .getOAuthAdminService(server)
                                    .getAllOAuthApplicationData();
                            if (allOAuthApplicationData_server != null) {
                                for (OAuthConsumerAppDTO oAuthConsumerAppDTO_server : allOAuthApplicationData_server) {
                                    if (oAuthConsumerAppDTO_server.getApplicationName().equals(oAuthConsumerAppDTO_dest
                                                                                                       .getApplicationName()

                                    )) {
                                        List<Property> newProperties = new ArrayList<Property>();
                                        Property property = new Property();
                                        property.setName(
                                                OAuth2Config.OAUTH_CONSUMER_SECRET.getConfigName());
                                        property.setValue(oAuthConsumerAppDTO_server.getOauthConsumerSecret());
                                        newProperties.add(property);
                                        inboundAuthenticationRequestConfig_source.setProperties(newProperties);
                                        inboundAuthenticationRequestConfig_source.setInboundAuthKey
                                                (oAuthConsumerAppDTO_server.getOauthConsumerKey());
                                        updateProperty.put(identityServerArtifact.getArtifactFile()
                                                           + "-OIDC.ClientId",
                                                           oAuthConsumerAppDTO_server.getOauthConsumerKey());
                                        updateProperty.put(identityServerArtifact.getArtifactFile()
                                                           + "-OIDC.ClientSecret",
                                                           oAuthConsumerAppDTO_server.getOauthConsumerSecret
                                                                   ());
                                        updateProperty.put(identityServerArtifact.getArtifactFile()
                                                           + "-OIDC.CallBackUrl",
                                                           oAuthConsumerAppDTO_server.getCallbackUrl());
                                        break;
                                    }
                                }
                            }
                        } catch (Exception e) {
                            throw new CarbonSolutionException("Error occurred while getAllOAuthApplicationData.", e);
                        }
                    }
                }
            }
        }

        IdentityServer identityServer = new IdentityServer(server);
        updateProperty.put(identityServerArtifact.getArtifactFile()
                           + "-OIDC.IdentityServerHostName", identityServer.getHost());
        updateProperty.put(identityServerArtifact.getArtifactFile()
                           + "-OIDC.IdentityServerPort", identityServer.getPort() + "");
        ApplicationUtility.updateProperty(identityServerArtifact, updateProperty);
    }

    private static OAuthConsumerAppDTO getoAuthConsumerAppDTO(InboundAuthenticationRequestConfig
                                                                      inboundAuthenticationRequestConfig_source) {
        List<Property> properties = inboundAuthenticationRequestConfig_source.getProperties();
        OAuthConsumerAppDTO oAuthConsumerAppDTO_dest = new OAuthConsumerAppDTO();
        for (Property property : properties) {
            if (property.getName().equals(OAuth2Config.OAUTH_VERSION.getConfigName())) {
                oAuthConsumerAppDTO_dest.setOAuthVersion(property.getValue());
            }
            if (property.getName().equals(OAuth2Config.APPLICATION_NAME.getConfigName())) {
                oAuthConsumerAppDTO_dest.setApplicationName(property.getValue());
            }
            if (property.getName().equals(OAuth2Config.CALLBACK_URL.getConfigName())) {
                oAuthConsumerAppDTO_dest.setCallbackUrl(property.getValue());
            }
            if (property.getName().equals(OAuth2Config.GRANT_TYPES.getConfigName())) {
                oAuthConsumerAppDTO_dest.setGrantTypes(property.getValue());
            }
            if (property.getName().equals(OAuth2Config.OAUTH_CONSUMER_KEY.getConfigName())) {
                oAuthConsumerAppDTO_dest.setOauthConsumerKey(property.getValue());
            }
            if (property.getName().equals(OAuth2Config.OAUTH_CONSUMER_SECRET.getConfigName())) {
                oAuthConsumerAppDTO_dest.setOauthConsumerSecret(property.getValue());
            }
            if (property.getName().equals(OAuth2Config.PKCE_MANDATORY.getConfigName())) {
                oAuthConsumerAppDTO_dest.setPkceMandatory(Boolean.parseBoolean(property.getValue()));
            }
            if (property.getName().equals(OAuth2Config.PKCE_SUPPORT_PLAN.getConfigName())) {
                oAuthConsumerAppDTO_dest.setPkceSupportPlain(Boolean.parseBoolean(property.getValue()));
            }
            if (property.getName().equals(OAuth2Config.STATE.getConfigName())) {
                oAuthConsumerAppDTO_dest.setState(property.getValue());
            }
            if (property.getName().equals(OAuth2Config.REFRESH_TOKEN_EXPIRE_TIME.getConfigName())) {
                oAuthConsumerAppDTO_dest.setRefreshTokenExpiryTime(Long.parseLong(property.getValue()));
            }
            if (property.getName().equals(OAuth2Config.USER_ACCESS_TOKEN_EXPIRE_TIME.getConfigName())) {
                oAuthConsumerAppDTO_dest.setUserAccessTokenExpiryTime(
                        Long.parseLong(property.getValue()));
            }
            if (property.getName().equals(OAuth2Config.APPLICATION_ACCESS_TOKEN_EXPIRE_TIME.getConfigName())) {
                oAuthConsumerAppDTO_dest.setApplicationAccessTokenExpiryTime(
                        Long.parseLong(property.getValue()));
            }
            if (property.getName().equals(OAuth2Config.USER_NAME.getConfigName())) {
                oAuthConsumerAppDTO_dest.setOAuthVersion(property.getValue());
            }
        }
        return oAuthConsumerAppDTO_dest;
    }

    private static void updateSensibleDefaults(InboundAuthenticationRequestConfig
                                                       inboundAuthenticationRequestConfig_source) {

        List<Property> properties = inboundAuthenticationRequestConfig_source.getProperties();
        Set<String> propertyNameSet = new HashSet<>();
        for (Property property : properties) {
            propertyNameSet.add(property.getName());
        }

        updateDefaultIfNotExist(properties, propertyNameSet, OAuth2Config.OAUTH_VERSION);
        updateDefaultIfNotExist(properties, propertyNameSet, OAuth2Config.GRANT_TYPES);
        updateDefaultIfNotExist(properties, propertyNameSet, OAuth2Config.PKCE_MANDATORY);
        updateDefaultIfNotExist(properties, propertyNameSet, OAuth2Config.PKCE_SUPPORT_PLAN);
        updateDefaultIfNotExist(properties, propertyNameSet, OAuth2Config.REFRESH_TOKEN_EXPIRE_TIME);
        updateDefaultIfNotExist(properties, propertyNameSet, OAuth2Config.USER_ACCESS_TOKEN_EXPIRE_TIME);
        updateDefaultIfNotExist(properties, propertyNameSet, OAuth2Config.APPLICATION_ACCESS_TOKEN_EXPIRE_TIME);
    }

    private static void updateDefaultIfNotExist(List<Property> properties, Set<String> propertyNameSet,
                                                OAuth2Config oAuth2Config) {
        if (!propertyNameSet.contains(oAuth2Config)) {
            Property property = new Property();
            property.setName(oAuth2Config.getConfigName());
            property.setValue(oAuth2Config.getDefaultValue());
            properties.add(property);
        }
    }*/

    public static IdentityServerArtifact getOAuthArtifactIfExists(IdentityServerArtifact identityServerArtifact)
            throws CarbonSolutionException {

        IdentityServerArtifact identityServerArtifactRet = null;
        String artifactFile = identityServerArtifact.getArtifactFile();
        String oauth2ResourceName = artifactFile.replace(".xml", "_oauth.xml");
        String oauth2AbsoluteResourcePath = identityServerArtifact.getAbsoluteArtifactHomePath()
                + File.separator + oauth2ResourceName;
        if (new File(oauth2AbsoluteResourcePath).exists()) {
            String oauth2ResourcePath = identityServerArtifact.getResourcePath().
                    replace(identityServerArtifact.getArtifactFile(), oauth2ResourceName);
            List<IdentityServerArtifact> load = IdentityServerArtifact.load(oauth2ResourcePath);
            if (load.size() == 1) {
                identityServerArtifactRet = load.get(0);
            }
        }
        return identityServerArtifactRet;
    }
}
