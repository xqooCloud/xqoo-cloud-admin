package com.xqoo.authorization.constants;

/**
 * 授权中心常量
 * @author gaoyang
 */
public class AuthorizationCenterConstant {

    // 超级管理员默认userId
    public final static String SUPER_ADMIN_UID = "1";

    // token 前缀
    public final static String TOKEN_PREFIX = "auth:";
    // 在线用户token前缀
    public final static String LOGINED_USER = "logined_user:";

    // 登录异常锁定前缀
    public final static String LOCK_LOGIN = "lock_login:";

    // errCode 前缀
    public final static String ERR_CODE_PREFIX = "login_err_code:";

    // 注册手机验证码前缀
    public final static String REGISTER_CODE_PREFIX = "register_code:";

    public final static String DEFAULT_AVATAR = "https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png";
}
