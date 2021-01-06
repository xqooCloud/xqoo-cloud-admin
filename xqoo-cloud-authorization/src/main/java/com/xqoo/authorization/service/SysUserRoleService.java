package com.xqoo.authorization.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xqoo.authorization.bo.AddUserRoleInfoBO;
import com.xqoo.authorization.bo.SysUserRoleInfoBO;
import com.xqoo.authorization.entity.SysUserRoleEntity;
import com.xqoo.authorization.vo.SysUserRoleDetailVO;
import com.xqoo.authorization.vo.UserHaveNotRoleVO;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.entity.ResultEntity;

import java.util.List;

/**
 * 用户角色表(SysUserRole)表服务接口
 *
 * @author makejava
 * @since 2020-12-06 19:20:40
 */
public interface SysUserRoleService extends IService<SysUserRoleEntity> {

    SysUserRoleInfoBO getUserRoleMap(String userId);

    Integer hasUserBindRole(Integer roleId);

    ResultEntity<SysUserRoleDetailVO> getUserRoleDetailInfo(String userId);

    ResultEntity delUserRoleByList(List<Integer> list, String userId);

    ResultEntity addListRole(AddUserRoleInfoBO bo, CurrentUser currentUser);

    ResultEntity<UserHaveNotRoleVO> getUserNoRoles(String userId, Integer page, Integer pageSize);
}
