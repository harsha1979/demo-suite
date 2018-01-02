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

package org.wso2.carbon.solution.deployer.iam.impl.sp.workflow;


import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class WorkflowAssociation {

    private static String NAME = "name";
    private static String EVENT = "event";
    private static String CONDITION = "condition";

    private String workflowId;
    private String associationName;
    private String event;
    private String condition;


    public static List<WorkflowAssociation> loadWorkflowAssociations(String workflowId, Properties properties) {
        List<WorkflowAssociation> workflowAssociations = new ArrayList<>();
        int count = 1;
        boolean finish = true;
        while (finish) {
            String name = (String) properties.get(NAME + "." + count);
            if (name == null) {
                finish = false;
            } else {
                String event = (String) properties.get(EVENT + "." + count);
                String condition = (String) properties.get(CONDITION + "." + count);
                WorkflowAssociation workflowAssociation = new WorkflowAssociation();
                workflowAssociation.setAssociationName(name);
                workflowAssociation.setCondition(condition);
                workflowAssociation.setEvent(event);
                workflowAssociation.setWorkflowId(workflowId);
                workflowAssociations.add(workflowAssociation);
                count++;
            }
        }
        return workflowAssociations;
    }

    public String getAssociationName() {
        return associationName;
    }

    public void setAssociationName(String associationName) {
        this.associationName = associationName;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }
}
