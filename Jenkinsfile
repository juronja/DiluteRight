pipeline {
    agent any
    environment {
        IMAGE_TAG = "juronja/dilute-right:latest"
        CONTAINER_NAME = "dilute-right"
    }
        
    stages {
        stage('Build Dockerhub image') {
            environment {
                DOCKERHUB_CREDS = credentials('dockerhub-creds')
            }
            steps {
                echo "Building Dockerhub image ..."
                sh "docker build -t $IMAGE_TAG ."
                // Next line in single quotes for security
                //sh 'echo $DOCKERHUB_CREDS_PSW | docker login -u $DOCKERHUB_CREDS_USR --password-stdin'
                sh "docker push $IMAGE_TAG"
            }
        }
        stage('Deploy Docker container') {
            steps {
                script {
                    // Check if container exists
                    def containerId = sh(script: "docker ps --quiet --filter name=$CONTAINER_NAME", returnStdout: true).trim()

                    if (containerId.isEmpty()) {
                        echo "Container $CONTAINER_NAME not found. Skipping stop/remove steps."
                    } else {
                        echo "Stopping and removing existing container $CONTAINER_NAME ..."
                        sh "docker stop $CONTAINER_NAME"
                        sh "docker rm $CONTAINER_NAME"
                        sh "docker rmi $IMAGE_TAG" // Remove leftover image if needed
                    }

                    // Always run the container regardless of previous existence
                    echo "Starting container $CONTAINER_NAME ..."
                    sh "docker run -d -p 7474:80 --restart unless-stopped --name $CONTAINER_NAME $IMAGE_TAG"
                }
            }
        }
    }
}