package com.xqoo.paycenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xqoo.paycenter.entity.PayRefundWaterFlowEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 退款记录流水表(PayRefundWaterFlow)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-16 16:45:47
 */
@Mapper
@Repository
public interface PayRefundWaterFlowMapper extends BaseMapper<PayRefundWaterFlowEntity> {

}