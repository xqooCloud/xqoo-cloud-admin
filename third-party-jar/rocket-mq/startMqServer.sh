#!/bin/bash

export BASE_DIR="/opt/rocketmq-bin"
export LOG_DIR="${BASE_DIR}/logs"

#sh文件路径及名称（目录按照各自配置）
APP_NAME="${BASE_DIR}/bin/mqnamesrv"

if [ ! -d "${LOG_DIR}" ]; then
  mkdir ${LOG_DIR}
fi

nohup sh $APP_NAME > ${LOG_DIR}/mqnamesrv.log 2>&1 &