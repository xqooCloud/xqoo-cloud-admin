package com.xqoo.paycenter.abstracts;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayResponse;
import com.xqoo.paycenter.bean.AliPayPropertiesBean;

/**
 * @ClassName: AliAbsPayService
 * @Description: 阿里支付服务接口
 * @Author: wangxiaobo
 * @Date: 2019/11/16 10:21
 * @Verison 1.0
 **/
public abstract class AliAbsPayService {
    /**
     *
     * @param returnUrl
     * @param alipayNotifyUrl
     * @param body
     * @param subject
     * @param amount
     * @param outTradeNo
     * @return
     * @throws AlipayApiException
     */
    public abstract AlipayResponse createOrder(AliPayPropertiesBean alipayConfig, String returnUrl, String alipayNotifyUrl, String body, String subject, Double amount, String outTradeNo) throws AlipayApiException;
}
