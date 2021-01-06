package com.xqoo.operlog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.feign.entity.SysOperationLogResponseInfoEntity;
import com.xqoo.operlog.vo.ShowOperationDataVO;

/**
 * 操作日志返回信息(SysOperationLogResponseInfo)表服务接口
 *
 * @author makejava
 * @since 2020-11-30 12:07:00
 */
public interface SysOperationLogResponseInfoService extends IService<SysOperationLogResponseInfoEntity> {

    void addRecord(SysOperationLogResponseInfoEntity entity) throws Exception;

    ResultEntity<ShowOperationDataVO>  getDataByLogId(String logId);
}
