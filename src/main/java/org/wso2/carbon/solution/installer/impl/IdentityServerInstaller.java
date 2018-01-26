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
package org.wso2.carbon.solution.installer.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.solution.CarbonSolutionException;
import org.wso2.carbon.solution.deployer.iam.IdentityServerDeployer;
import org.wso2.carbon.solution.deployer.iam.IdentityServerDeployerFactory;
import org.wso2.carbon.solution.endpoint.iam.IdentityServerAdminClient;
import org.wso2.carbon.solution.endpoint.iam.config.IdentityServer;
import org.wso2.carbon.solution.installer.Installer;
import org.wso2.carbon.solution.model.server.Server;
import org.wso2.carbon.solution.model.server.iam.IdentityServerArtifact;
import org.wso2.carbon.solution.util.Constant;
import org.wso2.carbon.solution.util.ResourceManager;
import org.wso2.carbon.tenant.mgt.stub.beans.xsd.TenantInfoBean;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * IdentityServerInstaller is an installer that is implemented to target to WSO2 Identity Server artifacts.
 */
public class IdentityServerInstaller extends Installer {

    private static Log log = LogFactory.getLog(IdentityServerInstaller.class);
    private static Set<String> tenantDomains = new HashSet<>();

    @Override
    public void install(String resourcePath) throws CarbonSolutionException {

        List<IdentityServerArtifact> identityServerArtifactList = IdentityServerArtifact.load(resourcePath);
        for (IdentityServerArtifact identityServerArtifact : identityServerArtifactList) {
            log.info("Installing artifact in IdentityServer under, " + identityServerArtifact.getResourcePath());
            if (ignore(identityServerArtifact)) {
                continue;
            }
            Server serverConfig = ResourceManager
                    .getServerConfig(getInstallerName(), identityServerArtifact.getInstanceName());
            if (!identityServerArtifact.getTenantDomain().equals(Constant.Tenant.CARBON_SUPER)) {
                createTenant(identityServerArtifact.getTenantDomain(), serverConfig);
            }
            IdentityServerDeployer deployer = IdentityServerDeployerFactory.getInstance()
                    .getDeployer(identityServerArtifact.getArtifactType());
            deployer.deploy(identityServerArtifact, serverConfig);
        }
    }

    private boolean ignore(IdentityServerArtifact identityServerArtifact) {

        String artifactFile = identityServerArtifact.getArtifactFile();
        if (artifactFile.endsWith("_oauth.xml") || artifactFile.endsWith("_saml.xml") || artifactFile.endsWith(".db")
                || artifactFile.equals("out.properties") || artifactFile.endsWith("_association.properties")) {
            return true;
        }
        return false;
    }

    @Override
    protected String getInstallerName() {

        return "identity-server";
    }

    private void createTenant(String tenantDomain, Server serverConfig) throws CarbonSolutionException {

        if (tenantDomains.contains(tenantDomain)) {
            return;
        }
        ResourceManager.loadKeyStores(serverConfig.getServerName(), serverConfig.getInstance());
        try {
            IdentityServer identityServer = new IdentityServer(serverConfig);
            TenantInfoBean tenant = IdentityServerAdminClient.getTenantMgtAdminService(serverConfig).getTenant(tenantDomain);
            if (StringUtils.isEmpty(tenant.getAdmin())) {
                TenantInfoBean tenantInfoBean = new TenantInfoBean();
                tenantInfoBean.setActive(true);
                tenantInfoBean.setAdmin(identityServer.getTenantAdminUserName());
                tenantInfoBean.setAdminPassword(identityServer.getTenantAdminPassword());
                tenantInfoBean.setFirstname(tenantDomain);
                tenantInfoBean.setLastname(tenantDomain);
                tenantInfoBean.setTenantDomain(tenantDomain);
                tenantInfoBean.setEmail("admin@" + tenantDomain);
                IdentityServerAdminClient.getTenantMgtAdminService(serverConfig).addTenant(tenantInfoBean);
            }
            tenant = IdentityServerAdminClient.getTenantMgtAdminService(serverConfig).getTenant(tenantDomain);
            tenantDomains.add(tenant.getTenantDomain());
        } catch (Exception e) {
            throw new CarbonSolutionException("Error occurred while create a tenant : " + tenantDomain, e);
        }

    }
}
