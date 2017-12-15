pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Cleanup') {
            steps  {
                sh 'rm -rf target/'
                sh 'rm -rf allure-report/'
            }
        }

        stage('Build') {
            steps {
                withEnv(["PATH+MAVEN=${tool 'Maven 3.5'}/bin"]) {
                    sh "mvn --batch-mode -V -U -e clean test"
                }
            }
        }

        stage('Generate Report') {
            steps {
                withEnv(["PATH+ALLURE=${tool 'Allure 2.4'}/bin"]) {
                    sh "allure generate target/allure-results -o allure-report"
                }
            }
        }

        stage('Publish Report') {
            steps {
                publishHTML([
                        allowMissing         : false,
                        alwaysLinkToLastBuild: true,
                        keepAll              : true,
                        reportDir            : 'allure-report',
                        reportFiles          : 'index.html',
                        reportName           : 'Allure Report'
                ])
            }
        }
    }
}