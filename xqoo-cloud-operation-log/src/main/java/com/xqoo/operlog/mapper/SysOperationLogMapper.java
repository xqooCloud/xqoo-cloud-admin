package com.xqoo.operlog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xqoo.feign.entity.SysOperationLogEntity;
import com.xqoo.operlog.bo.QueryOperatorBO;
import com.xqoo.operlog.vo.OperationLogVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统操作日志表(SysOperationLog)表数据库访问层
 *
 * @author makejava
 * @since 2020-11-30 12:06:56
 */
@Mapper
@Repository
public interface SysOperationLogMapper extends BaseMapper<SysOperationLogEntity> {

    List<OperationLogVO> getOperationList(@Param(value = "bo") QueryOperatorBO bo);

    Integer getOperationListCount(@Param(value = "bo") QueryOperatorBO bo);
}
