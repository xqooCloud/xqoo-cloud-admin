package com.xqoo.operlog.controller;

import com.xqoo.feign.common.bo.OperationLogRecordBO;
import com.xqoo.operlog.service.SysOperationLogRequestInfoService;
import com.xqoo.operlog.service.SysOperationLogResponseInfoService;
import com.xqoo.operlog.service.SysOperationLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/operationLog")
@Api(tags = "操作日志处理接口")
public class OperationHandleFeignController {

    private final static Logger logger = LoggerFactory.getLogger(OperationHandleFeignController.class);

    @Autowired
    private SysOperationLogService sysOperationLogService;

    @Autowired
    private SysOperationLogRequestInfoService sysOperationLogRequestInfoService;

    @Autowired
    private SysOperationLogResponseInfoService sysOperationLogResponseInfoService;

    @PostMapping("/addRecord")
    @ApiOperation(value = "新增操作日志记录(内部调用)")
    public Boolean addLogRecord(@ApiIgnore @RequestBody OperationLogRecordBO entity) {
        if (entity == null) {
            return false;
        }
        if (entity.getSysOperationLogEntity() == null) {
            return false;
        }
        try {
            sysOperationLogService.addRecord(entity.getSysOperationLogEntity());
            if (entity.getSysOperationLogRequestInfoEntity() != null) {
                sysOperationLogRequestInfoService.addRecord(entity.getSysOperationLogRequestInfoEntity());
            }
            if (entity.getSysOperationLogResponseInfoEntity() != null) {
                sysOperationLogResponseInfoService.addRecord(entity.getSysOperationLogResponseInfoEntity());
            }
            return true;
        } catch (Exception e) {
            logger.error("[操作日志中心]日志增发生错误，错误原因：{}，错误信息：{}",
                    e.getClass().getSimpleName(), e.getMessage());
            return false;
        }
    }
}
