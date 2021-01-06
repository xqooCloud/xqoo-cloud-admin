package com.xqoo.authorization.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xqoo.authorization.entity.SysRoleMenuEntity;

import java.util.List;

/**
 * 角色菜单表(SysRoleMenu)表服务接口
 *
 * @author makejava
 * @since 2020-12-06 19:20:40
 */
public interface SysRoleMenuService extends IService<SysRoleMenuEntity> {

    List<Integer> getRoleMenuIds(List<Integer> roleIds);

    boolean insertList(Integer roleId, List<Integer> menuIds, String userId) throws RuntimeException;

    void updateUserMenuList(Integer roleId, List<Integer> menuIds, String userId) throws RuntimeException;
}
