package com.xqoo.filemanager.service.aliyun.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.xqoo.common.core.utils.JacksonUtils;
import com.xqoo.common.core.utils.StringUtils;
import com.xqoo.common.dto.SystemCommunicateDTO;
import com.xqoo.common.enums.CommunicateStatusEnum;
import com.xqoo.filemanager.bean.FileConfigPropertiesBean;
import com.xqoo.filemanager.enums.UploadPlatEnum;
import com.xqoo.filemanager.service.aliyun.AliyunOssBaseService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author gaoyang
 */
@Service("aliyunOssBaseService")
public class AliyunOssBaseServiceImpl implements AliyunOssBaseService {

    private final static Logger logger = LoggerFactory.getLogger(AliyunOssBaseServiceImpl.class);

    @Autowired
    @Qualifier("fileConfigPropertiesBean")
    private FileConfigPropertiesBean fileConfigPropertiesBean;

    @Override
    public Map<String, String> getAliyunOssConfig() {
        return fileConfigPropertiesBean.getFileManagerConfigBean().getOrDefault(UploadPlatEnum.ALI.getKey(), Collections.emptyMap());
    }

    @Override
    public SystemCommunicateDTO<Map<String, String>> getUploadFileSign(String accessKey, String accessSecret,
                                                            String endpoint, String dirPath, String host, String callbackUrl, long expire){
        OSS client = new OSSClientBuilder().build(endpoint, accessKey, accessSecret);
        try {
            long expireEndTime = System.currentTimeMillis() + expire * 1000;
            Date expiration = new Date(expireEndTime);
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dirPath);

            String postPolicy = client.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes(StandardCharsets.UTF_8.name());
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = client.calculatePostSignature(postPolicy);

            Map<String, String> respMap = new LinkedHashMap<String, String>();
            respMap.put("accessid", accessKey);
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);
            respMap.put("dir", dirPath);
            respMap.put("host", host);
            respMap.put("expire", String.valueOf(expireEndTime / 1000));

