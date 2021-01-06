package com.xqoo.authorization.service.base;

import com.xqoo.authorization.bo.LoginModelBO;
import com.xqoo.authorization.entity.LoginReturnEntity;
import com.xqoo.common.enums.LoginSourceEnum;
import com.xqoo.authorization.vo.UserOnlinedVO;
import com.xqoo.common.entity.ResultEntity;

import java.util.Map;

public interface AuthorizationBaseService {

    /**
     * 获取token
     * @return
     */
    ResultEntity<LoginReturnEntity> getToken(LoginModelBO bo);


    /**
     * 登出
     * @param token token
     * @param loginSourceEnum
     * @return
     */
    ResultEntity<Map<String, Object>> loginOut(String token, LoginSourceEnum loginSourceEnum);


    /**
     * 变更角色，踢出用户，删除用户，禁用用户时删除他当前token
     */
    boolean removeUserAllTokenByUserId(String userId) throws Exception;

    /**
     * 获取用户是否token存在，存在于哪几个端
     */
    UserOnlinedVO getUserOnlineInfo(String userId);

}
