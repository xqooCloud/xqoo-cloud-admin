package com.xqoo.paycenter.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.feign.service.uidgenerator.UidGeneratorFeign;
import com.xqoo.paycenter.bean.WxPayPropertiesBean;
import com.xqoo.paycenter.bo.PayWaterFlowQueryBO;
import com.xqoo.paycenter.bo.WxPayNeedParam;
import com.xqoo.paycenter.bo.WxRefundNeedParam;
import com.xqoo.paycenter.constant.PayModuleConstant;
import com.xqoo.paycenter.dto.FinalUnifiedOrderResponse;
import com.xqoo.paycenter.entity.PayRefundWaterFlowEntity;
import com.xqoo.paycenter.enums.PayPlatType;
import com.xqoo.paycenter.exception.WechatAppPayServiceException;
import com.xqoo.paycenter.service.PayRefundWaterFlowService;
import com.xqoo.paycenter.service.PaySuccessService;
import com.xqoo.paycenter.service.PayWaterFlowService;
import com.xqoo.paycenter.service.WxPayBusinessService;
import com.xqoo.paycenter.utils.*;
import com.xqoo.paycenter.vo.PayWaterFlowVO;
import org.apache.commons.lang3.StringUtils;
import org.jdom.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

@Service(value = "WxPayBusinessServiceImpl")
public class WxPayBusinessServiceImpl implements WxPayBusinessService {

    private final static Logger log = LoggerFactory.getLogger(WxPayBusinessServiceImpl.class);

    @Autowired
    @Qualifier("WxPayConfig")
    private WxPayPropertiesBean wxPayPropertiesBean;

    @Autowired
    private WxPayAppUtilService wxPayAppUtilService;

    @Autowired
    private UidGeneratorFeign uidGeneratorFeign;

    @Autowired
    private PayRefundWaterFlowService payRefundWaterFlowService;

    @Autowired
    private PayWaterFlowService payWaterFlowService;

    @Autowired
    private PaySuccessService paySuccessService;

//    @Autowired
//    private SysUserFeign sysUserFeign;

    @Autowired
    private WxPayJSAPIUtil wxPayJSAPIUtil;



