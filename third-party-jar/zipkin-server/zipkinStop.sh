#!/bin/bash

#jar包文件路径及名称（目录按照各自配置）
APP_NAME="${BASE_DIR}/zipkin-server-2.21.7-exec.jar"

pid=`ps -ef|grep $APP_NAME | grep -v grep | awk '{print $2}'`
if [ -z "$pid" ] ; then
        echo "No zipkin-server running."
        exit -1;
fi

echo "The zipkin-server(${pid}) is running..."

kill ${pid}

echo "Send shutdown request to zipkin-server(${pid}) OK"