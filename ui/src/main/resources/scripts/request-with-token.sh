#!/bin/bash

if [ $# -lt 2 ]; then
  echo "Few arguments! Use: $0 <access_token> <path>"
  exit 1
fi

curl -kv -H "Authorization: Bearer $1" http://localhost:30080/$2
