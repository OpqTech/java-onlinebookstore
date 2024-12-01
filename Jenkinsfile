pipeline{

	parameters {
		booleanParam defaultValue: true, name: 'sonar'
		choice choices: ['env'], description: '''dev
		qa
		uat
		pre-prod
		prod''', name: ''
		string defaultValue: 'ls-l', name: 'command'
		text defaultValue: '''dadad
		adada
		adad''', name: 'text'
		}
		
	agent {label 'dev'}
	stages {
		stage('GIT checkout') {
			steps {
				checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/OpqTech/java-onlinebookstore.git']])
			}
		}
		stage('Build') {
			steps {
				echo 'This is Build stage'
			}
		}
		stage('Push') {
			steps {
				echo 'This is push stage'
			}
		}
		stage('Sonar Scan ') {
			steps {
				echo 'This is sonar scan stage'
			}
		}		
		stage('Deploy') {
			steps {
				parallel (
					step1: {
						echo 'This is step1'
					},
					step2: {
						echo 'This is step2'
					},
					step3: {
						echo 'This is step3'
					}										
				)
			}			
		}
	}
}
