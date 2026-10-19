
pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Verifica ambiente Docker') {
            steps {
                sh '/Users/daniele/.docker/bin/docker --version'
                sh '/Users/daniele/.docker/bin/docker compose version'
                sh '/Users/daniele/.docker/bin/docker ps'
            }
        }
    }
}
