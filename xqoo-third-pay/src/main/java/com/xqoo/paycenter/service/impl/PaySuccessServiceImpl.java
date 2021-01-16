package com.xqoo.paycenter.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xqoo.rocket.dto.thirdpay.PaySuccessNotifyDTO;
import com.xqoo.rocket.constant.MqTagsConstant;
import com.xqoo.rocket.util.InitProducer;
import com.xqoo.feign.common.bo.paycenter.RefundConfirmBO;
import com.xqoo.paycenter.bo.PayWaterFlowQueryBO;
import com.xqoo.paycenter.constant.PayModuleConstant;
import com.xqoo.paycenter.entity.PayWaterFlowEntity;
import com.xqoo.paycenter.enums.PayPlatType;
import com.xqoo.paycenter.service.PaySuccessService;
import com.xqoo.paycenter.service.PayWaterFlowService;
import com.xqoo.paycenter.vo.PayWaterFlowVO;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(value = "PaySuccessServiceImpl")
public class PaySuccessServiceImpl implements PaySuccessService {

    private final static Logger log = LoggerFactory.getLogger(PaySuccessServiceImpl.class);

    @Autowired
    private PayWaterFlowService payWaterFlowService;

    @Autowired
    private InitProducer initProducer;

    @Override
    public Boolean paySuccessNotice(String payTransactionId, String transactionId,
                                    String ciPayId, BigDecimal totalAmount, PayPlatType payPlatType) {
        PayWaterFlowQueryBO queryBO = new PayWaterFlowQueryBO();
        queryBO.setPayTransactionId(payTransactionId);
        List<PayWaterFlowVO> payWfList = payWaterFlowService.queryPayWaterFlow(queryBO);
        if(payWfList.size() == 1
                && payWfList.get(0).getPayStatus().compareTo(PayModuleConstant.AliPayConstant.NOT_PAY_YET) == 0){
            PayWaterFlowEntity payWfEntity = new PayWaterFlowEntity();
            payWfEntity.setPayTransactionId(payTransactionId);
            payWfEntity.setPayStatus(PayModuleConstant.AliPayConstant.PAY_DONE);
            payWfEntity.setTransactionId(transactionId);
            payWfEntity.setClientPayId(ciPayId);
            payWfEntity.setPayTime(new Date());
            payWaterFlowService.updatePayWaterFlowSingle(payWfEntity);
        }
        try {
            //创建一个消息实例，包含 topic、tag 和 消息体
            //如下：topic 为 "TopicTest"，tag 为 "push"
            Message message = new Message();
            message.setTopic(MqTagsConstant.THIRD_PAY_TOPIC);
            message.setTags(MqTagsConstant.PayModuleTopicTags.CONFIRM_MONEY);
            PaySuccessNotifyDTO dto = new PaySuccessNotifyDTO();
            dto.setCiPayId(ciPayId);
            dto.setPayPlatType(payPlatType.getValue());
            dto.setPayTransactionId(payTransactionId);
            dto.setTotalAmount(totalAmount);
            dto.setTransactionId(transactionId);
            message.setBody(JSON.toJSONBytes(dto));
            initProducer.getProducer().send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    log.info("[支付模块][info]支付结果通知[" + payTransactionId
                            + "]发送结果=%s, msg=%s ", sendResult.getSendStatus(), sendResult.toString() + "\n");
                }

                @Override
                public void onException(Throwable e) {
                    e.printStackTrace();
                    //补偿机制，根据业务情况进行使用，看是否进行重试
                }
            }, 3000L);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 退款成功通知MQ，返回json的byte数组，payTransactionId为付款单号，refundAmount为退款金额
     * @param payTransactionId
     * @param refundAmount
     */
    @Override
    public void confirmRefundMQ(String payTransactionId, BigDecimal refundAmount, String refundOrderDetailId){
        try {
            //创建一个消息实例，包含 topic、tag 和 消息体
            //如下：topic 为 "TopicTest"，tag 为 "push"
            Message message = new Message();
            message.setTopic(MqTagsConstant.THIRD_PAY_TOPIC);
            message.setTags(MqTagsConstant.PayModuleTopicTags.CONFIRM_REFUND);
            RefundConfirmBO refundBO = new RefundConfirmBO();
            refundBO.setOrderDetailId(refundOrderDetailId);
            refundBO.setPayTransactionId(payTransactionId);
            refundBO.setRefundAmount(refundAmount);
            message.setBody(JSONObject.toJSONBytes(refundBO));
            initProducer.getProducer().send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    log.info("[支付模块][info]退款结果通知[" + payTransactionId
                            + "]发送结果=%s, msg=%s ", sendResult.getSendStatus(), sendResult.toString() + "\n");
                }

                @Override
                public void onException(Throwable e) {
                    e.printStackTrace();
                    //补偿机制，根据业务情况进行使用，看是否进行重试
                }
            }, 3000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 增加支付流水记录
     * @param deviceType
     * @param payAmount
     * @param payComment
     * @param transactionId
     * @param payUserId
     * @param payUSerName
     * @param payPlatType
     * @return
     */
    @Override
    public Boolean addPayWaterFlowRecord(String deviceType,BigDecimal payAmount, String payComment,
                                          String transactionId, String payUserId, String payUSerName, PayPlatType payPlatType){
        PayWaterFlowEntity addPayWF = new PayWaterFlowEntity();
        addPayWF.setClientPayId("none");
        addPayWF.setPayDevice(deviceType);
        addPayWF.setPayAmount(payAmount);
        addPayWF.setPayComment(payComment);
        addPayWF.setPayTransactionId(transactionId);
        addPayWF.setPayPlat(payPlatType.getValue());
        addPayWF.setPayUserId(payUserId);
        addPayWF.setPayStatus(PayModuleConstant.AliPayConstant.NOT_PAY_YET);
        addPayWF.setPayUserName(payUSerName);
        addPayWF.setTransactionId("none");
        return payWaterFlowService.addPayRecord(addPayWF);
    }

    /**
     * 苹果充值成功后的消息生产者接口
     *
     * @param map
     * @return
     */
    @Override
    public Boolean iosRechargeSuccessNoticeMQ(Map<String, Object> map) {

        try {
            //创建一个消息实例，包含 topic、tag 和 消息体
            //如下：topic 为 "TopicTest"，tag 为 "push"
            Message message = new Message();
            message.setTopic(MqTagsConstant.THIRD_PAY_TOPIC);
            message.setTags(MqTagsConstant.PayModuleTopicTags.IOS_RECHARGE_SUCCESS);
            message.setBody(JSON.toJSONBytes(map));

            initProducer.getProducer().send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    log.info("[IOS支付模块][info]IOS充值结果通知,交易流水号[" + map.get("transactionId")
                            + "]发送结果=%s, msg=%s ", sendResult.getSendStatus(), sendResult.toString() + "\n");
                }

                @Override
                public void onException(Throwable throwable) {
                    throwable.printStackTrace();
                    //补偿机制，根据业务情况进行使用，看是否进行重试
                }
            },3000L);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
