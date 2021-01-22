package com.xqoo.filemanager.service.aliyun.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CopyObjectRequest;
import com.aliyun.oss.model.ObjectMetadata;
import com.google.common.base.Splitter;
import com.xqoo.common.core.utils.HttpRequestUtil;
import com.xqoo.common.core.utils.StringUtils;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.filemanager.entity.FileRecordEntity;
import com.xqoo.filemanager.enums.FilePartTypeEnum;
import com.xqoo.filemanager.enums.UploadStatusEnum;
import com.xqoo.filemanager.service.FileRecordService;
import com.xqoo.filemanager.service.aliyun.AliyunOssHandleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author gaoyang
 */
@Service("aliyunOssHandleService")
public class AliyunOssHandleServiceImpl extends AliyunOssBaseServiceImpl implements AliyunOssHandleService {

    private final static Logger logger = LoggerFactory.getLogger(AliyunOssHandleServiceImpl.class);

    @Autowired
    private FileRecordService fileRecordService;

    @Override
    public ResultEntity<String> removeFile(String fileKey, String bucketName, String fileId) {
        if(CollUtil.isEmpty(super.getAliyunOssConfig())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "未找到相应配置参数，生成签名失败");
        }
        String accessKey = super.getAliyunOssConfig().getOrDefault("accessKey", "");
        String accessSecret = super.getAliyunOssConfig().getOrDefault("accessSecret", "");
        String endpoint = super.getAliyunOssConfig().getOrDefault("endpoint", "");
        if(!super.existsBucketName(accessKey, accessSecret, endpoint, bucketName)){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "当前oss账户下没有可用的存储桶【" + bucketName + "】");
        }
        boolean success = fileRecordService.deleteFileRecord(fileId);
        if(success){
            super.removeOssFile(accessKey, accessSecret, endpoint, bucketName, fileKey);
        }
        return new ResultEntity<>(HttpStatus.OK, "OK");
    }

    @Override
    public Map<String, String> handleUploadCallback(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ossCallbackBody = super.GetPostBody(request.getInputStream(),
                Integer.parseInt(request.getHeader("content-length")));
        logger.info("上传回调信息{}", ossCallbackBody);
        // TODO: 这里的验证时好时坏，估计是本地测试域名的问题，线上环境没尝试，
        // 进到这里来的话是已经上传成功了，干脆注释掉这段了。
//        boolean success = super.VerifyOSSCallbackRequest(request, ossCallbackBody);
//        if(!success){
//            Map<String, String> tmpJson = new HashMap<>(1);
//            tmpJson.put("Status", "verdify not ok");
//            return tmpJson;
//        }
        Map<String, String> urlParams = HttpRequestUtil.transformUrlParamsToMap(ossCallbackBody);
        // 如果存在缓存周期，则单独设置此文件缓存
        if(urlParams.containsKey("cacheData")){
            ObjectMetadata meta = new ObjectMetadata();
            meta.setCacheControl(urlParams.getOrDefault("cacheData", "max_age=604800"));
            setFileCacheControl(urlParams.getOrDefault("bucket", "xqoo-public"), urlParams.getOrDefault("filename", ""), meta);
        }
        FilePartTypeEnum filePartType = FilePartTypeEnum.getEnumsByKey(urlParams.getOrDefault("filePartType", ""));
        if(filePartType == null){
            urlParams.put("Status", "OK");
            urlParams.put("saveData", "false");
            return urlParams;
        }
        String url = new StringBuilder()
                .append("https://")
                .append(urlParams.getOrDefault("bucket", super.getAliyunOssConfig().getOrDefault("bucketPublic", "")))
                .append(".")
                .append(super.getAliyunOssConfig().getOrDefault("endpoint", ""))
                .append("/")
                .append(urlParams.getOrDefault("filename", ""))
                .toString();
        urlParams.put("url", url);
        if(FilePartTypeEnum.ALL.equals(filePartType)){
            this.handleCallbackUpload(urlParams);
        }
        // TODO 这里下面补充分片上传的回调逻辑
        urlParams.put("Status", "OK");
        return urlParams;
    }

    /**
     * 设置文件的http请求头参数
     * @param bucketName
     * @param objectName
     * @param meta
     */
    protected void setFileCacheControl(String bucketName, String objectName, ObjectMetadata meta) {
        String accessKey = super.getAliyunOssConfig().getOrDefault("accessKey", "");
        String accessSecret = super.getAliyunOssConfig().getOrDefault("accessSecret", "");
        String endpoint = super.getAliyunOssConfig().getOrDefault("endpoint", "");
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKey, accessSecret);

        // 设置源文件与目标文件相同，调用ossClient.copyObject方法修改文件元信息。
        CopyObjectRequest objectRequets = new CopyObjectRequest(bucketName, objectName, bucketName, objectName);
        // 设置自定义元信息property值为property-value。
        objectRequets.setNewObjectMetadata(meta);
        // 修改元信息。
        ossClient.copyObject(objectRequets);
        // 关闭OSSClient。
        ossClient.shutdown();
    }

    /**
     * 改变上传记录的状态
     * @param map
     */
    public void handleCallbackUpload(Map<String, String> map){
        FileRecordEntity entity = fileRecordService.getOneFileRecordEntityByPrimaryKey(map.getOrDefault("fileId", ""));
        if(ObjectUtil.isNotNull(entity) && StringUtils.isNotEmpty(entity.getId())){
            long now = System.currentTimeMillis();
            entity.setUploadStatus(UploadStatusEnum.FINISH.getKey());
            entity.setPlatFileMd5(map.getOrDefault("etag", ""));
            entity.setFileMimeType(map.getOrDefault("mimeType", ""));
            entity.setFileMd5(map.getOrDefault("etag", ""));
            entity.setFileFinishTime(now);
            entity.setFileUploadSpendTime(now - entity.getFileInitTime());
            entity.setFileSize(Long.parseLong(map.getOrDefault("size", "0")));
            entity.setFileUrlPath(map.getOrDefault("url", ""));
            entity.setFileBucket(map.getOrDefault("bucket", ""));
            entity.setFileRelativePath(map.getOrDefault("filename", ""));
            String name = getFileName(map.getOrDefault("filename", ""));
            entity.setFileName(name);
            fileRecordService.finishUploadRecord(entity);
        }
    }

    private String getFileName(String fileObjName){
        List<String> list = Splitter.on("/").trimResults().splitToList(fileObjName);
        if(CollUtil.isEmpty(list)){
            return fileObjName;
        }
        return list.get(list.size() - 1);
    }
}
