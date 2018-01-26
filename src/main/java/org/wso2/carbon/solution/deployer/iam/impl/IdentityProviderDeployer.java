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

package org.wso2.carbon.solution.deployer.iam.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.application.common.model.idp.xsd.IdentityProvider;
import org.wso2.carbon.solution.CarbonSolutionException;
import org.wso2.carbon.solution.deployer.iam.IdentityServerDeployer;
import org.wso2.carbon.solution.endpoint.iam.IdentityServerAdminClient;
import org.wso2.carbon.solution.model.server.Server;
import org.wso2.carbon.solution.model.server.iam.IdentityServerArtifact;
import org.wso2.carbon.solution.util.Constant;
import org.wso2.carbon.solution.util.ResourceManager;

/**
 * IdentityProviderDeployer is deploying identity server artifacts.
 */
public class IdentityProviderDeployer extends IdentityServerDeployer {

    private Log log = LogFactory.getLog(IdentityProviderDeployer.class);

    @Override
    public void doDeploy(IdentityServerArtifact identityServerArtifact, Server server) throws
            CarbonSolutionException {

        IdentityProvider identityProvider = ResourceManager
                .loadXMLToObject(identityServerArtifact.getAbsoluteResourcePath(), IdentityProvider.class);

        IdentityProvider idPByName = null;
        try {
            idPByName = IdentityServerAdminClient.getIdentityProviderMgtService(server)
                    .getIdPByName(identityProvider.getIdentityProviderName());
        } catch (Exception e) {
            log.error("Error occurred while getIdPByName, " + identityProvider.getIdentityProviderName());
        }
        if (idPByName == null) {
            log.debug("Add new IDP, " + identityProvider.getIdentityProviderName());
            try {
                IdentityServerAdminClient.getIdentityProviderMgtService(server).addIdP(identityProvider);
            } catch (Exception e) {
                log.error("Error occurred while addIdP, " + identityProvider.getIdentityProviderName());
            }
        } else {
            log.debug("Update existing IDP, " + identityProvider.getIdentityProviderName());
            try {
                IdentityServerAdminClient.getIdentityProviderMgtService(server)
                        .updateIdP(idPByName.getIdentityProviderName(), identityProvider);
            } catch (Exception e) {
                log.error("Error occurred while updateIdP, " + identityProvider.getIdentityProviderName());
            }
        }
    }

    @Override
    protected void cleanAll(Server server) throws CarbonSolutionException {

        try {
            IdentityProvider[] allIdPs = IdentityServerAdminClient.getIdentityProviderMgtService(server).getAllIdPs();
            for (IdentityProvider idp : allIdPs) {
                try {
                    IdentityServerAdminClient.getIdentityProviderMgtService(server)
                            .deleteIdP(idp.getIdentityProviderName());
                } catch (Exception e) {
                    log.error("Error occurred while deleting idp, " + idp.getIdentityProviderName(), e);
                }
            }
        } catch (Exception e) {
            log.error("Error occurred while getAllIdPs, " + e.getMessage());
        }
    }

    @Override
    protected String getArtifactType() {

        return Constant.ResourceFolder.IDENTITY_PROVIDERS_FOLDER;
    }
}
