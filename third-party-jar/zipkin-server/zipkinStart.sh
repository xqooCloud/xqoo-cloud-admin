#!/bin/bash

export BASE_DIR=`cd $(dirname $0)/.; pwd`

#jar包文件路径及名称（目录按照各自配置）
APP_NAME="${BASE_DIR}/zipkin-server-2.21.7-exec.jar"

#日志文件路径及名称（目录按照各自配置）
LOG_FILE="${BASE_DIR}/logs/zipkin-server.log"

MYSQL_SOURCE_PORT=3306
MYSQL_SOURCE_HOST="47.103.200.242"
MYSQL_SOURCE_DB="xqoo_system_zipkin"
MYSQL_SOURCE_USER="root"
MYSQL_SOURCE_USER_PWD="gao2236"


#查询进程，并杀掉当前jar/java程序

pid=`ps -ef|grep $APP_NAME | grep -v grep | awk '{print $2}'`
kill -9 $pid
echo "[$pid] zipkinServer has stoped"

sleep 2
#判断jar包文件是否存在，如果存在启动jar包，并时时查看启动日志

if [ ! -d "${BASE_DIR}/logs" ]; then
  mkdir ${BASE_DIR}/logs
fi

if test -e $APP_NAME
then
echo 'starting zipkinServer application...'

# 启动jar包，指向日志文件，2>&1 & 表示打开或指向同一个日志文件
nohup java -jar $APP_NAME \
  --STORAGE_TYPE=mysql \
  --MYSQL_HOST=$MYSQL_SOURCE_HOST \
  --MYSQL_TCP_PORT=$MYSQL_SOURCE_PORT \
  --MYSQL_DB=$MYSQL_SOURCE_DB \
  --MYSQL_USER=$MYSQL_SOURCE_USER \
  --MYSQL_PASS=$MYSQL_SOURCE_USER_PWD \
 > $LOG_FILE 2>&1 &

#echo '$APP_NAME 启动成功...'
else
echo '$APP_NAME file dose not exists!'
fi
