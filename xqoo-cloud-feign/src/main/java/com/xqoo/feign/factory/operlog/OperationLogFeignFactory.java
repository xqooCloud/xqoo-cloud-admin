package com.xqoo.feign.factory.operlog;

import com.xqoo.common.core.utils.JacksonUtils;
import com.xqoo.feign.common.bo.OperationLogRecordBO;
import com.xqoo.feign.service.operlog.OperationLogFeign;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class OperationLogFeignFactory implements FallbackFactory<OperationLogFeign> {

    private static Logger log = LoggerFactory.getLogger(OperationLogFeignFactory.class);

    @Override
    public OperationLogFeign create(Throwable e) {
        log.warn("[操作日志中心Feign]调用日志中心Feig出错");
        e.printStackTrace();
        return new OperationLogFeign() {
            @Override
            public byte[] addHandleRecord(OperationLogRecordBO entity) {
                log.error("[操作日志中心]新增操作日志发生错误，错误原因：{},错误信息：{}",
                        e.getClass().getSimpleName(), e.getMessage());
                return JacksonUtils.toJsonBytes(false);
            }
        };
    }
}
