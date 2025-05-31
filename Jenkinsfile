pipeline {
    agent any
    parameters {
      choice choices: ['dev', 'qa', 'uat', 'prod'], description: 'choose the env', name: 'environment'
      string defaultValue: 'mvn clean install', description: 'update the command based on req', name: 'command'
      booleanParam defaultValue: true, description: 'enable to run sonar scan', name: 'sonar'
    }
    stages {
        stage ('checkout') {
            steps {
                echo "This is checkout stage"
            }
        }
        stage ('build') {
            steps {
                echo "This is build stage "
            }
        }
        stage ('sonarscan') {
            steps {
                echo "This is sonarscan stage "
            }
        }               
        stage ('push') {
            steps {
                echo "This is push stage"
            }
        }
        stage ('deploy') {
            steps {
                echo "This is deploy stage "
            }
        }                    
    }
}
