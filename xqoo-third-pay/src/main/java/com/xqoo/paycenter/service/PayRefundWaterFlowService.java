package com.xqoo.paycenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xqoo.common.page.PageResponseBean;
import com.xqoo.paycenter.bo.PayRefundWaterFlowQueryBO;
import com.xqoo.paycenter.entity.PayRefundWaterFlowEntity;
import com.xqoo.paycenter.vo.PayRefundWaterFlowVO;

import java.util.List;

/**
 * 退款记录流水表(PayRefundWaterFlow)表服务接口
 *
 * @author makejava
 * @since 2020-03-16 16:45:47
 */
public interface PayRefundWaterFlowService extends IService<PayRefundWaterFlowEntity> {

    /**
     * 分页查询退款记录
     * @param queryBO
     * @return
     */
    PageResponseBean<PayRefundWaterFlowVO> queryPageRefundWaterFlow(PayRefundWaterFlowQueryBO queryBO);

    /**
     * 查询退款记录实体List
     * @param queryBO
     * @return
     */
    List<PayRefundWaterFlowVO> queryRefundWaterFlow(PayRefundWaterFlowQueryBO queryBO);

    /**
     * 新增退款记录
     * @param entity
     * @return
     */
    Boolean addRefundRecord(PayRefundWaterFlowEntity entity);

}
