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
                    sh "mvn --batch-mode -V -U -e clean test -Dselenide.browser=chrome -Dremote=http://hub:4444/wd/hub"
                }
            }
        }

        stage('Generate Report') {
            steps {
                withEnv(["PATH+ALLURE=${tool 'Allure 2.4'}/bin"]) {
                    sh "allure generate target/surefire-reports -o allure-report"
                }
            }
        }

        stage('Publish Allure Report') {
            steps {
                publishHTML([
                        allowMissing         : false,
                        alwaysLinkToLastBuild: true,
                        keepAll              : true,
                        reportDir            : 'allure-report',
                        reportFiles          : 'index.html',
                        reportName           : 'Allure Report',
                        reportTitles         : ''
                ])
            }
        }

        stage('Publish Cucumber Report') {
            steps {
                publishHTML([
                        allowMissing         : false,
                        alwaysLinkToLastBuild: true,
                        keepAll              : true,
                        reportDir            : 'target/cucumber',
                        reportFiles          : 'index.html',
                        reportName           : 'Cucumber Report',
                        reportTitles         : ''
                ])
            }
        }
    }
}