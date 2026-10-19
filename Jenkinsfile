
pipeline {
    agent any

    environment {
        PATH = "/Users/daniele/.docker/bin:/usr/local/bin:/opt/homebrew/bin:/usr/bin:/bin:/usr/sbin:/sbin"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Esegui test Selenium') {
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: 'selenium-test-login',
                        usernameVariable: 'EMAIL_LOGIN',
                        passwordVariable: 'PASSWORD_LOGIN'
                    )
                ]) {
                    sh '/Users/daniele/.docker/bin/docker compose up --build --exit-code-from tests tests'
                }
            }
        }
    }

    post {
        always {
            withCredentials([
                usernamePassword(
                    credentialsId: 'selenium-test-login',
                    usernameVariable: 'EMAIL_LOGIN',
                    passwordVariable: 'PASSWORD_LOGIN'
                )
            ]) {
                sh '/Users/daniele/.docker/bin/docker compose down --volumes --remove-orphans'
            }
        }
    }
}
