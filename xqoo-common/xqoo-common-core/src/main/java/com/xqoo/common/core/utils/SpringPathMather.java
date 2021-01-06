package com.xqoo.common.core.utils;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.List;

/**
 * @author gaoyang by 2020-11-22
 * 通配符比较
 * spring自带工具包
 */
public class SpringPathMather {

    /**
     * spring通配符比较工具
     * @param List<String> pattern 比较公式，*代表任意
     * @param path 实际字符串值
     * @return 返回path是否符合patter的格式
     */
    public static boolean MatchPath(List<String> pattern, String path){
        for (String s : pattern) {
            if(MatchPath(s, path)){
                return true;
            }
        }
        return false;
    }

    /**
     * spring通配符比较工具
     * @param pattern 比较公式，*代表任意
     * @param path 实际字符串值
     * @return 返回path是否符合patter的格式
     */
    public static boolean MatchPath(String pattern, String path){
        PathMatcher pm = new AntPathMatcher();
        return pm.matchStart(pattern, path);
    }

    /**
     * 判断是否全部是 ** 字符
     * @param str
     * @return
     */
    public static boolean isAllMatch(String str){
        if(StringUtils.isEmpty(str)){
            return false;
        }
        char[] chars = str.toCharArray();
        int count = 0;
        for (char c : chars) {
            if(c == '*'){
                count ++;
            }
        }
        if(count == str.length()){
            return true;
        }
        return false;
    }

}
