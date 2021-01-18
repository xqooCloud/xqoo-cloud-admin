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

    /**
     * 支付宝web支付预下单接口
     * @param aliPayNeedParam
     * @param plat
     * @return
     * @throws AlipayApiException
     */
    ResultEntity aliPay(AliPayNeedParam aliPayNeedParam, String plat) throws AlipayApiException;


    /**
     * 支付宝APP支付接口
     * @param aliPayNeedParam
     * @param plat
     * @return
     * @throws AlipayApiException
     */
    ResultEntity aliPayApp(AliPayNeedParam aliPayNeedParam, String plat) throws AlipayApiException;

    /**
     * 支付支付宝退款接口
     * @param aliRefundNeedParam
     * @return
     * @throws AlipayApiException
     */
    ResultEntity aliRefundPay(AliRefundNeedParam aliRefundNeedParam) throws AlipayApiException;

    /**
     * 支付宝支付回调接口
     * @param params
     * @return
     */
    Boolean aliPayNotifyNotice(Map<String, String> params);
}
