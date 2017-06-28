#!/bin/sh
mkdir log

echo -e "Start eureka-server\n"
nohup java -jar ./eureka-server/target/eureka-server.jar > ./log/eureka-server.log 2 >&1 &

echo -e "Start hpe-gateway\n"
nohup java -jar ./hpe-gateway/target/hpe-gateway.jar > ./log/hpe-gateway.log 2 >&1 &

echo -e "Start auth\n"
nohup java -jar ./auth/target/auth.jar > ./log/auth.log 2 >&1 &

echo -e "Start hello service\n"
nohup java -jar ./hello/target/hello.jar > ./log/hello.log 2 >&1 &
