package com.xqoo.salecenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xqoo.salecenter.entity.CommentContentInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据源表(comment_conten_info)表数据库访问层
 *
 * @author xqoo-code-gen
 * @date 2021-02-01 14:45:10
 */

@Mapper
@Repository
public interface CommentContentInfoMapper extends BaseMapper<CommentContentInfoEntity> {

    /**
     * 数据源表(comment_conten_info)批量插入
     * @param list
     * @throws RuntimeException
     */
    void insertList(@Param("list") List<CommentContentInfoEntity> list) throws RuntimeException;
}
