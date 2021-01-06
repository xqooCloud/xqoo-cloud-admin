package com.xqoo.authorization.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xqoo.authorization.entity.SysApiEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 用户接口资源(SysApi)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-06 19:20:39
 */
@Mapper
@Repository
public interface SysApiMapper extends BaseMapper<SysApiEntity> {

}