pipeline {
    agent any
    environment {
        BUILD_VERSION = VersionNumber (versionNumberString: '${BUILD_YEAR}.${BUILD_MONTH}.${BUILDS_THIS_MONTH}')
        IMAGE_TAG = "juronja/$JOB_NAME"
        CONTAINER_NAME = "$JOB_NAME"
    }
        
    stages {
        stage('Build Dockerhub image') {
            environment {
                DOCKERHUB_CREDS = credentials('dockerhub-creds')
            }
            steps {
                echo "Building Dockerhub image ..."
                sh "docker build -t $IMAGE_TAG:latest -t $IMAGE_TAG:$BUILD_VERSION ."
                // Next line in single quotes for security
                sh 'echo $DOCKERHUB_CREDS_PSW | docker login -u $DOCKERHUB_CREDS_USR --password-stdin'
                sh "docker push $IMAGE_TAG:latest"
                sh "docker push $IMAGE_TAG:$BUILD_VERSION"
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
                        sh "docker rmi $IMAGE_TAG:latest" // Remove leftover image if needed
                        sh "docker rmi $IMAGE_TAG:$BUILD_VERSION" // Remove leftover image if needed
                    }

                    // Always run the container regardless of previous existence
                    echo "Starting container $CONTAINER_NAME ..."
                    sh "docker run -d -p 7474:80 --restart unless-stopped --name $CONTAINER_NAME $IMAGE_TAG:latest"
                    sh "docker image prune --force"
                }
            }
        }
//        stage('Deploy Docker container') {
//            steps {
//                script {
//                    // Check if container exists
//                    def containerId = sh(script: "docker ps --quiet --filter name=$CONTAINER_NAME", returnStdout: true).trim()
//
//                    if (containerId.isEmpty()) {
//                        echo "Container $CONTAINER_NAME not found. Building and deploying..."
//                        sh "docker-compose up -d --build"
//                    } else {
//                        echo "Container $CONTAINER_NAME already exists. Stopping and restarting with latest image..."
//                        sh "docker-compose down" // Stop and remove existing containers/networks (if defined in docker-compose.yml)
//                        sh "docker-compose up -d" // Start/restart services with latest image
//                    }
//                }
//            }
//        }
    }
}