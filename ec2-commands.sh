#!/bin/bash

# Check if container exists
wget https://cloud-images.ubuntu.com/$UBUNTU_RLS/current/$UBUNTU_RLS-server-cloudimg-amd64.img
echo "something2"

# Always run the container regardless of previous existence
docker-compose down
docker image prune --force