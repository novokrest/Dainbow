#!/bin/bash

USER_INFO_URI=https://www.googleapis.com/oauth2/v1/userinfo?alt=json
USER_INFO_URI_2=https://www.googleapis.com/plus/v1/people/me
TOKEN=ya29.GlteBVScY0VzD6Tld39JoKCrXl4VxOCzBWJg1fYyi9qTH7FRTuPhYL4ciGn8oe7BEcXG7vvdm1VMCctLdhVct1ZExZN14W9NEaHrBWr7U7u_i-pupocutkzSJT96

curl -kv -H "Authorization: Bearer $TOKEN" $USER_INFO_URI
curl -kv -H "Authorization: Bearer $TOKEN" $USER_INFO_URI_2
