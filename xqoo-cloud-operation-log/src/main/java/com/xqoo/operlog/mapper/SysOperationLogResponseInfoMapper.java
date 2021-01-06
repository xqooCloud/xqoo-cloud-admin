package com.xqoo.operlog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xqoo.feign.entity.SysOperationLogResponseInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 操作日志返回信息(SysOperationLogResponseInfo)表数据库访问层
 *
 * @author makejava
 * @since 2020-11-30 12:07:00
 */
@Mapper
@Repository
public interface SysOperationLogResponseInfoMapper extends BaseMapper<SysOperationLogResponseInfoEntity> {

}
