pipeline {
    agent any
        
    stages {
        stage('Build Dockerhub image') {
            environment {
                DOCKERHUB_CREDS = credentials('dockerhub-creds')
            }
            steps {
                echo "Building Dockerhub image ..."
                sh "docker build -t juronja/dilute-right:latest ."
                // Next line in single quotes for security
                //sh 'echo $DOCKERHUB_CREDS_PSW | docker login -u $DOCKERHUB_CREDS_USR --password-stdin'
                sh "docker push juronja/dilute-right:latest"
            }
        }
        stage('Deploy Docker container') {
            steps {
                echo "Deploying container ..."
                echo "$USER -- $HOME"
                sh "docker compose version"
                // Next line in single quotes for security
                //sh 'echo $NEXUS_CREDS_PSW | docker login -u $NEXUS_CREDS_USR --password-stdin 64.226.97.173:8082'
                //sh "docker push 64.226.97.173:8082/java-maven-app:1.1"
            }
        }
    }
}