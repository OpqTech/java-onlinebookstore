pipeline {
    agent { label 'dev' } // Specify the label of your Jenkins Slave. Modify 'dev' if you use a different label.

    environment {
        MAVEN_HOME = tool name: 'Maven' // Ensure 'Maven' matches the name in Jenkins tool configuration.
        SONARQUBE_SERVER = 'Sonar' // Define the SonarQube server ID.
        SONAR_SCANNER_HOME = tool name: 'SonarScanner' // Ensure 'SonarScanner' matches the name in Jenkins tool configuration.
        ARTIFACTORY_SERVER = 'Artifactory' // Define Artifactory server ID.
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout the source code from your repository
                git url: 'https://github.com/shashank1553/java-onlinebookstore.git', branch: 'main'
            }
        }

        stage('Build') {
            steps {
                // Build the Maven project
                sh "${MAVEN_HOME}/bin/mvn clean install"
            }
        }

        stage('SonarQube Analysis') {
            steps {
                // Run SonarQube analysis
                withSonarQubeEnv(SONARQUBE_SERVER) {
                    sh "${MAVEN_HOME}/bin/mvn sonar:sonar"
                }
            }
        }

        stage('Deploy to Artifactory') {
            steps {
                script {
                    // Upload artifacts to JFrog Artifactory
                    rtUpload (
                        serverId: ARTIFACTORY_SERVER,
                        spec: '''{
                            "files": [{
                                "pattern": "target/onlinebookstore.war", // Path to the artifact to upload
                                "target": "maven-releases/onlinebookstore/" // Target repository path in Artifactory
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
                    sh """
                        curl -u admin:Admin12345 --upload-file target/onlinebookstore.war "http://35.171.26.114:8081/artifactory/maven-releases/onlinebookstore/"
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
