package com.xqoo.salecenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xqoo.salecenter.entity.GoodsCommentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据源表(goods_comment)表数据库访问层
 *
 * @author xqoo-code-gen
 * @date 2021-02-01 14:46:30
 */

@Mapper
@Repository
public interface GoodsCommentMapper extends BaseMapper<GoodsCommentEntity> {

    /**
     * 数据源表(goods_comment)批量插入
     * @param list
     * @throws RuntimeException
     */
    void insertList(@Param("list") List<GoodsCommentEntity> list) throws RuntimeException;
}
