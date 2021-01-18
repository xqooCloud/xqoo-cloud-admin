#!/bin/bash

export BASE_DIR="/opt/rocketmq-bin"

#jar包文件路径及名称（目录按照各自配置）
APP_NAME="${BASE_DIR}/rocketmq-console-ng.jar"

pid=`ps -ef|grep $APP_NAME | grep -v grep | awk '{print $2}'`
if [ -z "$pid" ] ; then
        echo "No rocketmq-console running."
        exit -1;
fi

echo "The rocketmq-console(${pid}) is running..."

kill ${pid}

echo "Send shutdown request to rocketmq-console(${pid}) OK"