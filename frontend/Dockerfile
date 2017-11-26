FROM node:6.9.4-alpine
MAINTAINER novokrest013@gmail.com
EXPOSE 8080
RUN mkdir -p /app/config
RUN mkdir -p /app/public
RUN mkdir -p /app/server
WORKDIR /app
ADD ./package.json /app
RUN npm install
ADD ./config /app/config
ADD ./public /app/public
ADD ./server /app/server
CMD NODE_ENV=aws node server/src/js/server.js
