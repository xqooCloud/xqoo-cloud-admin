package com.xqoo.paycenter.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.xqoo.common.constants.CommonConstants;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.feign.service.uidgenerator.UidGeneratorFeign;
import com.xqoo.paycenter.bean.AliPayPropertiesBean;
import com.xqoo.paycenter.bo.AliPayNeedParam;
import com.xqoo.paycenter.bo.AliRefundNeedParam;
import com.xqoo.paycenter.bo.PayWaterFlowQueryBO;
import com.xqoo.paycenter.constant.PayModuleConstant;
import com.xqoo.paycenter.entity.PayRefundWaterFlowEntity;
import com.xqoo.paycenter.enums.PayPlatType;
import com.xqoo.paycenter.service.AliPayBusinessService;
import com.xqoo.paycenter.service.PayRefundWaterFlowService;
import com.xqoo.paycenter.service.PaySuccessService;
import com.xqoo.paycenter.service.PayWaterFlowService;
import com.xqoo.paycenter.utils.AliAbsPayAPPUtil;
import com.xqoo.paycenter.utils.AliAbsPayPCUtil;
import com.xqoo.paycenter.utils.AliRefundPayUtil;
import com.xqoo.paycenter.vo.PayWaterFlowVO;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 支付宝支付业务实现层
 *
 * @author makejava
 * @since 2020-03-14 16:01:09
 */
@Service(value = "AliPayBusinessServiceImpl")
public class AliPayBusinessServiceImpl implements AliPayBusinessService {

    private final static Logger log = LoggerFactory.getLogger(AliPayBusinessServiceImpl.class);

    @Autowired
    @Qualifier("AliPayConfig")
    private AliPayPropertiesBean aliPayPropertiesBean;

    @Autowired
    private UidGeneratorFeign uidGeneratorFeign;

    @Autowired
    private PayRefundWaterFlowService payRefundWaterFlowService;

    @Autowired
    private PayWaterFlowService payWaterFlowService;

    @Autowired
    private PaySuccessService paySuccessService;


    @Override
    public ResultEntity<String> aliPay(AliPayNeedParam aliPayNeedParam, String plat) throws AlipayApiException {
        log.info("[支付模块][info]支付宝" + plat + "下单程序，支付单号:["
                + aliPayNeedParam.getPayTransactionId() + "],支付金额:" + aliPayNeedParam.getPayAmount().toString());
        String returnUrl = aliPayPropertiesBean.getReturnUrl();
        if(StringUtils.isNotEmpty(aliPayNeedParam.getReturnUrl())){
            returnUrl = aliPayNeedParam.getReturnUrl();
        }
        // 新增支付记录
        Boolean addReco = paySuccessService.addPayWaterFlowRecord(aliPayNeedParam.getDeviceType(),aliPayNeedParam.getPayAmount(),
                aliPayNeedParam.getPayComment(),aliPayNeedParam.getPayTransactionId(),aliPayNeedParam.getUserId(),aliPayNeedParam.getUserName(),
                PayPlatType.ALIBABA);
        if(!addReco){
            throw new AlipayApiException("[customer020]", "生成支付记录失败，无法执行付款");
        }
        AliAbsPayPCUtil aliPayPCUtil = new AliAbsPayPCUtil();
        AlipayResponse alipayResponse = aliPayPCUtil.createOrder(aliPayPropertiesBean,
                returnUrl, aliPayPropertiesBean.getNotifyUrl(),aliPayNeedParam.getPayComment(),
                aliPayPropertiesBean.getSubject(),
                aliPayNeedParam.getPayAmount().doubleValue(),
                aliPayNeedParam.getPayTransactionId());
        log.info("[支付模块][info]支付宝" + plat + "端下单程序，支付单号:["
                + aliPayNeedParam.getPayTransactionId() + "]下单完成");
        return new ResultEntity<>(HttpStatus.OK, "发起支付成功",alipayResponse.getBody());
    }

