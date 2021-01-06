package com.xqoo.feign.service.operlog;

import com.xqoo.feign.common.bo.OperationLogRecordBO;
import com.xqoo.feign.factory.operlog.OperationLogFeignFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author mumu
 * @date 2019/11/15 上午11:26
 **/
@Component
@FeignClient(name = "xqoo-cloud-operation-log", fallbackFactory = OperationLogFeignFactory.class)
public interface OperationLogFeign {

    @PostMapping("/operationLog/addRecord")
    byte[] addHandleRecord(@RequestBody OperationLogRecordBO entity);
}
