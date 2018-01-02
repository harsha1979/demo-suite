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
import org.wso2.carbon.solution.CarbonSolutionException;
import org.wso2.carbon.solution.deployer.iam.IdentityServerDeployer;
import org.wso2.carbon.solution.deployer.iam.impl.sp.role.RoleAttribute;
import org.wso2.carbon.solution.deployer.iam.impl.sp.user.UserAttribute;
import org.wso2.carbon.solution.endpoint.iam.IdentityServerAdminClient;
import org.wso2.carbon.solution.model.server.Server;
import org.wso2.carbon.solution.model.server.iam.IdentityServerArtifact;
import org.wso2.carbon.user.mgt.stub.types.carbon.FlaggedName;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

public class UserRoleDeployer extends IdentityServerDeployer {
    private static Log log = LogFactory.getLog(UserRoleDeployer.class);

    @Override
    protected void doDeploy(IdentityServerArtifact identityServerArtifact, Server server)
            throws CarbonSolutionException {
        if (identityServerArtifact.getArtifactFile().equals("role.properties")) {
            Properties properties = new Properties();
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = new FileInputStream(identityServerArtifact.getAbsoluteResourcePath());
                properties.load(fileInputStream);
                List<RoleAttribute> roleAttributeList = RoleAttribute.loadroles(properties);
                for (RoleAttribute roleAttribute : roleAttributeList) {
                    try {
                        IdentityServerAdminClient.getUserAdminService(server).
                                addRole(roleAttribute.getRoleName(), null,
                                        roleAttribute.getPermissions().split(","), false);
                    } catch (Exception e) {
                        log.error("Error occurred while adding user," + e.getMessage());
                    }
                }
            } catch (Exception e) {
                throw new CarbonSolutionException("Error occurred while loading role properties," + e.getMessage());
            } finally {
                try {
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                } catch (Exception e) {
                    log.error("Error occurred while closing the input stream.");
                }
            }
        } else if (identityServerArtifact.getArtifactFile().equals("user.properties")) {
            Properties properties = new Properties();
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = new FileInputStream(identityServerArtifact.getAbsoluteResourcePath());
                properties.load(fileInputStream);
                List<UserAttribute> userAttributeList = UserAttribute.loadUsers(properties);
                for (UserAttribute userAttribute : userAttributeList) {
                    try {
                        IdentityServerAdminClient.getUserAdminService(server).
                                addUser(userAttribute.getUserName(), userAttribute.getPassword(),
                                        userAttribute.getRoles().split(","), null, null);
                    } catch (Exception e) {
                        log.error("Error occurred while adding user," + e.getMessage());
                    }
                }
            } catch (Exception e) {
                throw new CarbonSolutionException("Error occurred while loading userrole properties," + e.getMessage());
            } finally {
                try {
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                } catch (Exception e) {
                    log.error("Error occurred while closing the input stream.");
                }
            }
        }
    }

    @Override
    protected void cleanAll(Server server) throws CarbonSolutionException {

        try {

            String[] users = IdentityServerAdminClient.getUserAdminService(server).listUsers(null, -1);
            for (String user : users) {
                IdentityServerAdminClient.getUserAdminService(server).deleteUser(user);
            }

            FlaggedName[] allRolesNames = IdentityServerAdminClient.getUserAdminService(server)
                    .getAllRolesNames(null, -1);
            for (FlaggedName role : allRolesNames) {

            }
        } catch (Exception e) {
            log.error("Error occurred while listUsers, " + e.getMessage());
        }
    }

    @Override
    protected String getArtifactType() {
        return "user-role";
    }
}