    @Override
    public ResultEntity<String> aliPayApp(AliPayNeedParam aliPayNeedParam, String plat) throws AlipayApiException {
        log.info("[支付模块][info]支付宝" + plat + "下单程序，支付单号:["
                + aliPayNeedParam.getPayTransactionId() + "],支付金额:" + aliPayNeedParam.getPayAmount().toString());
        String returnUrl = aliPayPropertiesBean.getReturnUrl();
        if(StringUtils.isNotEmpty(aliPayNeedParam.getReturnUrl())){
            returnUrl = aliPayNeedParam.getReturnUrl();
        }
        // 新增支付记录
        Boolean addReco = paySuccessService.addPayWaterFlowRecord(aliPayNeedParam.getDeviceType(),aliPayNeedParam.getPayAmount(),
                aliPayNeedParam.getPayComment(),aliPayNeedParam.getPayTransactionId(),aliPayNeedParam.getUserId(),aliPayNeedParam.getUserName(),
                PayPlatType.ALIBABA);
        if(!addReco){
            throw new AlipayApiException("[customer020]", "生成支付记录失败，无法执行付款");
        }
        AliAbsPayAPPUtil aliPayAPPUtil = new AliAbsPayAPPUtil();
        AlipayResponse alipayResponse = aliPayAPPUtil.createOrder(aliPayPropertiesBean,
                returnUrl, aliPayPropertiesBean.getNotifyUrl(),aliPayNeedParam.getPayComment(),
                aliPayPropertiesBean.getSubject(),
                aliPayNeedParam.getPayAmount().doubleValue(),
                aliPayNeedParam.getPayTransactionId());
        log.info("[支付模块][info]支付宝" + plat + "端下单程序，支付单号:["
                + aliPayNeedParam.getPayTransactionId() + "]下单完成");
        return new ResultEntity<>(HttpStatus.OK, "发起支付成功",alipayResponse.getBody());
    }

