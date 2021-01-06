package com.xqoo.authorization.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xqoo.authorization.entity.SysRoleApiEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色和api接口关联表(SysRoleApi)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-06 19:20:40
 */
@Mapper
@Repository
public interface SysRoleApiMapper extends BaseMapper<SysRoleApiEntity> {

    void insertList(@Param("list") List<SysRoleApiEntity> list);
}
