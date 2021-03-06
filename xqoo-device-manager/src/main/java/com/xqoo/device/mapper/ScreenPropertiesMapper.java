package com.xqoo.device.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xqoo.device.entity.ScreenPropertiesEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 数据源表(screen_properties)表数据库访问层
 *
 * @author xqoo-code-gen
 * @date 2021-01-28 15:12:35
 */

@Mapper
@Repository
public interface ScreenPropertiesMapper extends BaseMapper<ScreenPropertiesEntity> {

    /**
     * 数据源表(screen_properties)批量插入
     * @param list
     * @throws RuntimeException
     */
    void insertList(@Param("list") List<ScreenPropertiesEntity> list) throws RuntimeException;

    /**
     * 根据parentId获取所有id列表
     * @param parentId
     * @return
     */
    List<Long> getIdsByParentId(@PathVariable("parentId") String parentId);
}
