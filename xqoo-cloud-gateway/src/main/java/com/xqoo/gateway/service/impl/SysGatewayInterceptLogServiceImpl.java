package com.xqoo.gateway.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.xqoo.common.core.utils.StringUtils;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageResponseBean;
import com.xqoo.gateway.bean.GatewayInterceptQueryBO;
import com.xqoo.gateway.entity.SysGatewayInterceptLogEntity;
import com.xqoo.gateway.enums.InterceptTypeEnmu;
import com.xqoo.gateway.mapper.SysGatewayInterceptLogMapper;
import com.xqoo.gateway.service.SysGatewayInterceptLogService;
import com.xqoo.gateway.vo.GatewayInterceptVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 网关拦截记录表(SysGatewayInterceptLog)表服务实现类
 *
 * @author makejava
 * @since 2020-11-29 23:42:35
 */
@Service("sysGatewayInterceptLogService")
public class SysGatewayInterceptLogServiceImpl extends ServiceImpl<SysGatewayInterceptLogMapper, SysGatewayInterceptLogEntity> implements SysGatewayInterceptLogService {

    private final static Logger logger = LoggerFactory.getLogger(SysGatewayInterceptLogServiceImpl.class);

    @Autowired
    private SysGatewayInterceptLogMapper sysGatewayInterceptLogMapper;

    @Override
    public void addInterceptRecord(String requestIp, String requestPort, String url, InterceptTypeEnmu type) {
        SysGatewayInterceptLogEntity entity = new SysGatewayInterceptLogEntity();
        entity.setInterceptTime(System.currentTimeMillis());
        entity.setInterceptType(type.getKey());
        entity.setRequestIp(requestIp);
        entity.setRequestPort(requestPort);
        entity.setRequestUrl(url);
        try{
            sysGatewayInterceptLogMapper.insert(entity);
        }catch (Exception e){
            logger.warn("[网关]添加拦截记录时发生了错误，错误原因：{}，信息：{}", e.getClass().getSimpleName(), e.getMessage());
        }
    }

    @Override
    public ResultEntity<PageResponseBean<GatewayInterceptVO>> getInterceptLogPageRecord(GatewayInterceptQueryBO bo) {
        LambdaQueryWrapper<SysGatewayInterceptLogEntity> queryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotEmpty(bo.getInterceptType())){
            queryWrapper.eq(SysGatewayInterceptLogEntity::getInterceptType, bo.getInterceptType());
        }
        if(StringUtils.isNotEmpty(bo.getRequestIp())){
            queryWrapper.likeRight(SysGatewayInterceptLogEntity::getRequestIp, bo.getRequestIp());
        }
        Integer count = sysGatewayInterceptLogMapper.selectCount(queryWrapper);
        PageResponseBean<GatewayInterceptVO> result = new PageResponseBean<>(bo.getPage(), bo.getPageSize(), count);
        if(count < 1){
            result.setContent(Collections.emptyList());
            return new ResultEntity<>(HttpStatus.OK, "ok", result);
        }
        PageHelper.startPage(bo.getPage(), bo.getPageSize());
        List<SysGatewayInterceptLogEntity> entities = sysGatewayInterceptLogMapper.selectList(queryWrapper);
        List<GatewayInterceptVO> voList = entities.stream().map(item -> {
            GatewayInterceptVO vo = new GatewayInterceptVO();
            BeanUtils.copyProperties(item, vo);
            vo.setInterceptTypeName(InterceptTypeEnmu.getValueByKey(vo.getInterceptType()));
            vo.setInterceptTime(DateUtil.date(item.getInterceptTime()));
            return vo;
        }).collect(Collectors.toList());
        result.setContent(voList);
        return new ResultEntity<>(HttpStatus.OK, "ok", result);
    }
}
