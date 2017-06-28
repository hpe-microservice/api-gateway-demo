#!/bin/sh
mkdir log
echo "Start eureka-server"
nohup java -jar ./eureka-server/target/eureka-server.jar > ./log/eureka-server.log 2 >&1 &
sleep 5
echo "Start hpe-gateway"
nohup java -jar ./hpe-gateway/target/hpe-gateway.jar > ./log/hpe-gateway.log 2 >&1 &
sleep 3
echo "Start auth"
nohup java -jar ./auth/target/auth.jar > ./log/auth.log 2 >&1 &
sleep 3
echo "Start hello"
nohup java -jar ./hello/target/hello.jar > ./log/hello.log 2 >&1 &
