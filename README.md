第1章 快速开始Hello World
===


大约20年前，程序员们使用“企业级Java Bean”（EJB）开发企业应用，需要配置复杂的XML。在二十世纪初期，新兴Java技术——Spring，横空出世。使用极简XML和POJO（普通Java对象），结合EJB的替代品（如Hibernate），Spring在企业级Java开发上占据了绝对领先地位。

但是，随着Spring的不断发展，当初的XML配置逐渐变得复杂庞大，成了累赘，遭众多程序员“诟病”。后来，Spring推出了JavaConfig项目，使用声明式的注解，大量减少了显式的XML配置。

然而，故事到这里，并没有结束。

下面即将进入我们的 SpringBoot 之旅。

> Boot：引导，启动也。


本章我们快速开始 Hello World 的体验。

### 1.1 Spring Boot CLI groovy版Hello World

### 1.1.1 配置环境

首先安装Spring Boot CLI，我们去这里下载最新版本的压缩包
http://repo.spring.io/snapshot/org/springframework/boot/spring-boot-cli/
当前最新版本是：2.0.0.BUILD-SNAPSHOT 。

我们把下载好的spring-boot-cli-2.0.0.BUILD-SNAPSHOT-bin.zip 解压到相应的目录下，然后配置环境变量：

```
$vim .bashrc
export SPRING_BOOT_HOME=/Users/jack/soft/spring-2.0.0.BUILD-SNAPSHOT
export PATH=$PATH:$SPRING_BOOT_HOME/bin

```


> 提示：更多安装参考文档：http://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#getting-started-installing-the-cli

在命令行验证spring环境安装成功：
```
$ source .bashrc
$ spring --version
Spring CLI v2.0.0.BUILD-SNAPSHOT
```



### 1.1.2 编写控制器groovy代码

随便打开一个编辑器，新建文件HelloController.groovy，敲入如下代码：

```
 @Controller
 class HelloController{
      @RequestMapping("/hello")
      @ResponseBody
      String hello(){
          return "Hello World!"
      }
 }
```

### 1.1.3 运行测试

然后在命令行执行：
```
$ spring run HelloController.groovy
```

你将会在控制台看到输出以下日志

