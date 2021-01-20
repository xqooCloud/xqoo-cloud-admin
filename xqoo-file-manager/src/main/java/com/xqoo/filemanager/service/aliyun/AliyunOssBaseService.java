package com.xqoo.filemanager.service.aliyun;

import java.util.Map;

public interface AliyunOssBaseService {

    /**
     * 获取阿里云配置参数
     * @return
     */
    Map<String, String> getAliyunOssConfig();
}
