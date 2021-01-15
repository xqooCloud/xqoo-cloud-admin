package com.xqoo.paycenter.utils;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayResponse;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.xqoo.paycenter.abstracts.AliAbsPayService;
import com.xqoo.paycenter.bean.AliPayPropertiesBean;

/**
 * @ClassName: AliAbsPayAPPUtil
 * @Description: 支付宝APP支付工具类
 * @Author: wangxiaobo
 * @Date: 2019/11/16 10:16
 * @Verison 1.0
 **/
public class AliAbsPayAPPUtil extends AliAbsPayService {

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
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody(body);
        model.setSubject(subject);
        model.setOutTradeNo(outTradeNo);
        model.setTotalAmount(String.valueOf(amount));
        model.setTimeoutExpress(alipayConfig.getTimeoutExpress());
        model.setProductCode(alipayConfig.getProductCode());
        request.setBizModel(model);
        request.setNotifyUrl(alipayNotifyUrl);
        request.setReturnUrl(returnUrl);
        return alipayClient.sdkExecute(request);
    }
}
