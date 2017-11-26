#!/bin/bash

IMAGE=dainbow-react-backend
USER=dnovokrest
TAG=latest
REPO_IMAGE=$USER/$IMAGE:$TAG

docker build --build-arg JAR_FILE=build/libs/dainbow-server-backend-1.0.0.jar -t $IMAGE .
docker tag $IMAGE $REPO_IMAGE 
docker push $REPO_IMAGE
