package com.xqoo.email.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xqoo.email.entity.EmailConfigEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据源表(email_config)表数据库访问层
 *
 * @author xqoo-code-gen
 * @date 2021-01-11 16:52:01
 */

@Mapper
@Repository
public interface EmailConfigMapper extends BaseMapper<EmailConfigEntity> {

    void insertList(@Param("list") List<EmailConfigEntity> list) throws RuntimeException;
}
