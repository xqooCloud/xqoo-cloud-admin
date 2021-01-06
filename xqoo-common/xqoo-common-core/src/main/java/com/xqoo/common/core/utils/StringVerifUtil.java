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

}
