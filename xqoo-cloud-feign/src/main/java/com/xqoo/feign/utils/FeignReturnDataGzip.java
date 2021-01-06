package com.xqoo.feign.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.xqoo.common.core.utils.JacksonUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 消费接口工具类
 *
 * @author 高扬
 * @since 2019-12-08 12:03
 */
public class FeignReturnDataGzip {

    /**
     * 解压消费接口Gzip压缩的Byte数据
     *
     * @param zipData 消费接口压缩数据
     * @param returnClass 需要返回的类型
     * @param <T>
     * @return
     */
    public static  <T> T Unzip(byte[] zipData, Class<T> returnClass){
        if(zipData == null){
            return null;
        }
        return JacksonUtils.toObj(zipData, returnClass);
    }

    /**
     * 解压消费接口Gzip压缩的List型Byte数据
     *
     * @param zipData 消费接口压缩数据
     * @param returnClass 需要返回的类型
     * @param <T>
     * @return
     */
    public static <T> List<T> UnzipList(byte[] zipData, Class<T> returnClass){
        if(zipData == null){
            return null;
        }
        JsonNode jsonNode = JacksonUtils.toObj(zipData, JsonNode.class);
        List<T> rtnList = new ArrayList<>(jsonNode.size());
        jsonNode.forEach(item -> {
            T t = JacksonUtils.toObj(item, new TypeReference<T>() {
                @Override
                public Type getType() {
                    return super.getType();
                }
            });
            rtnList.add(t);
        });
        return rtnList;
    }
}
