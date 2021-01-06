package com.xqoo;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * FileName: ApplicationUidGerenator.java
 * 百度uidgenerator工具
 * @author 高扬
 * @Date   2019-11-13
 * @version 1.00

 */

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan("com.xqoo.baidu.fsg.uid.worker.dao")
public class ApplicationUidGerenator {
    public static void main(String[] args){
        SpringApplication.run(ApplicationUidGerenator.class, args);
    }
}
