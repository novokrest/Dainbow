#!/bin/bash

IMAGE=dnovokrest/dainbow-react-frontend

BACKGROUND_FLAG=-d

if [ $# -gt 0 ] && [ $1 == "debug" ]; then
  BACKGROUND_FLAG=
fi

docker pull $IMAGE
docker run -it --rm --name react-frontend -p 80:8080 $BACKGROUND_FLAG $IMAGE
