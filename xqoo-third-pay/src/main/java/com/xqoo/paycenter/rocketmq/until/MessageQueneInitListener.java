package com.xqoo.paycenter.rocketmq.until;

import com.xqoo.paycenter.rocketmq.service.ThirdPartyPayConsumerService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * spring监听器，用以启动时新增rocketMq的监听器，
 * 增加消息监听器时可单独重建类，或在onApplicationEvent中增加新的bean调用
 * @author gaoyang
 */
public class MessageQueneInitListener implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        System.out.println("------------开启第三方支付模块配置更新监听器--------------");
        ConfigurableApplicationContext applicationContext = applicationReadyEvent.getApplicationContext();
        ThirdPartyPayConsumerService thirdPartyPayConsumerService = applicationContext.getBean(ThirdPartyPayConsumerService.class);
        System.out.println("-------------支付模块配置更新消费者启动------------");
        thirdPartyPayConsumerService.PayConfigRefreshListener();
    }
}
