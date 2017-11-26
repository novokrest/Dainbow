#!/bin/bash

NODE_ENV=aws webpack
docker build -t dnovokrest/dainbow-react-frontend .
docker push dnovokrest/dainbow-react-frontend
