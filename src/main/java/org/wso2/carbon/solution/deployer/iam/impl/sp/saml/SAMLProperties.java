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

package org.wso2.carbon.solution.deployer.iam.impl.sp.saml;


import java.util.Properties;

/**
 * SAMLProperties is wrapper class to load the property.
 */
public class SAMLProperties {

    public String SP_ENTITY_ID = "SPEntityId";
    public String ASSERTION_CONSUMER_URL = "AssertionConsumerURL";
    public String IDP_ENTITY_ID = "IdPEntityId";
    public String IDP_URL = "IdPURL";
    public Properties samlProperties = null;
    private String PREFIX = "SAML";
    private String fileName;

    public SAMLProperties(String fileName) {
        this.fileName = fileName;
        this.samlProperties = new Properties();
    }

    private SAMLProperties(Properties samlProperties) {
        this.samlProperties = samlProperties;
    }

    public static SAMLProperties getSAMLProperties(Properties properties, String fileName) {
        SAMLProperties oAuthProperties = new SAMLProperties(properties);
        oAuthProperties.setFileName(fileName);
        return oAuthProperties;
    }

    public String getAssertionConsumerUrl() {
        return getSAMLPropertyValue(ASSERTION_CONSUMER_URL);
    }

    public void setAssertionConsumerUrl(String assertionConsumerUrl) {
        this.setSAMLProperty(ASSERTION_CONSUMER_URL, assertionConsumerUrl);
    }

    public String getIdpEntityId() {
        return getSAMLPropertyValue(IDP_ENTITY_ID);
    }

    public void setIdpEntityId(String idpEntityId) {
        this.setSAMLProperty(IDP_ENTITY_ID, idpEntityId);
    }

    public String getIdpUrl() {
        return getSAMLPropertyValue(IDP_URL);
    }

    public void setIdpUrl(String idpUrl) {
        this.setSAMLProperty(IDP_URL, idpUrl);
    }

    public Properties getSamlProperties() {
        return samlProperties;
    }

    public String getSpEntityId() {
        return getSAMLPropertyValue(SP_ENTITY_ID);
    }

    public void setSpEntityId(String spEntityId) {
        this.setSAMLProperty(SP_ENTITY_ID, spEntityId);
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    private String getSAMLPropertyValue(String key) {
        String propertyName = PREFIX + "_" + fileName + "_" + key;
        return (String) this.samlProperties.get(propertyName);
    }

    private void setSAMLProperty(String key, String value) {
        String propertyName = PREFIX + "_" + fileName + "_" + key;
        this.samlProperties.setProperty(propertyName, value);
    }
}
