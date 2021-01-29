package com.xqoo.device.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xqoo.device.entity.ScreenBaseInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据源表(screen_base_info)表数据库访问层
 *
 * @author xqoo-code-gen
 * @date 2021-01-27 10:50:14
 */

@Mapper
@Repository
public interface ScreenBaseInfoMapper extends BaseMapper<ScreenBaseInfoEntity> {

    /**
     * 数据源表(screen_base_info)批量插入
     * @param list
     * @throws RuntimeException
     */
    void insertList(@Param("list") List<ScreenBaseInfoEntity> list) throws RuntimeException;
}
