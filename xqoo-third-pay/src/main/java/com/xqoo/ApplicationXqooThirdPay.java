package com.xqoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class ApplicationXqooThirdPay {

    public static void main(String[] args) {
        SpringApplication sa = new SpringApplication(ApplicationXqooThirdPay.class);
        // 增加启动消息消费监听器代码，多个需要addListeners
//        sa.addListeners(new ExampleMqListener());
        sa.run(args);
    }
}
