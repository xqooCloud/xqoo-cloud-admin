package com.xqoo.sms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xqoo.sms.entity.ErrCodeMessageEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * (ErrCodeMessage)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-12 14:28:27
 */
@Mapper
@Repository
public interface ErrCodeMessageMapper extends BaseMapper<ErrCodeMessageEntity> {

}