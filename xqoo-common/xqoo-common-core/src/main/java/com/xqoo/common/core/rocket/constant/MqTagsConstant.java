package com.xqoo.common.core.rocket.constant;

/**
 * 各模块所需消息主题下tag常量，用到时增加
 */
public class MqTagsConstant {

    /**
     * 系统权限控制模块消息主题名
     */
    public final static String SYSTEM_AUTH_TOPIC = "SYSTEM_AUTH_TOPIC";

    /**
     * 系统后台控制模块消息主题名
     */
    public final static String SYSTEM_CONSOLE_TOPIC = "SYSTEM_CONSOLE_TOPIC";
    /**
     * 系统后台控制模块消息tag
     */
    public static class ManagerModuleTopicTags{
        /**
         * 加入积分账户
         */
        public final static String ADD_INTEGRAL_ACCOUNT = "addIntegralAccount";
    }

    /**
     * 网关模块消息主题名
     */
    public final static String GATEWAY_TOPIC = "GATEWAY_TOPIC";

    /**
     * 律瀛产品模块消息主题名
     */
    public final static String LVYING_PRODUCT_TOPIC = "LVYING_PRODUCT_TOPIC";

    /**
     * 产品模块消息主题tag
     */
    public static class ProductModuleTopicTags{
        /**
         * 刷新订单类型配置
         */
        public final static String COMMENT_ORDER = "commentOrder";

        /**
         * 更新预览产品配置
         */
        public final static String HANDLE_PREVIEW = "handlePreview";
    }

    /**
     * 订单模块消息主题名
     */
    public final static String ORDER_FORM_TOPIC = "ORDER_FORM_TOPIC";

    /**
     * 订单模块消息主题的tag
     */
    public static class OrderFormModuleTopicTags{
        /**
         * 刷新订单类型配置
         */
        public final static String CONFIG_REFRESH = "configRefresh";
        /**
         * 取消订单
         */
        public final static String CANCEL_ORDER = "cancelOrder";
        /**
         * 更新分成
         */
        public final static String ALLOCATION_HANDLE = "allocationHandle";
        /**
         * 积分处理
         */
        public final static String INTEGRAL_HANDLE = "integralHandle";

        /**
         * 卡卷处理
         */
        public final static String COUPON_HANDLE = "couponHandle";
        /**
         * 代金券类处理
         */
        public final static String CYBERMONEY_HANDLE = "cybermoneyHandle";
        /**
         * 促销处理
         */
        public final static String PROMOTE_HANDLE = "promoteHandle";
        /**
         * 授权处理
         */
        public final static String AUTH_PRODUCT_HANDLE = "authProductHandle";
    }

    /**
     * 销售模块消息主题名
     */
    public final static String SALE_CENTER_TOPIC = "SALE_CENTER_TOPIC";

    /**
     * 销售模块消息主题的tag
     */
    public static class SaleCenterModuleTopicTags{
        /**
         * 刷新订单类型配置
         */
        public final static String CONFIG_REFRESH = "configRefresh";
        /**
         * 取消订单
         */
        public final static String CANCEL_ORDER = "cancelOrder";
        /**
         * 更新分成
         */
        public final static String ALLOCATION_HANDLE = "allocationHandle";
        /**
         * 积分处理
         */
        public final static String INTEGRAL_HANDLE = "integralHandle";

        /**
         * 卡卷处理
         */
        public final static String COUPON_HANDLE = "couponHandle";
        /**
         * 代金券类处理
         */
        public final static String CYBERMONEY_HANDLE = "cybermoneyHandle";
        /**
         * 促销处理
         */
        public final static String PROMOTE_HANDLE = "promoteHandle";

        /**
         * 退款团购订单
         */
        public final static String REFUND_GROUP_BUY = "refundGroupBuy";

        /**
         * 确认团购订单
         */
        public final static String CONFIRM_GROUP_BUY = "confirmGroupBuy";
    }

    /**
     * 提供商模块消息主题名
     */
    public final static String MERCHANT_CENTER_TOPIC = "MERCHANT_CENTER_TOPIC";



    /**
     * 提供商模块消息主题的tag
     */
    public static class MerchantCenterModuleTopicTags{
        /**
         * 刷新分成类型配置
         */
        public final static String CONFIG_REFRESH = "configRefresh";

        /**
         * 通知分成结果
         */
        public final static String ALLOCATION_CONFIRM = "allocationConfirm";
    }

    /**
     * 第三方支付模块的消息主题名
     */
    public final static String THIRD_PARTY_PAY_TOPIC = "THIRD_PARTY_PAY_TOPIC";

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

    public static class IntegralLevelConfigTags{
        /**
         * 刷新积分等级配置规则
         */
        public final static String CONFIG_REFRESH = "configRefresh";
    }
}
