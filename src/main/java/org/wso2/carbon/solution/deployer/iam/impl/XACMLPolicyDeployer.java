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

package org.wso2.carbon.solution.deployer.iam.impl;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.entitlement.stub.dto.PolicyDTO;
import org.wso2.carbon.solution.CarbonSolutionException;
import org.wso2.carbon.solution.deployer.iam.IdentityServerDeployer;
import org.wso2.carbon.solution.endpoint.iam.IdentityServerAdminClient;
import org.wso2.carbon.solution.model.server.Server;
import org.wso2.carbon.solution.model.server.iam.IdentityServerArtifact;
import org.wso2.carbon.solution.util.ResourceManager;

public class XACMLPolicyDeployer extends IdentityServerDeployer {
    private static Log log = LogFactory.getLog(XACMLPolicyDeployer.class);

    @Override
    protected void doDeploy(IdentityServerArtifact identityServerArtifact, Server server)
            throws CarbonSolutionException {
        PolicyDTO policyDTO = ResourceManager.loadXMLToObject(
                identityServerArtifact.getAbsoluteResourcePath(), PolicyDTO.class);
        String policyId = policyDTO.getPolicyId();
        try {
            try {
                PolicyDTO policy = IdentityServerAdminClient.getEntitlementPolicyAdminService(server)
                        .getPolicy(policyDTO.getPolicyId(), false);
                if (policy != null) {
                    IdentityServerAdminClient.getEntitlementPolicyAdminService(server).dePromotePolicy(policyId);
                    IdentityServerAdminClient.getEntitlementPolicyAdminService(server).removePolicy(policyId, false);
                }
            } catch (Exception e) {
                log.error("Error occurred while clearing the existing XACML policies, " + e.getMessage());
            }

            IdentityServerAdminClient.getEntitlementPolicyAdminService(server).addPolicy(policyDTO);
            PolicyDTO policy = IdentityServerAdminClient.getEntitlementPolicyAdminService(server)
                    .getPolicy(policyDTO.getPolicyId(), false);
            IdentityServerAdminClient.getEntitlementPolicyAdminService(server).
                    publishPolicies(new String[] { policy.getPolicyId() },
                                    new String[] { "PDP Subscriber" }, "CREATE", policy.getVersion(), true, 0);
        } catch (Exception e) {
            throw new CarbonSolutionException("Error occurred while installing XACML policies, " +
                                              identityServerArtifact.getResourcePath());
        }
    }

    @Override
    protected void cleanAll(Server server) throws CarbonSolutionException {

    }

    @Override
    protected String getArtifactType() {
        return "xacml-policy";
    }
}