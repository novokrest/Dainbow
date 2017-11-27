#!/bin/bash

if [ $# -lt 1 ]; then
  echo Few arguments: specify environment e.g. dev
  exit 1
fi

IMAGE=dnovokrest/dainbow-react-frontend:latest

NODE_ENV=$1 webpack
docker build -t $IMAGE .

if [ $# -gt 1 ] && [ $2 == "push" ]; then
  docker push $IMAGE
fi


