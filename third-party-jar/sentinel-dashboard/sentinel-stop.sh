#!/bin/bash
export BASE_DIR=`cd $(dirname $0)/.; pwd`
#jar包文件路径及名称（目录按照各自配置）
APP_NAME="${BASE_DIR}/sentinel-dashboard.jar"

pid=`ps -ef|grep $APP_NAME | grep -v grep | awk '{print $2}'`
if [ -z "$pid" ] ; then
        echo "No sentinel-dashboard running."
        exit -1;
fi

echo "The sentinel-dashboard(${pid}) is running..."

kill ${pid}

echo "Send shutdown request to sentinel-dashboard(${pid}) OK"