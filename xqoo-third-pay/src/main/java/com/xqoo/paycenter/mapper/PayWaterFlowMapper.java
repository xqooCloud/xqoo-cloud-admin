package com.xqoo.paycenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xqoo.paycenter.entity.PayWaterFlowEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 支付流水表(PayWaterFlow)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-16 16:45:47
 */
@Mapper
@Repository
public interface PayWaterFlowMapper extends BaseMapper<PayWaterFlowEntity> {

}