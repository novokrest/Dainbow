#!/bin/bash

CODE=4/AAAFnvXP9yIdTUQMXFPhtpq8G843JYhO46ENBNZDuGByXT9k6qbKym1EFb60BZ8OkdCA9lCPro0j6Vl-njZoNYU
CLIENT_ID=1069112770187-86kp601ddg7bt7m4kvf1s6pokmienrob.apps.googleusercontent.com
CLIENT_SECRET=tH-1KPwe3Q7LgXwdd_qVJTA_
REDIRECT_URI=http://localhost:30005/dainbow/accounter/oauth2

curl -kv -X POST -H 'Content-Type: application/x-www-form-urlencoded' -d "code=$CODE&client_id=$CLIENT_ID&client_secret=$CLIENT_SECRET&redirect_uri=$REDIRECT_URI&grant_type=authorization_code" https://www.googleapis.com/oauth2/v4/token

