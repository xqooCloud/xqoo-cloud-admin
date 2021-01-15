package com.xqoo.paycenter.utils;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayResponse;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.xqoo.paycenter.abstracts.AliAbsPayService;
import com.xqoo.paycenter.bean.AliPayPropertiesBean;

/**
 * @ClassName: AliAbsPayPCUtil
 * @Description: 阿里PC端支付工具类
 * @Author: wangxiaobo
 * @Date: 2019/11/16 10:24
 * @Verison 1.0
 **/
public class AliAbsPayPCUtil extends AliAbsPayService {

    @Override
    public AlipayResponse createOrder(AliPayPropertiesBean alipayConfig,
                                      String returnUrl,
                                      String alipayNotifyUrl,
                                      String body,
                                      String subject,
                                      Double amount,
                                      String outTradeNo) throws AlipayApiException {
        DefaultAlipayClient alipayClient = new DefaultAlipayClient(
                alipayConfig.getGatewayUrl(),
                alipayConfig.getAppId(),
                alipayConfig.getPrivateKey(),
                alipayConfig.getFormat(),
                alipayConfig.getCharset(),
                alipayConfig.getPublicKey(),
                alipayConfig.getSignType());
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
        model.setOutTradeNo(outTradeNo);
        model.setTotalAmount(String.valueOf(amount));
        model.setTimeoutExpress(alipayConfig.getTimeoutExpress());
        model.setProductCode(alipayConfig.getProductCode());
        model.setBody(body);
        model.setSubject(subject);
        request.setBizModel(model);
        request.setBizContent("{\"out_trade_no\":\""+outTradeNo+"\",\"product_code\":\"FAST_INSTANT_TRADE_PAY\",\"total_amount\":"+String.valueOf(amount)+",\"subject\":\""+subject+"\",\"body\":\""+body+"\"}");
        request.setNotifyUrl(alipayNotifyUrl);
        request.setReturnUrl(returnUrl);
        return alipayClient.pageExecute(request);
    }
}
