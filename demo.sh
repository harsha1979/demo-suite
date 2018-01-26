#!/bin/sh

if [ -z "$JAVA_HOME" ]; then
  echo "You must set the JAVA_HOME variable before running the client."
  exit 1
fi
tmppath=$CLASSPATH
export CLASSPATH=$CLASSPATH:org.wso2.carbon.demosuite-1.0.0-jar-with-dependencies.jar
$JAVA_HOME/bin/java org.wso2.carbon.solution.SolutionInstallationApplication $@



cd demo-resources/servers/tomcat/apache-tomcat-9.0.1/bin
sh ./catalina.sh run
