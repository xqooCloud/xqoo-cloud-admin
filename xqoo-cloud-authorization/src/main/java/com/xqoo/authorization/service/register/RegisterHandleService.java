package com.xqoo.authorization.service.register;

import com.xqoo.authorization.bo.NormalRegisterBO;
import com.xqoo.common.entity.ResultEntity;

/**
 * @author gaoyang
 */
public interface RegisterHandleService {

    /**
     * 获取短信验证码
     * @param phonenumber
     * @param tmpCode
     * @param requestIp
     * @return
     */
    ResultEntity<String> getRegisterPhoneCode(String phonenumber, String tmpCode, String requestIp);

    /**
     * 普通账号密码注册
     * @param bo
     * @return
     */
    ResultEntity<String> normalRegister(NormalRegisterBO bo);
}
