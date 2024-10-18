#!/bin/bash

# Downloading and overwriting the compose file
wget -O compose.yaml https://raw.githubusercontent.com/juronja/DiluteRight/refs/heads/main/compose.yaml

# Stoping and starting the container
docker-compose down
#docker image prune --force
docker-compose up -d