package com.xqoo.sms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xqoo.sms.entity.SendLogEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * (SendLog)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-12 11:48:40
 */
@Mapper
@Repository
public interface SendLogMapper extends BaseMapper<SendLogEntity> {

}