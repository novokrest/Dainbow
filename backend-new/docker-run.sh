#!/bin/bash

if [ $# -lt 1 ]; then
  echo Few arguments: specify Spring profile e.g. dev
  exit 1
fi

BACKGROUND_FLAG=-d

if [ $# -gt 1 ] && [ $2 == "debug" ]; then
  BACKGROUND_FLAG=
fi  

docker run -it --rm \
  --name react-backend \
  -e SPRING_PROFILES_ACTIVE=$1 \
  -p 8080:8080 \
  $BACKGROUND_FLAG dnovokrest/dainbow-react-backend
