package com.xqoo.sms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xqoo.sms.entity.SmsSignEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * (SmsSign)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-11 14:41:26
 */
@Mapper
@Repository
public interface SmsSignMapper extends BaseMapper<SmsSignEntity> {

}