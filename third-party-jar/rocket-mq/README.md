#rocketMQ使用说明
---
sh脚本仅执行启动，rocketMQ主程序请到官网下载
http://rocketmq.apache.org/release_notes/release-notes-4.8.0/

下载完成后请修改bin/文件夹下runserver.sh和runbroker.sh中的启动参数，默认4g内存偏大
rocketmq不支持jdk9+，如果系统默认jdk环境是jdk9+而且不方便修改系统环境变量，注释runserver.sh和runbroker.sh中的下列代码
[ ! -e "$JAVA_HOME/bin/java" ] && JAVA_HOME=$HOME/jdk/java
[ ! -e "$JAVA_HOME/bin/java" ] && JAVA_HOME=/usr/java
[ ! -e "$JAVA_HOME/bin/java" ] && error_exit "Please set the JAVA_HOME variable in your environment, We need java(x64)!"

然后修改 export JAVA_HOME=/固定jdk路径,
windows 系统修改同名.cmd即可

剩余启动参数以及其他配置参考以下网站
https://www.jianshu.com/p/912701cf1705

另外如果如果rocketMQ在运行中系统重启，很有可能会导致broker.json文件错误导致broker无法启动
此时需要寻找store文件夹下还原config文件夹中的各个json，一般在启动用户的根目录下

##配置topic
---
启动完成之后，执行下列语句新增topic
-n后面的地址为broker地址，-b后面的地址在broker启动日志中可以查到，默认都是本地内网IP+10911，-t 后面则是topic名

sh /opt/rocketmq-bin/bin/mqadmin updateTopic -n 127.0.0.1:9876 -b 192.168.0.6:10911 -t THIRD_PAY_TOPIC
