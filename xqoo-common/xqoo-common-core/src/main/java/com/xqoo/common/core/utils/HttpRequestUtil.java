package com.xqoo.common.core.utils;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import java.util.*;

/**
 * @author gaoyang
 * http 请求工具类
 */
public class HttpRequestUtil {

    /**
     * 将url参数转换成map
     * @param param aa=11&bb=22&cc=33
     * @return
     */
    public static Map<String, String> transformUrlParamsToMap(String param) {
        if (StringUtils.isBlank(param)) {
            return Collections.emptyMap();
        }
        Map<String, String> map = new LinkedHashMap<>();
        List<String> params = Splitter.on("&").trimResults().splitToList(param);
        params.forEach(item -> {
            List<String> p = Splitter.on("=").trimResults().splitToList(item);
            if (p.size() == 2 && StringUtils.isNotEmpty(p.get(1))) {
                String value = p.get(1);
                if(value.indexOf("\"") == 0){
                    value = value.substring(1);
                }
                if(value.indexOf("\"") == value.length() - 1){
                    value = value.substring(0, value.length() - 1);
                }
                map.put(p.get(0), Encodes.urlDecode(value));
            }
        });
        return map;
    }

    /**
     * 将map转换成url
     * @param map
     * @return
     */
    public static String transformMapToUrlParams(Map<String, Object> map) {
        if (map == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        List<String> result = new ArrayList<>();
        map.forEach((key, value) -> {
            result.add(sb.append(key)
                    .append("=")
                    .append(value)
                    .append("&").toString());
        });
        return Joiner.on("&").skipNulls().join(result);
    }

}
