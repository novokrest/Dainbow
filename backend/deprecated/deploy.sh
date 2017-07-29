#!/bin/bash

WAR_FILE_TO_DEPLOY=target/dainbow-server-1.0-SNAPSHOT.war
WEBAPP_NAME=dainbow

if [ $# -gt 0 ]; then
    WAR_FILE_TO_DEPLOY=$1
fi

if [ $# -gt 1 ]; then
    WEBAPP_NAME=$2
fi

TOMCAT=/c/tools/apache-tomcat-8.5.15
TOMCAT_BIN=$TOMCAT/bin
TOMCAT_WEBAPPS=$TOMCAT/webapps
TOMCAT_WEBAPP_DIR=$TOMCAT_WEBAPPS/$WEBAPP_NAME
TOMCAT_WEBAPP_WAR=$TOMCAT_WEBAPPS/$WEBAPP_NAME.war

echo "Cleaning..."

if [ -f "$TOMCAT_WEBAPP_WAR" ]; then
    echo "Deleting $TOMCAT_WEBAPP_WAR..."
    rm -f $TOMCAT_WEBAPP_WAR
    echo "deleted."
fi


if [ -d "$TOMCAT_WEBAPP_DIR" ]; then
    echo "Deleting $TOMCAT_WEBAPP_DIR..."
    rm -rf $TOMCAT_WEBAPP_DIR
    echo "deleted."
fi


echo "cleaned."

echo "Copying $WAR_FILE_TO_DEPLOY -> $TOMCAT_WEBAPP_WAR..."
cp $WAR_FILE_TO_DEPLOY $TOMCAT_WEBAPP_WAR

#echo "Stopping tomcat"
#$TOMCAT_BIN/shutdown.sh

echo "Starting Tomcat"
$TOMCAT_BIN/startup.sh

until [ "`curl -I -s -L http://localhost:8080/dainbow/books | grep 'HTTP/1.1 200'`" != "" ]; do
    echo --- sleeping for 1 seconds
    sleep 1
done

echo "Done."