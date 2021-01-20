package com.xqoo.filemanager.service.aliyun.impl;

import com.aliyun.oss.common.utils.BinaryUtil;
import com.xqoo.common.core.utils.StringUtils;
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

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Collections;
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

}
