package com.xqoo.paycenter.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.feign.service.uidgenerator.UidGeneratorFeign;
import com.xqoo.paycenter.bo.AliRefundNeedParam;
import com.xqoo.paycenter.bo.PayWaterFlowQueryBO;
import com.xqoo.paycenter.bo.goldcoin.IosConsumptionCoinByProductParamBO;
import com.xqoo.paycenter.bo.goldcoin.IosRequestParamBO;
import com.xqoo.paycenter.constant.PayModuleConstant;
import com.xqoo.paycenter.entity.PayRefundWaterFlowEntity;
import com.xqoo.paycenter.entity.goldcoin.CoinAccountInfoEntity;
import com.xqoo.paycenter.entity.goldcoin.CoinTransactionLogEntity;
import com.xqoo.paycenter.enums.PayDeviceType;
import com.xqoo.paycenter.enums.PayPlatType;
import com.xqoo.paycenter.mapper.goldcoin.CoinAccountInfoMapper;
import com.xqoo.paycenter.mapper.goldcoin.CoinTransactionLogMapper;
import com.xqoo.paycenter.mapper.goldcoin.IosConsumptionCoinByProductMapper;
import com.xqoo.paycenter.service.IosPayBusinessService;
import com.xqoo.paycenter.service.PayRefundWaterFlowService;
import com.xqoo.paycenter.service.PaySuccessService;
import com.xqoo.paycenter.service.PayWaterFlowService;
import com.xqoo.paycenter.utils.IosServerCheckUtil;
import com.xqoo.paycenter.vo.PayWaterFlowVO;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: IosPayBusinessServiceImpl
 * @Description: 苹果支付服务接口实现层
 * @Author: liangyongpeng
 * @Date: 2020/4/29 16:56
 * @Verison 1.0
 **/
@Service(value = "iosPayBusinessService")
@RefreshScope
public class IosPayBusinessServiceImpl implements IosPayBusinessService {

    private static final Logger logger = LoggerFactory.getLogger(IosPayBusinessService.class);

    @Value("${iosPayUrl.urlText:https://sandbox.itunes.apple.com/verifyReceipt}")
    private String iosUrl;

    private final static String sandBoxUrl = "https://sandbox.itunes.apple.com/verifyReceipt";

    @Autowired
    private PaySuccessService paySuccessServiceImpl;

    @Autowired
    private IosConsumptionCoinByProductMapper iosConsumptionCoinByProductMapper;

    @Autowired
    private CoinAccountInfoMapper coinAccountInfoMapper;

    @Autowired
    private CoinTransactionLogMapper transactionLogMapper;

    @Autowired
    private UidGeneratorFeign uidGeneratorFeign;

    @Autowired
    private PayWaterFlowService payWaterFlowService;

    @Autowired
    private PayRefundWaterFlowService payRefundWaterFlowService;

