#!/bin/bash

# 1. Compile and build the project
echo "Building fresh .war archive..."
if mvn clean package; then
    
    # 2. Clear out any old versions in Tomcat to avoid deployment cache issues
    echo "Clearing old deployment files from Tomcat..."
    rm -rf ~/servers/apache-tomcat-10.1.61/webapps/jakartaEE*
    
    # 3. Copy the newly built war file directly to Tomcat
    echo "Deploying to Tomcat webapps..."
    cp target/*.war ~/servers/apache-tomcat-10.1.61/webapps/jakartaEE.war
    
    echo "----------------------------------------"
    echo "SUCCESS! Open your browser and go to:"
    echo "http://localhost:8080/jakartaEE/home"
    echo "----------------------------------------"
else
    echo "ERROR: Maven build failed. Check your Java code syntax typos!"
    exit 1
fi
