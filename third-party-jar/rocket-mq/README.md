# rocketMQ
rocketMQ请到官方下载 [中科大镜像源-4.8.0版本](https://mirrors.tuna.tsinghua.edu.cn/apache/rocketmq/4.8.0/rocketmq-all-4.8.0-bin-release.zip),
下载完成后请解压任意目录，如不想更改启动脚本则解压到/opt/rocketmq-bin/目录下(不含外层rocketmq-all-4.8.0-bin-release文件夹，仅文件夹内的内容，windows系统自行修改目录)
下，修改runserver.sh和runbroker.sh中jvm内存参数(源码中内存占用过大)，然后先运行startMqServer.sh，再运行startMqBroker.sh
## server
运行成功打印如下日志
```verilog
	OpenJDK 64-Bit Server VM warning: Using the DefNew young collector with the CMS collector is deprecated and will likely be removed in a future release
OpenJDK 64-Bit Server VM warning: UseCMSCompactAtFullCollection is deprecated and will likely be removed in a future release.
The Name Server boot success. serializeType=JSON
```
启动报错包含$JAVA_HOME/bin/lib/ext…… no such Directory 等字样则为jdk版本过高,java 9+以上版本需要修改mq的server，broker，tools三个启动脚本的参数
具体请参看这里，[rocketMQ 在jdk9+修改启动参数](https://www.cnblogs.com/architectforest/p/13652282.html),或者直接使用third-party-jar/rocket-mq/jdk11 文件夹下的配置文件
替换rocketmq/bin目录下三个脚本文件，windows版的自行百度，改的地方差不多，只是语法问题
**如果之前使用jdk1.8启动过rocket，需要删除用户目录下的store文件夹内的所有东西重新生成，生产环境切勿如此做**
**里面classpath的变量根据实际情况修改**
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
## broker
出现以下日志打印说明启动成功
```verilog
The broker[Gao-PC, 192.168.0.6:10911] boot success. serializeType=JSON and name server is 127.0.0.1:9876
```
剩余启动参数以及其他配置参考以下网站
https://www.jianshu.com/p/912701cf1705

另外windows下如果rocketMQ在运行中系统重启，很有可能会导致broker.json文件错误导致broker无法启动
此时需要寻找store文件夹下还原config文件夹中的各个json，一般在启动用户的根目录下

## 配置topic
---
启动完成之后，执行下列语句新增topic
-n后面的地址为broker地址，-b后面的地址在broker启动日志中可以查到，默认都是本地内网IP+10911，-t 后面则是topic名


sh /opt/rocketmq-bin/bin/mqadmin updateTopic -t THIRD_PAY_TOPIC -b 192.168.0.5:10911 -n 127.0.0.1:9876
sh /opt/rocketmq-bin/bin/mqadmin updateTopic -t FILE_MANAGER_TOPIC -b 192.168.0.5:10911 -n 127.0.0.1:9876