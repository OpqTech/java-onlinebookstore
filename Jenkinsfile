pipeline {
    agent { label 'dev' } // Specify the label of your Jenkins Slave. Modify 'dev' if you use a different label.

    environment {
        MAVEN_HOME = tool name: 'Maven' // Configure Maven installation. Ensure 'Maven' matches the name in Jenkins tool configuration.
        SONARQUBE_SERVER = 'Sonar' // Define the SonarQube server ID. Replace 'Sonar' with your SonarQube server ID if different.
        SONAR_SCANNER_HOME = tool name: 'SonarScanner' // Configure SonarQube Scanner installation. Ensure 'SonarScanner' matches the name in Jenkins tool configuration.
        ARTIFACTORY_SERVER = 'Artifactory' // Define Artifactory server ID. Replace 'Artifactory' with your Artifactory server ID if different.
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout the source code from your repository
                // Modify the URL and branch if your repository or branch name changes
                git url: 'https://github.com/shashank1553/java-onlinebookstore.git', branch: 'main'
            }
        }

        stage('Build') {
            steps {
                // Build the Maven project
                // Adjust Maven command if your build process requires additional options
                sh "${MAVEN_HOME}/bin/mvn clean install"
            }
        }

        stage('SonarQube Analysis') {
            steps {
                // Run SonarQube analysis
                // Ensure the server ID matches your SonarQube configuration
                withSonarQubeEnv(SONARQUBE_SERVER) {
                    sh "${MAVEN_HOME}/bin/mvn sonar:sonar"
                }
            }
        }

        stage('Deploy to Artifactory') {
            steps {
                script {
                    // Upload artifacts to JFrog Artifactory
                    // Modify the 'serverId' and 'spec' according to your Artifactory configuration and repository structure
                    rtUpload (
                        serverId: ARTIFACTORY_SERVER,
                        spec: '''{
                            "files": [{
                                "pattern": "target/*.war", // Path to the artifact to upload. Modify if different.
                                "target": "maven-releases/myapp/" // Target repository path in Artifactory (Maven release repository). Modify as needed.
                            }]
                        }'''
                    )
                }
            }
        }

        stage('Deploy to Tomcat') {
            steps {
                script {
                    // Deploy the WAR file to Apache Tomcat server
                    // Adjust the Tomcat URL and credentials if your server details or credentials change
                    sh """
                        curl --upload-file target/*.war \\
                        "http://admin:123456@54.226.160.53:8080/manager/text/deploy?path=/myapp&update=true"
                    """
                }
            }
        }
    }

    post {
        always {
            cleanWs() // Clean the workspace after the build
        }
        success {
            echo "Build, Analysis, and Deployment succeeded!"
        }
        failure {
            echo "Build failed. Check the logs!"
        }
    }
}
