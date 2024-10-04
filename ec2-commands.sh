#!/bin/bash

# Check if container exists
echo "something"


# Always run the container regardless of previous existence
echo "Starting container $CONTAINER_NAME ..."
sh "$DOCKER_RUN"
sh "docker image prune --force"