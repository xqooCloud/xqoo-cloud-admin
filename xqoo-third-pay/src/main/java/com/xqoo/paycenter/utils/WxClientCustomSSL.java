package com.xqoo.paycenter.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;

public class WxClientCustomSSL {
    private static final Logger logger = LoggerFactory.getLogger(WxClientCustomSSL.class);

    public static String doRefund(String url,String data,String sslPassword,String sslPath) throws Exception {
        /**
         * 注意PKCS12证书 是从微信商户平台-》账户设置-》 API安全 中下载的
         */

        logger.info(sslPath);
//        logger.info("[微信退款证书]：获取微信退款证书地址："+ClientCustomSSL.class.getResource("/").getPath()+"apiclient_cert.p12" + sslPath);
        KeyStore keyStore  = KeyStore.getInstance("PKCS12");
        FileInputStream instream = new FileInputStream(new File(sslPath));//P12文件目录
        try {
            /**
             * 此处要改
             * */
            keyStore.load(instream, sslPassword.toCharArray());//这里写密码..默认是你的MCHID
        } finally {
            instream.close();
        }

        // Trust own CA and all self-signed certs
        /**
         * 此处要改
         * */
        SSLContext sslcontext = SSLContexts.custom()
                .loadKeyMaterial(keyStore, sslPassword.toCharArray())//这里也是写密码的
                .build();
        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[] { "TLSv1" },
                null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .build();
        try {
            logger.info("[微信退款证书]：开始执行请求----------------" );
            HttpPost httpost = new HttpPost(url); // 设置响应头信息
            httpost.addHeader("Connection", "keep-alive");
            httpost.addHeader("Accept", "*/*");
            httpost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            httpost.addHeader("Host", "api.mch.weixin.qq.com");
            httpost.addHeader("X-Requested-With", "XMLHttpRequest");
            httpost.addHeader("Cache-Control", "max-age=0");
            httpost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
            httpost.setEntity(new StringEntity(data, "UTF-8"));
            CloseableHttpResponse response = httpclient.execute(httpost);
            logger.info("[微信退款证书]：请求结束，获取response----------------" );
            try {
                HttpEntity entity = response.getEntity();

                String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
                EntityUtils.consume(entity);
                logger.info("[微信退款证书]：获得返回xml,转换为json----------------" + jsonStr);
                return jsonStr;
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }

}
