package com.xqoo.authorization.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.xqoo.authorization.bo.AddUserInfoBO;
import com.xqoo.authorization.bo.QueryUserInfoBO;
import com.xqoo.authorization.constants.AuthorizationCenterConstant;
import com.xqoo.authorization.entity.SysUserEntity;
import com.xqoo.authorization.enums.UserStatusEnum;
import com.xqoo.authorization.mapper.SysUserMapper;
import com.xqoo.authorization.service.SysUserService;
import com.xqoo.authorization.service.base.AuthorizationBaseService;
import com.xqoo.authorization.vo.UserInfoVO;
import com.xqoo.common.constants.SqlQueryConstant;
import com.xqoo.common.core.utils.MD5Util;
import com.xqoo.common.core.utils.Pbkdf2Util;
import com.xqoo.common.core.utils.RandomUtil;
import com.xqoo.common.core.utils.StringUtils;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageResponseBean;
import com.xqoo.feign.service.uidgenerator.UidGeneratorFeign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 系统用户表(SysUser)表服务实现类
 *
 * @author makejava
 * @since 2020-11-23 19:13:25
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserEntity> implements SysUserService {


    private static final Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private UidGeneratorFeign uidGeneratorFeign;

    @Autowired
    private AuthorizationBaseService authorizationBaseService;

    @Override
    public SysUserEntity GetOnlyOneByLoginId(String loginId) {
        LambdaQueryWrapper<SysUserEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUserEntity::getLoginId, loginId);
        queryWrapper.eq(SysUserEntity::getDelFlag, SqlQueryConstant.NOT_LOGIC_DEL);
        SysUserEntity sysUserEntity = sysUserMapper.selectOne(queryWrapper);
        if(sysUserEntity == null || StringUtils.isEmpty(sysUserEntity.getUserId())){
            return new SysUserEntity();
        }
        return sysUserEntity;
    }

    @Override
    public void SetLastLoginTime(String userId, Long loginTime) {
        Date loginDate = DateUtil.date(loginTime);
        SysUserEntity entity = new SysUserEntity();
        entity.setUserId(userId);
        entity.setLastLoginTime(loginDate);
        try {
            sysUserMapper.updateById(entity);
        }catch (Exception e){
            logger.error("[授权中心]存储用户id:{}的登录时间时发生错误，错误信息{}，原因{}",
                    userId, e.getMessage(), e.getClass().getSimpleName());
        }

    }

    @Override
    public ResultEntity<PageResponseBean<UserInfoVO>> pageGetUserInfo(QueryUserInfoBO bo) {
        LambdaQueryWrapper<SysUserEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUserEntity::getDelFlag, SqlQueryConstant.NOT_LOGIC_DEL);
        if(StringUtils.isNotEmpty(bo.getUserId())){
            queryWrapper.eq(SysUserEntity::getUserId, bo.getUserId());
        }
        if(StringUtils.isNotEmpty(bo.getUserName())){
            queryWrapper.like(SysUserEntity::getUserName, bo.getUserName());
        }
        if(bo.getUserStatus() != null){
            queryWrapper.eq(SysUserEntity::getUserStatus, bo.getUserStatus());
        }
        if(bo.getUserType() != null){
            queryWrapper.eq(SysUserEntity::getUserType, bo.getUserType());
        }
        if(StringUtils.isNotEmpty(bo.getLoginId())){
            queryWrapper.like(SysUserEntity::getLoginId, bo.getLoginId());
        }
        Integer count = sysUserMapper.selectCount(queryWrapper);
        count = count == null ? 0 : count;
        PageResponseBean<UserInfoVO> result = new PageResponseBean<>(bo.getPage(), bo.getPageSize(), count);
        if(count < 1){
            result.setContent(Collections.emptyList());
            return new ResultEntity<>(HttpStatus.OK, "ok", result);
        }
        PageHelper.startPage(bo.getPage(), bo.getPageSize());
        List<UserInfoVO> voList = sysUserMapper.pageQueryUserInfo(bo);
        result.setContent(voList);
        return new ResultEntity<>(HttpStatus.OK, "ok", result);
    }

    @Override
    public ResultEntity addUserInfo(AddUserInfoBO bo) {
        if(loginIdExists(bo.getLoginId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "当前loginId已存在");
        }
        SysUserEntity entity = new SysUserEntity();
        String userId = getUserId(bo.getLoginId(), bo.getPassword());
        entity.setLoginId(bo.getLoginId());
        entity.setSalt(RandomUtil.randomStr(6));
        entity.setUserName(bo.getUserName());
        entity.setPassword(Pbkdf2Util.entryptPassword(bo.getPassword()));
        entity.setUserStatus(UserStatusEnum.NORMAL.getKey());
        entity.setUserId(userId);
        entity.setUserType(bo.getUserType());
        try{
            sysUserMapper.insert(entity);
            return new ResultEntity<>(HttpStatus.OK, "新增成功", userId);
        }catch (Exception e){
            logger.error("[资源中心]新增用户失败，原因{},信息{}", e.getClass().getSimpleName(), e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "新增用户时数据库发生错误，新增失败");
        }

    }

    @Override
    public boolean loginIdExists(String loginId) {
        LambdaQueryWrapper<SysUserEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUserEntity::getLoginId, loginId);
        queryWrapper.eq(SysUserEntity::getDelFlag, SqlQueryConstant.NOT_LOGIC_DEL);

        Integer count = sysUserMapper.selectCount(queryWrapper);
        if(count == null || count < 1){
            return false;
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultEntity delUserInfo(String userId) {
        LambdaQueryWrapper<SysUserEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUserEntity::getUserId, userId);
        queryWrapper.eq(SysUserEntity::getDelFlag, SqlQueryConstant.NOT_LOGIC_DEL);
        SysUserEntity entity = sysUserMapper.selectOne(queryWrapper);
        if(entity == null || StringUtils.isEmpty(entity.getUserId())){
            return new ResultEntity<>(HttpStatus.OK, "删除成功，此用户已不存在");
        }
        if(AuthorizationCenterConstant.SUPER_ADMIN_UID.equals(entity.getUserId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "超级管理员账号不允许删除");
        }
        entity.setDelFlag(SqlQueryConstant.LOGIC_DEL);
        try{
            sysUserMapper.updateById(entity);
            boolean success = authorizationBaseService.removeUserAllTokenByUserId(userId);
            return new ResultEntity<>(HttpStatus.OK, "删除成功", userId);
        }catch (Exception e){
            logger.error("[资源中心]删除用户失败，原因{},信息{}", e.getClass().getSimpleName(), e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "删除用户时数据库发生错误，新增失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultEntity updateUserStatus(String userId, String type) {
        LambdaQueryWrapper<SysUserEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUserEntity::getUserId, userId);
        queryWrapper.eq(SysUserEntity::getDelFlag, SqlQueryConstant.NOT_LOGIC_DEL);
        SysUserEntity entity = sysUserMapper.selectOne(queryWrapper);
        if(entity == null || StringUtils.isEmpty(entity.getUserId())){
            return new ResultEntity<>(HttpStatus.OK, "更新状态失败，未找到相应用户信息");
        }
        if(AuthorizationCenterConstant.SUPER_ADMIN_UID.equals(entity.getUserId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "超级管理员账号不允许变动");
        }
        if("freeze".equals(type)){
            if(!UserStatusEnum.NORMAL.getKey().equals(entity.getUserStatus())){
                return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "当前用户状态已被封禁或停用，请勿重复操作");
            }
            entity.setUserStatus(UserStatusEnum.FREEZE.getKey());
        }else if("unFreeze".equals(type)){
            if(!UserStatusEnum.FREEZE.getKey().equals(entity.getUserStatus())){
                return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "当前用户状态不为停用状态，无法启用");
            }
            entity.setUserStatus(UserStatusEnum.NORMAL.getKey());
        }else if("deny".equals(type)){
            if(!UserStatusEnum.NORMAL.getKey().equals(entity.getUserStatus())){
                return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "当前用户状态已停用或被封禁，请勿重复操作");
            }
            entity.setUserStatus(UserStatusEnum.DENY.getKey());
        }else if("unDeny".equals(type)){
            if(!UserStatusEnum.DENY.getKey().equals(entity.getUserStatus())){
                return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "当前用户状态不为封禁状态，无法解封");
            }
            entity.setUserStatus(UserStatusEnum.NORMAL.getKey());
        }else{
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "操作类型无法识别，放弃执行");
        }
        try{
            sysUserMapper.updateById(entity);
            boolean success = authorizationBaseService.removeUserAllTokenByUserId(userId);
            return new ResultEntity<>(HttpStatus.OK, "状态变更完成", userId);
        }catch (Exception e){
            logger.error("[资源中心]变更用户状态失败，变更类型{}，原因{},信息{}", type, e.getClass().getSimpleName(), e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "变更用户状态时数据库发生错误，新增失败");
        }
    }

    private String getUserId(String loginId, String password){
        String userId = uidGeneratorFeign.getUid("user-id");
        if(StringUtils.isEmpty(userId)){
            logger.warn("[资源中心]获取userId失败，流水号生成中心无法访问返回空值，使用本地默认md5生成userId");
            userId = MD5Util.MD5Encode(loginId+password+RandomUtil.randomStr(4), StandardCharsets.UTF_8.name());
        }
        return userId;
    }
}
