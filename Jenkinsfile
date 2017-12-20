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
                sh 'rm -rf allure-results/'
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
                    results          : [[path: 'allure-results']]
            ])
        }
    }
    finnally {
        stage("Log reports"){
            junit 'build/test-results/**/*.xml'
            artifacts =
                 'build/reports/**/*,build/test-results/**/*,logs/**/*'
            archiveArtifacts artifacts: artifacts
        }
    }
}