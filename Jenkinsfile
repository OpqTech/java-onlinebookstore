pipeline {
    agent any
    stages {
        stage ('checkout') {
            steps {
                echo "This is checkout stage"
            }
        }
        stage ('build') {
            steps {
                echo "This is build stage"
            }
        }
        stage ('sonarscan') {
            steps {
                echo "This is sonarscan stage"
            }
        }               
        stage ('push') {
            steps {
                echo "This is push stage"
            }
        }
        stage ('deploy') {
            steps {
                echo "This is deploy stage"
            }
        }                    
    }
}
