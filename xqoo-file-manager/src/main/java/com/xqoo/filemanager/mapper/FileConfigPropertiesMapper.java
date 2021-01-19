package com.xqoo.filemanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xqoo.filemanager.entity.FileConfigPropertiesEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据源表(file_config_properties)表数据库访问层
 *
 * @author xqoo-code-gen
 * @date 2021-01-19 20:48:15
 */

@Mapper
@Repository
public interface FileConfigPropertiesMapper extends BaseMapper<FileConfigPropertiesEntity> {

    /**
     * 数据源表(file_config_properties)批量插入
     * @param list
     * @throws RuntimeException
     */
    void insertList(@Param("list") List<FileConfigPropertiesEntity> list) throws RuntimeException;
}
