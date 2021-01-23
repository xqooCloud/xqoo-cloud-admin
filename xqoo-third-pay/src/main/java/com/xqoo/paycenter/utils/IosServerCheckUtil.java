package com.xqoo.paycenter.utils;

import javax.net.ssl.*;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Locale;

/**
 * @ClassName: IosServerCheckUtil
 * @Description: 苹果IAP内购验证工具类
 * @Author: liangyongpeng
 * @Date: 2020/4/29 15:50
 **/
public class IosServerCheckUtil {

    /**
     * X509TrustManager 证书信任管理器
     */
    private static class TrustAnyTrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String authType) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String authType) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[]{};
        }
    }


    private static class TrustAnyHostnameVerifier implements HostnameVerifier {

        /**
         * verify 在构建https请求时防止被攻击验证
         */
        @Override
        public boolean verify(String s, SSLSession sslSession) {
            return false;
        }
    }

    /**
     * 苹果服务器验证
     *
     * @param receipt
     *            账单
     * @url 要验证的地址
     * @return null 或返回结果 沙盒 https://sandbox.itunes.apple.com/verifyReceipt
     *
     */
    public static String buyAppCheck(String receipt,String url) {

        try {
            SSLContext ssl = SSLContext.getInstance("SSL");
            ssl.init(null, new TrustManager[] { new TrustAnyTrustManager() }, new java.security.SecureRandom());
            // 通过url类我们可也让访问网站资源就像访问本地资源一样方便快捷,进而对该该引用进行操作，在操作之前我们应该打开这些资源与内容的链接
            URL console = new URL(url);

            HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
            conn.setSSLSocketFactory(ssl.getSocketFactory());
            conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
            conn.setRequestMethod("POST");
            conn.setRequestProperty("content-type", "text/json");
            conn.setRequestProperty("Proxy-Connection", "Keep-Alive");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            BufferedOutputStream hurlBufOus = new BufferedOutputStream(conn.getOutputStream());

            //拼成固定的格式传给平台
            String str = String.format(Locale.CHINA, "{\"receipt-data\":\"" + receipt + "\"}");
            hurlBufOus.write(str.getBytes());
            hurlBufOus.flush();

            InputStream is = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line = null;
            StringBuffer sb = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            return sb.toString();
        } catch (Exception e) {
            System.out.println("苹果服务器异常");
            e.printStackTrace();
        }

        return null;
    }

}
