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

package org.wso2.carbon.solution.deployer.iam.impl.sp.role;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RoleAttribute {

    private static String ROLE_NAME = "role.name";
    private static String PERMISSIONS = "role.permissions";

    private String roleName;
    private String permissions;

    public static List<RoleAttribute> loadroles(Properties properties) {

        List<RoleAttribute> roleAttributes = new ArrayList<>();
        int count = 1;
        boolean finish = true;
        while (finish) {
            String name = (String) properties.get(ROLE_NAME + "." + count);
            if (name == null) {
                finish = false;
            } else {
                String permissions = (String) properties.get(PERMISSIONS + "." + count);
                RoleAttribute roleAttribute = new RoleAttribute();
                roleAttribute.setRoleName(name);
                roleAttribute.setPermissions(permissions);
                roleAttributes.add(roleAttribute);
                count++;
            }
        }
        return roleAttributes;
    }

    public String getPermissions() {

        return permissions;
    }

    public void setPermissions(String permissions) {

        this.permissions = permissions;
    }

    public String getRoleName() {

        return roleName;
    }

    public void setRoleName(String roleName) {

        this.roleName = roleName;
    }
}
