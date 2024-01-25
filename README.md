# What is Archetype?
* https://maven.apache.org/archetype/index.html
* 简而言之，Archetype 是一个 Maven 项目模板工具包。原型被定义为原始模式或模型，所有其他同类事物都是从中制造出来的。这些名称很合适，因为我们试图提供一个系统，提供一种一致的方法来生成 Maven 项目。Archetype 将帮助作者为用户创建 Maven 项目模板，并为用户提供生成这些项目模板的参数化版本的方法。

# 本模板工程的作用
* SpringBoot 服务端模板工程，统一约束规范，提高研发效率、降低生产风险


## SpringBoot 2.3.12.RELEASE 集成的工具和库
* SpringBoot 2.3.12.RELEASE
* SpringCloud Hoxton.SR12
* 配置管理和服务注册发现：Nacos 2.2.7.RELEASE
* 数据库驱动：mysql
* 数据库连接池：HikariCP 4.0.3
* ORM框架：mybatis-plus 3.4.2
* 分页插件：pagehelper 1.4.2
* 监控指标数据暴露插件：micrometer 1.6.13
* API网关注册：shenyu 2.5.0
* 消息队列：Kafka 2.5.1
* 日志记录：log4j2 2.13.3
* Feign openFeign 10.12

## SpringBoot 3.1.8 集成的工具和库
* SpringBoot 3.1.8
* SpringCloud 2022.0.3
* 配置管理和服务注册发现：Nacos 2022.0.0.0
* 数据库驱动：mysql
* 数据库连接池：HikariCP 4.0.3
* ORM框架：mybatis-plus-boot-starter 3.5.5
* 分页插件：pagehelper-spring-boot-starter 1.4.7
* 接口文档: springdoc + knife4j
* 监控指标数据暴露插件：micrometer-registry-prometheus 1.11.7
* API网关注册：shenyu 2.5.0
* 消息队列：Kafka 3.4.1
* 日志记录：log4j2 2.20.0



# 使用方法
## 创建自己的模版工程
* 安装java,maven并配置好环境变量
* 配置maven的settings.xml文件，将maven仓库地址改为公司私有仓库
* clone项目，通过终端进入spring-boot-server-template目录
* 按照自身需求调整项目代码，编译
```linux
# maven指定编译出模版工程代码 ， org.zhangyinhao.om是需要为变量的包名
mvn archetype:create-from-project -Darchetype.properties=archetype.properties -DpackageName=org.zhangyinhao.om
```
* 此时会在 spring-boot-server-template/target/generated-sources/archetype 目录生成编译后的代码，通过终端进入该目录
```linux
# 发布到私有仓库
mvn clean install deploy
```

## 项目团队成员使用该模版创建工程
* 当有新的项目，需要使用模板创建工程的时候
```linux
# 通过模板创建项目
mvn archetype:generate -D archetypeGroupId=org.zhangyinhao.template -D archetypeArtifactId=spring-boot-server-template-archetype -D archetypeCatalog=remote -D archetypeVersion=1.0-SNAPSHOT -D interactiveMode=false -DarchetypeCatalog=remote -D groupId=com.xxx.dd -D artifactId=user-center -D version=0.0.1-SNAPSHOT -D package=com.xxx.dd.user
```


## 工程模块说明
* common: 通用工具类、domain定义等
* facade: 对外提供的接口定义并提供jar包，项目内工程之间的互相调用
* mapper: 数据层
* service: 业务处理相关代码
    1. feign存放远程调用的FeignClient
    2. mq对消息中间件数据处理的出口和入口
    3. execute 业务处理 没有事务管理
    4. manage 涉及到事务管理的从这里开启事物
* start:启动项、配置以及web接口

## 项目业务包名说明
* controller：是否创建子包看个人的习惯，调用方可能是内部系统前端运营界面的，可能是本系统内的其他服务调用
* dal：dao放数据层面的能够公用的查询接口；entity数据库表的映射；mapper
* service：业务处理相关代码
    1. feign存放远程调用的FeignClient
    2. mq对消息中间件数据处理的出口和入口
    3. execute 业务处理 没有事务管理
    4. manage 涉及到事务管理的从这里开启事物

## 约束
* service中禁止相互依赖


