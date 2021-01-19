package com.xqoo.filemanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xqoo.filemanager.entity.FileManagerConfigEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据源表(file_manager_config)表数据库访问层
 *
 * @author xqoo-code-gen
 * @date 2021-01-19 20:16:13
 */

@Mapper
@Repository
public interface FileManagerConfigMapper extends BaseMapper<FileManagerConfigEntity> {

    /**
     * 批量新增
     * @param list
     * @throws RuntimeException
     */
    void insertList(@Param("list") List<FileManagerConfigEntity> list) throws RuntimeException;
}
