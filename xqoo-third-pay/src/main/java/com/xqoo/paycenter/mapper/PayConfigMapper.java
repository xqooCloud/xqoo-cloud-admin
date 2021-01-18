package com.xqoo.paycenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xqoo.paycenter.entity.PayConfigEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 支付配置总表(PayConfig)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-14 16:01:07
 */
@Mapper
@Repository
public interface PayConfigMapper extends BaseMapper<PayConfigEntity> {

}