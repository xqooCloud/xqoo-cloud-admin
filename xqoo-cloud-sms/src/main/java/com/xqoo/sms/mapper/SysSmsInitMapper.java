package com.xqoo.sms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xqoo.sms.entity.SysSmsInitEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * (SysSmsInit)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-12 16:55:18
 */
@Mapper
@Repository
public interface SysSmsInitMapper extends BaseMapper<SysSmsInitEntity> {

}