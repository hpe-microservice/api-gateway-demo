## How to run
1. 下载代码：
``` shell
$ git clone git@github.com:hpe-microservice/api-gateway-demo.git
```
2. 修改数据库 **url、username、password**（api-gateway-demo/auth/src/main/resources/application.yml）：
``` shell
$ cd api-gateway-demo
$ cat auth/src/main/resources/application.yml
spring:
  datasource:
    type: org.apache.tomcat.jdbc.pool.DataSource
    driverClassName: com.mysql.jdbc.Driver
    url: jdbc:mysql://192.168.10.128:3306/zjmcc_db?useUnicode=true&characterEncoding=UTF-8&useSSL=false
    username: zjmcc
    password: password
  jpa:
    show-sql: true
    hibernate:
      ddl-auto: update
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQL5Dialect
```
3. 构建工程
``` shell
$  mvn clean package -DskipTests=true
```
4. 运行：
``` shell
$ cd api-gateway-demo
$ ./run.sh
```

Note:

- 所有的 log 在目录 `api-gateway-demo/log`；
- auth 工程启动后，会自动在连接的 MySQL 数据库中创建 **auth** 表（如 auth 表不存在）；

## How to stop
Window 下通过 **命令管理器** 结束 JVM 进程。
Linux 下通过 `kill -9 <pid>` 结束 JVM 进程。
