package com.xqoo.filemanager.service.aliyun.impl;

import cn.hutool.core.collection.CollUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.xqoo.common.core.utils.JacksonUtils;
import com.xqoo.common.core.utils.StringUtils;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.filemanager.service.aliyun.AliyunPreUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gaoyang
 */
@Service("aliyunPreUploadService")
public class AliyunPreUploadServiceImpl extends AliyunOssBaseServiceImpl implements AliyunPreUploadService {

    private final static Logger logger = LoggerFactory.getLogger(AliyunPreUploadServiceImpl.class);

    @Override
    public ResultEntity<JsonNode> getUploadSign(String path) {
        Map<String, String> configMap = super.getAliyunOssConfig();
        if(CollUtil.isEmpty(configMap)){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "未找到相应配置参数，生成签名失败");
        }
        String accessId = configMap.getOrDefault("accessKey", "");
        String accessKey = configMap.getOrDefault("accessSecret", "");
        String endpoint = configMap.getOrDefault("endpoint", "");
        String bucket = configMap.getOrDefault("bucketName", "");
        String host = "http://" + bucket + "." + endpoint;
        // callbackUrl为 上传回调服务器的URL，请将下面的IP和Port配置为您自己的真实信息。
        String callbackUrl = configMap.getOrDefault("callbackUrl", "");
        String dir = StringUtils.isNotEmpty(path) ? path : configMap.getOrDefault("defaultPath", "");

        OSSClient client = new OSSClient(endpoint, accessId, accessKey);
        try {
            long expireTime = 30;
            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            Date expiration = new Date(expireEndTime);
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

            String postPolicy = client.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = client.calculatePostSignature(postPolicy);

            Map<String, String> respMap = new LinkedHashMap<String, String>();
            respMap.put("accessid", accessId);
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);
            respMap.put("dir", dir);
            respMap.put("host", host);
            respMap.put("expire", String.valueOf(expireEndTime / 1000));
            // respMap.put("expire", formatISO8601Date(expiration));

            ObjectNode jasonCallback = JacksonUtils.createObjectNode();
            jasonCallback.put("callbackUrl", callbackUrl);
            jasonCallback.put("callbackBody",
                    "filename=${object}&size=${size}&mimeType=${mimeType}&height=${imageInfo.height}&width=${imageInfo.width}");
            jasonCallback.put("callbackBodyType", "application/x-www-form-urlencoded");
            String base64CallbackBody = BinaryUtil.toBase64String(jasonCallback.toString().getBytes());
            respMap.put("callback", base64CallbackBody);

            JsonNode ja1 = JacksonUtils.transferToJsonNode(respMap);
            // System.out.println(ja1.toString());
            return new ResultEntity<>(HttpStatus.OK, "ok", ja1);

        } catch (Exception e) {
            // Assert.fail(e.getMessage());
            logger.warn("[文件模块-aliyunOss]生成上传签名出错，出错原因：{}，出错信息：{}",
                    e.getClass().getSimpleName(), e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "生成签名失败，请联系系统运维人员处理");
        }
    }

    @Override
    public ResultEntity<JsonNode> showOssFileList() {
        Map<String, String> configMap = super.getAliyunOssConfig();
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String accessKey = configMap.getOrDefault("accessKey", "");
        String accessSecret = configMap.getOrDefault("accessSecret", "");
        String endpoint = configMap.getOrDefault("endpoint", "");
        String bucketName = configMap.getOrDefault("bucketName", "");

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKey, accessSecret);
        // 列举文件。如果不设置KeyPrefix，则列举存储空间下的所有文件。如果设置KeyPrefix，则列举包含指定前缀的文件。
        ListObjectsV2Result result = ossClient.listObjectsV2(bucketName);
        List<OSSObjectSummary> ossObjectSummaries = result.getObjectSummaries();
        // 关闭OSSClient。
        ossClient.shutdown();
        return new ResultEntity<>(HttpStatus.OK, "ok", JacksonUtils.transferToJsonNode(ossObjectSummaries));
    }
}