    /**
     * IOS客户端支付
     *
     * @param bo
     * @return
     */
    @Override
    public ResultEntity doIosRequest(IosRequestParamBO bo) {

        // 校验完数据，进行沙盒环境测试，返回数据。
        logger.info("客户端传过来的交易流水号：" + bo.getTransactionId() + "客户端传过来的收据：" + bo.getApplePayLoad());

        if(!judgeEntityParam(bo)){
            return new ResultEntity(HttpStatus.NOT_ACCEPTABLE,"传入参数不正确，充值失败!");
        }

        // 先线上测试  发送消息给平台
        logger.info("支付地址{}", iosUrl);
        String checkResult = IosServerCheckUtil.buyAppCheck(bo.getApplePayLoad(), iosUrl);

        // 当苹果服务器没有返回验证结果。
        if(checkResult == null){
            logger.info("无订单信息!");
             return new ResultEntity(HttpStatus.NOT_ACCEPTABLE,"充值失败，未获取到相应支付凭证验证结果！");
        }

        // 当返回有验证结果时
        logger.info("线上，苹果平台返回JSON:" + checkResult);
        JSONObject job = JSONObject.parseObject(checkResult);
        String status  = job.getString("status");

        // 判断返回状态 21007 收据信息是测试用（sandbox），但却被发送到产品环境中验证。
        if ("21007".equals(status)) {                                            //是沙盒环境，应沙盒测试，否则执行下面
            checkResult = IosServerCheckUtil.buyAppCheck(bo.getApplePayLoad(), sandBoxUrl);            //2.再沙盒测试  发送平台验证
            logger.info("沙盒环境，苹果平台返回JSON:" + checkResult);
            job = JSONObject.parseObject(checkResult);
            status = job.getString("status");
        }

//        status = "0";
        // 0 正常状态

        if("0".equals(status)){
            String r_receipt = job.getString("receipt");
            JSONObject returnJson = JSONObject.parseObject(r_receipt);
            JSONArray in_app_array = returnJson.getJSONArray("in_app");
            JSONObject in_appJson = JSONObject.parseObject(in_app_array.get(in_app_array.size() - 1).toString());
            String product_id = in_appJson.getString("product_id");
            String transaction_id = in_appJson.getString("transaction_id");

            // 测试用
//            String product_id = "ly128";
//            String transaction_id = bo.getTransactionId();

            Map<String,Object> map= new HashMap<String,Object>();
            // 如果传如的交易流水号等于返回的交易流水号，那么说明两表示的是一条交易记录。
            if(bo.getTransactionId().equals(transaction_id)){
                // 表示的就是金币配置表的id，如：ly128,表示充值128块钱。
                map.put("productId",product_id);
                map.put("userId",bo.getUserId());
                map.put("orderCode",bo.getOrderCode());
                map.put("transactionId",bo.getTransactionId());
                map.put("orderAmount",bo.getOrderAmount());
                // 发送消息告诉订单模块支付完成。那边更改用户账户金币插入流水表信息等。
                Boolean aBoolean = paySuccessServiceImpl.iosRechargeSuccessNoticeMQ(map);
                if(aBoolean){
                    return new ResultEntity<>(HttpStatus.OK,"充值成功!",map);
                }else {
                    return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE,"充值失败,请重试!");
                }

            }else {
                logger.info("交易流水号不一致，传入["+ bo.getTransactionId() + "],服务器返回["+  transaction_id + "]，充值失败!");
                return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE,"交易流水号不一致，充值失败!");
            }
        }else {
            logger.info("充值校验付款凭证状态不为0,充值订单不确认，实际返回状态码:[" + status + "]");
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE,"充值失败!");
        }

    }

    /**
     * IOS 金币购买产品
     *
     * @param bo 请求参数
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultEntity iosConsumptionCoinByProduct(IosConsumptionCoinByProductParamBO bo) {

        Boolean addReco = paySuccessServiceImpl.addPayWaterFlowRecord(PayDeviceType.APP.getValue(),
                bo.getTotalCoinConsumption().setScale(2, BigDecimal.ROUND_HALF_UP),
                "苹果内购支付",bo.getPayTransactionId(),bo.getUserId(),bo.getUserName(),
                PayPlatType.IOS);
        if(!addReco){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE,
                    "[customer020]", "生成支付记录失败，无法执行付款");
        }
        try {
            CoinAccountInfoEntity coinAccountInfoEntityInfo = iosConsumptionCoinByProductMapper.getCoinAccountInfoEntityInfo(bo.getUserId());
            BigInteger factAmount = BigInteger
                    .valueOf(bo.getTotalCoinConsumption().multiply(BigDecimal.valueOf(100)).longValue());
//            if(coinAccountInfoEntityInfo == null){
//                return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE,"该用户不存在,购买产品失败");
//            }
            if(coinAccountInfoEntityInfo == null || coinAccountInfoEntityInfo.getCoinTotal().compareTo(factAmount) < 0){
                return new ResultEntity<>(HttpStatus.PAYMENT_REQUIRED,"账户金币不足，请先充值!");
            }
            // 重置该用户的账户金币余额
            coinAccountInfoEntityInfo.setCoinTotal(coinAccountInfoEntityInfo.getCoinTotal().subtract(factAmount));

            // 更新用户金币信息
            coinAccountInfoMapper.updateById(coinAccountInfoEntityInfo);

            // 生成一个交易流水号
            String transactionUid = getTransactionUid("IOSP");

            // 插入用户金币支付流水
            CoinTransactionLogEntity coinTransactionLogEntity = new CoinTransactionLogEntity();
            coinTransactionLogEntity.setUserId(bo.getUserId());
            coinTransactionLogEntity.setDelFlag("0");
            // 交易类型，0-收入，1-支出
            coinTransactionLogEntity.setTradeType(1);
            coinTransactionLogEntity.setOrderCode(bo.getPayTransactionId());
            coinTransactionLogEntity.setTradeAmount(factAmount);
            coinTransactionLogEntity.setTradeComment("消费金币，消费数量为[" + bo.getTotalCoinConsumption() +"]");

            transactionLogMapper.insert(coinTransactionLogEntity);


            /*PaySuccessNotifyBO paySuccessNotifyBO = new PaySuccessNotifyBO();
            // 支付单号
            paySuccessNotifyBO.setPayTransactionId(bo.getPayTransactionId());
            // 设置流水号
            paySuccessNotifyBO.setTransactionId(transactionUid);
            // 设置支付单号的用户id
            paySuccessNotifyBO.setCiPayId(bo.getUserId());
            // 设置本次支付的金额
            paySuccessNotifyBO.setTotalAmount(new BigDecimal(bo.getTotalCoinConsumption()));
            // 设置支付的类型
            paySuccessNotifyBO.setPayPlatType(PayPlatType.IOS.getValue());*/

            // 发送消息给订单模块
            Boolean aBoolean = paySuccessServiceImpl.paySuccessNotice(bo.getPayTransactionId(), transactionUid,
                    bo.getUserId(),
                    bo.getTotalCoinConsumption(),
                    PayPlatType.IOS);
            if(aBoolean){
                return new ResultEntity(HttpStatus.OK,"购买成功!");
            }else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return new ResultEntity(HttpStatus.NOT_ACCEPTABLE,"[支付模块]发送消息失败，购买出错!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResultEntity(HttpStatus.NOT_ACCEPTABLE,"购买产品出错!");
        }

    }

    @Override
    public ResultEntity iosRefunded(AliRefundNeedParam aliRefundNeedParam){
        logger.info("[支付模块][info]IOS退款程序，支付单号:["
                + aliRefundNeedParam.getPayTransactionId() + "],退款金额:" + aliRefundNeedParam.getRefundAmount().toString());
        if(StringUtils.isEmpty(aliRefundNeedParam.getPayTransactionId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE,"[customer007]", "缺失支付单号");
        }
        if(StringUtils.isEmpty(aliRefundNeedParam.getOrderDetailId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE,"[customer007]", "缺失退款订单明细号");
        }
        if(StringUtils.isEmpty(aliRefundNeedParam.getRefundReason())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE,"[customer008]", "缺失退款原因");
        }
        if(BigDecimal.ZERO.compareTo(aliRefundNeedParam.getRefundAmount()) > -1){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE,"[customer009]", "退款金额为0");
        }

        String tradeNo = "none";
        String payUserId = "none";

        // 补充---此处需要新增根据付款单号查询交易流水号的操作
        PayWaterFlowQueryBO queryWF = new PayWaterFlowQueryBO();
        queryWF.setPayTransactionId(aliRefundNeedParam.getPayTransactionId());
        List<PayWaterFlowVO> waterFlowVOS = payWaterFlowService.queryPayWaterFlow(queryWF);
        if(CollUtil.isEmpty(waterFlowVOS)){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE,"[customer010]", "未查询到对应付款记录，无法退款");
        }
        if(waterFlowVOS.size() > 1){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE,"[customer011]", "对应付款记录大于1条，数据有误，无法退款");
        }
        PayWaterFlowVO waterFlowVO = waterFlowVOS.get(0);
        if(PayModuleConstant.AliPayConstant.PAY_DONE.compareTo(waterFlowVO.getPayStatus()) != 0){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE,"[customer012]", "所选退款记录未完成付款，无法退款");
        }
        if(StringUtils.isEmpty(waterFlowVO.getTransactionId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE,"[customer013]", "丢失平台支付流水号，无法退款");
        }
        tradeNo = waterFlowVO.getTransactionId();
        payUserId = waterFlowVO.getPayUserId();

        BigInteger totalCoinAmount = BigInteger.valueOf(aliRefundNeedParam.getRefundAmount()
                .multiply(BigDecimal.valueOf(100)).longValue());

        CoinAccountInfoEntity coinAccountInfoEntityInfo = iosConsumptionCoinByProductMapper.getCoinAccountInfoEntityInfo(payUserId);
        if(coinAccountInfoEntityInfo == null){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE,"该用户金币账户不存在,退款金币失败");
        }
        // 重置该用户的账户金币余额
        coinAccountInfoEntityInfo.setCoinTotal(coinAccountInfoEntityInfo.getCoinTotal().add(totalCoinAmount));

        String refundCode = this.getTransactionUid("IOSRF");

        // 插入退款记录数据
        PayRefundWaterFlowEntity refundLog = new PayRefundWaterFlowEntity();
        refundLog.setRefundCode(refundCode);
        refundLog.setRefundOrderDetailId(aliRefundNeedParam.getOrderDetailId());
        refundLog.setRefundLaunch(aliRefundNeedParam.getRefundLaunch());
        refundLog.setPayTransactionId(aliRefundNeedParam.getPayTransactionId());
        refundLog.setRefundAmount(aliRefundNeedParam.getRefundAmount());
        refundLog.setRefundReason(aliRefundNeedParam.getRefundReason());
        refundLog.setRefundUserId(payUserId);
        refundLog.setRefundPlat(PayPlatType.IOS.getValue());
        refundLog.setTradeId(tradeNo);
        refundLog.setRefundStatus(PayModuleConstant.AliPayConstant.REFUND_DONE);

        // 插入用户金币支付流水
        CoinTransactionLogEntity coinTransactionLogEntity = new CoinTransactionLogEntity();
        coinTransactionLogEntity.setUserId(payUserId);
        coinTransactionLogEntity.setDelFlag("0");
        // 交易类型，0-收入，1-支出
        coinTransactionLogEntity.setTradeType(0);
        coinTransactionLogEntity.setOrderCode(aliRefundNeedParam.getOrderDetailId());
        coinTransactionLogEntity.setTradeAmount(totalCoinAmount);
        coinTransactionLogEntity.setTradeComment("金币退款，亏款数量为[" + totalCoinAmount +"]"
                + "退款原因：" + aliRefundNeedParam.getRefundReason());

        Boolean successLog = payRefundWaterFlowService.addRefundRecord(refundLog);
        if(!successLog){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE,"新增退款流水出错，IOS金币退款失败");
        }
        // 更新用户金币信息
        try{
            coinAccountInfoMapper.updateById(coinAccountInfoEntityInfo);
            transactionLogMapper.insert(coinTransactionLogEntity);
            paySuccessServiceImpl.confirmRefundMQ(aliRefundNeedParam.getPayTransactionId(),
                    aliRefundNeedParam.getRefundAmount(),
                    aliRefundNeedParam.getOrderDetailId());
            return new ResultEntity<>(HttpStatus.OK,"IOS金币退款完成");
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE,"写入退款数据发生错误");
        }
    }

    /**
     * 判断参数是否为空
     *
     * @param bo
     * @return
     */
    private Boolean judgeEntityParam(IosRequestParamBO bo){

        if(StringUtils.isEmpty(bo.getOrderCode())){
            return false;
        }
        if(StringUtils.isEmpty(bo.getApplePayLoad())){
            return false;
        }
        if(StringUtils.isEmpty(bo.getTransactionId())){
            return false;
        }if(StringUtils.isEmpty(bo.getUserId())){
            return false;
        }
        return true;
    }

    private String getTransactionUid(String type) {
        String uid = uidGeneratorFeign.getUid(type);
        if (StringUtils.isEmpty(uid)) {
            return null;
        }
        String round = String.valueOf(RandomUtil.randomDouble(0, 1, 6, RoundingMode.UP));
        round = round.substring(round.indexOf(".") + 1);
        while (round.length() < 6) {
            round = round + "0";
        }
        return "CP" + uid + round;
    }
}