    @Override
    public ResultEntity<String> aliRefundPay(AliRefundNeedParam aliRefundNeedParam) throws AlipayApiException {
        log.info("[支付模块][info]支付宝退款程序，支付单号:["
                + aliRefundNeedParam.getPayTransactionId() + "],退款金额:" + aliRefundNeedParam.getRefundAmount().toString());
        if(StringUtils.isEmpty(aliRefundNeedParam.getPayTransactionId())){
            throw new AlipayApiException("[customer007]", "缺失支付单号");
        }
        if(StringUtils.isEmpty(aliRefundNeedParam.getOrderDetailId())){
            throw new AlipayApiException("[customer007]", "缺失退款订单明细号");
        }
        if(StringUtils.isEmpty(aliRefundNeedParam.getRefundReason())){
            throw new AlipayApiException("[customer008]", "缺失退款原因");
        }
        if(BigDecimal.ZERO.compareTo(aliRefundNeedParam.getRefundAmount()) > -1){
            throw new AlipayApiException("[customer009]", "退款金额为0");
        }
        String tradeNo = "none";
        String payUserId = "none";

        // 补充---此处需要新增根据付款单号查询交易流水号的操作
        PayWaterFlowQueryBO queryWf = new PayWaterFlowQueryBO();
        queryWf.setPayTransactionId(aliRefundNeedParam.getPayTransactionId());
        List<PayWaterFlowVO> waterFlowVos = payWaterFlowService.queryPayWaterFlow(queryWf);
        if(CollUtil.isEmpty(waterFlowVos)){
            throw new AlipayApiException("[customer010]", "未查询到对应付款记录，无法退款");
        }
        if(waterFlowVos.size() > 1){
            throw new AlipayApiException("[customer011]", "对应付款记录大于1条，数据有误，无法退款");
        }
        PayWaterFlowVO waterFlowVO = waterFlowVos.get(0);
        if(PayModuleConstant.AliPayConstant.PAY_DONE.compareTo(waterFlowVO.getPayStatus()) != 0){
            throw new AlipayApiException("[customer012]", "所选退款记录未完成付款，无法退款");
        }
        if(StringUtils.isEmpty(waterFlowVO.getTransactionId())){
            throw new AlipayApiException("[customer013]", "丢失平台支付流水号，无法退款");
        }
        tradeNo = waterFlowVO.getTransactionId();
        payUserId = waterFlowVO.getPayUserId();

        String refundCode = this.getRefundUid();
        AliRefundPayUtil aliRefundPayUtil = new AliRefundPayUtil();
        AlipayTradeRefundResponse response = aliRefundPayUtil.AliRefund(aliPayPropertiesBean,
                tradeNo,aliRefundNeedParam.getPayTransactionId(),
                refundCode,
                aliRefundNeedParam.getRefundAmount().toString(),
                aliRefundNeedParam.getRefundReason());
        // 插入退款记录数据
        PayRefundWaterFlowEntity refundLog = new PayRefundWaterFlowEntity();
        refundLog.setRefundCode(refundCode);
        refundLog.setRefundOrderDetailId(aliRefundNeedParam.getOrderDetailId());
        refundLog.setRefundLaunch(aliRefundNeedParam.getRefundLaunch());
        refundLog.setPayTransactionId(aliRefundNeedParam.getPayTransactionId());
        refundLog.setRefundAmount(aliRefundNeedParam.getRefundAmount());
        refundLog.setRefundReason(aliRefundNeedParam.getRefundReason());
        refundLog.setRefundUserId(payUserId);
        refundLog.setRefundPlat(PayPlatType.ALIBABA.getValue());
        refundLog.setTradeId(tradeNo);
        refundLog.setRefundStatus(PayModuleConstant.AliPayConstant.REFUND_FAIL);
        if (response.isSuccess()) {
            if (CommonConstants.ALIPAY_SUCCESS.equals(response.getCode())) {
                log.info("[支付模块][info]支付宝退款程序，支付单号:["
                        + aliRefundNeedParam.getPayTransactionId() + "],退款金额:"
                        + aliRefundNeedParam.getRefundAmount().toString() + "退款完成");
                refundLog.setRefundStatus(PayModuleConstant.AliPayConstant.REFUND_DONE);
                refundLog.setRefundTime(new Date());
                payRefundWaterFlowService.addRefundRecord(refundLog);
                // 消息通知退款成功
                paySuccessService.confirmRefundMQ(aliRefundNeedParam.getPayTransactionId(),
                        aliRefundNeedParam.getRefundAmount(),
                        aliRefundNeedParam.getOrderDetailId());
                return new ResultEntity<>(HttpStatus.OK, "退款成功");
            } else {
                String errMsg = "[支付模块][error]支付宝退款程序，支付单号:["
                        + aliRefundNeedParam.getPayTransactionId() + "退款失败，详细信息参考：" + response.getSubMsg();
                log.info(errMsg);
                refundLog.setRemarkTips(errMsg);
                payRefundWaterFlowService.addRefundRecord(refundLog);
                return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE,"退款失败");
            }
        } else {
            String errMsg = "[支付模块][error]支付宝退款程序，支付单号:["
                    + aliRefundNeedParam.getPayTransactionId() + "退款失败，详细信息参考：" + response.getSubMsg();
            log.info(errMsg);
            refundLog.setRemarkTips(errMsg);
            payRefundWaterFlowService.addRefundRecord(refundLog);
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE,"退款失败");
        }
    }

    @Override
    public Boolean aliPayNotifyNotice(Map<String, String> params) {
        // 系统的支付单号
        String payTransactionId =params.get("out_trade_no");
        // 交易流水号
        String transactionId = params.get("trade_no");
        // 对应平台的付款id，退款时需要此号
        String ciPayId = params.get("seller_id");

        BigDecimal totalAmount = new BigDecimal(params.get("total_amount"));

        // 此处需要增加相关记录收款的逻辑
        return paySuccessService.paySuccessNotice(payTransactionId, transactionId, ciPayId, totalAmount, PayPlatType.ALIBABA);
    }


    private String getRefundUid() {
        String uid = uidGeneratorFeign.getUid("refund-id");
        if (StringUtils.isEmpty(uid)) {
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