![螢幕快照 2017-08-01 13.02.12.png](http://upload-images.jianshu.io/upload_images/1233356-80aaa347f354a856.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


一个极简的Restful Hello World就搞定了。

浏览器访问：http://localhost:8080/hello（或者curl http://localhost:8080/hello）

你会看到输出：

```
Hello World!
```


## 1.2 常规的Java版的Hello World

#### 1. 创建 SpringBoot Web 项目

我们可以直接在命令行输入

```
$ spring init -dweb --build gradle
Using service at https://start.spring.io

Content saved to 'demo.zip'
```
来创建一个模板项目，也可以到Spring Initializer ： https://start.spring.io/ 页面进行操作。

为了更加直观，我们在Spring Initializer页面创建项目示例。如下图所示


![创建项目](http://upload-images.jianshu.io/upload_images/1233356-ebfd0cfb4f7f1245.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

首先，我们依次选择 Gradle Project， Java， SpringBoot 版本是 2.0.0.BUILD-SNAPSHOT 。

然后，配置项目基本信息

Group ：com.easy.springboot
Artifact： chapter1_helloworld

依赖项我们就先只用 Web 。

最后，点击 Generate Project , 页面会自动生成一个模板工程 zip 包：chapter1_helloworld.zip 。

#### 2.解压zip，导入idea中

导入 Gradle 工程，配置 Gradle 环境，如下图

![配置 Gradle 环境](http://upload-images.jianshu.io/upload_images/1233356-aa3e261627e2dbb1.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

等待 IDEA 构建完毕，我们将得到一个标准的 SpringBoot Application

```
.
├── build.gradle
├── chapter1_helloworld.iml
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── easy
    │   │           └── springboot
    │   │               └── chapter1_helloworld
    │   │                   └── Chapter1HelloworldApplication.java
    │   └── resources
    │       ├── application.properties
    │       ├── static
    │       └── templates
    └── test
        ├── java
        │   └── com
        │       └── easy
        │           └── springboot
        │               └── chapter1_helloworld
        │                   └── Chapter1HelloworldApplicationTests.java
        └── resources

19 directories, 9 files


```

#### 3. build.gradle配置文件

我们来看一下生成的模板工程里面的Gradle配置文件

```
buildscript {
	ext {
		springBootVersion = '2.0.0.BUILD-SNAPSHOT'
	}
	repositories {
		mavenCentral()
		maven { url "https://repo.spring.io/snapshot" }
		maven { url "https://repo.spring.io/milestone" }
	}
	dependencies {
		classpath("org.springframework.boot:spring-boot-gradle-plugin:${springBootVersion}")
	}
}

apply plugin: 'java'
apply plugin: 'eclipse'
apply plugin: 'org.springframework.boot'
apply plugin: 'io.spring.dependency-management'

version = '0.0.1-SNAPSHOT'
sourceCompatibility = 1.8

repositories {
	mavenCentral()
	maven { url "https://repo.spring.io/snapshot" }
	maven { url "https://repo.spring.io/milestone" }
}


dependencies {
	compile('org.springframework.boot:spring-boot-starter-web')
	testCompile('org.springframework.boot:spring-boot-starter-test')
}

```

我们通过下面的表格简要说明如下

|    配置项     |     功能说明       |
|---|---|
|spring-boot-gradle-plugin| 这个插件在 Gradle中提供了SpringBoot支持，例如打包可执行的jar或war，运行SpringBoot应用程序等功能。它使用spring-boot-dependencies 提供的依赖管理。这样我们在dependencies 添加SpringBoot依赖的时候可以不用指定版本号。|
|apply plugin: 'org.springframework.boot'|应用spring-boot-gradle-plugin插件|
|compile('org.springframework.boot:spring-boot-starter-web')| Spring 全栈式Web开发启动器，包括Tomcat、spring-webmvc、SpringBoot AutoConfigure 等核心依赖 |



#### 4. SpringBoot 应用启动类

我们使用 @SpringBootApplication 注解来标注SpringBoot 应用的启动类。

```
package com.easy.springboot.chapter1_helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Chapter1HelloworldApplication {

	public static void main(String[] args) {
		SpringApplication.run(Chapter1HelloworldApplication.class, args);
	}
}

```


同样的，你将看到控制台输出如下日志:

```
...
 :: Spring Boot ::  (v2.0.0.BUILD-SNAPSHOT)

2017-08-01 14:31:43.849  INFO 4987 --- [           main] c.e.s.c.Chapter1HelloworldApplication    : Starting Chapter1HelloworldApplication on jacks-MacBook-Air.local with PID 4987 
...
2017-08-01 14:31:47.841  INFO 4987 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http)
2017-08-01 14:31:47.846  INFO 4987 --- [           main] c.e.s.c.Chapter1HelloworldApplication    : Started Chapter1HelloworldApplication in 5.74 seconds (JVM running for 7.021)

```

通过日志，我们可以看到SpringBootApplication大致的启动流程。这个过程中，很大一部分工作是在做SpringBoot 的自动配置。

#### 5. @SpringBootApplication注解 
@SpringBootApplication注解是 SpringBoot 应用的启动类的注解，它包括三个注解，如下表

|   注解       |      功能说明      |
|-----------|------------------|
|@SpringBootConfiguration| 等同于@Configuration，表示将该类是SpringBoot 应用程序入口类|
|@EnableAutoConfiguration | 表示程序启动时，启动SpringBoot默认的自动配置。自动配置类通常根据类路径和我们自定义的 bean 来执行相应的自动配置策略。例如, 如果我们的类路径上有tomcat-embedded.jar ， 那么SpringBoot 就会尝试去自动配置TomcatServletWebServerFactory Bean，或者去配置我们自定义的 ServletWebServerFactory Bean。|
|@ComponentScan |表示程序启动时自动扫描当前包及子包下所有类。|



#### 6. 编写控制器类HelloWorldController

我们简单编写一个 RestController，代码如下

```
package com.easy.springboot.chapter1_helloworld.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @RequestMapping("hello")
    String hello() {
        return "Hello World! " + new Date();
    }
}

```

#### 7. 运行测试

我们在 IDEA 中直接点击 main 函数运行


![点击 main 函数运行](http://upload-images.jianshu.io/upload_images/1233356-a22f1dccb0f62dc1.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

可以看到控制台输入如下的日志：
```
...
 :: Spring Boot ::  (v2.0.0.BUILD-SNAPSHOT)

2017-08-01 14:57:24.928  INFO 5224 --- [           main] c.e.s.c.Chapter1HelloworldApplication    : Starting Chapter1HelloworldApplication on  
...
Mapped "{[/hello]}" onto java.lang.String com.example.controller.HelloWorldController.hello()
...
2017-08-01 14:57:28.468  INFO 5224 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http)
2017-08-01 14:57:28.479  INFO 5224 --- [           main] c.e.s.c.Chapter1HelloworldApplication    : Started Chapter1HelloworldApplication in 4.128 seconds (JVM running for 4.914)
```


浏览器访问：http://localhost:8080/hello

我们将看到如下输出：

```
Hello World! Tue Apr 04 23:08:33 CST 2017
```

另外，如果是在命令行运行，使用SpringBoot gradle插件的执行：
```
gradle bootRun
```
如果是使用的 Maven，那么对应的是SpringBoot maven插件的执行：
```
mvn spring-boot:run
```

#### 8. 运维监控

SpringBoot 本身提供了丰富的健康监控等运维功能，这些功能在 actuator 里。我们在build.gradle的依赖里面添加一行actuator的配置
```
compile('org.springframework.boot:spring-boot-starter-actuator')
```

为了方便测试，我们在application.properties 添加如下配置

```
endpoints.metrics.enabled=true
management.security.enabled=false // 去掉安全校验
```
然后重启应用，我们可以看到 IDEA 的控制台Endpoints 的 Beans  Tab界面里面有我们的这个 SpringBoot 应用的所有 Bean 的信息列表

![Endpoints 的 Beans 界面](http://upload-images.jianshu.io/upload_images/1233356-e2dc46b5badc5023.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


![helloWorldController Bean](http://upload-images.jianshu.io/upload_images/1233356-17e56d184b2496c0.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

Health Tab 界面中我们可以看到服务器（本机）的磁盘使用信息


![Health Tab 界面](http://upload-images.jianshu.io/upload_images/1233356-db9672d5c2bee988.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


在请求映射 Mappings 界面我们可以看到SpringBoot 应用提供出来的所有的 Rest 接口

![Mappings 界面](http://upload-images.jianshu.io/upload_images/1233356-76d9877574fcb321.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


例如，我们访问http://127.0.0.1:8080/application/health ， 可以得到如下输出

```
{
  "status": "UP",
  "diskSpace": {
    "status": "UP",
    "total": 120108089344,
    "free": 1280909312,
    "threshold": 10485760
  }
}
```
其他的监控 API，可以类似访问，看看输出的内容。关于spring actuator 监控的更多内容，我们将在后面的章节中介绍。



## 本章小结

本章我们通过两个Hello World 实例，快速体验了 SpringBoot 开发的过程。在 SpringBoot 里面我们不再有各种繁杂的 xml 配置，不再有配置、启动、运行 Tomcat Web 服务器的一系列繁琐操作了。

同时您也一定发现了：我们的这个 Web 应用是怎么启动运行的？

下一章中我们就来一起学习一下 SpringBoot 应用的启动过程和自动配置原理。

https://github.com/EasySpringBoot/HelloWorld/tree/hello_world_2017.4.4