            ObjectNode jasonCallback = JacksonUtils.createObjectNode();
            jasonCallback.put("callbackUrl", callbackUrl);
            jasonCallback.put("callbackBody",
                    "filename=${object}&size=${size}&etag=${etag}&bucket=${bucket}&mimeType=${mimeType}" +
                            "&height=${imageInfo.height}&width=${imageInfo.width}&fileUid=${x:uid_var}" +
                            "&cacheData=${x:cache_var}&fileId=${x:file_id_var}&filePartType=${x:file_part_type}" +
                            "&filePartNo=${x:file_part_no}&filePartFinish=${x:file_part_last}&accessType=${x:access_type}"
            );
            jasonCallback.put("callbackBodyType", "application/json");
            String base64CallbackBody = BinaryUtil.toBase64String(jasonCallback.toString().getBytes());
            respMap.put("callback", base64CallbackBody);
            return new SystemCommunicateDTO<>(CommunicateStatusEnum.SUCCESS, "ok" , respMap);

        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // Assert.fail(e.getMessage());
            logger.warn("[文件模块-aliyunOss]生成上传签名出错，出错原因：{}，出错信息：{}",
                    e.getClass().getSimpleName(), e.getMessage());
            return new SystemCommunicateDTO<>(CommunicateStatusEnum.FAIL, "获取签名失败，发生异常");
        }
    }

    @Override
    public boolean existsBucketName(String accessKey, String accessSecret, String endpoint, String bucketName) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKey, accessSecret);

        boolean exists = ossClient.doesBucketExist(bucketName);
        ossClient.shutdown();
        return exists;
    }

    @Override
    public void removeOssFile(String accessKey, String accessSecret, String endpoint, String bucketName, String fileObject) {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKey, accessSecret);
        boolean found = ossClient.doesObjectExist(bucketName, fileObject, true);
        if(found){
            ossClient.deleteObject(bucketName, fileObject);
        }
        // 删除文件。如需删除文件夹，请将ObjectName设置为对应的文件夹名称。如果文件夹非空，则需要将文件夹下的所有object删除后才能删除该文件夹。

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    @Override
    public void uploadFileByInputStream(String accessKey, String accessSecret, String endpoint, String bucketName, String fileObjectName, byte[] data) {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKey, accessSecret);
        ossClient.putObject(bucketName, fileObjectName, new ByteArrayInputStream(data));
        ossClient.shutdown();
    }

    /**
     * 验证上传回调的Request
     *
     * @param request
     * @param ossCallbackBody
     * @return
     * @throws NumberFormatException
     * @throws IOException
     */
    protected boolean VerifyOSSCallbackRequest(HttpServletRequest request, String ossCallbackBody)
            throws NumberFormatException, IOException {
        boolean ret = false;
        String autorizationInput = new String(request.getHeader("Authorization"));
        String pubKeyInput = request.getHeader("x-oss-pub-key-url");
        byte[] authorization = BinaryUtil.fromBase64String(autorizationInput);
        byte[] pubKey = BinaryUtil.fromBase64String(pubKeyInput);
        String pubKeyAddr = new String(pubKey);
        if (!pubKeyAddr.startsWith("http://gosspublic.alicdn.com/")
                && !pubKeyAddr.startsWith("https://gosspublic.alicdn.com/")) {
            logger.warn("[文件模块-aliyunOss]pub key addr must be oss address");
            return false;
        }
        String retString = executeGet(pubKeyAddr);
        if(StringUtils.isEmpty(retString)){
            return false;
        }
        retString = retString.replace("-----BEGIN PUBLIC KEY-----", "");
        retString = retString.replace("-----END PUBLIC KEY-----", "");
        String queryString = request.getQueryString();
        String uri = request.getRequestURI();
        String authStr = java.net.URLDecoder.decode(uri, "UTF-8");
        if (queryString != null && !"".equals(queryString)) {
            authStr += "?" + queryString;
        }
        authStr += "\n" + ossCallbackBody;
        ret = doCheck(authStr, authorization, retString);
        return ret;
    }

    /**
     * 获取public key
     *
     * @param url
     * @return
     */
    public String executeGet(String url) {
        BufferedReader in = null;

        String content = null;
        try {
            //配置OkHttp
            OkHttpClient client = new OkHttpClient.Builder().build();
            Request.Builder requestBuilder = new Request.Builder();
            //配置url
            requestBuilder.url(url);
            //配置请求方式
            requestBuilder.get();
            Response response = client.newCall(requestBuilder.build()).execute();
            assert response.body() != null;
            in = new BufferedReader(new InputStreamReader(response.body().byteStream()));
            StringBuilder sb = new StringBuilder("");
            String line = "";
            String nL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line).append(nL);
            }
            in.close();
            content = sb.toString();
        } catch (Exception e) {
            logger.error("[文件模块-aliyunOss]获取断oss上传签名publicKey发生错误，错误原因:{},错误信息:{}",
                    e.getClass().getSimpleName(), e.getMessage());
        } finally {
            if (in != null) {
                try {
                    in.close();// 最后要关闭BufferedReader
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return content;
    }

    /**
     * 验证RSA
     *
     * @param content
     * @param sign
     * @param publicKey
     * @return
     */
    public static boolean doCheck(String content, byte[] sign, String publicKey) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] encodedKey = BinaryUtil.fromBase64String(publicKey);
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
            java.security.Signature signature = java.security.Signature.getInstance("MD5withRSA");
            signature.initVerify(pubKey);
            signature.update(content.getBytes());
            return signature.verify(sign);
        } catch (Exception e) {
            logger.error("[文件模块-aliyunOss]验证上传签名rsa发生异常，异常原因：{}，异常信息:{}",
                    e.getClass().getSimpleName(), e.getMessage());
            return false;
        }
    }


    /**
     * 获取Post消息体
     *
     * @param is
     * @param contentLen
     * @return
     */
    public String GetPostBody(InputStream is, int contentLen) {
        if (contentLen > 0) {
            int readLen = 0;
            int readLengthThisTime = 0;
            byte[] message = new byte[contentLen];
            try {
                while (readLen != contentLen) {
                    readLengthThisTime = is.read(message, readLen, contentLen - readLen);
                    // Should not happen.
                    if (readLengthThisTime == -1) {
                        break;
                    }
                    readLen += readLengthThisTime;
                }
                return new String(message);
            } catch (IOException e) {
            }
        }
        return "";
    }

}
