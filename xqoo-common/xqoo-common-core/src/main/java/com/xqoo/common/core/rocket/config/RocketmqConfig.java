package com.xqoo.common.core.rocket.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "apache.rocketmq")
public class RocketmqConfig {

    private String topic;

    private String namesrvAddr;

    private Consumer consumer;

    private Producer producer;


    public static class Producer{
        private String producerGroup;

        public String getProducerGroup() {
            return producerGroup;
        }

        public void setProducerGroup(String producerGroup) {
            this.producerGroup = producerGroup;
        }
    }

    public static class Consumer{
        private String pushConsumer;
        public String getPushConsumer() {
            return pushConsumer;
        }
        public void setPushConsumer(String pushConsumer) {
            this.pushConsumer = pushConsumer;
        }
    }

    public String getNamesrvAddr() {
        return namesrvAddr;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public Producer getProducer() {
        return producer;
    }

    public String getTopic() {
        return topic;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
