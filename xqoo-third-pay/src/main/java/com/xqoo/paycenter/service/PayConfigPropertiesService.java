package com.xqoo.paycenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.paycenter.bo.PayConfigPropertiesQueryBO;
import com.xqoo.paycenter.entity.PayConfigPropertiesEntity;
import com.xqoo.paycenter.vo.PayConfigPropertiesVO;

import java.util.List;
import java.util.Map;

/**
 * (PayConfigProperties)表服务接口
 *
 * @author makejava
 * @since 2020-03-14 16:01:09
 */
public interface PayConfigPropertiesService extends IService<PayConfigPropertiesEntity> {

    /**
     * 分页查询参数明细
     * @param queryBO
     * @return
     */
    List<PayConfigPropertiesVO> queryPayConfigProperties(PayConfigPropertiesQueryBO queryBO);

    /**
     * 增加参数明细
     * @param payConfigPropertiesEntity
     * @return
     * @throws Exception
     */
    ResultEntity<PayConfigPropertiesEntity> addPropertiesInfo(PayConfigPropertiesEntity payConfigPropertiesEntity) throws Exception;

    /**
     * 修改参数明细
     * @param payConfigPropertiesEntity
     * @return
     * @throws Exception
     */
    ResultEntity<PayConfigPropertiesEntity> updatePropertiesInfo(PayConfigPropertiesEntity payConfigPropertiesEntity) throws Exception;

    /**
     * 封装支付宝参数明细，传递给bean使用
     * @param type
     * @return
     */
    Map<String, String> getAliConigInit(String type);

    /**
     * 封装微信参数明细，传递给bean使用
     * @param type
     * @return
     */
    Map<String, String> getWxConigInit(String type);

    /**
     * 推送最新参数配置
     * @throws Exception
     */
    void updatePayConfig(String refreshPlat) throws Exception;
}
