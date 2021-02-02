package com.xqoo.salecenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xqoo.salecenter.entity.SaleDetailInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据源表(sale_detail_info)表数据库访问层
 *
 * @author xqoo-code-gen
 * @date 2021-02-01 15:07:13
 */

@Mapper
@Repository
public interface SaleDetailInfoMapper extends BaseMapper<SaleDetailInfoEntity> {

    /**
     * 数据源表(sale_detail_info)批量插入
     * @param list
     * @throws RuntimeException
     */
    void insertList(@Param("list") List<SaleDetailInfoEntity> list) throws RuntimeException;
}
