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
import org.wso2.carbon.identity.workflow.mgt.stub.metadata.Association;
import org.wso2.carbon.identity.workflow.mgt.stub.metadata.WorkflowWizard;
import org.wso2.carbon.solution.CarbonSolutionException;
import org.wso2.carbon.solution.deployer.iam.IdentityServerDeployer;
import org.wso2.carbon.solution.deployer.iam.impl.sp.workflow.WorkflowAssociation;
import org.wso2.carbon.solution.endpoint.iam.IdentityServerAdminClient;
import org.wso2.carbon.solution.model.server.Server;
import org.wso2.carbon.solution.model.server.iam.IdentityServerArtifact;
import org.wso2.carbon.solution.util.ResourceManager;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

/**
 * Workflow deployer.
 */
public class WorkflowDeployer extends IdentityServerDeployer {
    private static final String ARTIFACT_TYPE_WORKFLOW_DEF = "workflow";
    private static Log log = LogFactory.getLog(WorkflowDeployer.class);

    public IdentityServerArtifact getWorkflowAssociationIfExists(IdentityServerArtifact identityServerArtifact)
            throws CarbonSolutionException {
        IdentityServerArtifact identityServerArtifactRet = null;
        String artifactFile = identityServerArtifact.getArtifactFile();
        String wfAssResourceName = artifactFile.replace(".xml", "_association.properties");
        String wfAssAbsoluteResourcePath = identityServerArtifact.getAbsoluteArtifactHomePath()
                                           + File.separator + wfAssResourceName;
        if (new File(wfAssAbsoluteResourcePath).exists()) {
            String wfAssResourcePath = identityServerArtifact.getResourcePath().
                    replace(identityServerArtifact.getArtifactFile(), wfAssResourceName);
            List<IdentityServerArtifact> load = IdentityServerArtifact.load(wfAssResourcePath);
            if (load.size() == 1) {
                identityServerArtifactRet = load.get(0);
            }
        }
        return identityServerArtifactRet;
    }

    @Override
    protected void doDeploy(IdentityServerArtifact identityServerArtifact, Server server)
            throws CarbonSolutionException {

        WorkflowWizard workflow = ResourceManager.loadXMLToObject(
                identityServerArtifact.getAbsoluteResourcePath(), WorkflowWizard.class);
        if (workflow != null) {
            try {
                WorkflowWizard workflowTmp = getWorkflow(workflow.getWorkflowName(), server);
                if (workflowTmp != null) {
                    IdentityServerAdminClient.getWorkflowAdminService(server)
                            .removeWorkflow(workflowTmp.getWorkflowId());
                }
                IdentityServerAdminClient.getWorkflowAdminService(server).addWorkflow(workflow);
                workflow = getWorkflow(workflow.getWorkflowName(), server);
            } catch (Exception e) {
                throw new CarbonSolutionException("Error occurred while installing WorkflowWizard, " +
                                                  identityServerArtifact.getResourcePath());
            }

            IdentityServerArtifact workflowAssociationIfExists = getWorkflowAssociationIfExists(identityServerArtifact);
            if (workflowAssociationIfExists != null) {
                Properties properties = new Properties();
                FileInputStream fileInputStream = null;
                try {
                    fileInputStream = new FileInputStream(workflowAssociationIfExists.getAbsoluteResourcePath());
                    properties.load(fileInputStream);
                    List<WorkflowAssociation> workflowAssociations = WorkflowAssociation
                            .loadWorkflowAssociations(workflow.getWorkflowId(), properties);
                    for (WorkflowAssociation workflowAssociation : workflowAssociations) {
                        IdentityServerAdminClient.getWorkflowAdminService(server).
                                addAssociation(workflowAssociation.getAssociationName(),
                                               workflowAssociation.getWorkflowId(), workflowAssociation.getEvent(),
                                               workflowAssociation.getCondition());
                    }
                } catch (Exception e) {
                    log.error("Error occurred while loading association properties," + e.getMessage(), e);
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
    }

    @Override
    protected void cleanAll(Server server) throws CarbonSolutionException {
        try {
            Association[] associations = IdentityServerAdminClient.getWorkflowAdminService(server)
                    .listAllAssociations();
            for (Association association : associations) {
                try {
                    IdentityServerAdminClient.getWorkflowAdminService(server)
                            .removeAssociation(association.getAssociationId());
                } catch (Exception e) {
                    log.error("Error occurred while removeAssociation, " + association.getAssociationName(), e);
                }
            }
            WorkflowWizard[] workflowWizards = IdentityServerAdminClient.getWorkflowAdminService(server)
                    .listWorkflows();
            for (WorkflowWizard workflowWizard : workflowWizards) {
                try {
                    IdentityServerAdminClient.getWorkflowAdminService(server)
                            .removeWorkflow(workflowWizard.getWorkflowId());
                } catch (Exception e) {
                    log.error("Error occurred while removeWorkflow, " + workflowWizard.getWorkflowName(), e);
                }
            }
        } catch (Exception e) {
            log.error("Error occurred while getWorkflowAdminService or getWorkflowAdminService.", e);
        }
    }

    private WorkflowWizard getWorkflow(String workflowName, Server server) throws CarbonSolutionException {
        WorkflowWizard workflowWizard = null;
        try {
            WorkflowWizard[] workflowWizards = IdentityServerAdminClient.getWorkflowAdminService(server)
                    .listWorkflows();
            if (workflowWizards != null) {
                for (WorkflowWizard workflowWizardTmp : workflowWizards) {
                    if (workflowWizardTmp.getWorkflowName().equals(workflowName)) {
                        workflowWizard = workflowWizardTmp;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            throw new CarbonSolutionException("Error occurred while getting workflow, " + e.getMessage());
        }
        return workflowWizard;
    }

    @Override
    protected String getArtifactType() {
        return ARTIFACT_TYPE_WORKFLOW_DEF;
    }
}
