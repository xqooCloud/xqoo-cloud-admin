package com.xqoo.email.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xqoo.email.entity.EmailTemplateEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据源表(email_template)表数据库访问层
 *
 * @author xqoo-code-gen
 * @date 2021-01-11 17:06:49
 */

@Mapper
@Repository
public interface EmailTemplateMapper extends BaseMapper<EmailTemplateEntity> {

    void insertList(@Param("list") List<EmailTemplateEntity> list) throws RuntimeException;
}
