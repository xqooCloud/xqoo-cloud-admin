package com.xqoo.authorization.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xqoo.authorization.entity.SysRoleMenuEntity;
import com.xqoo.authorization.mapper.SysRoleMenuMapper;
import com.xqoo.authorization.service.SysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色菜单表(SysRoleMenu)表服务实现类
 *
 * @author makejava
 * @since 2020-12-06 19:20:40
 */
@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenuEntity> implements SysRoleMenuService {

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public List<Integer> getRoleMenuIds(List<Integer> roleIds) {
        if(CollUtil.isEmpty(roleIds)){
            return Collections.emptyList();
        }
        List<Integer> menuIds = sysRoleMenuMapper.getRoleMenuIds(roleIds);
        if(CollUtil.isEmpty(menuIds)){
            return Collections.emptyList();
        }
        return menuIds;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean insertList(Integer roleId, List<Integer> menuIds, String userId) throws RuntimeException {
        if(CollUtil.isEmpty(menuIds)){
            return true;
        }
        List<SysRoleMenuEntity> list = menuIds.stream().map(item -> {
            SysRoleMenuEntity entity = new SysRoleMenuEntity();
            entity.setRoleId(roleId);
            entity.setMenuId(item);
            entity.setCreateBy(userId);
            entity.setCreateDate(new Date());
            return entity;
        }).collect(Collectors.toList());
        sysRoleMenuMapper.insertList(list);
        return true;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void updateUserMenuList(Integer roleId, List<Integer> menuIds, String userId) throws RuntimeException {
        LambdaQueryWrapper<SysRoleMenuEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysRoleMenuEntity::getRoleId, roleId);
        List<SysRoleMenuEntity> entityList = sysRoleMenuMapper.selectList(queryWrapper);
        List<Integer> entityMenuIdList = new ArrayList<>();
        List<Integer> removeIdList = new ArrayList<>();
        // menuIds里不存在的原有数据，就是删除数据
        entityList.forEach(item -> {
            if(!menuIds.contains(item.getMenuId())){
                removeIdList.add(item.getId());
            }
            entityMenuIdList.add(item.getMenuId());
        });
        // 原有数据里不存在的menuId,就是新增数据
        List<Integer> willAddList = menuIds.stream().filter(item -> !entityMenuIdList.contains(item)).collect(Collectors.toList());
        // 重新赋值新增数据
        entityList = new ArrayList<>(willAddList.size());
        for (Integer item : willAddList) {
            SysRoleMenuEntity entity = new SysRoleMenuEntity();
            entity.setMenuId(item);
            entity.setRoleId(roleId);
            entity.setCreateBy(userId);
            entity.setCreateDate(new Date());
            entityList.add(entity);
        }
        if(CollUtil.isNotEmpty(entityList)){
            sysRoleMenuMapper.insertList(entityList);
        }
        if(CollUtil.isNotEmpty(removeIdList)){
            queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.in(SysRoleMenuEntity::getId, removeIdList);
            sysRoleMenuMapper.delete(queryWrapper);
        }
    }
}
