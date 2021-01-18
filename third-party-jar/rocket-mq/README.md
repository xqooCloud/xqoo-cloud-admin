# rocketMQ
rocketMQ请到官方下载 [中科大镜像源-4.8.0版本](https://mirrors.tuna.tsinghua.edu.cn/apache/rocketmq/4.8.0/rocketmq-all-4.8.0-bin-release.zip),
下载完成后请解压任意目录，如不想更改启动脚本则解压到/opt/rocketmq-bin/目录下(不含外层rocketmq-all-4.8.0-bin-release文件夹，仅文件夹内的内容，windows系统自行修改目录)下，修改runserver.sh和runbroker.sh中jvm内存参数(源码中内存占用过大)，然后先运行startMqServer.sh，再运行startMqBroker.sh
## server
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

sh /opt/rocketmq-bin/bin/mqadmin updateTopic -n 127.0.0.1:9876 -b 192.168.0.6:10911 -t THIRD_PAY_TOPIC
