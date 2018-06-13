# simple-job-framework

## 项目介绍
本项目以尽量简洁的方式实现一个任务调度框架，演示一个任务调度框架所使用到的核心技术栈，包括job/trigger/scheduler等。本项目参考quartz-scheduler的架构实现。

## 项目结构
整个项目目录结构如下，
```
- pom.xml 整个Maven项目的pom文件，这是项目的根目录
+ job-framework
  - pom.xml
  + src
    + main/java/com/pphh/job
      + job 任务
      + trigger 触发器
      + scheduler 调度器
      + jobstore 任务/触发器信息
      + thread 执行线程池
      + executor 执行器
      + factory 调度器工厂
      + util
+ job-sample 演示样例
  - pom.xml
  + src/main/java/
    + com/pphh/job/demo
      + SimpleDemo  简单的演示样例
      + MultipleTriggersDemo 演示多个触发器的演示样例
```

## 项目构建
构建环境：JDK7+和Maven3。

请打开shell窗口，切换在演示项目的根目录中，执行如下命令，对项目编译打包，
``` bash
mvn clean package
```

## 演示

1. 演示简单的定时触发任务，每个5秒，重复10次，执行命令如下，
   ``` bash
   java -jar ./job-samples/target/job-samples-1.0-SNAPSHOT.jar
   ```
  上述命令将会在控制台输出如下信息，
  ```
  [20180613 22:32:54-856][ThreadPoolTaskExecutor-1] hello,world
  [20180613 22:32:59-683][ThreadPoolTaskExecutor-2] hello,world
  [20180613 22:33:04-683][ThreadPoolTaskExecutor-3] hello,world
  [20180613 22:33:09-683][ThreadPoolTaskExecutor-4] hello,world
  [20180613 22:33:14-683][ThreadPoolTaskExecutor-5] hello,world
  [20180613 22:33:19-688][ThreadPoolTaskExecutor-6] hello,world
  [20180613 22:33:24-683][ThreadPoolTaskExecutor-7] hello,world
  [20180613 22:33:29-683][ThreadPoolTaskExecutor-8] hello,world
  [20180613 22:33:34-683][ThreadPoolTaskExecutor-9] hello,world
  [20180613 22:33:39-683][ThreadPoolTaskExecutor-10] hello,world
  ```

2. 演示多个定时触发任务，包括，1）定时触发器默认配置，执行一次；2）每个5秒，重复10次；3）每个1秒，重复60次。
   执行命令如下，
   ``` bash
   java -classpath ./job-samples/target/job-samples-1.0-SNAPSHOT.jar com.pphh.job.demo.MultipleTriggersDemo
   ```
  上述命令将会在控制台输出如下信息，
  ```
  [20180613 22:39:16-253][ThreadPoolTaskExecutor-6] hello,world
  [20180613 22:39:17-326][ThreadPoolTaskExecutor-7] hello,world
  [20180613 22:39:18-290][ThreadPoolTaskExecutor-8] hello,world
  ...
  ```

## 参考信息
演示样例参考了quartz-scheduler的[官方项目](https://github.com/quartz-scheduler/quartz)。

本项目以演示为主要目的，在实现任务调度的功能演示基础上，大大简化结构代码，方便理解quartz-scheduler的技术实现和架构。

## 联系 Contact
我们的邮箱地址：peipeihh@qq.com，欢迎来信联系。

## 开源许可协议 License
Apache License 2.0
