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

package org.wso2.carbon.solution.deployer.iam.impl.sp.user;


import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UserAttribute {

    private static String USER_NAME = "user.name";
    private static String PASSWORD = "user.password";
    private static String ROLES = "user.role";

    private String userName;
    private String password;
    private String roles;

    public static List<UserAttribute> loadUsers(Properties properties) {
        List<UserAttribute> userAttributeList = new ArrayList<>();
        int count = 1;
        boolean finish = true;
        while (finish) {
            String name = (String) properties.get(USER_NAME + "." + count);
            if (name == null) {
                finish = false;
            } else {
                String password = (String) properties.get(PASSWORD + "." + count);
                String roles = (String) properties.get(ROLES + "." + count);
                UserAttribute userAttribute = new UserAttribute();
                userAttribute.setUserName(name);
                userAttribute.setPassword(password);
                userAttribute.setRoles(roles);
                userAttributeList.add(userAttribute);
                count++;
            }
        }
        return userAttributeList;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
