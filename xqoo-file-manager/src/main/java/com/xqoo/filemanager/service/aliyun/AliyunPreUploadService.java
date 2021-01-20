package com.xqoo.filemanager.service.aliyun;

import com.fasterxml.jackson.databind.JsonNode;
import com.xqoo.common.entity.ResultEntity;

/**
 * @author gaoyang
 */
public interface AliyunPreUploadService extends AliyunOssBaseService{

    /**
     * 获取阿里云oss上传签名
     * @param path
     * @return
     */
    ResultEntity<JsonNode> getUploadSign(String path);

    /**
     * 获取阿里云oss文件列表
     */
    ResultEntity<JsonNode> showOssFileList();
}
