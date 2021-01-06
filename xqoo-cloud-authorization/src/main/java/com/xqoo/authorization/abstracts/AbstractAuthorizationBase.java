package com.xqoo.authorization.abstracts;

import com.xqoo.authorization.bo.LoginModelBO;
import com.xqoo.common.enums.LoginSourceEnum;
import com.xqoo.authorization.pojo.ValidateLoginEffectivePOJO;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.pojo.LoginUserInfoPOJO;

public abstract class AbstractAuthorizationBase {

    /**
     * 校验逻辑
     * @param bo
     * @return
     */
    protected abstract ValidateLoginEffectivePOJO validateLoginEffective(LoginModelBO bo);

    /**
     * 校验登录是否在线
     * @param effective
     * @param loginSourceEnum
     */
    protected abstract ValidateLoginEffectivePOJO checkTokenOnlined(ValidateLoginEffectivePOJO effective, LoginSourceEnum loginSourceEnum);
    /**
     * 生成登录token
     * @param effective
     * @return
     */
    protected abstract String generatorToken(ValidateLoginEffectivePOJO effective, LoginSourceEnum loginSource);

    /**
     * 生成jwt的令牌
     * @return
     */
    protected abstract String generatorJtwToken(CurrentUser currentUser);

    /**
     * 获取当前登录人的概要信息
     * @param userId
     * @return
     */
    protected abstract LoginUserInfoPOJO getLoginUserInfo(String userId);
}
