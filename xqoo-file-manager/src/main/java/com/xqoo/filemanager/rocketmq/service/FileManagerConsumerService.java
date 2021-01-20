package com.xqoo.filemanager.rocketmq.service;

import com.xqoo.filemanager.bean.FileConfigPropertiesBean;
import com.xqoo.filemanager.bean.FileManagerConfigBean;
import com.xqoo.filemanager.entity.FileManagerConfigEntity;
import com.xqoo.filemanager.enums.UploadPlatEnum;
import com.xqoo.filemanager.rocketmq.constant.MessageFileManagerConstant;
import com.xqoo.filemanager.service.FileConfigPropertiesService;
import com.xqoo.rocket.config.RocketmqConfig;
import com.xqoo.rocket.constant.MqTagsConstant;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

/**
 * 消息消费监听器
 * @author GaoYang
 * @since 2020-03-14 17:31:25
 */
@Service
public class FileManagerConsumerService {

    private final static Logger logger = LoggerFactory.getLogger(FileManagerConsumerService.class);

    @Autowired
    private RocketmqConfig rocketmqConfig;

    @Autowired
    @Qualifier("fileConfigPropertiesBean")
    private FileConfigPropertiesBean fileConfigPropertiesBean;

    @Autowired
    @Qualifier("fileManagerConfigBean")
    private FileManagerConfigBean fileManagerConfigBean;

    @Autowired
    private FileConfigPropertiesService fileConfigPropertiesService;

    /**
     * 支付配置更新监听器
     */
    public void fileConfigRefreshListener(){
        //消费者的组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(rocketmqConfig.getConsumer().getPushConsumer());

        //指定NameServer地址，多个地址以 ; 隔开
        consumer.setNamesrvAddr(rocketmqConfig.getNamesrvAddr());
        try {
            //订阅支付模块相关付款配置刷新
            consumer.subscribe(rocketmqConfig.getTopic(), MqTagsConstant.FileModuleTopicTags.CONFIG_REFRESH);

            // 广播模式消费，所有节点均执行，默认为集群模式
            consumer.setMessageModel(MessageModel.BROADCASTING);
            //设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费
            /*如果非第一次启动，那么按照上次消费的位置继续消费，此参数广播模式下无效，集群模式如若多台那滞留消费理应被其他服务器
            * 消耗，也无太大用处，不过为防止数据处理丢失集群模式还是开着吧
            * */
//            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            consumer.registerMessageListener((MessageListenerConcurrently) (list, context) -> {
                MessageExt messageExt = list.get(0);
                try {
                    String messageBody = new String(messageExt.getBody(), RemotingHelper.DEFAULT_CHARSET);
                    switch(messageBody){
                        case MessageFileManagerConstant
                                .FileConfigRefreshOrder
                                .ALI_OSS_CONFIG_REFRESH:
                            this.updateConfig(UploadPlatEnum.ALI);
                            break;
                        case MessageFileManagerConstant
                                .FileConfigRefreshOrder
                                .TENCENT_COS_CONFIG_REFRESH:
                            this.updateConfig(UploadPlatEnum.TENCENT);
                            break;
                        default:
                            logger.info("[支付模块]:刷新支付参数命令不明，未执行操作");
                    }
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
    }

    private void updateConfig(UploadPlatEnum plat){
        Optional<FileManagerConfigEntity> find = fileManagerConfigBean.getBeanList().stream().filter(item
                -> plat.getKey().equals(item.getUploadPlat())).findFirst();
        find.ifPresent(item -> {
            Map<String, String> aliConfigMap = fileConfigPropertiesService.getPropertiesByParentId(item.getId());
            fileConfigPropertiesBean.getFileManagerConfigBean().put(item.getUploadPlat(), aliConfigMap);
            logger.info("[文件模块]:刷新了文件模块【{}】配置参数", plat.getValue());
        });
    }
}
