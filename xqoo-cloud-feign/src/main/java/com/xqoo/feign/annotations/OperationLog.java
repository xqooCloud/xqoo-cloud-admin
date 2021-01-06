package com.xqoo.feign.annotations;

import com.xqoo.feign.enums.operlog.OperationTypeEnum;

import java.lang.annotation.*;

@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationLog {

    /**
     * 说明信息
     */
    String tips() default "";

    /**
     * 简要说明
     */
    String remark() default "";

    /**
     * 操作类别
     */
    OperationTypeEnum operatorType() default OperationTypeEnum.OTHER;

    /**
     * 是否保存请求的参数
     */
    boolean isSaveRequestData() default false;

    /**
     * 是否保存返回的数据
     */
    boolean isSaveResponseData() default true;
}
