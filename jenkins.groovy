def buildEC2() {
    sh "ssh -o StrictHostKeyChecking=no ec2-user@35.157.110.150 echo "something""
    
//    Check if container exists
//    def containerId = sh(script: "docker ps --quiet --filter name=$CONTAINER_NAME", returnStdout: true).trim()
//
//    if (containerId.isEmpty()) {
//        echo "Container $CONTAINER_NAME not found. Skipping stop/remove steps."
//    } else {
//        echo "Stopping and removing existing container $CONTAINER_NAME ..."
//        sh "docker stop $CONTAINER_NAME"
//        sh "docker rm $CONTAINER_NAME"
//        sh "docker rmi $DOCKERH_REPO/$IMAGE_TAG:latest" // Remove leftover image if needed
//        sh "docker rmi $DOCKERH_REPO/$IMAGE_TAG:$BUILD_VERSION" // Remove leftover image if needed
//    }
//
//    // Always run the container regardless of previous existence
//    echo "Starting container $CONTAINER_NAME ..."
//    sh "$DOCKER_RUN"
//    sh "docker image prune --force"
}

return this