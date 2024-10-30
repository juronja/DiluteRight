#!/bin/bash

# Stoping the container
docker compose down
docker image prune --force

# Downloading and overwriting the compose file
wget -O compose.yaml https://raw.githubusercontent.com/juronja/DiluteRight/refs/heads/main/compose.yaml

# Starting the container
docker compose up -d