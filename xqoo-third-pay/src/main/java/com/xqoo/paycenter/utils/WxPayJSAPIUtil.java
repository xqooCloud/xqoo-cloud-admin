package com.xqoo.paycenter.utils;

import com.xqoo.paycenter.abstracts.WxPayAbsUtil;
import com.xqoo.paycenter.bean.WxPayPropertiesBean;
import com.xqoo.paycenter.dto.FinalUnifiedOrderResponse;
import com.xqoo.paycenter.dto.UnifiedOrder;
import com.xqoo.paycenter.dto.UnifiedOrderResponse;
import com.xqoo.paycenter.dto.WechatAppPayProtocolHandler;
import com.xqoo.paycenter.exception.MalformedPduException;
import com.xqoo.paycenter.exception.WechatAppPayServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WxPayJSAPIUtil extends WxpayService {

    private final static Logger log = LoggerFactory.getLogger(WxPayJSAPIUtil.class);

    @Override
    public FinalUnifiedOrderResponse createOrder(WxPayPropertiesBean wxpayConfig, String openId, String body, Double amount, String wxpayNotifyUrl, String outTradeNo, String type) throws WechatAppPayServiceException {
        UnifiedOrder order = new UnifiedOrder();

        order.setDevice_info("WEB");
        order.setNonce_str(WxPayUtil.getRandomString(16));
        //sign
        //signType
        order.setBody(body);
        order.setTotal_fee(amount.intValue());
        order.setSpbill_create_ip(wxpayConfig.getIp());
        //交易开始时间
        order.setTime_start(WxPayUtil.timeStart());
        //必须大于5分钟 此处默认为60分钟
        order.setTime_expire(WxPayUtil.timeExpire());
        order.setOut_trade_no(outTradeNo);
        order.setAppid(wxpayConfig.getOaAppId());
        order.setMch_id(wxpayConfig.getMchId());
        order.setNotify_url(wxpayNotifyUrl);
        order.setTrade_type(type);
        order.setOpenid(openId);
        String responseText = null;
        try {
            // 序列化成xml
            String xml = WechatAppPayProtocolHandler.marshalToXml(order, wxpayConfig.getApiKey());
            log.debug("Sending to wechat for unified order: " + xml);
            // 发到服务器并收回XML响应
            responseText = WechatHttpCapableClient.sendHttpPostAndReturnString(UNIFIED_ORDER_URL, xml);
            if (responseText == null) {
                throw new WechatAppPayServiceException(
                        "Unified order API to Weixin failed! Received empty response. Order Info: "
                                + order.toString());
            }
            // 反序列化为Java对象并返回
            UnifiedOrderResponse unifiedOrderResponse = WechatAppPayProtocolHandler.unmarshalFromXml(
                    responseText, UnifiedOrderResponse.class);
            log.debug("Response from WX is {}", unifiedOrderResponse);

            FinalUnifiedOrderResponse finalUnifiedOrderResponse = new FinalUnifiedOrderResponse();
            BeanUtils.copyProperties(unifiedOrderResponse, finalUnifiedOrderResponse);
            finalUnifiedOrderResponse.setOutTradeNo(order.getOut_trade_no());
            finalUnifiedOrderResponse.setTimestamp("" + System.currentTimeMillis() / 1000);
            finalUnifiedOrderResponse.setNonce_str(WxPayUtil.getRandomString(16));
            Map<String, Object> signMap = new HashMap<String, Object>();

            signMap.put("appId", wxpayConfig.getAppId());
            signMap.put("timeStamp", finalUnifiedOrderResponse.getTimestamp());
            signMap.put("signType", "MD5");
            signMap.put("nonceStr", finalUnifiedOrderResponse.getNonce_str());
            signMap.put("package", "prepay_id=" + finalUnifiedOrderResponse.getPrepay_id());
            finalUnifiedOrderResponse.setSign(WxPayAbsUtil.getSign(signMap, wxpayConfig.getApiKey()));

            return finalUnifiedOrderResponse;
        } catch (IOException e) {
            log.warn("Failed to call Weixin for order {} ", order, e);
            throw new WechatAppPayServiceException("Unified order API to Weixin failed! Order Info: "
                    + order.toString(), e);
        } catch (MalformedPduException e) {
            log.warn("Failed to unmarshall the order response {} ", responseText, e);
            throw new WechatAppPayServiceException("Failed to unmarshall the order response: "
                    + responseText + " for the order: " + order.toString(), e);
        }
    }
}
