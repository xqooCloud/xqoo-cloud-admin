package com.xqoo.paycenter.utils;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.xqoo.paycenter.bean.AliPayPropertiesBean;

public class AliRefundPayUtil {

    public AlipayTradeRefundResponse AliRefund(AliPayPropertiesBean alipayConfig,
                                               String tradeNo,
                                               String payTransactionId,
                                               String refundCode,
                                               String refundAmount,
                                               String refundReason) throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.getGatewayUrl(),
                alipayConfig.getAppId(),
                alipayConfig.getPrivateKey(),
                alipayConfig.getFormat(),
                alipayConfig.getCharset(),
                alipayConfig.getPublicKey(),
                alipayConfig.getSignType());
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        request.setBizContent("{" +
                "\"trade_no\":\""+ tradeNo + "\"," +
                "\"out_trade_no\":\"" + payTransactionId + "\"," +
                "\"out_request_no\":\"" + refundCode + "\"," +
                "\"refund_amount\":\"" + refundAmount + "\"," +
                "\"refund_reason\":\"" + refundReason + "\"" +
                "}");
        return alipayClient.execute(request);
    }
}
