#!/bin/bash

#TODO: integrate in build process
cp makeup/src/css/* public/css

npm run build && npm run serve
