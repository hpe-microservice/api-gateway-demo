#!/bin/sh
mkdir log
nohup java -jar ./eureka-server/target/eureka-server.jar > ./log/eureka-server.log 2 >&1 &
nohup java -jar ./hello/target/hello.jar > ./log/hello.log 2 >&1 &
nohup java -jar ./auth/target/auth.jar > ./log/auth.log 2 >&1 &
