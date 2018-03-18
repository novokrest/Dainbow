#!/bin/bash

if [ $# -lt 2 ]; then
  echo "Few arguments! Use: $0 <client_id> <client_base64>"
  exit 1
fi

curl -kv -XPOST http://localhost:30005/oauth/token -H "Authorization: Basic $2" -d "grant_type=password&username=user1&password=one&client_id=$1"
