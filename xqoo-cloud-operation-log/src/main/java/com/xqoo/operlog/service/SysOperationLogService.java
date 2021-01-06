package com.xqoo.operlog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageResponseBean;
import com.xqoo.feign.entity.SysOperationLogEntity;
import com.xqoo.operlog.bo.QueryOperatorBO;
import com.xqoo.operlog.vo.OperationLogVO;

/**
 * 系统操作日志表(SysOperationLog)表服务接口
 *
 * @author makejava
 * @since 2020-11-30 12:06:57
 */
public interface SysOperationLogService extends IService<SysOperationLogEntity> {

    void addRecord(SysOperationLogEntity entity) throws Exception;

    ResultEntity<PageResponseBean<OperationLogVO>> getOperationLogPageList(QueryOperatorBO bo);
}
