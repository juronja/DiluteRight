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
                echo "Deploying container ..."
                sh "docker stop $CONTAINER_NAME && docker rm $CONTAINER_NAME"
                sh "docker rmi $IMAGE_TAG"
                sh "docker run -d -p 7474:80 --restart unless-stopped --name $CONTAINER_NAME $DOCKER_IMAGE"
            }
        }
    }
}