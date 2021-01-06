package com.xqoo.common.core.rocket.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitProducer {/*

    @Value("${apache.rocketmq.producer.producerGroup}")
    private String producerGroup;

    @Value("${apache.rocketmq.namesrvAddr}")
    private String namesrvAddr;

    private DefaultMQProducer producer;

    @PostConstruct
    public void initProducer(){
        producer = new DefaultMQProducer(producerGroup);
//        producer.setCreateTopicKey("AUTO_CREATE_TOPIC_KEY");

        //生产者投递消息重试次数
        producer.setRetryTimesWhenSendFailed(3);

        //指定NameServer地址，多个地址以 ; 隔开
        producer.setNamesrvAddr(namesrvAddr);

        start();
    }

    public DefaultMQProducer getProducer(){
        return this.producer;
    }

    *//**
     * 对象在使用之前必须要调用一次，只能初始化一次
     *//*
    public void start(){
        try {
            this.producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    *//**
     * 一般在应用上下文，使用上下文监听器，进行关闭
     *//*
    public void shutdown(){
        this.producer.shutdown();
    }*/
}
