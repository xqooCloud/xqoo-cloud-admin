package com.xqoo.annex.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xqoo.annex.entity.FooterNavGroupEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据源表(footer_nav_group)表数据库访问层
 *
 * @author xqoo-code-gen
 * @date 2021-01-14 15:41:37
 */

@Mapper
@Repository
public interface FooterNavGroupMapper extends BaseMapper<FooterNavGroupEntity> {

    void insertList(@Param("list") List<FooterNavGroupEntity> list) throws RuntimeException;
}
