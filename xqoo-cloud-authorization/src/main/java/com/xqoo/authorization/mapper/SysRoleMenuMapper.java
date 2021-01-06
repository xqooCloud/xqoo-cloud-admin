package com.xqoo.authorization.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xqoo.authorization.entity.SysRoleMenuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色菜单表(SysRoleMenu)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-06 19:20:40
 */
@Mapper
@Repository
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenuEntity> {

    List<Integer> getRoleMenuIds(@Param("roleIds")List<Integer> roleIds);

    void insertList(@Param("list")List<SysRoleMenuEntity> list);
}
