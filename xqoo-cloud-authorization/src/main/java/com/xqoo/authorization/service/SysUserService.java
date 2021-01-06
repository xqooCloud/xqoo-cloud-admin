package com.xqoo.authorization.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xqoo.authorization.bo.AddUserInfoBO;
import com.xqoo.authorization.bo.QueryUserInfoBO;
import com.xqoo.authorization.entity.SysUserEntity;
import com.xqoo.authorization.vo.UserInfoVO;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageResponseBean;

/**
 * 系统用户表(SysUser)表服务接口
 *
 * @author makejava
 * @since 2020-11-23 19:13:24
 */
public interface SysUserService extends IService<SysUserEntity> {

    SysUserEntity GetOnlyOneByLoginId(String loginId);

    void SetLastLoginTime(String userId, Long loginTime);

    ResultEntity<PageResponseBean<UserInfoVO>>pageGetUserInfo(QueryUserInfoBO bo);

    ResultEntity addUserInfo(AddUserInfoBO bo);

    boolean loginIdExists(String loginId);

    ResultEntity delUserInfo(String userId);

    ResultEntity updateUserStatus(String userId, String type);
}
