pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Cleanup') {
            steps {
                sh 'rm -rf target/'
                sh 'rm -rf allure-report/'
            }
        }

        stage('Build Maven') {
            steps {
                withEnv(["PATH+MAVEN=${tool 'Maven 3.5'}/bin"]) {
                    sh "mvn --batch-mode -V -U -e clean test -Dselenide.browser=chrome -Dremote=http://hub:4444/wd/hub"
                }
            }
        }
    }
    post {
        always {
            allure([
                    commandline      : '2.4',
                    includeProperties: true,
                    jdk              : '',
                    properties       : [],
                    reportBuildPolicy: 'ALWAYS',
                    results          : [[path: 'allure-results']],
            ])
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