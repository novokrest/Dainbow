#!/bin/bash

curl -X POST -H "Content-Type: application/json" -d '{ "bookId" : 10, "beginPage" : 33, "endPage" : 55, "beginTime" : "2015-11-15T10:00:00.000+0000", "endTime" : "2015-11-15T12:00:00.000+0000" }' http://localhost:8091/api/v1/read/history