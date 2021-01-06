package com.xqoo.operlog.controller;

import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageResponseBean;
import com.xqoo.operlog.bo.QueryOperatorBO;
import com.xqoo.operlog.service.SysOperationLogRequestInfoService;
import com.xqoo.operlog.service.SysOperationLogResponseInfoService;
import com.xqoo.operlog.service.SysOperationLogService;
import com.xqoo.operlog.vo.OperationLogVO;
import com.xqoo.operlog.vo.ShowOperationDataVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/operLog")
@Api(tags = "操作日志控制器")
@Validated
public class OperationLogController {

    @Autowired
    private SysOperationLogService sysOperationLogService;

    @Autowired
    private SysOperationLogRequestInfoService sysOperationLogRequestInfoService;

    @Autowired
    private SysOperationLogResponseInfoService sysOperationLogResponseInfoService;

    @PostMapping("/getPageOperationLog")
    @ApiOperation("分页获取操作日志明细")
    public ResultEntity<PageResponseBean<OperationLogVO>> getOperationLogPageList(@RequestBody QueryOperatorBO bo){
        return sysOperationLogService.getOperationLogPageList(bo);
    }

    @GetMapping("/getOperationData")
    @ApiOperation("获取操作日志参数")
    public ResultEntity<ShowOperationDataVO> getOperationData(@RequestParam(value = "logId", required = false)
                                                                  @NotBlank(message = "日志id不能为空") String logId,
                                                              @RequestParam(value = "type", required = false)
                                                              @NotBlank(message = "类型不能为空") String type){
        if("request".equals(type)){
            return sysOperationLogRequestInfoService.getDataByLogId(logId);
        }
        if("response".equals(type)){
            return sysOperationLogResponseInfoService.getDataByLogId(logId);
        }
        return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "类型无法识别，不能查询");
    }
}
