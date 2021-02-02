package com.xqoo.salecenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xqoo.salecenter.entity.GoodsPropertiesEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据源表(goods_properties)表数据库访问层
 *
 * @author xqoo-code-gen
 * @date 2021-02-01 15:02:42
 */

@Mapper
@Repository
public interface GoodsPropertiesMapper extends BaseMapper<GoodsPropertiesEntity> {

    /**
     * 数据源表(goods_properties)批量插入
     * @param list
     * @throws RuntimeException
     */
    void insertList(@Param("list") List<GoodsPropertiesEntity> list) throws RuntimeException;
}
