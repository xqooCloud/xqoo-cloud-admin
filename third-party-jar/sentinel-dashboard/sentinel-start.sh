#!/bin/bash

export BASE_DIR=`cd $(dirname $0)/.; pwd`

#jar包文件路径及名称（目录按照各自配置）
APP_NAME="${BASE_DIR}/sentinel-dashboard.jar"

#日志文件路径及名称（目录按照各自配置）
LOG_FILE="${BASE_DIR}/logs/sentinel-dashboard.log"

APP_PORT=8718
APP_CONF="${BASE_DIR}/config/application.properties"
APP_SERVER="127.0.0.1:$APP_PORT"
APP_CONTENT_NAME="sentinel-dashboard"
APP_AUTH_USER="sentinel"
APP_AUTH_PWD="!@#123qwe"

#查询进程，并杀掉当前jar/java程序

pid=`ps -ef|grep $APP_NAME | grep -v grep | awk '{print $2}'`
kill -9 $pid
echo "[$pid] sentinel-dashboard has stoped"

sleep 2
#判断jar包文件是否存在，如果存在启动jar包，并时时查看启动日志

if [ ! -d "${BASE_DIR}/logs" ]; then
  mkdir ${BASE_DIR}/logs
fi

if test -e $APP_NAME
then
echo 'starting sentinel-dashboard application...'

# 启动jar包，指向日志文件，2>&1 & 表示打开或指向同一个日志文件
nohup java -Dserver.port=$APP_PORT \
-Dspring.config.location=$APP_CONF \
-Dcsp.sentinel.dashboard.server=$APP_SERVER \
-Dproject.name=$APP_CONTENT_NAME \
-Dsentinel.dashboard.auth.username=$APP_AUTH_USER \
-Dsentinel.dashboard.auth.password=$APP_AUTH_PWD \
-jar $APP_NAME > $LOG_FILE 2>&1 &

#echo '$APP_NAME 启动成功...'
else
echo '$APP_NAME file dose not exists!'
fi
