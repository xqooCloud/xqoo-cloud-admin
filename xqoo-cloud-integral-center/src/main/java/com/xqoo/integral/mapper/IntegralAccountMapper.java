package com.xqoo.integral.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xqoo.integral.entity.IntegralAccountEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据源表(integral_account)表数据库访问层
 *
 * @author xqoo-code-gen
 * @date 2021-01-26 11:17:12
 */

@Mapper
@Repository
public interface IntegralAccountMapper extends BaseMapper<IntegralAccountEntity> {

    /**
     * 数据源表(integral_account)批量插入
     * @param list
     * @throws RuntimeException
     */
    void insertList(@Param("list") List<IntegralAccountEntity> list) throws RuntimeException;
}
