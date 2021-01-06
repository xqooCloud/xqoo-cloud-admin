package com.xqoo.gateway.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xqoo.gateway.entity.SysGatewayInterceptLogEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 网关拦截记录表(SysGatewayInterceptLog)表数据库访问层
 *
 * @author makejava
 * @since 2020-11-29 23:42:35
 */
@Mapper
@Repository
public interface SysGatewayInterceptLogMapper extends BaseMapper<SysGatewayInterceptLogEntity> {

}