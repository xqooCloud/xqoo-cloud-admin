package com.xqoo.paycenter.controller;

import com.xqoo.common.entity.ResultEntity;
import com.xqoo.paycenter.bo.WxPayNeedParam;
import com.xqoo.paycenter.bo.WxRefundNeedParam;
import com.xqoo.paycenter.exception.WechatAppPayServiceException;
import com.xqoo.paycenter.service.WxPayBusinessService;
import com.xqoo.paycenter.utils.WxPayUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.jdom.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

@RestController(value = "WxPayBusinessController")
@RequestMapping(value = "/wxPay")
@Api(tags = "支付模块微信支付业务控制器")
public class WxPayBusinessController {

    @Autowired
    private WxPayBusinessService wxPayBusinessService;

    @ApiOperation(value = "微信pc预支付，返回支付二维码")
    @PostMapping(value = "/pcPay")
    public ResultEntity wxPcPay(@RequestBody WxPayNeedParam wxPayNeedParam){
        if(BigDecimal.ZERO.compareTo(wxPayNeedParam.getPayAmount()) > -1){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "支付失败，支付金额为0");
        }
        if(StringUtils.isEmpty(wxPayNeedParam.getPayTransactionId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "支付失败，缺失支付单号");
        }
        if(StringUtils.isEmpty(wxPayNeedParam.getUserId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "支付失败，缺失支付人编号");
        }
        if(StringUtils.isEmpty(wxPayNeedParam.getDeviceType())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "支付失败，缺失支付设备参数");
        }
        if(StringUtils.isEmpty(wxPayNeedParam.getUserName())){
            wxPayNeedParam.setUserName("佚名");
        }
        if(StringUtils.isEmpty(wxPayNeedParam.getPayComment())){
            wxPayNeedParam.setPayComment("微信付款程序[贵州兴黔科技有限公司提供]");
        }
        try{
            return wxPayBusinessService.wxPcPay(wxPayNeedParam);
        }catch (WechatAppPayServiceException e){
            System.out.print("[支付模块][Exception]微信支付时发生错误，错误单号[" +
                    wxPayNeedParam.getPayTransactionId() + "]，错误信息：[" + e.getErrorCode().value() + "]" + e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "支付失败，发生内部错误" +
                    "错误信息：[" + e.getErrorCode().value() + "]" + e.getMessage());
        }
    }

    @ApiOperation(value = "微信APP预支付")
    @PostMapping(value = "/appPay")
    public ResultEntity wxAppPay(@RequestBody WxPayNeedParam wxPayNeedParam){
        if(BigDecimal.ZERO.compareTo(wxPayNeedParam.getPayAmount()) > -1){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "支付失败，支付金额为0");
        }
        if(StringUtils.isEmpty(wxPayNeedParam.getPayTransactionId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "支付失败，缺失支付单号");
        }
        if(StringUtils.isEmpty(wxPayNeedParam.getUserId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "支付失败，缺失支付人编号");
        }
        if(StringUtils.isEmpty(wxPayNeedParam.getDeviceType())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "支付失败，缺失支付设备参数");
        }
        if(StringUtils.isEmpty(wxPayNeedParam.getUserName())){
            wxPayNeedParam.setUserName("佚名");
        }
        if(StringUtils.isEmpty(wxPayNeedParam.getPayComment())){
            wxPayNeedParam.setPayComment("微信付款程序[贵州兴黔科技有限公司提供]");
        }
        try{
            return wxPayBusinessService.wxAppPay(wxPayNeedParam);
        }catch (WechatAppPayServiceException e){
            System.out.print("[支付模块][Exception]微信支付时发生错误，错误单号[" +
                    wxPayNeedParam.getPayTransactionId() + "]，错误信息：[" + e.getErrorCode().value() + "]" + e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "支付失败，发生内部错误" +
                    "错误信息：[" + e.getErrorCode().value() + "]" + e.getMessage());
        }
    }

    @ApiOperation("微信公众号JSAPI支付预下单接口")
    @PostMapping("/JSAPIPay")
    public ResultEntity JSAPIPay(@RequestBody WxPayNeedParam wxPayNeedParam){
        if(wxPayNeedParam.getPayAmount().compareTo(BigDecimal.ZERO) < 1){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "支付金额为0，发起支付失败");
        }
        if(StringUtils.isEmpty(wxPayNeedParam.getPayTransactionId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "丢失系统支付单号，发起支付失败");
        }
        if(StringUtils.isEmpty(wxPayNeedParam.getDeviceType())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "丢失支付平台，发起支付失败");
        }
        if(StringUtils.isEmpty(wxPayNeedParam.getUserId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "丢失用户id，发起支付失败");
        }
        return wxPayBusinessService.wxJsApiPay(wxPayNeedParam);
    }

    @ApiOperation(value = "微信退款链接")
    @PostMapping(value = "/wxPayRefund")
    public ResultEntity wxPayRefund(@RequestBody Map<String, Object> refundMap) {
        WxRefundNeedParam wxRefundNeedParam = new WxRefundNeedParam();
        wxRefundNeedParam.fromMap(refundMap);
        try {
            return wxPayBusinessService.wxRefundPay(wxRefundNeedParam);
        } catch (WechatAppPayServiceException e) {
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        }
    }

    @ApiOperation(value = "微信返回支付结果链接")
    @PostMapping(value = "/wxPayNotify")
    public String wxPayNotify(HttpServletRequest request, HttpServletResponse response) {
        try {
            Map<String, Object> params = WxPayUtil.doXMLParse(request);
            Boolean result = wxPayBusinessService.wxPayNotifyNotice(params);
            if(result){
                return "SUCCESS";
            }
            return "FAIL";
        } catch (JDOMException | IOException e) {
            e.printStackTrace();
            return "FAIL";
        }
    }
}
