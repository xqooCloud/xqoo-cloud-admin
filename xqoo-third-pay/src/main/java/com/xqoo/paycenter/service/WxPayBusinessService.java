package com.xqoo.paycenter.service;

import com.xqoo.common.entity.ResultEntity;
import com.xqoo.paycenter.bean.WxPayPropertiesBean;
import com.xqoo.paycenter.bo.WxPayNeedParam;
import com.xqoo.paycenter.bo.WxRefundNeedParam;
import com.xqoo.paycenter.exception.WechatAppPayServiceException;

import java.util.Map;

public interface WxPayBusinessService {

    /**
     * 微信电脑端预支付
     * @param wxPayNeedParam
     * @return
     * @throws WechatAppPayServiceException
     */
    ResultEntity wxPcPay(WxPayNeedParam wxPayNeedParam) throws WechatAppPayServiceException;

    /**
     * 微信APP端预支付
     * @param wxPayNeedParam
     * @return
     * @throws WechatAppPayServiceException
     */
    ResultEntity wxAppPay(WxPayNeedParam wxPayNeedParam) throws WechatAppPayServiceException;

    /**
     * 微信小程序端预支付
     * @param wxPayNeedParam
     * @return
     * @throws WechatAppPayServiceException
     */
    ResultEntity wxSmallProgramPay(WxPayNeedParam wxPayNeedParam) throws WechatAppPayServiceException;

    /**
     * 微信公众号端预支付
     * @param wxPayNeedParam
     * @return
     * @throws WechatAppPayServiceException
     */
    ResultEntity wxJsApiPay(WxPayNeedParam wxPayNeedParam) throws WechatAppPayServiceException;

    /**
     * 微信退款
     * @param wxRefundNeedParam
     * @return
     * @throws WechatAppPayServiceException
     */
    ResultEntity wxRefundPay(WxRefundNeedParam wxRefundNeedParam) throws WechatAppPayServiceException;

    /**
     * 微信收款回调通知
     * @param params
     * @return
     */
    Boolean wxPayNotifyNotice(Map<String, Object> params);

    /**
     * 获取微信参数
     * @return
     */
    WxPayPropertiesBean getWechatConfig();
}
