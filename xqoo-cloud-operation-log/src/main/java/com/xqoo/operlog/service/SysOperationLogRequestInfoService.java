package com.xqoo.operlog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.feign.entity.SysOperationLogRequestInfoEntity;
import com.xqoo.operlog.vo.ShowOperationDataVO;

/**
 * 操作日志请求信息(SysOperationLogRequestInfo)表服务接口
 *
 * @author makejava
 * @since 2020-11-30 12:06:59
 */
public interface SysOperationLogRequestInfoService extends IService<SysOperationLogRequestInfoEntity> {

    void addRecord(SysOperationLogRequestInfoEntity entity) throws Exception;

    ResultEntity<ShowOperationDataVO> getDataByLogId(String logId);
}
