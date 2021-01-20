package com.xqoo.filemanager.rocketmq.until;

import com.xqoo.filemanager.rocketmq.service.FileManagerConsumerService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * spring监听器，用以启动时新增rocketMq的监听器，
 * 增加消息监听器时可单独重建类，或在onApplicationEvent中增加新的bean调用
 * @author gaoyang
 */
public class MessageQueneInitListener implements ApplicationListener<ApplicationReadyEvent> {

    private FileManagerConsumerService fileManagerConsumerService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        System.out.println("------------开启文件管理模块配置更新监听器--------------");
        ConfigurableApplicationContext applicationContext = applicationReadyEvent.getApplicationContext();
        fileManagerConsumerService = applicationContext.getBean(FileManagerConsumerService.class);
        System.out.println("-------------文件模块配置更新消费者启动------------");
        fileManagerConsumerService.fileConfigRefreshListener();
    }
}
