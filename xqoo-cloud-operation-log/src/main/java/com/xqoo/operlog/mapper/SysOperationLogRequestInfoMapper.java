package com.xqoo.operlog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xqoo.feign.entity.SysOperationLogRequestInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 操作日志请求信息(SysOperationLogRequestInfo)表数据库访问层
 *
 * @author makejava
 * @since 2020-11-30 12:06:59
 */
@Mapper
@Repository
public interface SysOperationLogRequestInfoMapper extends BaseMapper<SysOperationLogRequestInfoEntity> {

}
