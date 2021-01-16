#!/bin/bash

export BASE_DIR="/opt/rocketmq-bin"
export LOG_DIR="${BASE_DIR}/logs"

#sh文件路径及名称（目录按照各自配置）
APP_NAME="${BASE_DIR}/bin/mqbroker"

if [ ! -d "${LOG_DIR}" ]; then
  mkdir ${LOG_DIR}
fi

nohup sh $APP_NAME -n 127.0.0.1:9876 autoCreateTopicEnable=true > $LOG_DIR/broker.log 2>&1 &