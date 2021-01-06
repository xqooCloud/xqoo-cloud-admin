package com.xqoo.operlog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.enums.LoginSourceEnum;
import com.xqoo.common.page.PageResponseBean;
import com.xqoo.feign.entity.SysOperationLogEntity;
import com.xqoo.feign.enums.operlog.OperationStatusEnum;
import com.xqoo.feign.enums.operlog.OperationTypeEnum;
import com.xqoo.operlog.bo.QueryOperatorBO;
import com.xqoo.operlog.mapper.SysOperationLogMapper;
import com.xqoo.operlog.service.SysOperationLogService;
import com.xqoo.operlog.vo.OperationLogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * 系统操作日志表(SysOperationLog)表服务实现类
 *
 * @author makejava
 * @since 2020-11-30 12:06:58
 */
@Service("sysOperationLogService")
public class SysOperationLogServiceImpl extends ServiceImpl<SysOperationLogMapper, SysOperationLogEntity> implements SysOperationLogService {

    @Autowired
    private SysOperationLogMapper sysOperationLogMapper;

    @Override
    public void addRecord(SysOperationLogEntity entity) throws Exception{
        sysOperationLogMapper.insert(entity);
    }

    @Override
    public ResultEntity<PageResponseBean<OperationLogVO>> getOperationLogPageList(QueryOperatorBO bo) {
        Integer count = sysOperationLogMapper.getOperationListCount(bo);
        PageResponseBean<OperationLogVO> result = new PageResponseBean<>(bo.getPage(), bo.getPageSize(), count);
        if(count < 1){
            result.setContent(Collections.emptyList());
            return new ResultEntity<>(HttpStatus.OK, "ok", result);
        }
        List<OperationLogVO> list = sysOperationLogMapper.getOperationList(bo);
        list.forEach(item -> {
            item.setLoginSourceName(LoginSourceEnum.getValueByKey(item.getLoginSource()));
            item.setOperationStatusName(OperationStatusEnum.getValueByKey(item.getOperationStatus()));
            item.setOperationTypeName(OperationTypeEnum.getValueByKey(item.getOperationType()));
        });
        result.setContent(list);
        return new ResultEntity<>(HttpStatus.OK, "ok", result);
    }
}
