package com.xqoo.gateway.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageResponseBean;
import com.xqoo.gateway.bean.GatewayInterceptQueryBO;
import com.xqoo.gateway.entity.SysGatewayInterceptLogEntity;
import com.xqoo.gateway.enums.InterceptTypeEnmu;
import com.xqoo.gateway.vo.GatewayInterceptVO;

/**
 * 网关拦截记录表(SysGatewayInterceptLog)表服务接口
 *
 * @author makejava
 * @since 2020-11-29 23:42:35
 */
public interface SysGatewayInterceptLogService extends IService<SysGatewayInterceptLogEntity> {

    void addInterceptRecord(String requestIp, String requestPort, String url, InterceptTypeEnmu type);

    ResultEntity<PageResponseBean<GatewayInterceptVO>> getInterceptLogPageRecord(GatewayInterceptQueryBO bo);
}
