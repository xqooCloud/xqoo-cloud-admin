package com.xqoo.common.core.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串内容校验
 * @author gaoyang by 2020-11-21
 */
public class StringVerifUtil {

    /**
     * 不得包含 $%^&+=\~！￥%……&*（）——+{}【】‘；：”“’。，、？
     * @param str
     * @return
     */
    public static boolean isSpecialChar(String str) {
        String regEx = "[ `$%^&+=\\[\\]~！￥%……&*（）——+{}【】‘；：”“’。，、？]|\n|\r|\t";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }

    /**
     * 判断是否手机号
     * @param phoneNumber
     * @return
     */
    public static boolean isPhoneNumber(String phoneNumber){
        if(StringUtils.isEmpty(phoneNumber)){
            return false;
        }
        String regEx = "^1[1-9]\\d{9}$";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(phoneNumber);
        return m.find();
    }

}
