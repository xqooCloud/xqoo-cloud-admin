package com.xqoo.paycenter.utils;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayResponse;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.xqoo.paycenter.abstracts.AliAbsPayService;
import com.xqoo.paycenter.bean.AliPayPropertiesBean;

/**
 * @ClassName: AliAbsPayWAPUtil
 * @Description: 阿里支付WAP工具类
 * @Author: wangxiaobo
 * @Date: 2019/11/16 10:23
 * @Verison 1.0
 **/
public class AliAbsPayWAPUtil extends AliAbsPayService {

    @Override
    public AlipayResponse createOrder(AliPayPropertiesBean alipayConfig,
                                      String returnUrl,
                                      String alipayNotifyUrl,
                                      String body,
                                      String subject,
                                      Double amount,
                                      String outTradeNo) throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(
                alipayConfig.getGatewayUrl(),
                alipayConfig.getAppId(),
                alipayConfig.getPrivateKey(),
                alipayConfig.getFormat(),
                alipayConfig.getCharset(),
                alipayConfig.getPublicKey(),
                alipayConfig.getSignType());
        AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
        model.setBody(body);
        model.setTotalAmount(String.valueOf(amount));
        model.setTimeoutExpress(alipayConfig.getTimeoutExpress());
        model.setProductCode(alipayConfig.getProductCode());
        request.setBizModel(model);
        model.setSubject(subject);
        model.setOutTradeNo(outTradeNo);
        request.setNotifyUrl(alipayNotifyUrl);
        request.setReturnUrl(returnUrl);
        return alipayClient.pageExecute(request);
    }
}
