package com.xqoo.authorization.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xqoo.authorization.entity.SysRoleApiEntity;

import java.util.List;

/**
 * 角色和api接口关联表(SysRoleApi)表服务接口
 *
 * @author makejava
 * @since 2020-12-06 19:20:40
 */
public interface SysRoleApiService extends IService<SysRoleApiEntity> {

    boolean insertList(Integer roleId, List<Integer> apiIds, String userId) throws RuntimeException;

    void updateUserApiList(Integer roleId, List<Integer> apiIds, String userId) throws RuntimeException;
}
