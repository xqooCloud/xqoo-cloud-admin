package com.xqoo.paycenter.controller;

import com.xqoo.common.entity.ResultEntity;
import com.xqoo.paycenter.bo.AliRefundNeedParam;
import com.xqoo.paycenter.bo.goldcoin.IosConsumptionCoinByProductParamBO;
import com.xqoo.paycenter.bo.goldcoin.IosRequestParamBO;
import com.xqoo.paycenter.service.IosPayBusinessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author liangyongpeng
 * @create 2020/5/3 13:01
 */
@Api(tags = {
        "苹果进出帐接口控制层"
})
@RestController
@RequestMapping(value = "/iosRecharge")
public class IosPayBusinessController {

    @Autowired
    private IosPayBusinessService iosPayBusinessService;

    @ApiOperation(value = "IOS充值接口")
    @PostMapping(value = "/doIosRequest")
    public ResultEntity doIosRequest(@RequestBody IosRequestParamBO bo){

        return iosPayBusinessService.doIosRequest(bo);

    }

    @ApiOperation(value = "IOS金币购买接口")
    @PostMapping(value = "/iosConsumptionCoinByProduct")
    public ResultEntity iosConsumptionCoinByProduct(@RequestBody IosConsumptionCoinByProductParamBO bo){

        if(StringUtils.isEmpty(bo.getUserId())){
            return new ResultEntity(HttpStatus.NOT_ACCEPTABLE,"用户id是必须的参数!");
        }
        if(StringUtils.isEmpty(bo.getPayTransactionId())){
            return new ResultEntity(HttpStatus.NOT_ACCEPTABLE,"支付单号是必须的参数!");
        }
        if(bo.getTotalCoinConsumption() == null){
            return new ResultEntity(HttpStatus.NOT_ACCEPTABLE,"订单支付价格是必须的参数!");
        }

        return iosPayBusinessService.iosConsumptionCoinByProduct(bo);

    }

    @ApiOperation(value = "IOS退款链接")
    @PostMapping(value = "/IOSPayRefund")
    public ResultEntity IOSPayRefund(@RequestBody Map<String, Object> refundMap) {
        AliRefundNeedParam payBo = new AliRefundNeedParam();
        payBo.fromMap(refundMap);
        return iosPayBusinessService.iosRefunded(payBo);
    }
}
