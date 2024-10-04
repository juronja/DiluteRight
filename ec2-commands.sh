#!/bin/bash

# Downloading the compose file
wget https://raw.githubusercontent.com/juronja/DiluteRight/refs/heads/main/compose.yaml

# Stoping and starting the container
docker-compose down
docker image prune --force
docker-compose up -d