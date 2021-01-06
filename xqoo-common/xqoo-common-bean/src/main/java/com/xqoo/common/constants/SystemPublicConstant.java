package com.xqoo.common.constants;

/**
 * 系统共用常量
 */
public class SystemPublicConstant {

    /**
     * token在header中的key名字
     */
    public final static String AUTH_HEADER_KEY_NAME = "xqoo-authorization";

    /**
     * 请求头中增加userId的key名字
     */
    public final static String USER_ID_HEADER_KEY_NAME = "xqoo-uuid";
    /**
     * 请求头中增加userName的key名字
     */
    public final static String USER_NAME_HEADER_KEY_NAME = "xqoo-user-name";

    /**
     * 请求头中增加当前登录人的登录来源
     */
    public final static String USER_LOGIN_SOURCE_HEADER_KEY_NAME = "xqoo-login-source";

    /**
     * 请求头中增加原始访问ip
     */
    public final static String REMOTE_REQUEST_IP = "xqoo-remote-address";

    /**
     * 请求头中增加原始访问端口
     */
    public final static String REMOTE_REQUEST_PORT = "xqoo-remote-address-port";

}
