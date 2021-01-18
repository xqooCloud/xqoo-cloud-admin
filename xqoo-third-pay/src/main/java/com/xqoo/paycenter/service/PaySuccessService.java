package com.xqoo.paycenter.service;

import com.xqoo.paycenter.enums.PayPlatType;

import java.math.BigDecimal;
import java.util.Map;

public interface PaySuccessService {

    Boolean paySuccessNotice(String payTransactionId, String transactionId,
                             String ciPayId, BigDecimal totalAmount, PayPlatType payPlatType);

    void confirmRefundMQ(String payTransactionId, BigDecimal refundAmount, String refundOrderDetailId);

    Boolean addPayWaterFlowRecord(String deviceType,BigDecimal payAmount, String payComment,
                                  String transactionId, String payUserId, String payUSerName, PayPlatType payPlatType);

    /**
     * 苹果充值成功后的消息生产者接口
     *
     * @param map
     * @return
     */
    Boolean iosRechargeSuccessNoticeMQ(Map<String,Object> map);


    /**
     * IOS购买产品成功后的消息生产者接口
     *
     * @param bo
     * @return
     */
//    Boolean iosCoinBuyProductMQ(PaySuccessNotifyBO bo);
}
