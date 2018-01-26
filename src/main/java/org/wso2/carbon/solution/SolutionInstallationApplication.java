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
package org.wso2.carbon.solution;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.solution.util.ApplicationUtility;
import org.wso2.carbon.solution.util.Constant;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * This is the class that we initiate the installation task.
 */
public class SolutionInstallationApplication {

    private static Log log = LogFactory.getLog(SolutionInstallationApplication.class);

    public static void main(String[] args) {

        log.info("WSO2 Identity Server - Solution Installation Task.");

        try {
            List<String> list = Arrays.asList(args);
            if (!validateInputArgs(args)) {
                list = readSolution();
            }
            SolutionInstaller.install(list);
            log.info("Installation was done.");
        } catch (Exception e) {
            String errorMessage = "Error occurred while executing the installation process.";
            log.error(errorMessage, e);
        } finally {
            log.info("Cleaning the tmp files.....");
            ApplicationUtility.cleanGeneratedFiles();
        }

    }

    /**
     * Read solution.
     *
     * @return
     */
    private static List<String> readSolution() {

        List<String> list = new ArrayList<>();
        System.out.print("Enter solution name/s :");
        Scanner scanner = new Scanner(System.in);
        String solution = scanner.nextLine();
        if (!StringUtils.isEmpty(solution)) {
            Set<String> availableSolutions = getAvailableSolutions();
            solution = solution.trim();
            String[] splitSolution = solution.split(" ");
            for (String solutionName : splitSolution) {
                if (!availableSolutions.contains(solutionName.trim())) {
                    System.out.println("\"" + solutionName + "\" is not available under solutions folder. Please " +
                            "provide the valid solution names.");
                    return readSolution();
                }
            }
            list.addAll(Arrays.asList(splitSolution));
        } else {
            return readSolution();
        }
        return list;
    }

    /**
     * Reading solution list.
     *
     * @return
     */
    private static Set<String> getAvailableSolutions() {

        Set<String> solutionSet = new HashSet<>();
        File solutionsHome = new File(Constant.ResourcePath.SOLUTION_HOME_PATH);
        if (solutionsHome.exists()) {
            File[] solutionFolderList = solutionsHome.listFiles();
            for (File file : solutionFolderList) {
                solutionSet.add(file.getName());
            }
        }
        return solutionSet;
    }

    private static boolean validateInputArgs(String[] splitSolution) {
        boolean validated =  true;
        Set<String> availableSolutions = getAvailableSolutions();
        if (splitSolution.length > 0) {
            for (String solutionName : splitSolution) {
                if (!availableSolutions.contains(solutionName.trim())) {
                    System.out.println("\"" + solutionName + "\" is not available under solutions folder. Please " +
                            "provide the valid solution names.");
                    validated = false;
                    break ;
                }
            }
        }else{
            validated = false;
        }
        return validated ;
    }
}
