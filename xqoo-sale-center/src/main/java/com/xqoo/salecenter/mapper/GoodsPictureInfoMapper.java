package com.xqoo.salecenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xqoo.salecenter.entity.GoodsPictureInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据源表(goods_picture_info)表数据库访问层
 *
 * @author xqoo-code-gen
 * @date 2021-02-01 15:01:19
 */

@Mapper
@Repository
public interface GoodsPictureInfoMapper extends BaseMapper<GoodsPictureInfoEntity> {

    /**
     * 数据源表(goods_picture_info)批量插入
     * @param list
     * @throws RuntimeException
     */
    void insertList(@Param("list") List<GoodsPictureInfoEntity> list) throws RuntimeException;
}
