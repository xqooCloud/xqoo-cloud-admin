package com.xqoo.paycenter.constant;


/**
 * 支付模块常量
 */
public class PayModuleConstant {

    /**
     * 当前版本配置
     */
    public final static Integer ACTIVE_CONFIG = 1;
    /**
     * 历史版本配置
     */
    public final static Integer HISTORY_CONFIG = 0;

    /**
     * 配置模板状态-草稿
     */
    public final static Integer CONFIG_DRAFT = 0;
    /**
     * 配置模板状态-已发布
     */
    public final static Integer CONFIG_PUBLIC = 1;
    /**
     * 配置模板状态-已废弃
     */
    public final static Integer CONFIG_CANCEL = 2;

    /**
     * 支付宝的常量
     */
    public static class AliPayConstant{
        /**
         * 已付款
         */
        public final static Integer PAY_DONE = 1;
        /**
         * 未付款
         */
        public final static Integer NOT_PAY_YET = 0;
        /**
         * 退款完成
         */
        public final static Integer REFUND_DONE = 1;
        /**
         * 退款失败
         */
        public final static Integer REFUND_FAIL = 0;
    }
}
