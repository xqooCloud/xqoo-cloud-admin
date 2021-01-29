package com.xqoo.filemanager.service.aliyun;

import com.google.common.collect.HashMultimap;
import com.xqoo.common.entity.ResultEntity;
import org.springframework.web.multipart.MultipartFile;

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
     * 批量删除文件
     * @param multimap
     * @return
     */
    Boolean removeFileBatch(HashMultimap<String, String> multimap);


    /**
     * 处理上传回调
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    Map<String, String> handleUploadCallback(HttpServletRequest request, HttpServletResponse response) throws IOException;


    /**
     * 上传文件
     * @param file
     * @param path
     * @param isProtected
     * @return
     */
    String uploadFileByMultipartFile(MultipartFile file, String path, Boolean isProtected);
}
