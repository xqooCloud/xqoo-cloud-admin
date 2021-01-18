package com.xqoo.paycenter.service;

import com.xqoo.common.entity.ResultEntity;
import com.xqoo.paycenter.bo.AliRefundNeedParam;
import com.xqoo.paycenter.bo.goldcoin.IosConsumptionCoinByProductParamBO;
import com.xqoo.paycenter.bo.goldcoin.IosRequestParamBO;

/**
 * @ClassName: IosPayBusinessService
 * @Description: 苹果支付服务接口
 * @Author: liangyongpeng
 * @Date: 2020/4/29 16:55
 **/
public interface IosPayBusinessService {

    /**
     * IOS客户端支付
     *
     * @param bo
     * @return
     */
    ResultEntity doIosRequest(IosRequestParamBO bo);


    /**
     * IOS 金币购买产品
     *
     * @param bo 请求参数
     * @return
     */
    ResultEntity iosConsumptionCoinByProduct(IosConsumptionCoinByProductParamBO bo);


    ResultEntity iosRefunded(AliRefundNeedParam param);

}
