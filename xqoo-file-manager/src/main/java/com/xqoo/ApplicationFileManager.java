package com.xqoo;

import com.xqoo.filemanager.rocketmq.until.MessageQueneInitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author gaoyang
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class ApplicationFileManager {

    public static void main(String[] args) {
        SpringApplication sa = new SpringApplication(ApplicationFileManager.class);
        // 增加启动消息消费监听器代码，多个需要addListeners
        sa.addListeners(new MessageQueneInitListener());
        sa.run(args);
    }
}
