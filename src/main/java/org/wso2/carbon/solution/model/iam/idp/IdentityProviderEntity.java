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
package org.wso2.carbon.solution.model.iam.idp;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class IdentityProviderEntity implements Serializable {

    private final static long serialVersionUID = -3149586909801964681L;
    private IdentityProvider identityProvider;

    /**
     * No args constructor for use in serialization
     */
    public IdentityProviderEntity() {

    }

    /**
     * @param identityProvider
     */
    public IdentityProviderEntity(IdentityProvider identityProvider) {

        super();
        this.identityProvider = identityProvider;
    }

    @Override
    public boolean equals(Object other) {

        if (other == this) {
            return true;
        }
        if ((other instanceof IdentityProviderEntity) == false) {
            return false;
        }
        IdentityProviderEntity rhs = ((IdentityProviderEntity) other);
        return new EqualsBuilder().append(identityProvider, rhs.identityProvider).isEquals();
    }

    public IdentityProvider getIdentityProvider() {

        return identityProvider;
    }

    public void setIdentityProvider(IdentityProvider identityProvider) {

        this.identityProvider = identityProvider;
    }

    @Override
    public int hashCode() {

        return new HashCodeBuilder().append(identityProvider).toHashCode();
    }

    @Override
    public String toString() {

        return new ToStringBuilder(this).append("identityProvider", identityProvider).toString();
    }
}
