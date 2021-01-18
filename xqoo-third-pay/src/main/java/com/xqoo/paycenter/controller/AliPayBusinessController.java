package com.xqoo.paycenter.controller;

import com.alipay.api.AlipayApiException;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.paycenter.bo.AliPayNeedParam;
import com.xqoo.paycenter.bo.AliRefundNeedParam;
import com.xqoo.paycenter.service.AliPayBusinessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController(value = "AliPayBusinessController")
@RequestMapping(value = "/aliPay")
@Api(tags = "支付模块支付宝支付业务控制器")
public class AliPayBusinessController {

    @Autowired
    private AliPayBusinessService aliPayBusinessService;

    @ApiOperation(value = "阿里pc预支付，返回支付二维码")
    @PostMapping(value = "/pcPay")
    public ResultEntity AliPcPay(@RequestBody AliPayNeedParam aliPayNeedParam){
        if(BigDecimal.ZERO.compareTo(aliPayNeedParam.getPayAmount()) > -1){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "支付失败，支付金额为0");
        }
        if(StringUtils.isEmpty(aliPayNeedParam.getPayTransactionId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "支付失败，缺失支付单号");
        }
        if(StringUtils.isEmpty(aliPayNeedParam.getUserId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "支付失败，缺失支付人编号");
        }
        if(StringUtils.isEmpty(aliPayNeedParam.getDeviceType())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "支付失败，缺失支付设备参数");
        }
        if(StringUtils.isEmpty(aliPayNeedParam.getUserName())){
            aliPayNeedParam.setUserName("佚名");
        }
        if(StringUtils.isEmpty(aliPayNeedParam.getPayComment())){
            aliPayNeedParam.setPayComment("支付宝付款程序[贵州兴黔科技有限公司提供]");
        }
        try{
            return aliPayBusinessService.aliPay(aliPayNeedParam, "PC");
        }catch (AlipayApiException e){
            System.out.print("[支付模块][Exception]支付宝支付时发生错误，错误单号[" +
                    aliPayNeedParam.getPayTransactionId() + "]，错误信息：[" + e.getErrCode() + "]" + e.getErrMsg());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "支付失败，发生内部错误" +
                    "错误信息：[" + e.getErrCode() + "]" + e.getErrMsg());
        }
    }

    @ApiOperation(value = "阿里APP预支付，返回支付二维码")
    @PostMapping(value = "/appPay")
    public ResultEntity AliAppPay(@RequestBody AliPayNeedParam aliPayNeedParam){
        if(BigDecimal.ZERO.compareTo(aliPayNeedParam.getPayAmount()) > -1){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "支付失败，支付金额为0");
        }
        if(StringUtils.isEmpty(aliPayNeedParam.getPayTransactionId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "支付失败，缺失支付单号");
        }
        if(StringUtils.isEmpty(aliPayNeedParam.getUserId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "支付失败，缺失支付人编号");
        }
        if(StringUtils.isEmpty(aliPayNeedParam.getDeviceType())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "支付失败，缺失支付设备参数");
        }
        if(StringUtils.isEmpty(aliPayNeedParam.getUserName())){
            aliPayNeedParam.setUserName("佚名");
        }
        if(StringUtils.isEmpty(aliPayNeedParam.getPayComment())){
            aliPayNeedParam.setPayComment("支付宝付款程序[贵州兴黔科技有限公司提供]");
        }
        try{
            return aliPayBusinessService.aliPayApp(aliPayNeedParam,"APP");
        }catch (AlipayApiException e){
            System.out.print("[支付模块][Exception]支付宝支付时发生错误，错误单号[" +
                    aliPayNeedParam.getPayTransactionId() + "]，错误信息：[" + e.getErrCode() + "]" + e.getErrMsg());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "支付失败，发生内部错误" +
                    "错误信息：[" + e.getErrCode() + "]" + e.getErrMsg());
        }
    }

    @ApiOperation(value = "支付宝退款链接")
    @PostMapping(value = "/aliPayRefund")
    public ResultEntity aliPayRefund(@RequestBody Map<String, Object> refundMap) {
        AliRefundNeedParam aliRefundNeedParam = new AliRefundNeedParam();
        aliRefundNeedParam.fromMap(refundMap);
        try {
            return aliPayBusinessService.aliRefundPay(aliRefundNeedParam);
        } catch (AlipayApiException e) {
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, e.getErrMsg());
        }
    }

    @ApiOperation(value = "支付宝返回支付结果链接")
    @PostMapping(value = "/aliPayNotify")
    public String aliPayNotify(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
        System.out.println(trade_status);
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Object o : requestParams.keySet()) {
            String name = (String) o;
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        Boolean handleOk = false;
        if ("TRADE_SUCCESS".equals(trade_status)) {

            handleOk = aliPayBusinessService.aliPayNotifyNotice(params);
        }
        if(handleOk) {
            return "success";
        }else{
            return "fail";
        }
    }

}
