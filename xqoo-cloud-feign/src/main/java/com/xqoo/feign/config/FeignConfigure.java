package com.xqoo.feign.config;

import feign.Request;
import feign.Retryer;
import feign.codec.Decoder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 消费中心配置
 */
@Configuration
public class FeignConfigure {
    private final static long connectTimeOutMillis = 120;//超时时间 s
    private final static long readTimeOutMillis = 120;

    /**
     * 设置超时时间
     * @return
     */
    @Bean
    public Request.Options options() {
        return new Request.Options(connectTimeOutMillis, TimeUnit.SECONDS, readTimeOutMillis, TimeUnit.SECONDS, true);
    }

    /**
     * 重试次数，2次
     * @return
     */
    @Bean
    public Retryer feignRetryer() {
        return new Retryer.Default(200, 1000, 2);
    }

    /**
     * 增加转码识别，不加会报错
     * @return
     */
    @Bean
    public Decoder feignDecoder() {
        return new ResponseEntityDecoder(new SpringDecoder(feignHttpMessageConverter()));
    }

    public ObjectFactory<HttpMessageConverters> feignHttpMessageConverter() {
        final HttpMessageConverters httpMessageConverters = new HttpMessageConverters(new GateWayMappingJackson2HttpMessageConverter());
        return new ObjectFactory<HttpMessageConverters>() {
            @Override
            public HttpMessageConverters getObject() throws BeansException {
                return httpMessageConverters;
            }
        };
    }

    public class GateWayMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
        GateWayMappingJackson2HttpMessageConverter(){
            List<MediaType> mediaTypes = new ArrayList<>();
            mediaTypes.add(MediaType.valueOf(MediaType.TEXT_HTML_VALUE + ";charset=UTF-8"));
            setSupportedMediaTypes(mediaTypes);
        }
    }
}
