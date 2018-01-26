/*
 *  Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.wso2.carbon.solution.deployer.tomcat;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.solution.CarbonSolutionException;
import org.wso2.carbon.solution.deployer.iam.impl.sp.oauth2.OAuth2Properties;
import org.wso2.carbon.solution.deployer.iam.impl.sp.saml.SAMLProperties;
import org.wso2.carbon.solution.endpoint.tomcat.TomcatServer;
import org.wso2.carbon.solution.model.server.Server;
import org.wso2.carbon.solution.model.server.iam.IdentityServerArtifact;
import org.wso2.carbon.solution.model.server.tomcat.TomcatServerArtifact;
import org.wso2.carbon.solution.model.solution.DeployerDependency;
import org.wso2.carbon.solution.model.solution.SolutionConfig;
import org.wso2.carbon.solution.util.ApplicationUtility;
import org.wso2.carbon.solution.util.Constant;
import org.wso2.carbon.solution.util.MultipartUtility;
import org.wso2.carbon.solution.util.ResourceManager;
import org.wso2.carbon.solution.util.ZipDir;
import org.wso2.carbon.solution.util.ZipUtility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * Web application deployer.
 */
public class WebAppDeployer {

    private static final String FRANCESCA = "francesca.com";
    private static final String LEBENS = "lebens.com";
    private static final String LEBENS_LEGAZY = "lebens-legazy.com";
    private static Log log = LogFactory.getLog(WebAppDeployer.class);

    private static String TOMCAT_SERVER_PATH = Constant.ResourcePath.RESOURCE_HOME_PATH + File.separator + Constant
            .ResourceFolder.SERVERS_HOME_FOLDER + File.separator + "tomcat" + File.separator + "apache-tomcat-9.0.1"
            + File.separator + "webapps";

    /**
     * Tomcat web app deploying.
     *
     * @param tomcatServerArtifact
     * @param server
     * @throws CarbonSolutionException
     */
    public void deploy(TomcatServerArtifact tomcatServerArtifact, Server server) throws CarbonSolutionException {

        //Creating tmp out folder.
        String outDirName = tomcatServerArtifact.getAbsoluteArtifactHomePath() + "out";
        File outDir = new File(outDirName);
        boolean mkdirs = outDir.mkdirs();
        if (!mkdirs) {
            throw new CarbonSolutionException("Error occurred while creating out folder.");
        }
        ApplicationUtility.addToCleaningTask(outDirName);

        deployWebApp(tomcatServerArtifact, server);
    }

    private void deployWebApp(TomcatServerArtifact tomcatServerArtifact, Server server)
            throws CarbonSolutionException {

        String solutionWebApp = tomcatServerArtifact.getSolution() + "-" + tomcatServerArtifact.getWebApp();
        String outDir = tomcatServerArtifact.getAbsoluteArtifactHomePath() + "out";

        try {
            ZipUtility.unzip(ApplicationUtility.getCommonWebAppHome() + File.separator + tomcatServerArtifact.getWebApp()
                    + Constant.WAR_EXT, outDir + File.separator + solutionWebApp);

            String customSSOPropertyFile = tomcatServerArtifact.getAbsoluteArtifactHomePath() + tomcatServerArtifact
                    .getWebApp() + File.separator + Constant.SSO_PROPERTIES;
            String ssoPropertyFileAbsolutePath = outDir + File.separator +
                    solutionWebApp + File.separator + Constant.WEB_INF +
                    File.separator + Constant.CLASSES_FOLDER +
                    File.separator + Constant.SSO_PROPERTIES;
            if (new File(customSSOPropertyFile).exists()) {
                FileUtils.copyFile(new File(customSSOPropertyFile), new File(ssoPropertyFileAbsolutePath));
            }

            SolutionConfig solutionConfig = ResourceManager.getSolutionConfig(tomcatServerArtifact.getSolution());
            List<DeployerDependency> deployerDependencies = solutionConfig.getDeployerDependencies();
            for (DeployerDependency deployerDependency : deployerDependencies) {
                if ((tomcatServerArtifact.getSolution() + File.separator + deployerDependency.getDeployer())
                        .equals(tomcatServerArtifact.getResourcePath())) {
                    List<IdentityServerArtifact> identityServerArtifactList = IdentityServerArtifact
                            .load(tomcatServerArtifact.getSolution() + File.separator +
                                    deployerDependency.getDependency());
                    if (identityServerArtifactList != null && identityServerArtifactList.size() == 1) {
                        IdentityServerArtifact identityServerArtifact = identityServerArtifactList.get(0);
                        Properties outProperty = identityServerArtifact.getOutProperty();

                        Properties ssoProperty = new Properties();

                        FileInputStream fileInputStream = null;
                        try {
                            fileInputStream = new FileInputStream(ssoPropertyFileAbsolutePath);
                            ssoProperty.load(fileInputStream);
                        } catch (IOException e) {
                            throw new CarbonSolutionException("Error occurred while loading property file.", e);
                        } finally {
                            try {
                                if (fileInputStream != null) {
                                    fileInputStream.close();
                                }
                            } catch (Exception e) {
                                log.error("Error occurred while closing the input stream.");
                            }
                        }

                        updateSSOProperties(outProperty, ssoProperty, tomcatServerArtifact.getWebApp(),
                                identityServerArtifact.getArtifactFile());

                        FileOutputStream outInputStream = null;
                        try {
                            outInputStream = new FileOutputStream(ssoPropertyFileAbsolutePath);
                            ssoProperty.store(outInputStream, null);
                        } catch (IOException e) {
                            throw new CarbonSolutionException("Error occurred while updating property file.", e);
                        } finally {
                            try {
                                if (outInputStream != null) {
                                    outInputStream.close();
                                }
                            } catch (Exception e) {
                                log.error("Error occurred while closing the output stream.");
                            }
                        }
                    }
                    break;
                }
            }
            ZipDir.zip(outDir + File.separator + solutionWebApp, "war");
            deployToFileSystem(outDir + File.separator + solutionWebApp + Constant.WAR_EXT, server);
        } catch (IOException e) {
            throw new CarbonSolutionException("Error occurred while deploying the app.", e);
        }
    }

