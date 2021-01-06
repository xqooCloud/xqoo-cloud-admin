package com.xqoo.common.core.utils;

import com.xqoo.common.core.exception.SystemException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

public class AESUtil {


    private static final String TYPE = "AES";

    private static final String SECURERANDOM_KEY = "AES/ECB/PKCS7Padding";

    static {
        //如果是PKCS7Padding填充方式，则必须加上下面这行
        Security.addProvider(new BouncyCastleProvider());
    }
    /**
     * 解密
     *
     * @param content   密文
     * @param key 加密密码
     * @return String
     * @throws Exception 异常
     */
    public static String decode(String content, String key) throws Exception {
        SecretKeySpec secretKey = getKey(key);
//        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), TYPE);
        Cipher cipher = Cipher.getInstance(SECURERANDOM_KEY, "BC");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] c = decoder.decodeBuffer(content);
        byte[] result = cipher.doFinal(c);
        return new String(result, StandardCharsets.UTF_8);
    }

    /**
     * 加密
     *
     * @param content      原文
     * @param key 加密密码
     * @return String
     * @throws Exception 异常
     */
    public static String encode(String content, String key) throws Exception {
        SecretKeySpec secretKey = getKey(key);
//        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), TYPE);
        Cipher cipher = Cipher.getInstance(SECURERANDOM_KEY, "BC");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] p = content.getBytes(StandardCharsets.UTF_8);
        byte[] result = cipher.doFinal(p);
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(result);
    }


    public static SecretKeySpec getKey(String keySeed) throws NoSuchAlgorithmException {
        if (keySeed == null || keySeed.trim().length() != 16) {
            throw new SystemException("AES秘钥长度不足16位");
        };
        return new SecretKeySpec(keySeed.getBytes(StandardCharsets.UTF_8) , TYPE);

    }

}
