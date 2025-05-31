pipeline {
    agent any
    parameters {
        choice choices: ['dev', 'qa', 'uat', 'prod'], description: 'Choose the env', name: 'environment'
        booleanParam defaultValue: true, description: 'Enable to run sonar scan', name: 'sonar'
    }
    environment {
        SONARQUBE_ENV = 'sonarscanner' 
        PROJECT_KEY = 'onelinebookstore'
        JFROG_URL = 'http://<jfrog>:8081/artifactory/onlinebookstore'
    }
    stages {
        stage('checkout') {
            steps {
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: '', url: 'https://github.com/OpqTech/java-onlinebookstore.git']])
            }
        }

        stage('sonarscan') {
            when {
                expression { params.sonar }
            }
            steps {
                withSonarQubeEnv("${SONARQUBE_ENV}") {
                    sh "mvn clean verify sonar:sonar -Dsonar.projectKey=${PROJECT_KEY}"
                    //sh 'mvn sonar:sonar -Dsonar.projectKey=test -Dsonar.host.url=http://<sonar-ip>:9000/ -Dsonar.branch.name=main -Dsonar/login=squ_fe540d1f3e9f1799cbcde6b1acef600670e9eff1'
                }
            }
        }

        stage('quality gate') {
            when {
                expression { params.sonar }
            }
            steps {
                timeout(time: 2, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: false
                }
            }
        }

        stage('build') {
            steps {
                sh 'mvn clean install'
                archiveArtifacts artifacts: 'target/*.war', fingerprint: true
            }
            post {
                always {
                    jacoco execPattern: 'target/*.exec'
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('push') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'jfrog', passwordVariable: 'password', usernameVariable: 'username')]) {
                    sh "curl -X PUT -u ${username}:${password} -T target/*.war '${JFROG_URL}/onlinebookstore-0.0.1-SNAPSHOT.war'"
                }
                }
        }

        stage('deploy') {
            steps {
                sh 'cp -r /target/*.war /opt/tomcat/apache-tomcat-9.0.68/webapps/'
            }
        }
    }
}
