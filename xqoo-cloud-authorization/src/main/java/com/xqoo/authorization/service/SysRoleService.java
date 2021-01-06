package com.xqoo.authorization.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xqoo.authorization.bo.QueryRoleBO;
import com.xqoo.authorization.entity.SysRoleEntity;
import com.xqoo.authorization.vo.SysRoleDetailVO;
import com.xqoo.authorization.vo.SysRolePageInfoVO;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.entity.ResultEntity;

import java.util.List;

/**
 * 角色表(SysRole)表服务接口
 *
 * @author makejava
 * @since 2020-12-06 19:20:40
 */
public interface SysRoleService extends IService<SysRoleEntity> {

    /**
     * 分页获取角色新吸
     */
    ResultEntity<SysRolePageInfoVO> pageGetRoleInfo(QueryRoleBO bo, CurrentUser currentUser);

    /**
     * 获取角色明细信息
     * @param roleId
     * @return
     */
    ResultEntity<SysRoleDetailVO> getRoleDetail(Integer roleId);

    /**
     * 更新或删除角色信息
     */
    ResultEntity updateRoleInfo(SysRoleDetailVO vo, CurrentUser currentUser);

    /**
     * 删除角色
     * @param roleId
     * @param currentUser
     * @return
     */
    ResultEntity removeRoleInfo(Integer roleId, CurrentUser currentUser);


    /**
     * 获取用户没有的角色统计
     * @param userId
     * @return
     */
    Integer getUserNoRoleCount(String userId);

    /**
     * 获取用户没有的角色列表
     * @param userId
     * @return
     */
    List<SysRoleEntity> getUserNoRole(String userId, Integer page, Integer pageSize);

}
