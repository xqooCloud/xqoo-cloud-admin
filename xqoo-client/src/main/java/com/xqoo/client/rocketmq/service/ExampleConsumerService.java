package com.xqoo.client.rocketmq.service;

import org.springframework.stereotype.Service;

@Service
public class ExampleConsumerService {
   /* @Autowired
    private RocketmqConfig rocketmqConfig;

    public void exampleMqListener(){
        //消费者的组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(rocketmqConfig.getConsumer().getPushConsumer());

        //指定NameServer地址，多个地址以 ; 隔开
        consumer.setNamesrvAddr(rocketmqConfig.getNamesrvAddr());
        try {
            //订阅PushTopic下Tag为push的消息
            consumer.subscribe("MY_TOPIC", "push");

            //设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费
            //如果非第一次启动，那么按照上次消费的位置继续消费
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            consumer.registerMessageListener((MessageListenerConcurrently) (list, context) -> {
                MessageExt messageExt = list.get(0);
                try {
//                        System.out.println("messageExt: " + messageExt + "\n");//输出消息内容
                        String messageBody = new String(messageExt.getBody(), RemotingHelper.DEFAULT_CHARSET);
//                        throw new Exception("测试异常");
                        System.out.println("消费响应：msgId : " + messageExt.getMsgId() + ",  msgBody : " + messageBody);//输出消息内容
                } catch (Exception e) {
                    e.printStackTrace();
                    int reconsumeTimes = messageExt.getReconsumeTimes();
                    if (reconsumeTimes >= 3) {
                        // 重试次数超过限制即通知broker已消费
                        // 此处补充业务代码处理消费产生异常的操作
                        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                    }
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER; //稍后再试
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS; //消费成功
            });
            consumer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
