package com.xqoo.codegen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xqoo.codegen.entity.DataSourceInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 数据源表(DataSourceInfo)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-23 13:37:39
 */
@Mapper
@Repository
public interface DataSourceInfoMapper extends BaseMapper<DataSourceInfoEntity> {

}
