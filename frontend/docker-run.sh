#!/bin/bash

IMAGE=dnovokrest/dainbow-react-frontend

docker pull $IMAGE
docker run -it --rm --name react-frontend -p 80:8080 -d $IMAGE
