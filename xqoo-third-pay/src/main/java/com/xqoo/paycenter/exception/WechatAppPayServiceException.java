package com.xqoo.paycenter.exception;

import com.xqoo.common.core.exception.BusinessException;

/**
 * @ClassName: WechatAppPayServiceException
 * @Description: 微信支付异常
 * @Author: wangxiaobo
 * @Date: 2019/11/15 16:12
 * @Verison 1.0
 **/
public class WechatAppPayServiceException extends BusinessException {

    private static final long serialVersionUID = -7723215745332446916L;

    public WechatAppPayServiceException() {
        super();
    }

    public WechatAppPayServiceException(String message, Throwable cause,
                                        boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public WechatAppPayServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public WechatAppPayServiceException(String message) {
        super(message);
    }

    public WechatAppPayServiceException(Throwable cause) {
        super(cause);
    }
}
