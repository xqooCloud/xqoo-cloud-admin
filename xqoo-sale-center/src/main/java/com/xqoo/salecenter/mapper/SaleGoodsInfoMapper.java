package com.xqoo.salecenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xqoo.salecenter.entity.SaleGoodsInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据源表(sale_goods_info)表数据库访问层
 *
 * @author xqoo-code-gen
 * @date 2021-02-01 15:08:34
 */

@Mapper
@Repository
public interface SaleGoodsInfoMapper extends BaseMapper<SaleGoodsInfoEntity> {

    /**
     * 数据源表(sale_goods_info)批量插入
     * @param list
     * @throws RuntimeException
     */
    void insertList(@Param("list") List<SaleGoodsInfoEntity> list) throws RuntimeException;
}
