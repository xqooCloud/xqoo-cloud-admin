package com.xqoo.authorization.service.base;

import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.dto.AuthenDTO;

public interface AuthenticationBaseService {

    /**
     * 登录检验
     * @param token
     * @return
     */
    AuthenDTO loginCheck(String token);

    /**
     * 资源权限检验
     * @param userId
     * @return
     */
    AuthenDTO resourceCheck(String userId);

    /**
     * 刷新token持续时间
     * @param token
     */
    void refreshTokenExpire(String token, CurrentUser currentUser);

    /**
     * 获取当前用户基本信息
     * @param token
     * @return
     */
    CurrentUser getUserBaseInfo(String token);

    /**
     * 获取新的jwt令牌
     * @param currentUser
     * @return
     */
    String getNewJwtToken(CurrentUser currentUser);
}
