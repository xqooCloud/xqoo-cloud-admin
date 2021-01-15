package com.xqoo.paycenter.utils;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.xqoo.paycenter.abstracts.WxAbsPayService;
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
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service(value = "WxPayAppUtilService")
public class WxPayAppUtilService extends WxAbsPayService {

    private static final Logger logger = LoggerFactory.getLogger(WxPayAppUtilService.class);

    @Override
    public FinalUnifiedOrderResponse createOrder(WxPayPropertiesBean wxpayConfig,
                                                 String body,
                                                 Double amount,
                                                 String payTransactionId,
                                                 String type) throws WechatAppPayServiceException {
        UnifiedOrder order = new UnifiedOrder();

        order.setDevice_info("WEB");
        order.setNonce_str(WxPayUtil.getRandomString(16));
        //sign
        //signType
        order.setBody(body);
        order.setTotal_fee(amount.intValue());
        order.setSpbill_create_ip(wxpayConfig.getIp());
        //交易开始时间
        order.setTime_start(DateUtil.format(new Date(), "yyyyMMddHHmmss"));
        //必须大于5分钟 此处默认为30分钟
        order.setTime_expire(DateUtil.format(DateUtil.offset(new Date(), DateField.HOUR, 720), "yyyyMMddHHmmss"));
        order.setOut_trade_no(payTransactionId);
        order.setAppid(wxpayConfig.getAppAppId());
        order.setMch_id(wxpayConfig.getMchId());
        order.setNotify_url(wxpayConfig.getNotifyUrl());
        order.setTrade_type(type);
//      order.setProduct_id(RandCharsUtils.getRandomString(32));
        String responseText = null;
        try {
            // 序列化成xml
            String xml = WechatAppPayProtocolHandler.marshalToXml(order, wxpayConfig.getApiKey());
            logger.info("[支付模块]：微信序列化参数" + xml);
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
            logger.info("[支付模块]：微信返回参数{}", unifiedOrderResponse);

            FinalUnifiedOrderResponse finalUnifiedOrderResponse = new FinalUnifiedOrderResponse();
            BeanUtils.copyProperties(unifiedOrderResponse, finalUnifiedOrderResponse);
            finalUnifiedOrderResponse.setOutTradeNo(order.getOut_trade_no());
            finalUnifiedOrderResponse.setTimestamp("" + System.currentTimeMillis() / 1000);
            finalUnifiedOrderResponse.setNonce_str(WxPayUtil.getRandomString(16));
            Map<String, Object> signMap = new HashMap<String, Object>();


            signMap.put("appid", wxpayConfig.getAppAppId());
            signMap.put("timestamp", finalUnifiedOrderResponse.getTimestamp());
            signMap.put("noncestr", finalUnifiedOrderResponse.getNonce_str());
            signMap.put("package", "Sign=WXPay");
            signMap.put("partnerid", finalUnifiedOrderResponse.getMch_id());
            signMap.put("prepayid", finalUnifiedOrderResponse.getPrepay_id());
            finalUnifiedOrderResponse.setSign(WxPayAbsUtil.getSign(signMap, wxpayConfig.getApiKey()));
            logger.info(JSON.toJSONString(signMap));
            logger.info(JSON.toJSONString(finalUnifiedOrderResponse));

            return finalUnifiedOrderResponse;
        } catch (IOException e) {
            logger.error("Failed to call Weixin for order {} ", order, e);
            throw new WechatAppPayServiceException("Unified order API to Weixin failed! Order Info: "
                    + order.toString(), e);
        } catch (MalformedPduException e) {
            logger.error("Failed to unmarshall the order response {} ", responseText, e);
            throw new WechatAppPayServiceException("Failed to unmarshall the order response: "
                    + responseText + " for the order: " + order.toString(), e);
        }
    }

}
