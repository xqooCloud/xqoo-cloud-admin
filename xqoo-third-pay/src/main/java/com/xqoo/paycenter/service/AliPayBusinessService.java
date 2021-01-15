package com.xqoo.paycenter.service;

import com.alipay.api.AlipayApiException;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.paycenter.bo.AliPayNeedParam;
import com.xqoo.paycenter.bo.AliRefundNeedParam;

import java.util.Map;

/**
 * 支付宝支付业务Service
 *
 * @author gaoyang
 * @since 2020-03-14 16:01:09
 */
public interface AliPayBusinessService {

    ResultEntity AliPay(AliPayNeedParam aliPayNeedParam, String plat) throws AlipayApiException;

    ResultEntity AliPayApp(AliPayNeedParam aliPayNeedParam, String plat) throws AlipayApiException;

    ResultEntity AliRefundPay(AliRefundNeedParam aliRefundNeedParam) throws AlipayApiException;

    Boolean AliPayNotifyNotice(Map<String, String> params);
}
