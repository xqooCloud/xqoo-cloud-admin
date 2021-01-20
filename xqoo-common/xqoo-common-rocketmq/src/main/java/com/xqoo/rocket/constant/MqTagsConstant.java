package com.xqoo.rocket.constant;

/**
 * 各模块所需消息主题下tag常量，用到时增加
 */
public class MqTagsConstant {

    /**
     * 文件管理模块消息主题名
     */

    public final static String FILE_MANAGER_TOPIC = "FILE_MANAGER_TOPIC";

    /**
     * 文件管理模块消息主题tag
     */
    public static class FileModuleTopicTags{
        /**
         * 刷新文件模块配置
         */
        public final static String CONFIG_REFRESH = "configRefresh";
    }

    /**
     * 第三方支付模块的消息主题名
     */
    public final static String THIRD_PAY_TOPIC = "THIRD_PAY_TOPIC";

    /**
     * 第三方支付模块消息主题的tag
     */
    public static class PayModuleTopicTags{
        /**
         * 刷新支付配置
         */
        public final static String CONFIG_REFRESH = "configRefresh";

        /**
         * 确认收钱
         */
        public final static String CONFIRM_MONEY = "confirmMoney";
        /**
         * 确认退款
         */
        public final static String CONFIRM_REFUND = "confirmRefund";

        /**
         * IOS充值成功
         */
        public final static String IOS_RECHARGE_SUCCESS = "IosRechargeSuccess";

        /**
         * IOS 确认收钱成功
         */
        public final static String IOS_CONFIRM_MONEY = "iosConfirmMoney";
    }
}
