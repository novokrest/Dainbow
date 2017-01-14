#!/bin/bash

rm -rf target
mvn package && ./deploy.sh