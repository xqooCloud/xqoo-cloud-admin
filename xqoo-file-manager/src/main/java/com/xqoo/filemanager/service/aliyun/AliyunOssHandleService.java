package com.xqoo.filemanager.service.aliyun;

import com.xqoo.common.entity.ResultEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author gaoyang
 */
public interface AliyunOssHandleService extends AliyunOssBaseService{

    /**
     * 删除文件
     * @param fileKey
     * @param bucketName
     * @param fileId
     * @return
     */
    ResultEntity<String> removeFile(String fileKey, String bucketName, String fileId);


    /**
     * 处理上传回调
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    Map<String, String> handleUploadCallback(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