    private void updateSSOProperties(Properties outProperty, Properties ssoProperty, String webApp, String
            spFile) {

        if (webApp.equals(FRANCESCA)) {
            SAMLProperties samlProperties = SAMLProperties.getSAMLProperties(outProperty, spFile);
            if (StringUtils.isNotEmpty(samlProperties.getSpEntityId())) {
                ssoProperty.setProperty("SAML2.SPEntityId", samlProperties.getSpEntityId());
            }
            if (StringUtils.isNotEmpty(samlProperties.getAssertionConsumerUrl())) {
                ssoProperty.setProperty("SAML2.AssertionConsumerURL", samlProperties.getAssertionConsumerUrl());
            }
            if (StringUtils.isNotEmpty(samlProperties.getIdpEntityId())) {
                ssoProperty.setProperty("SAML2.IdPEntityId", samlProperties.getIdpEntityId());
            }
            if (StringUtils.isNotEmpty(samlProperties.getIdpUrl())) {
                ssoProperty.setProperty("SAML2.IdPURL", samlProperties.getIdpUrl());
            }
        } else if (webApp.equals(LEBENS) || webApp.equals(LEBENS_LEGAZY)) {
            OAuth2Properties oAuth2Properties = OAuth2Properties.getOAuth2Properties(outProperty, spFile);
            if (StringUtils.isNotEmpty(oAuth2Properties.getCallbackUrl())) {
                ssoProperty.setProperty("OIDC.CallBackUrl", oAuth2Properties.getCallbackUrl());
            }
            if (StringUtils.isNotEmpty(oAuth2Properties.getAuthorizeEndpoint())) {
                if (((String) ssoProperty.get("OIDC.AuthorizeEndpoint")).endsWith("/oauth2/authorize")) {
                    ssoProperty.setProperty("OIDC.AuthorizeEndpoint", oAuth2Properties.getAuthorizeEndpoint());
                } else {
                    String replaceURL = ((String) ssoProperty.get("OIDC.AuthorizeEndpoint"))
                            .replace("https://localhost:9443",
                                    oAuth2Properties.getServerUrl());
                    ssoProperty.setProperty("OIDC.AuthorizeEndpoint", replaceURL);
                }
            }
            if (StringUtils.isNotEmpty(oAuth2Properties.getTokenEndpoint())) {
                ssoProperty.setProperty("OIDC.TokenEndpoint", oAuth2Properties.getTokenEndpoint());
            }
            if (StringUtils.isNotEmpty(oAuth2Properties.getUserInfoEndpoint())) {
                ssoProperty.setProperty("OIDC.UserInfoEndpoint", oAuth2Properties.getUserInfoEndpoint());
            }
            if (StringUtils.isNotEmpty(oAuth2Properties.getConsumerKey())) {
                ssoProperty.setProperty("OIDC.ClientId", oAuth2Properties.getConsumerKey());
            }
            if (StringUtils.isNotEmpty(oAuth2Properties.getConsumerSecret())) {
                ssoProperty.setProperty("OIDC.ClientSecret", oAuth2Properties.getConsumerSecret());
            }
        }
    }

    private void deployToFileSystem(String file, Server server) throws CarbonSolutionException {

        File uploadFile1 = new File(file);
        TomcatServer tomcatServer = new TomcatServer(server);
        String deployPath = tomcatServer.getDeployPath();
        if (StringUtils.isEmpty(deployPath)) {
            deployPath = TOMCAT_SERVER_PATH;
        }
        try {
            FileUtils.copyFile(uploadFile1, new File(deployPath + File.separator + uploadFile1.getName()));
        } catch (IOException e) {
            throw new CarbonSolutionException("Error occurred while copying file, " + file, e);
        }
    }

    private void deploy(String file, Server server) throws CarbonSolutionException {

        TomcatServer tomcatServer = new TomcatServer(server);
        String charset = "UTF-8";
        File uploadFile1 = new File(file);

        unDeploy(uploadFile1.getName(), server);

        String requestURL = tomcatServer.getHTTPServerURL() + "/manager/html/upload";
        try {
            MultipartUtility multipart = new MultipartUtility(requestURL, charset);
            multipart.addHeaderField("User-Agent", "CodeJava");
            multipart.addHeaderField("Test-Header", "Header-Value");
            multipart.addFilePart("deployWar", uploadFile1);
            multipart.finish();
            log.info("Artifact = " + file + " deployed to the tomcat.");
        } catch (IOException ex) {
            throw new CarbonSolutionException("Error occurred while deploy file, " + file, ex);
        } finally {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {

            }
        }
    }

    private void unDeploy(String name, Server server) throws CarbonSolutionException {

        TomcatServer tomcatServer = new TomcatServer(server);
        String charset = "UTF-8";
        String requestURL = tomcatServer.getHTTPServerURL() + "/manager/html/undeploy?path=/" + name
                .replace(".war", "");
        try {
            MultipartUtility multipart = new MultipartUtility(requestURL, charset);
            multipart.finish();
            log.info("Artifact = " + name + " undeployed from the tomcat.");
        } catch (Exception ex) {
            throw new CarbonSolutionException("Error occurred while unDeploy file, " + name, ex);
        }
    }
}
