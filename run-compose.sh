#!/usr/bin/env bash

# ./gradlew clean build
#
# cp build/libs/java-stateful-k8s-0.0.1-SNAPSHOT.jar devops/jar/

# build the base jre container
docker build -f jre.Dockerfile -t docker-jre .

# stop previously running containers
docker-compose -f devops/dev.docker-compose.yml stop

# delete the containers - they will be rebuilt
# docker compose is odd in that it can reus the same option switch
    # the first -f is for the compose file. the second -f is to force so it does not ask for confirmation
docker-compose -f devops/dev.docker-compose.yml rm -f

# delete the app image so it gets recreated
# docker rmi $(docker images | grep "^docker_app" | awk "{print $3}")

# selectively re-build the container when required
docker-compose -f devops/dev.docker-compose.yml build app # --no-cache

# start all the containers in the compose file
docker-compose -f devops/dev.docker-compose.yml up -d

# delete the app folder once done
# rm -rf devops/app

## tail the logs
# same as above where docker compose uses the same switch for multiple things
    # the first -f is for the compose file and the seconf -f is to follow (tail) the logs
docker-compose -f devops/dev.docker-compose.yml logs -f
