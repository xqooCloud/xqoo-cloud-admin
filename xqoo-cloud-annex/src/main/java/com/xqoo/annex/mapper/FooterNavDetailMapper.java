package com.xqoo.annex.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xqoo.annex.entity.FooterNavDetailEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据源表(footer_nav_detail)表数据库访问层
 *
 * @author xqoo-code-gen
 * @date 2021-01-14 15:29:04
 */

@Mapper
@Repository
public interface FooterNavDetailMapper extends BaseMapper<FooterNavDetailEntity> {

    void insertList(@Param("list") List<FooterNavDetailEntity> list) throws RuntimeException;
}