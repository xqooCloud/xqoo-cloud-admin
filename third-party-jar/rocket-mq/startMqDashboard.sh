#!/bin/bash

export BASE_DIR="/opt/rocketmq-bin"

#jar包文件路径及名称（目录按照各自配置）
APP_NAME="${BASE_DIR}/rocketmq-console-ng.jar"

#日志文件路径及名称（目录按照各自配置）
LOG_FILE="${BASE_DIR}/logs/rocketmq-console.log"

#查询进程，并杀掉当前jar/java程序

pid=`ps -ef|grep $APP_NAME | grep -v grep | awk '{print $2}'`
kill -9 $pid
echo "[$pid] rocketmq-console has stoped"

sleep 2
#判断jar包文件是否存在，如果存在启动jar包，并时时查看启动日志

if [ ! -d "${BASE_DIR}/logs" ]; then
  mkdir ${BASE_DIR}/logs
fi

if test -e $APP_NAME
then
echo 'starting rocketmq-console application...'

# 启动jar包，指向日志文件，2>&1 & 表示打开或指向同一个日志文件
nohup java -jar $APP_NAME > $LOG_FILE 2>&1 &

#echo '$APP_NAME 启动成功...'
else
echo '$APP_NAME file dose not exists!'
fi
