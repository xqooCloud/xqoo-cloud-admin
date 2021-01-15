package com.xqoo.paycenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xqoo.paycenter.entity.PayConfigPropertiesEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * (PayConfigProperties)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-14 16:01:09
 */
@Mapper
@Repository
public interface PayConfigPropertiesMapper extends BaseMapper<PayConfigPropertiesEntity> {

}