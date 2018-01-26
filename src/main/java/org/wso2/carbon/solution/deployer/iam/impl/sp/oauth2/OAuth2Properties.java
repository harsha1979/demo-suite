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

import java.util.Properties;

public class OAuth2Properties {

    public static final String SERVER_URL = "ServerURL";
    public static final String CONSUMER_KEY = "ConsumerKey";
    public static final String CONSUMER_SECRET = "ConsumerSecret";
    public static final String CALLBACK_URL = "CallbackURL";
    public static final String SLO_URL = "SLOURL";
    public static final String IDP_ENTITY_ID = "IDPEntityID";
    public static final String IDP_URL = "IDPURL";
    public static final String USER_INFO_ENDPOINT = "UserInfoEndpoint";
    public static final String TOKEN_ENDPOINT = "TokenEndpoint";
    public static final String AUTHORIZE_ENDPOINT = "AuthorizeEndpoint";
    public static final String SP_NAME = "ServiceProviderName";
    private static final String PREFIX = "OAUTH2";
    public Properties oauth2Properties = null;
    private String fileName;

    public OAuth2Properties(String fileName) {

        this.fileName = fileName;
        this.oauth2Properties = new Properties();
    }

    private OAuth2Properties(Properties oauth2Properties) {

        this.oauth2Properties = oauth2Properties;
    }

    public static OAuth2Properties getOAuth2Properties(Properties properties, String fileName) {

        OAuth2Properties oAuth2Properties = new OAuth2Properties(properties);
        oAuth2Properties.setFileName(fileName);
        return oAuth2Properties;
    }

    public String getAuthorizeEndpoint() {

        return getOauth2PropertyValue(AUTHORIZE_ENDPOINT);
    }

    public void setAuthorizeEndpoint(String authorizeEndpoint) {

        this.setOAuth2Property(AUTHORIZE_ENDPOINT, authorizeEndpoint);
    }

    public String getCallbackUrl() {

        return getOauth2PropertyValue(CALLBACK_URL);
    }

    public void setCallbackUrl(String callbackUrl) {

        this.setOAuth2Property(CALLBACK_URL, callbackUrl);
    }

    public String getConsumerKey() {

        return getOauth2PropertyValue(CONSUMER_KEY);
    }

    public void setConsumerKey(String consumerKey) {

        this.setOAuth2Property(CONSUMER_KEY, consumerKey);
    }

    public String getConsumerSecret() {

        return getOauth2PropertyValue(CONSUMER_SECRET);
    }

    public void setConsumerSecret(String consumerSecret) {

        this.setOAuth2Property(CONSUMER_SECRET, consumerSecret);
    }

    public String getIdpEntityId() {

        return getOauth2PropertyValue(IDP_ENTITY_ID);
    }

    public void setIdpEntityId(String idpEntityId) {

        this.setOAuth2Property(IDP_ENTITY_ID, idpEntityId);
    }

    public String getIdpUrl() {

        return getOauth2PropertyValue(IDP_URL);
    }

    public void setIdpUrl(String idpUrl) {

        this.setOAuth2Property(IDP_URL, idpUrl);
    }

    public Properties getOauth2Properties() {

        return oauth2Properties;
    }

    public String getServerUrl() {

        return getOauth2PropertyValue(SERVER_URL);
    }

    public void setServerUrl(String serverUrl) {

        this.setOAuth2Property(SERVER_URL, serverUrl);
    }

    public String getSloUrl() {

        return getOauth2PropertyValue(SLO_URL);
    }

    public void setSloUrl(String sloUrl) {

        this.setOAuth2Property(SLO_URL, sloUrl);
    }

    public String getSpName() {

        return getOauth2PropertyValue(SP_NAME);
    }

    public void setSpName(String spName) {

        this.setOAuth2Property(SP_NAME, spName);
    }

    public String getTokenEndpoint() {

        return getOauth2PropertyValue(TOKEN_ENDPOINT);
    }

    public void setTokenEndpoint(String tokenEndpoint) {

        this.setOAuth2Property(TOKEN_ENDPOINT, tokenEndpoint);
    }

    public String getUserInfoEndpoint() {

        return getOauth2PropertyValue(USER_INFO_ENDPOINT);
    }

    public void setUserInfoEndpoint(String userInfoEndpoint) {

        this.setOAuth2Property(USER_INFO_ENDPOINT, userInfoEndpoint);
    }

    public void setFileName(String fileName) {

        this.fileName = fileName;
    }

    private String getOauth2PropertyValue(String key) {

        String propertyName = PREFIX + "_" + fileName + "_" + key;
        return (String) this.oauth2Properties.get(propertyName);
    }

    private void setOAuth2Property(String key, String value) {

        String propertyName = PREFIX + "_" + fileName + "_" + key;
        this.oauth2Properties.setProperty(propertyName, value);
    }
}
