package com.xqoo.filemanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xqoo.filemanager.entity.FileRecordEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据源表(file_record)表数据库访问层
 *
 * @author xqoo-code-gen
 * @date 2021-01-22 23:37:25
 */

@Mapper
@Repository
public interface FileRecordMapper extends BaseMapper<FileRecordEntity> {

    /**
     * 数据源表(file_record)批量插入
     * @param list
     * @throws RuntimeException
     */
    void insertList(@Param("list") List<FileRecordEntity> list) throws RuntimeException;
}
