package com.xqoo.authorization.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.xqoo.authorization.bo.QueryUserLoginHistoryBO;
import com.xqoo.authorization.entity.SysUserLoginHistoryEntity;
import com.xqoo.authorization.mapper.SysUserLoginHistoryMapper;
import com.xqoo.authorization.service.SysUserLoginHistoryService;
import com.xqoo.authorization.vo.UserLoginHistoryVO;
import com.xqoo.common.core.utils.StringUtils;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageResponseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户登录历史记录(SysUserLoginHistory)表服务实现类
 *
 * @author makejava
 * @since 2020-11-26 17:18:51
 */
@Service("sysUserLoginHistoryService")
public class SysUserLoginHistoryServiceImpl extends ServiceImpl<SysUserLoginHistoryMapper, SysUserLoginHistoryEntity> implements SysUserLoginHistoryService {


    private static final Logger logger = LoggerFactory.getLogger(SysUserLoginHistoryServiceImpl.class);

    @Autowired
    private SysUserLoginHistoryMapper sysUserLoginHistoryMapper;

    @Override
    public void AddLoginHistory(SysUserLoginHistoryEntity entity) {
        try {
            sysUserLoginHistoryMapper.insert(entity);
        }catch (Exception e){
            logger.error("[授权中心]新增用户登录记录时发生错误，userId{},错误原因：{}，错误信息概要：{}, 错误数据：{}",
                    entity.getUserId(), e.getClass().getSimpleName(), e.getMessage(), entity.toString());
        }
    }

    @Override
    public ResultEntity<PageResponseBean<UserLoginHistoryVO>> getLoginUserHistory(QueryUserLoginHistoryBO bo) {
        LambdaQueryWrapper<SysUserLoginHistoryEntity> queryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotEmpty(bo.getLoginSource())){
            queryWrapper.eq(SysUserLoginHistoryEntity::getLoginSource, bo.getLoginSource());
        }
        if(StringUtils.isNotEmpty(bo.getLoginType())){
            queryWrapper.eq(SysUserLoginHistoryEntity::getLoginType, bo.getLoginType());
        }
        if(StringUtils.isNotEmpty(bo.getUserId())){
            queryWrapper.eq(SysUserLoginHistoryEntity::getUserId, bo.getUserId());
        }
        if(StringUtils.isNotEmpty(bo.getLoginIp())){
            queryWrapper.likeRight(SysUserLoginHistoryEntity::getLoginIp, bo.getLoginIp());
        }
        Integer count = sysUserLoginHistoryMapper.selectCount(queryWrapper);
        PageResponseBean<UserLoginHistoryVO> result = new PageResponseBean<>(bo.getPage(), bo.getPageSize(), count);
        if(count < 1){
            result.setContent(Collections.emptyList());
            return new ResultEntity<>(HttpStatus.OK, "ok", result);
        }
        PageHelper.startPage(bo.getPage(), bo.getPageSize());
        List<SysUserLoginHistoryEntity> entityList = sysUserLoginHistoryMapper.selectList(queryWrapper);
        List<UserLoginHistoryVO> voList = entityList.stream().map(item -> {
            UserLoginHistoryVO vo = new UserLoginHistoryVO();
            BeanUtils.copyProperties(item, vo);
            vo.setLoginDate(DateUtil.date(item.getLoginDate()));
            return vo;
        }).collect(Collectors.toList());
        result.setContent(voList);
        return new ResultEntity<>(HttpStatus.OK, "ok", result);
    }
}
