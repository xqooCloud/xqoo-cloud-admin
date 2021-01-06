package com.xqoo.client.rocketmq.listener;

import com.xqoo.client.rocketmq.service.ExampleConsumerService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

public class ExampleMqListener implements ApplicationListener<ApplicationReadyEvent> {

    private ExampleConsumerService exampleConsumerService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        System.out.println("------------开启Client示例模块消息监听器--------------");
        ConfigurableApplicationContext applicationContext = applicationReadyEvent.getApplicationContext();
        exampleConsumerService = applicationContext.getBean(ExampleConsumerService.class);
        System.out.println("-------------示例消费者启动------------");
//        exampleConsumerService.exampleMqListener();
    }
}
