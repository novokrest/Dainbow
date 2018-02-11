#!/bin/bash

SCOPE=https://www.googleapis.com/auth/userinfo.profile
REDIRECT_URI=http://localhost:30005/dainbow/accounter/oauth2
CLIENT_ID=1069112770187-86kp601ddg7bt7m4kvf1s6pokmienrob.apps.googleusercontent.com

curl -kv "https://accounts.google.com/o/oauth2/v2/auth?scope=$SCOPE&access_type=offline&include_granted_scopes=true&state=state_parameter_passthrough_value&redirect_uri=$REDIRECT_URI&response_type=code&client_id=$CLIENT_ID"
