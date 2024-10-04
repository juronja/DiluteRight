#!/bin/bash

# Check if container exists
wget https://raw.githubusercontent.com/juronja/DiluteRight/refs/heads/main/compose.yaml
echo "something2"

# Always run the container regardless of previous existence
docker-compose down
docker image prune --force