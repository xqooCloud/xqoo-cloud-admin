package com.xqoo.common.core.utils;

import org.apache.commons.io.IOUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @author mumu
 * @date 2019/7/15 17:05
 * DES加密工具类
 */
public class DESUtil {

    private static String KEY = "EbQT9S2Ou+Lm";
    private static String CODE_TYPE = "UTF-8";

    /**
     * DES加密
     * @param datasource
     * @return
     */
    public static String encrypt(String datasource){
        try{
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(KEY.getBytes(CODE_TYPE));
            //创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            //Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES");
            //用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
            //现在，获取数据并加密
            byte[] temp = Base64.getEncoder().encode(cipher.doFinal(datasource.getBytes()));
            return IOUtils.toString(temp,"UTF-8");
        }catch(Throwable e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * DES解密
     * @return
     */
    public static String decrypt(String src) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom random = new SecureRandom();
        // 创建一个DESKeySpec对象
        DESKeySpec desKey = new DESKeySpec(KEY.getBytes(CODE_TYPE));
        // 创建一个密匙工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        // 将DESKeySpec对象转换成SecretKey对象
        SecretKey securekey = keyFactory.generateSecret(desKey);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, random);
        // 真正开始解密操作
        return IOUtils.toString(cipher.doFinal(Base64.getDecoder().decode(src)),"UTF-8");
    }

}
