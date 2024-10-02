def buildEC2() {
    // Check if container exists
    echo "piza" \
    && echo "donuth" \
    && echo "lala" \
    //sh "ssh -o StrictHostKeyChecking=no ec2-user@35.157.110.150 $DOCKER_RUN"

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

}

return this