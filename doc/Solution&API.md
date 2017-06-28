## 参考实现
https://github.com/hpe-microservice/api-gateway-demo

包含：

- auth: Auth（鉴权模块）参考实现；
- client: Client 参考实现；
- hello: 一个简单的业务 API demo；
- hpe-gateway: 测试用的 mock 网关；；
- eureka-server: 注册服务器；

## 数据库（User DB）
### Auth table
样例数据：

| ID | APPKEY |  APPNAME   | USERNAME | PASSWORD |
|----|--------|------------|----------|----------|
|  1 |   1007 | poc        | Mike     | abcd     |
|  2 |   1008 | helleworld | Joe      | 1234     |

DDL:
``` sql
CREATE TABLE `auth` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `appkey` int(11) NOT NULL,
  `appname` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=UTF8;
```

## Auth（鉴权模块）API
### /check
- **URL**： `/check`
- **功能**：根据传入的 appkey，username，password 检查该用户是否为合法用户，返回 true/false。
- **参数**：
    + int appkey
    + String username
    + String password
- **返回**：bool
- **代码定义**：
``` java
@RequestMapping("/check")
public boolean check(
    @RequestParam("appkey") int appkey,
    @RequestParam("username") String username,
    @RequestParam("password") String password)
```
- **使用示例**：
``` shell
# 返回：true
$ curl -G --data "appkey=1007&username=Mike&password=abcd" http://localhost:8080/check
```

### /query-app/{appkey}
- **URL**：`/query-app/{appkey}`
- **功能**：根据 appkey 获取对应的 appname。
- **参数**：无
- **返回**：String
- **代码定义**：
``` java
@RequestMapping("/query-app/{appkey}")
public String queryApp(@PathVariable int appkey)
```
- **使用示例**：
``` shell
# 返回："poc"
$ curl -G http://localhost:8080/query-app/1007
```

### /save
- **URL**： `/save`
- **功能**：添加新的 Auth 记录到数据库，传入的参数作为其字段值。
- **参数**：
    + int appkey
    + String appname
    + String username
    + String password
- **返回**：String
- **代码定义**：
``` java
@RequestMapping("/save")
public Auth save(
    @RequestParam("appkey") int appkey,
    @RequestParam("appname") String appname,
    @RequestParam("username") String username,
    @RequestParam("password") String password)
```
- **使用示例**：
``` shell
$ curl -G --data "appkey=1009&appname=bookapi&username=Liu&password=xyz" \
  http://localhost:8080/save
```

## 网关 API
### /open/token
- **URL**：`/open/token`
- **功能**：根据 appkey、 username、 password 获取 token。
- **参数**：
    + int appkey
    + String username
    + String password
- **返回**：String
- **代码定义**：
``` java
@RequestMapping(value = "/open/token")
public void apply(
    @RequestParam("appkey") String appkey,
    @RequestParam("username") String username,
    @RequestParam("password") String password)
```
- **使用示例**：
``` shell
# 返回 {"bizDesc":"Succeed","token":"lpWzspJWsrMXLyqahgGjeyrbuNaKZKmL"}
$ curl -G --data "appkey=1007&username=Mike&password=abcd" \
http://localhost:8080/open/token
```
