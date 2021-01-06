package com.xqoo;

import com.xqoo.client.rocketmq.listener.ExampleMqListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * FileName: ApplicationClient.java
 * springBoot启动项文件，@EnableFeignClients注解为每个应用服务必须注解
 * @author 高扬
 * @Date   2019-11-09
 * @version 1.00

 */

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class ApplicationClient {
    public static void main(String[] args){
        SpringApplication sa = new SpringApplication(ApplicationClient.class);
        // 增加启动消息消费监听器代码，多个需要addListeners
        sa.addListeners(new ExampleMqListener());
        sa.run(args);
    }
}
