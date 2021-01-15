package com.xqoo.paycenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xqoo.common.page.PageResponseBean;
import com.xqoo.paycenter.bo.PayWaterFlowQueryBO;
import com.xqoo.paycenter.entity.PayWaterFlowEntity;
import com.xqoo.paycenter.vo.PayWaterFlowVO;

import java.util.List;

/**
 * 支付流水表(PayWaterFlow)表服务接口
 *
 * @author makejava
 * @since 2020-03-16 16:45:47
 */
public interface PayWaterFlowService extends IService<PayWaterFlowEntity> {

    /**
     * 分页查询付款记录
     * @param queryBO
     * @return
     */
    PageResponseBean<PayWaterFlowVO> queryPagePayWaterFlow(PayWaterFlowQueryBO queryBO);

    /**
     * 查询付款记录实体List
     * @param queryBO
     * @return
     */
    List<PayWaterFlowVO> queryPayWaterFlow(PayWaterFlowQueryBO queryBO);

    /**
     * 新增付款记录
     * @param entity
     * @return
     */
    Boolean addPayRecord(PayWaterFlowEntity entity);

    /**
     * 修改单条记录部分字段
     * @param entity
     * @return
     */
    Boolean updatePayWaterFlowSingle(PayWaterFlowEntity entity);
}