    @Override
    public ResultEntity wxPcPay(WxPayNeedParam wxPayNeedParam) throws WechatAppPayServiceException {
        Map<String, Object> prePayRtnMap =new HashMap<String, Object>();
        SortedMap<String, Object> parameterMap = new TreeMap<String, Object>();
        try {
            prePayRtnMap = weixinPrePay(wxPayNeedParam);
            parameterMap.put("appid", wxPayPropertiesBean.getAppId());
            parameterMap.put("partnerid", wxPayPropertiesBean.getMchId());
            parameterMap.put("prepayid", prePayRtnMap.get("prepay_id"));
            parameterMap.put("package", "Sign=WXPay");
            parameterMap.put("codeUrl", prePayRtnMap.get("code_url"));
            parameterMap.put("noncestr", WxPayUtil.getRandomString(32));
            parameterMap.put("timestamp",WxPayUtil.getCurrentTimeMillis(10));
            String sign = WxPayUtil.createSign("UTF-8", parameterMap,wxPayPropertiesBean.getApiKey());
            parameterMap.put("sign", sign);
            Boolean addReco = paySuccessService.addPayWaterFlowRecord(wxPayNeedParam.getDeviceType(),
                    wxPayNeedParam.getPayAmount(), wxPayNeedParam.getPayComment(),wxPayNeedParam.getPayTransactionId(),
                    wxPayNeedParam.getUserId(),wxPayNeedParam.getUserName(),PayPlatType.WEIXIN);
            if(!addReco){
                return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE,"预支付失败，生成支付记录出错");
            }
            log.info("支付模块][info]微信下预付单成功，付款单号:" + wxPayNeedParam.getPayTransactionId());
            return new ResultEntity<>(HttpStatus.OK, "预下单成功", parameterMap);
        } catch (JDOMException | IOException e) {
            log.info("支付模块][info]微信下预付单异常,异常信息："+e.getMessage());
            e.printStackTrace();
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE,"预支付异常,请检查参数");
        }
    }

    @Override
    public ResultEntity wxAppPay(WxPayNeedParam wxPayNeedParam) throws WechatAppPayServiceException {
        FinalUnifiedOrderResponse response = wxPayAppUtilService.createOrder(wxPayPropertiesBean,
                wxPayPropertiesBean.getSubject(),
                wxPayNeedParam.getPayAmount().doubleValue()*100,
                wxPayNeedParam.getPayTransactionId(),
                "APP");
        Boolean addReco = paySuccessService.addPayWaterFlowRecord(wxPayNeedParam.getDeviceType(),
                wxPayNeedParam.getPayAmount(), wxPayNeedParam.getPayComment(),wxPayNeedParam.getPayTransactionId(),
                wxPayNeedParam.getUserId(),wxPayNeedParam.getUserName(),PayPlatType.WEIXIN);
        if(!addReco){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE,"预支付失败，生成支付记录出错");
        }
        return new ResultEntity<>(HttpStatus.OK,"",response);
    }

    @Override
    public ResultEntity wxSmallProgramPay(WxPayNeedParam wxPayNeedParam) throws WechatAppPayServiceException {
        return null;
    }

    @Override
    public ResultEntity wxJsApiPay(WxPayNeedParam wxPayNeedParam) throws WechatAppPayServiceException {
        /*WechatAuthInfoVO authInfo = FeignReturnDataGzip.Unzip(sysUserFeign
                .getWechatInfo(wxPayNeedParam.getUserId()), WechatAuthInfoVO.class);
        if(authInfo == null || StringUtils.isEmpty(authInfo.getWapOpenId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE,"丢失微信openId，支付失败");
        }
        Boolean addReco = paySuccessService.addPayWaterFlowRecord(wxPayNeedParam.getDeviceType(),
                wxPayNeedParam.getPayAmount(), wxPayNeedParam.getPayComment(),wxPayNeedParam.getPayTransactionId(),
                wxPayNeedParam.getUserId(),wxPayNeedParam.getUserName(),PayPlatType.WEIXIN);
        if(!addReco){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE,"预支付失败，生成支付记录出错");
        }
        FinalUnifiedOrderResponse  response = wxPayJSAPIUtil
                .createOrder(wxPayPropertiesBean,authInfo.getWapOpenId(),
                        wxPayNeedParam.getPayComment(),
                        wxPayNeedParam.getPayAmount().doubleValue()*100,
                        wxPayPropertiesBean.getNotifyUrl(),
                        wxPayNeedParam.getPayTransactionId(),
                        "JSAPI");*/
        // TODO 这里等修改了查询微信openId之后修改
        return new ResultEntity<>(HttpStatus.OK,"微信支付预下单成功", "response");
    }

    @Override
    public ResultEntity wxRefundPay(WxRefundNeedParam wxRefundNeedParam) throws WechatAppPayServiceException {
        String refundCode = this.getRefundUid();
        Map<String, Object> remap;

        PayWaterFlowQueryBO queryWF = new PayWaterFlowQueryBO();
        queryWF.setPayTransactionId(wxRefundNeedParam.getPayTransactionId());
        List<PayWaterFlowVO> waterFlowVOS = payWaterFlowService.queryPayWaterFlow(queryWF);
        if(CollUtil.isEmpty(waterFlowVOS)){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "未查询到对应付款记录，无法退款");
        }
        if(waterFlowVOS.size() > 1){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "对应付款记录大于1条，数据有误，无法退款");
        }
        PayWaterFlowVO waterFlowVO = waterFlowVOS.get(0);
        if(PayModuleConstant.AliPayConstant.PAY_DONE.compareTo(waterFlowVO.getPayStatus()) != 0){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "所选退款记录未完成付款，无法退款");
        }
        if(org.apache.commons.lang.StringUtils.isEmpty(waterFlowVO.getTransactionId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "丢失平台支付流水号，无法退款");
        }
        // 微信订单号
        String tradeNo = waterFlowVO.getTransactionId();
        // 系统付款人id
        String payUserId = waterFlowVO.getPayUserId();
        SortedMap<String, Object> parm = new TreeMap<String, Object>();
        parm.put("appid", wxPayPropertiesBean.getAppId());
        parm.put("mch_id", wxPayPropertiesBean.getMchId());
        parm.put("nonce_str", WxPayUtil.getRandomString(32));
        parm.put("transaction_id", tradeNo);
        parm.put("out_trade_no", wxRefundNeedParam.getPayTransactionId());//订单号
        parm.put("out_refund_no", refundCode); //退款单号
        parm.put("total_fee", wxRefundNeedParam.getPayAmount().multiply(BigDecimal.valueOf(100))
                .setScale(0,BigDecimal.ROUND_HALF_UP).toString()); // 订单总金额 从业务逻辑获取
        parm.put("refund_fee", wxRefundNeedParam.getRefundAmount().multiply(BigDecimal.valueOf(100))
                .setScale(0,BigDecimal.ROUND_HALF_UP).toString()); // 退款金额
        parm.put("op_user_id",wxPayPropertiesBean.getMchId());
        parm.put("refund_account", "REFUND_SOURCE_RECHARGE_FUNDS");//退款方式
        String sign = WxPayUtil.createSign("UTF-8", parm, wxPayPropertiesBean.getApiKey());
        parm.put("sign", sign);
        String requestXml = WxHttpUtil.getRequestXml(parm);
        // 退款记录
        PayRefundWaterFlowEntity refundLog = new PayRefundWaterFlowEntity();
        refundLog.setRefundCode(refundCode);
        refundLog.setRefundLaunch(wxRefundNeedParam.getRefundLaunch());
        refundLog.setPayTransactionId(wxRefundNeedParam.getPayTransactionId());
        refundLog.setRefundOrderDetailId(wxRefundNeedParam.getOrderDetailId());
        refundLog.setRefundAmount(wxRefundNeedParam.getRefundAmount());
        refundLog.setRefundReason(wxRefundNeedParam.getRefundReason());
        refundLog.setRefundUserId(payUserId);
        refundLog.setRefundPlat(PayPlatType.WEIXIN.getValue());
        refundLog.setTradeId(tradeNo);
        refundLog.setRefundStatus(PayModuleConstant.AliPayConstant.REFUND_FAIL);
        //微信退款调用，付款不适用
        String restxml = null;
        try {
            restxml = WxClientCustomSSL.doRefund(wxPayPropertiesBean.getRefundGatewayUrl(),
                    requestXml,
                    wxPayPropertiesBean.getSslPassword(),
                    wxPayPropertiesBean.getSslPath());
            remap = WxPayUtil.doXMLParse(restxml);
        } catch (Exception e) {
            log.error("[微信退款异常]:微信退款解析返回XML出现异常，请联系系统管理人员处理");
            refundLog.setRemarkTips("返回xml IO解析异常");
            payRefundWaterFlowService.addRefundRecord(refundLog);
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "返回xml IO解析异常");
        }

        if (CollUtil.isNotEmpty(remap) && "SUCCESS".equals(remap.get("result_code"))) {
            log.info("[支付模块][info]微信退款程序，支付单号:["
                    + wxRefundNeedParam.getPayTransactionId() + "],退款金额:"
                    + wxRefundNeedParam.getRefundAmount().toString() + "退款完成");
            refundLog.setRefundStatus(PayModuleConstant.AliPayConstant.REFUND_DONE);
            refundLog.setRefundTime(new Date());
            payRefundWaterFlowService.addRefundRecord(refundLog);
            // 退款消息发送
            paySuccessService.confirmRefundMQ(wxRefundNeedParam.getPayTransactionId(),
                    wxRefundNeedParam.getRefundAmount(),
                    wxRefundNeedParam.getOrderDetailId());
            return new ResultEntity<>(HttpStatus.OK,"退款成功");
        }else{
            log.info("退款失败，相关信息：" + remap.getOrDefault("return_msg","none"));
            refundLog.setRemarkTips(remap.get("return_msg").toString());
            payRefundWaterFlowService.addRefundRecord(refundLog);
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "退款失败。");
        }
    }

    @Override
    public Boolean wxPayNotifyNotice(Map<String, Object> params) {
        String status = params.getOrDefault("return_code", "").toString();
        String rtnMsg = params.getOrDefault("return_msg", "").toString();

        if (status.equals("SUCCESS")) {
            String payTransactionId = params.getOrDefault("out_trade_no","").toString();
            String ciPayId = params.getOrDefault("openid","").toString();
            // 微信支付订单号
            String transactionId = params.getOrDefault("transaction_id","").toString();

            Integer tmpTotal = Integer.parseInt(params.get("total_fee").toString());
            BigDecimal totalAmount = BigDecimal.valueOf(tmpTotal).divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP);
            // 此处需要增加相关记录收款的逻辑
            return paySuccessService.paySuccessNotice(payTransactionId, transactionId, ciPayId, totalAmount, PayPlatType.WEIXIN);

        }
        log.info("[支付模块][error]支付返回发生错误[" + rtnMsg
                + "]");
        return false;
    }

    @Override
    public WxPayPropertiesBean getWechatConfig() {
        return wxPayPropertiesBean;
    }

    /**
     * 微信支付统一下单
     *
     * @return
     * @throws IOException
     * @throws JDOMException
     */
    private Map<String, Object> weixinPrePay(WxPayNeedParam wxPayNeedParam) throws JDOMException, IOException {
        SortedMap<String, Object> parameterMap = new TreeMap<String, Object>();
        parameterMap.put("appid", wxPayPropertiesBean.getAppId());
        parameterMap.put("mch_id", wxPayPropertiesBean.getMchId());
        parameterMap.put("nonce_str", WxPayUtil.getRandomString(32));
        parameterMap.put("body",
                StringUtils.abbreviate(wxPayNeedParam.getPayComment().replaceAll("[^0-9a-zA-Z\\u4e00-\\u9fa5 ]", ""), 600));
        parameterMap.put("out_trade_no", wxPayNeedParam.getPayTransactionId());
        parameterMap.put("fee_type", wxPayPropertiesBean.getFeeType());
        BigDecimal total = wxPayNeedParam.getPayAmount().multiply(new BigDecimal(100));
        DecimalFormat df = new DecimalFormat("0");
        parameterMap.put("total_fee", df.format(total));
        parameterMap.put("spbill_create_ip", wxPayPropertiesBean.getSpbillCreateIp());
        parameterMap.put("notify_url", wxPayPropertiesBean.getNotifyUrl());
        parameterMap.put("trade_type", wxPayPropertiesBean.getTradeType());
        String sign = WxPayUtil.createSign("UTF-8", parameterMap, wxPayPropertiesBean.getApiKey());
        parameterMap.put("sign", sign);
        String requestXML = WxHttpUtil.getRequestXml(parameterMap);
        log.info("[支付模块][info]微信下预付单参数map："+parameterMap.toString());
        String result = WxHttpUtil.httpsRequest(wxPayPropertiesBean.getGatewayUrl(), "POST", requestXML);
        log.info("[支付模块][info]微信下预付单调用返回xml："+result);
        Map<String, Object> map=  WxPayUtil.doXMLParse(result);
        return map;
    }

    private String getRefundUid() {
        String uid = uidGeneratorFeign.getUid("refund-id");
        if (org.apache.commons.lang.StringUtils.isEmpty(uid)) {
            return null;
        }
        String round = String.valueOf(RandomUtil.randomDouble(0, 1, 6, RoundingMode.UP));
        round = round.substring(round.indexOf(".") + 1);
        while (round.length() < 6) {
            round = round + "0";
        }
        return "RF" + uid + round;
    }
}
