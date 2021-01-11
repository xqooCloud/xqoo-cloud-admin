package com.xqoo.sms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xqoo.sms.entity.SysSmsTemplateEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * (SysSmsTemplate)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-11 14:09:58
 */
@Mapper
@Repository
public interface SysSmsTemplateMapper extends BaseMapper<SysSmsTemplateEntity> {

}