# XQOO-CLOUD 后台管理系统

## 安装
---
1. 后端代码:
   	gitee:  https://gitee.com/gaoyangmaster/xqoo-cloud-admin
   	github:  https://github.com/xqooCloud/xqoo-cloud-admin.git
2. 前端控制台代码:
   	gitee: https://gitee.com/gaoyangmaster/xqoo-cloud-admin-ant-design-v5.git
   	github: https://github.com/xqooCloud/xqoo-cloud-admin-ant-design-v5.git 

后端采用springCloud + AlibabaNacos搭建的基础微服务架构
spring-boot版本2.3.4，cloud版本 Hoxton.SR8, nacos 版本1.3。2

前端管理控制台使用AntDesignPro 5.0版本(typeScript)
[antDesignPro官方文档](https://ant.design/components/overview-cn/)


## 包含  
---
服务注册中心-nacos   
配置中心-nacos
授权中心-authorization
网关-springCloudGateway   
服务消费中心-Feign  
限流熔断器-sentinel
系统日志记录-operationLog
公共服务-common
演示服务1-client
公共服务中集成了redis，swagger2, mybatis

## 特别说明
  third-party-jar文件夹中包含一些第三方的jar包以及一部分启动脚本
### nacos
   ~~nacos因为jar包较大没有上传，官方下载[1.3.2版本(zip)](https://github.com/alibaba/nacos/releases/download/1.4.1/nacos-server-1.4.1.zip)或[1.3.2版本(tar.gz)](https://github.com/alibaba/nacos/releases/download/1.4.1/nacos-server-1.4.1.tar.gz)之后将target文件夹整个丢入third-party-jar/nacos/文件夹下,修改conf中链接数据库参数配置后在bin/目录下启动即可~~
   nacos 1.3.2连接数据库存在一点小问题，连接过期断开之后不会重连，更换为1.4.2，同时1.4.2支持连接池参数修改[1.4.2版本(tar.gz)](https://github.com/alibaba/nacos/releases/download/1.4.1/nacos-server-1.4.1.tar.gz)
   1.4.2版本以下的nacos库执行1.4.0-ipv6_support-update脚本，更新下主要几张表的src_ip这个字段的长度就行
### zipkin
  版本为2.21.7，需要改动链接参数或数据源参数请参照[官网](https://zipkin.io/)
  修改后运行zipkinStart.sh启动即可(windows提供了一个简单的bat文件启动，非后台启动，直接关闭shell即关闭zipkin)，访问GUI http://127.0.0.1:9411 即可

### sentinel
  版本为1.8.0，版本不可替换，因源码有改动，只能使用项目内jar包启动，使用官方jar包将丢失部分功能 
  运行sh脚本后访问 GUI http://127.0.0.1:8718 进入图形管理界面，默认
	账号：sentinel
    密码：!@#123qwe
  各项参数在启动脚本中直接修改

```bash
  	APP_PORT=8718
	APP_CONF="${BASE_DIR}/config/application.properties"
	APP_SERVER="127.0.0.1:$APP_PORT"
	APP_CONTENT_NAME="sentinel-dashboard"
	APP_AUTH_USER="sentinel"
	APP_AUTH_PWD="!@#123qwe"
```
### rocketMQ
  rocketMQ请到官方下载 [中科大镜像源-4.8.0版本](https://mirrors.tuna.tsinghua.edu.cn/apache/rocketmq/4.8.0/rocketmq-all-4.8.0-bin-release.zip),
  下载完成后请解压任意目录，如不想更改启动脚本则解压到/opt/rocketmq-bin/目录下(不含外层rocketmq-all-4.8.0-bin-release文件夹，仅文件夹内的内容，windows系统自行修改目录)下，修改runserver.sh和runbroker.sh中jvm内存参数(源码中内存占用过大)，然后先运行startMqServer.sh，再运行startMqBroker.sh
#### server
运行成功打印如下日志
```verilog
	OpenJDK 64-Bit Server VM warning: Using the DefNew young collector with the CMS collector is deprecated and will likely be removed in a future release
OpenJDK 64-Bit Server VM warning: UseCMSCompactAtFullCollection is deprecated and will likely be removed in a future release.
The Name Server boot success. serializeType=JSON
```
启动报错包含$JAVA_HOME/bin/lib/ext…… no such Directory 等字样则为jdk版本过高，rocketMQ目前版本暂不支持jdk 1.8+，如果不方便直接修改环境变量，请单独安装jdk1.8并修改rocketMq/bin目录下所有脚本文件中包含如下代码的地方
```bash
[ ! -e "$JAVA_HOME/bin/java" ] && JAVA_HOME=$HOME/jdk/java
[ ! -e "$JAVA_HOME/bin/java" ] && JAVA_HOME=/usr/java
[ ! -e "$JAVA_HOME/bin/java" ] && error_exit "Please set the JAVA_HOME variable in your environment, We need java(x64)!"

export JAVA_HOME
export JAVA="$JAVA_HOME/bin/java"
```
将上述三行判定jdk位置的命令直接注释，然后export JAVA_HOME为固定jdk1.8路径，如下所示
```bash
#[ ! -e "$JAVA_HOME/bin/java" ] && JAVA_HOME=$HOME/jdk/java
#[ ! -e "$JAVA_HOME/bin/java" ] && JAVA_HOME=/usr/java
#[ ! -e "$JAVA_HOME/bin/java" ] && error_exit "Please set the JAVA_HOME variable in your environment, We need java(x64)!"

export JAVA_HOME="/usr/lib/jvm/java-8-openjdk"
export JAVA="$JAVA_HOME/bin/java"
```
#### broker
出现以下日志打印说明启动成功
```verilog
The broker[Gao-PC, 192.168.0.6:10911] boot success. serializeType=JSON and name server is 127.0.0.1:9876
```
#### dashboard
可视化界面启动已经使用请参照third-party-jar/rocket-mq中readme文档

## 框架中已包含的功能包括：
---
阿里数据库连接池
eu.bitwalker客户端系统解析
jacjson JSON解析工具
pagehelper分页插件
commons-io常用工具类
commons-fileupload文件上传工具
rg.jsoup HTML解析器
org.apache.poi 表格文件处理
org.apache.velocity 模板生成
mybatisPlus
okHttp
guava google java工具整合包
hutool 工具包
rocketMQ 消息中间件
---
## 端口，配置相关：
---
1. 端口占用：
nacos-8184
gateway-8080
其他服务模块自行定义，同一个工程建议服务模块统一一个端口号段，最好不要使用80端口
内置服务占用的接口如下：
授权服务:9011
系统日志: 9018
流水号生成服务：9003
springAdminWatch: 8099
sentinel-server: 8719
sentinel-dashboard: 8718
链路追踪zipkin：9411
消息中间件 9876
消息中间件控制台 8078

2. 启动顺序：
nacos最先启动
其他服务启动顺序随意
common和feign不需要启动
新建模块建构参照client和clientTwo示例
各模块直接对外暴露接口调用全部放在feign模块下
所有模块的SpringBootApplication启动文件放在com.xqoo目录下，否则会引起其他服务启动报错
各服务自己的数据库建表和初始化数据请存放在模块根目录下.sql文件，与pom.xml同级

3. 配置中心说明
具体详情请转至nacos官方文档中查看

---
## 内置功能说明：
内置第三方功能全部在xqoo-common模块下，部分功能为集成，需要单独调用的另做说明

1. Redis
	redis需要在独立模块中自己引入common-redis模块，具体使用方法参照xqoo-gateway中的fitter全局拦截器

2. 自定异以及返回实体中code含义
	模块中为进行捕获的异常将走全局异常处理，所有全局异常捕获均为正常响应状态200，返回结构为
```json
	{
		code: xxx,
		message: xxxx,
		data: JsonObject || JsonArray
	}
```
    code为int整形，具体参看jdk枚举HttpStatus，message为返回提示文本内容,data不一定都有，有的时候可做某些前端处理
---
    BusinessException： 业务异常，可自选异常码，默认SERVICE_UNAVAILABLE->503,选填返回Json
---
    AccessDenyException: 登录权限异常，未登录或者没有相应权限，默认UNAUTHORIZED->401,选填返回Json
---
    SystemException: 系统运行异常，一般是人为抛出一些BUG操作，默认SERVICE_UNAVAILABLE->503, 额外有个detailMessage参数String
---
    TokenAccessExpiredError：token令牌异常，过期、被踢、被禁等，默认FORBIDDEN->403, 必填返回Json
---
    MethodArgumentNotValidException: 此异常不可自己抛出，为valid框架校验参数异常，code固定METHOD_NOT_ALLOWED->405,data携带未通过验证参数的arr，每个arr中包含一个json对象，里面带有出错字段名field和对应字段错误信息errMsg。此异常仅当参数为@RequestBody时有效
---
    MissingServletRequestParameterException: 此异常不可自己抛出，为valid框架校验参数字段缺失，code固定METHOD_NOT_ALLOWED->405,此异常仅当参数为@RequestParam时有效,没有json对象返回
---
    ConstraintViolationException: 此异常不可自己抛出，为valid框架校验参数值缺失或不满足校验规则，code固定METHOD_NOT_ALLOWED->405,此异常仅当参数为@RequestParam时有效,data携带未通过验证参数的arr，每个arr中包含一个json对象，里面带有出错字段名field和对应字段错误信息errMsg
---
    HttpRequestMethodNotSupportedException: 此异常不可自己抛出，为借口调用method不正确，进返回所用method方法和支持的method方法,code固定METHOD_NOT_ALLOWED->405
---
    Exception: 未统一捕获的其他所有异常均返回INTERNAL_SERVER_ERROR->500,统一信息为系【统运行错误: + 'ExceptionClassName'】
---
    各服务中忘记抛出或捕获的异常，将在网关统一改写为开头返回结构，code则遵从服务响应到网关的HttpStatus转换为json，信息也是固定文本+异常信息
---
	通用返回：  
		业务成功-HttpStatus.OK->200
		请求地址已失效-HttpStatus.BAD_REQUEST->400
		需要登录-HttpStatus.UNAUTHORIZED->401
		需要付费-HttpStatus.PAYMENT_REQUIRED->402
		请求地址未找到-HttpStatus.NOT_FOUND-> 404
		表单提交方法错误或表单值验证不通过-METHOD_NOT_ALLOWED->405
		自定义错误-HttpStatus.NOT_ACCEPTABLE-> 406
		请求超时-HttpStatus.REQUEST_TIMEOUT->408
		请求次数过于频繁-HttpStatus.TOO_MANY_REQUESTS->429
		错误的网关-HttpStatus.BAD_GATEWAY->502
		网关访问超时-HttpStatus.GATEWAY_TIMEOUT->504
---
	授权专用返回：
		账户已被冻结或锁定 HttpStatus.LOCKED -> 423
		账户已停用 HttpStatus.DESTINATION_LOCKED -> 421
		密码多次错误 HttpStatus.PRECONDITION_FAILED -> 412
		网关专用返回：
		来源ip黑名单 HttpStatus.NOT_EXTENDED -> 510
		网关黑名单 HttpStatus.NETWORK_AUTHENTICATION_REQUIRED -> 511
3. 系统日志注解@OperationLog的使用
	注解是method级
	tips - 简要的接口操作说明
	operatorType - 接口的类型，OperationTypeEnum枚举
	isSaveRequestData - 是否存储请求参数的json字符串，仅支持post和put时有效，默认false
	isSaveResponseData - 是否存储接口返回的数据json字符串，默认true
	说明：此注解优先级低于spring自带的一些注解，部分spring层级较高的注解异常将不会被日志记录
	例如：@RequestBody 注解异常时，日志注解不生效
4. 参数校验注解
	控制器所有参数校验均使用org.springframework.validation.annotation.Validated,默认所有控制器类上都应增加注解@Validated,
	@RequestParam型的参数required需要设成false，然后在注解后增加@NotBlank或者			@NotNull，基础类型Integer，Boolean等不能使用NotBlank
	@RequestBody型参数需要在此注解后增加注解@Valid，具体的字段判定在实体类中单独在参数定义上增加，如果类中定义了其他类为参数，则参数类上也需要加@Valid
	样例查看common-core包中的TestVO，正则，自定义校验等用法请自行百度
5. 基于百度的分布式唯一流水号生成器
	模块名为xqoo-uid-generator，未压缩，使用的官方源码，方便以后修改规则，此服务需要建立一张数据库表，建表脚本在模块根目录内
	已在服务消费中心提供接口，其他模块需要使用用直接注入UidGeneratorFeign即可
	默认已经在生成流水号的代码中加锁，使用请勿重复加锁，直接获取即可。
	百度开源项目，详情可见[官方git](https://github.com/baidu/uid-generator)
	[第三方文章推荐链接](https://blog.csdn.net/fenglailea/article/details/90200602)
6. 链路追踪
	链路追踪启动第三方包中jar包执行，端口9411，需要重点关注下目前亲测只有谷歌浏览器能打开可视化界面看到数据
其他使用谷歌内核的浏览器虽然能看到界面，但是却看不到明细信息
7. sentinel-限流管理界面
	sentinel-dashboard基础版本1.8.0，规则拉取和推送有部分改动
	[官网的dashboard包](https://github.com/alibaba/Sentinel.git)不支持nacos双向绑定
	这是我个人修改的和nacos双向绑定的 dashboard 的源码 [sentinel-git](https://github.com/DissoluteGao/sentinelV2.git)
8. 消息中间件使用
	实际用例请参见xqoo-client和xqoo-client-two模块rocketmq包内示例，需要单独安装MQ客户端，示例中有部分代码示例
	更多用法请自行查阅[Apache rocketMQ官网说明](http://rocketmq.apache.org/)
9. 常见问题
  1. 如何获取当前登录用户
	目前有两种方式获取 在引入xqoo-cloud-feign包的情况下 在方法参数中打入一下注解

    @LoginUser CurrentUser currentUser 然后就可以获取到了
  2. 需要自动填充字段 mybatisPlus自带的
	详情请见 core模块 MyMetaObjectHandler 在需要自动填充的字段上加上就行
	利用代码生成的全部会自动加上
  3. swagger2 配置
	只需要引入此包即可,注意启动类的包必须在com.xqoo下,否则自行加commoneScan才能扫描到
	如果安装了其他的安全拦截框架,请放/v2/api-docs路径
  4. redies配置
	只需要引入此包后在配置文件中进行配置,使用的为springboot-data-redis,连接池工具为lettuce
  5. 网关配置
	在nacos中编辑public-xqoo-gateway-config.yml文件(PUBLIC_CONFIG_GROUP)
	网关内置ip，端口黑名单
	跳转连接黑名单
	无需登录资源白名单
	以上配置值均支持通配符

    网关中添加请求header,来源ip-SystemPublicConstant.REMOTE_REQUEST_IP：xqoo-remote-address
    网关中添加请求header,来源端口-SystemPublicConstant.REMOTE_REQUEST_PORT：xqoo-remote-address-port
    网关中添加请求header,登录人userId-SystemPublicConstant.USER_ID_HEADER_KEY_NAME：xqoo-uuid
    网关中添加请求header,登录人userName-SystemPublicConstant.USER_NAME_HEADER_KEY_NAME：xqoo-user-name

---
## docker 部署方案
首先保证docker 配置端口打开 默认为2375
然后idea安装docker插件 链接docker
然后往docker中pull基础运行镜像   adoptopenjdk/openjdk8-openj9:latest
然后在模块pom中加入下面的配置 需要修改的有docker地址 主类
```xml
<build>
        <finalName>${project.artifactId}</finalName>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <version>2.0.1.RELEASE</version>
                <configuration>
                    &lt;!&ndash; 指定该Main Class为全局的唯一入口 &ndash;&gt;
                    <mainClass>com.xqoo.ApplicationGateway</mainClass>
                    <layout>ZIP</layout>
                </configuration>
                <executions>
                    <execution>
                        <goals>
                            <goal>repackage</goal>&lt;!&ndash;可以把依赖的包都打包到生成的Jar包中&ndash;&gt;
                        </goals>
                    </execution>
                </executions>
            </plugin>
            &lt;!&ndash;使用docker-maven-plugin插件&ndash;&gt;
            <plugin>
                <groupId>com.spotify</groupId>
                <artifactId>docker-maven-plugin</artifactId>
                <version>1.0.0</version>
                &lt;!&ndash;将插件绑定在某个phase执行&ndash;&gt;
                <executions>
                    <execution>
                        <id>build-image</id>
                        &lt;!&ndash;用户只需执行mvn package ，就会自动执行mvn docker:build&ndash;&gt;
                        <phase>package</phase>
                        <goals>
                            <goal>build</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration>
                    &lt;!&ndash;指定生成的镜像名&ndash;&gt;
                    <imageName>${project.artifactId}</imageName>
                    &lt;!&ndash;指定标签&ndash;&gt;
                    <imageTags>
                        <imageTag>latest</imageTag>
                    </imageTags>
                    &lt;!&ndash; 指定 Dockerfile 路径&ndash;&gt;
                    <dockerDirectory>${project.basedir}</dockerDirectory>
                    &lt;!&ndash;指定远程 docker api地址&ndash;&gt;
                    <dockerHost>http://127.0.0.1:2375</dockerHost>
                    &lt;!&ndash; 这里是复制 jar 包到 docker 容器指定目录配置 &ndash;&gt;
                    <resources>
                        <resource>
                            <targetPath>/</targetPath>
                            &lt;!&ndash;jar 包所在的路径  此处配置的 即对应 target 目录&ndash;&gt;
                            <directory>${project.build.directory}</directory>
                            &lt;!&ndash; 需要包含的 jar包 ，这里对应的是 Dockerfile中添加的文件名　&ndash;&gt;
                            <include>${project.build.finalName}.jar</include>
                        </resource>
                    </resources>
                </configuration>
            </plugin>
        </plugins>
    </build>
```
---
### 模块目录下新建文件  Dockerfile 需要修改的只有Jar包的名字 
---
```makefile
#指定基础镜像，在其上进行定制
FROM adoptopenjdk/openjdk8-openj9:alpine-slim

#维护者信息
MAINTAINER xqoo

#这里的 /tmp 目录就会在运行时自动挂载为匿名卷，任何向 /tmp 中写入的信息都不会记录进容器存储层
VOLUME /tmp

#复制上下文目录下的生成的jar 到容器里
COPY target/xqoo-cloud-eureka.jar xqoo-cloud-eureka.jar

#RUN新建立一层，在其上执行这些命令，执行结束后， commit 这一层的修改，构成新的镜像。
#RUN bash -c "touch /xqoo-cloud-eureka.jar"

#声明运行时容器提供服务端口 可以暴露出来的端口 这里不暴露
#EXPOSE 8182
#指定默认环境变量
ENV JAVA_OPTS="-Xms256m -Xmx512m"
#指定容器启动程序及参数   <ENTRYPOINT> "<CMD>"
ENTRYPOINT java $JAVA_OPTS -Xshareclasses -Xquickstart -jar xqoo-cloud-eureka.jar
```
---
然后运行 maven 打包 会自动在docker创建一个镜像,注意,同一个模块有两个镜像注意更换名字,
镜像中心有模块镜像是想覆盖先删除 然后在docker上用镜像创建容器便可
JAVA_OPTS 是jvm优化 就设置这两个就行-Xms256m -Xmx512m 其他的镜像里面做的有优化
-d 是后台运行 都是docker命令 最后面的是镜像名字
docker run --env JAVA_OPTS=-Xms256m -Xmx512m --name xqoo-cloud-eureka -d xqoo-cloud-eureka:latest 

  ~~~

  ~~~