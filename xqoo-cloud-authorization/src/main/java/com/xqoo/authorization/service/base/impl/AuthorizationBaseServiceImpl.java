package com.xqoo.authorization.service.base.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.base.Splitter;
import com.xqoo.authorization.abstracts.AbstractAuthorizationBase;
import com.xqoo.authorization.bo.LoginModelBO;
import com.xqoo.authorization.bo.PasswordJsonBO;
import com.xqoo.authorization.bo.SysUserRoleInfoBO;
import com.xqoo.authorization.constants.AuthorizationCenterConstant;
import com.xqoo.authorization.entity.LoginReturnEntity;
import com.xqoo.authorization.entity.SysUserEntity;
import com.xqoo.authorization.entity.SysUserLoginHistoryEntity;
import com.xqoo.authorization.enums.LoginSingleEnum;
import com.xqoo.common.enums.LoginSourceEnum;
import com.xqoo.common.enums.LoginTypeEnum;
import com.xqoo.authorization.pojo.ValidateLoginEffectivePOJO;
import com.xqoo.authorization.service.SysUserLoginHistoryService;
import com.xqoo.authorization.service.SysUserRoleService;
import com.xqoo.authorization.service.SysUserService;
import com.xqoo.authorization.service.base.AuthorizationBaseService;
import com.xqoo.authorization.vo.UserOnlinedVO;
import com.xqoo.common.constants.AccountStatusConstant;
import com.xqoo.common.core.config.JWTUtils;
import com.xqoo.common.core.config.propetes.xqoo.AuthorizationConfigProperties;
import com.xqoo.common.core.config.propetes.xqoo.SecretConfigProperties;
import com.xqoo.common.core.dto.authorization.LoginTypeSwitchDTO;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.core.exception.BusinessException;
import com.xqoo.common.core.exception.SystemException;
import com.xqoo.common.core.utils.*;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.entity.SysUserBaseInfoEntity;
import com.xqoo.common.pojo.LoginUserInfoPOJO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 授权中心基础service，新增其他授权方式，请继承此service之后重写相应方法
 * @author gaoyang by 2020-11-23
 */
@Service("authorizationBaseService")
public class AuthorizationBaseServiceImpl extends AbstractAuthorizationBase implements AuthorizationBaseService {

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationBaseServiceImpl.class);

    @Autowired
    protected JWTUtils jwtUtils;

    @Autowired
    protected RedisTemplate<String, Object> redisTemplate;

    @Autowired
    protected StringRedisTemplate strRedisTemplate;

    @Autowired
    protected AuthorizationConfigProperties authorizationConfigProperties;

    @Autowired
    protected SecretConfigProperties secretConfigProperties;

    @Autowired
    protected SysUserService sysUserService;

    @Autowired
    protected SysUserLoginHistoryService sysUserLoginHistoryService;

    @Autowired
    protected SysUserRoleService sysUserRoleService;

    // 当前service所处理的登录类型
    private final static LoginTypeEnum allowLoginType = LoginTypeEnum.PASSWORD;

    /**
     * 获取token
     * @return
     */
    @Override
    public ResultEntity<LoginReturnEntity> getToken(LoginModelBO bo){

        LoginReturnEntity returnEntity = new LoginReturnEntity();

        // 第一步 校验请求登录类型是否当前处理类型
        // 默认不是账号密码登录的话，直接返回报错
        LoginTypeHandler typeHandler = handleLoginType(bo, allowLoginType);
        if(typeHandler.notAllowedType()){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE,
                    typeHandler.getMessage(), returnEntity);
        }

        //第二步，登录请求是否有效
        ValidateLoginEffectivePOJO effective = this.validateLoginEffective(bo);
        // 请求验证未通过，直接返回
        if(effective.isNotEffective()){
            returnEntity.setErrCode(effective.getErrCode());
            return new ResultEntity<>(effective.getCode(), effective.getMessage(), returnEntity);
        }
        // 这里非常重要，少了登录来源鉴权的时候很多判断全部会出问题
        effective.setLoginSourceEnum(bo.getLoginSource());

        // 第三步，校验登录用户是否已经在线，根据配置处理是否允许登录的情况
        effective = this.checkTokenOnlined(effective, bo.getLoginSource());
        // 如果不满足，直接返回
        if(effective.isNotEffective()){
            returnEntity.setErrCode(effective.getErrCode());
            return new ResultEntity<>(effective.getCode(), effective.getMessage(), returnEntity);
        }

        // 第四步，获取token，整合数据，返回前端
        String token = generatorToken(effective, bo.getLoginSource());
        if(StringUtils.isEmpty(token)){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "生成token失败，请重试", returnEntity);
        }
        returnEntity.setLastLogin(effective.getLastLoginTime());
        returnEntity.setLoginEnv(bo.getLoginEnv());
        returnEntity.setLoginIp(bo.getLoginIp());
        returnEntity.setLoginTime(bo.getLoginCurrentTimes());
        returnEntity.setToken(token);
        returnEntity.setUserId(effective.getUserId());
        returnEntity.setUserName(effective.getCurrentUser().getUserName());
        returnEntity.setAvatar(effective.getCurrentUser().getProfileUrl());
        returnEntity.setRoleIds(effective.getCurrentUser().getRoleIds());
        returnEntity.setRoleNames(effective.getCurrentUser().getRoleNames());
        returnEntity.setAdmin(effective.getCurrentUser().getAdmin());
        returnEntity.setUserPhone(effective.getCurrentUser().getUserPhone());
        returnEntity.setUserEmail(effective.getCurrentUser().getUserEmail());

        // 第五步，更新最近登录时间，新增登录记录
        // 更新登录时间 && 新增登录记录
        this.AddLoginRecord(effective.getUserId(), bo);

        // 第六步。清除锁定记录
        if(effective.getErrCount() != null && effective.getErrCount() > 0){
            this.removeErrRecord(effective.getUserId());
        }
        return new ResultEntity<>(HttpStatus.OK, "", returnEntity);
    }


    /**
     * @Descriptioin 登出token
     * @name LoginOut
     * @params [token]
     * @return com.xqoo.common.entity.ResultEntity<java.util.Map < java.lang.String, java.lang.Object>>
     */
    @Override
    public ResultEntity<Map<String, Object>> loginOut(String token, LoginSourceEnum loginSourceEnum) {
        String jwtToken = strRedisTemplate.opsForValue().get(AuthorizationCenterConstant.TOKEN_PREFIX  + token);
        if(StringUtils.isEmpty(jwtToken)){
            logger.debug("[授权中心]token：{}未找到对应令牌信息，直接退出", token);
            return new ResultEntity<>(HttpStatus.OK, "登出成功");
        }
        CurrentUser currentUser = jwtUtils.GetJwtCustomInfo(jwtToken);
        if(StringUtils.isEmpty(currentUser.getUserId())) {
            logger.debug("[授权中心]token：{}令牌信息：{}登出", token, currentUser.toString());
        }
        String userId = currentUser.getUserId();
        redisTemplate.delete(AuthorizationCenterConstant.TOKEN_PREFIX + token);
        redisTemplate.delete(AuthorizationCenterConstant.LOGINED_USER + loginSourceEnum.getKey() + ":" + userId);
        return new ResultEntity<>(HttpStatus.OK, "登出成功");
    }

    /**
     * 删除用户，禁用用户时删除他当前token
     */
    @Override
    public boolean removeUserAllTokenByUserId(String userId) throws Exception {
        Set<String> keySet = redisTemplate.keys(AuthorizationCenterConstant.LOGINED_USER+ "*:" + userId);
        if(CollUtil.isEmpty(keySet)){
            return true;
        }
        Set<String> tokenKey = keySet.stream().map(item -> {
            String value = strRedisTemplate.opsForValue().get(item);
            if(StringUtils.isEmpty(value)){
                return null;
            }
            return AuthorizationCenterConstant.TOKEN_PREFIX + value;
        }).filter(Objects::nonNull).collect(Collectors.toSet());
        redisTemplate.delete(keySet);
        redisTemplate.delete(tokenKey);
        return true;
    }

    @Override
    public UserOnlinedVO getUserOnlineInfo(String userId) {
        UserOnlinedVO vo = new UserOnlinedVO();
        Set<String> keySet = redisTemplate.keys(AuthorizationCenterConstant.LOGINED_USER+ "*:" + userId);
        if(CollUtil.isEmpty(keySet)){
            vo.setOnlined(false);
            vo.setOnlinedType(Collections.emptyList());
            return vo;
        }
        List<String> typeList = keySet.stream().map(item -> {
            List<String> keySplit = Splitter.on(":").trimResults().splitToList(item);
            if(CollUtil.isEmpty(keySplit) || keySplit.size() <2){
                return null;
            }
            return LoginSourceEnum.getValueByKey(keySplit.get(1));
        }).filter(Objects::nonNull).collect(Collectors.toList());
        vo.setOnlined(true);
        vo.setOnlinedType(typeList);
        return vo;
    }

    /**
     * 检验授权请求
     * @param bo
     * @return
     */
    @Override
    public ValidateLoginEffectivePOJO validateLoginEffective(LoginModelBO bo){

        ValidateLoginEffectivePOJO pojo = new ValidateLoginEffectivePOJO();
        // 此处是密码登录，就根据loginId查询用户信息就好，如果是其他方式，自己重写getLoginUserInfo的逻辑
        LoginUserInfoPOJO userInfo = this.getLoginUserInfo(bo.getLoginId());
        String userId = userInfo.getUserId();
        Long needCheckCodeTime = authorizationConfigProperties.getLoginErrLock().getNeedCheckErrorTime();
        if(userInfo.isGetInfoFail()){
            pojo.setCode(userInfo.getCode());
            pojo.setEffective(false);
            pojo.setMessage(userInfo.getHandleMessage());
            return pojo;
        }
        // 这里处理登录错误次数

        ValidateLoginEffectivePOJO loginErrorCount = this.checkLoginErrorCount(userId, bo.getErrCode());
        pojo.setErrCount(loginErrorCount.getErrCount());
        if(loginErrorCount.isNotEffective()){
            pojo.setCode(loginErrorCount.getCode());
            pojo.setEffective(false);
            pojo.setMessage(loginErrorCount.getMessage());
            pojo.setErrCode(loginErrorCount.getErrCode());
            return pojo;
        }
        // 这里比对密码
        String password = this.decodePassword(bo.getPassword(), bo.getRandomStr(), bo.getLoginCurrentTimes());
        if(!Pbkdf2Util.validatePassword(password, userInfo.getSysUserBaseInfoEntity().getPassword())){
            this.addLoginErrRecord(userId, "输入密码错误", bo.getLoginIp());
            pojo.setEffective(false);
            pojo.setCode(HttpStatus.NOT_ACCEPTABLE);
            pojo.setMessage("密码输入有误");
            if(loginErrorCount.getErrCount() > needCheckCodeTime - 1) {
                pojo.setCode(HttpStatus.PRECONDITION_FAILED);
                pojo.setMessage("输入密码已连续超过" + loginErrorCount.getErrCount() + "次错误");
                String returnCode = this.addLoginErrorCodeAndSend(userId);
                pojo.setErrCode(returnCode);
            }
            return pojo;
        }
        pojo.setCode(HttpStatus.OK);
        pojo.setEffective(true);
        pojo.setMessage("");
        pojo.setUserId(userId);
        pojo.setLastLoginTime(userInfo.getSysUserBaseInfoEntity().getLastLoginTime());
        CurrentUser currentUser = new CurrentUser();
        currentUser.setUserId(userId);
        currentUser.setLastLoginTime(userInfo.getSysUserBaseInfoEntity().getLastLoginTime());
        currentUser.setLoginSource(bo.getLoginSource().getKey());
        currentUser.setLoginSourceName(bo.getLoginSource().getValue());
        currentUser.setRoleIds(userInfo.getRoleIds());
        currentUser.setRoleNames(userInfo.getRoleNames());
        currentUser.setAdmin(userInfo.getAdmin());
        currentUser.setUserPhone(userInfo.getUserPhone());
        currentUser.setUserEmail(userInfo.getUserEmail());
        if(StringUtils.isEmpty(userInfo.getSysUserBaseInfoEntity().getProfileUrl())) {
            currentUser.setProfileUrl(AuthorizationCenterConstant.DEFAULT_AVATAR);
        }else{
            currentUser.setProfileUrl(userInfo.getSysUserBaseInfoEntity().getProfileUrl());
        }
        currentUser.setUserName(userInfo.getSysUserBaseInfoEntity().getUserName());
        pojo.setCurrentUser(currentUser);
        return pojo;
    }

    /**
     * 获取当前登录人的概要信息
     * @param loginId
     * @return
     */
    @Override
    public LoginUserInfoPOJO getLoginUserInfo(String loginId) {
        LoginUserInfoPOJO pojo = new LoginUserInfoPOJO();
        SysUserEntity userEntity = sysUserService.GetOnlyOneByLoginId(loginId);
        if(StringUtils.isEmpty(userEntity.getUserId())){
            pojo.setCode(HttpStatus.NOT_ACCEPTABLE);
            pojo.setGetInfoSuccess(false);
            pojo.setHandleMessage("此账号不存在");
            return pojo;
        }
        if(AccountStatusConstant.FREEZE.equals(userEntity.getUserStatus())){
            pojo.setCode(HttpStatus.LOCKED);
            pojo.setGetInfoSuccess(false);
            pojo.setHandleMessage("此账号已被封禁");
            return pojo;
        }
        if(AccountStatusConstant.STOP.equals(userEntity.getUserStatus())){
            pojo.setCode(HttpStatus.DESTINATION_LOCKED);
            pojo.setGetInfoSuccess(false);
            pojo.setHandleMessage("此账号已停用");
            return pojo;
        }
        SysUserRoleInfoBO roleMap = sysUserRoleService.getUserRoleMap(userEntity.getUserId());
        SysUserBaseInfoEntity baseInfoEntity = new SysUserBaseInfoEntity();
        BeanUtils.copyProperties(userEntity, baseInfoEntity);
        pojo.setGetInfoSuccess(true);
        pojo.setCode(HttpStatus.OK);
        pojo.setUserId(userEntity.getUserId());
        pojo.setRoleIds(roleMap.getRoleIds());
        pojo.setRoleNames(roleMap.getRoleNames());
        pojo.setSysUserBaseInfoEntity(baseInfoEntity);
        pojo.setAdmin(roleMap.isAdmin());
        return pojo;
    }
    /**
     * 查看是否登录异常次数过多被锁定
     * @name CheckLoginErrorCount
     * @params [userId]
     * @return com.xqoo.authorization.pojo.ValidateLoginEffectivePOJO
     */
    protected ValidateLoginEffectivePOJO checkLoginErrorCount(String userId, String errCode){
        ValidateLoginEffectivePOJO pojo = new ValidateLoginEffectivePOJO();
        pojo.setEffective(true);
        if(authorizationConfigProperties.getLoginErrLock().isNotActive()){
            return pojo;
        }
        Long count = redisTemplate.opsForList().size(AuthorizationCenterConstant.LOCK_LOGIN + userId);
        if(count == null){
            count = 0L;
        }
        pojo.setErrCount(count.intValue());
        if(authorizationConfigProperties.getLoginErrLock().getMaxErrorTime() <= count.intValue()){
            Long offesetSeconds = redisTemplate.getExpire(AuthorizationCenterConstant.LOCK_LOGIN + userId);
            if(offesetSeconds == null){
                offesetSeconds = 1L;
            }
            Date autoLock = DateUtil.offsetSecond(new Date(), offesetSeconds.intValue());
            pojo.setEffective(false);
            pojo.setMessage("当前账号已锁定\n将于["
                    + DateUtil.format(autoLock, "yyyy-MM-dd HH:mm:ss") + "]解锁");
            pojo.setCode(HttpStatus.LOCKED);
            return pojo;
        }
        Long needCheckCodeTime = authorizationConfigProperties.getLoginErrLock().getNeedCheckErrorTime();
        if(count > needCheckCodeTime){
            // 如果次数大于最低限制次数，则判断验证码是否正确
            if(StringUtils.isEmpty(errCode)){
                pojo.setCode(HttpStatus.PRECONDITION_FAILED);
                pojo.setEffective(false);
                pojo.setMessage("输入密码已连续超过" + count + "次错误,请输入验证码");
                String returnCode = this.addLoginErrorCodeAndSend(userId);
                pojo.setErrCode(returnCode);
                return pojo;
            }
            int status = this.checkErrorCode(errCode, userId);
            if(status > 0){
                pojo.setCode(HttpStatus.PRECONDITION_FAILED);
                pojo.setEffective(false);
                pojo.setMessage("验证码错误");
                String returnCode = this.addLoginErrorCodeAndSend(userId);
                pojo.setErrCode(returnCode);
                return pojo;
            }
        }
        return pojo;
    }


    /**
     * 校验token是否在线
     * @param effective
     * @param loginSourceEnum
     * @return
     */
    @Override
    public ValidateLoginEffectivePOJO checkTokenOnlined(ValidateLoginEffectivePOJO effective, LoginSourceEnum loginSourceEnum){
        if(authorizationConfigProperties.isNotLoginSingle()){
            return effective;
        }
        logger.debug("[授权中心]开启了单一登录");
        String loginSourceKey = loginSourceEnum.getKey();
        String userId = effective.getUserId();
        List<String> authTokenList = new ArrayList<>();
        String authToken = null;
        if(LoginSingleEnum.SOURCE.getKey().equals(authorizationConfigProperties.getLoginSingleType())){
            authToken = strRedisTemplate.opsForValue().get(AuthorizationCenterConstant.LOGINED_USER + loginSourceKey + ":" + userId);
            logger.debug("[授权中心]开启了根据登录来源判断单一登录");
        }else if(LoginSingleEnum.ALL.getKey().equals(authorizationConfigProperties.getLoginSingleType())){
            Set<String> keys = redisTemplate.keys(AuthorizationCenterConstant.LOGINED_USER + "*:" + userId);
            authTokenList = strRedisTemplate.opsForValue().multiGet(keys);
            logger.debug("[授权中心]开启了全平台判断单一登录");
        }else{
            authToken = strRedisTemplate.opsForValue().get(AuthorizationCenterConstant.LOGINED_USER + loginSourceKey + ":" + userId);
            logger.debug("[授权中心]开启了默认根据登录来源判断单一登录");
        }

        if(StringUtils.isEmpty(authToken) && CollUtil.isEmpty(authTokenList)){
            logger.debug("[授权中心]登录用户不存在token");
            return effective;
        }else{
            if(StringUtils.isNotEmpty(authToken)){
                redisTemplate.delete(AuthorizationCenterConstant.LOGINED_USER + loginSourceKey + ":" + userId);
                redisTemplate.delete(AuthorizationCenterConstant.TOKEN_PREFIX + authToken);
                logger.debug("[授权中心]kick当前已登录【{}】的token：{}", loginSourceKey, authToken);
            }
            if(CollUtil.isNotEmpty(authTokenList)){
                Set<String> keys = redisTemplate.keys(AuthorizationCenterConstant.LOGINED_USER + "*:" + userId);
                if (CollUtil.isNotEmpty(keys)) {
                    redisTemplate.delete(keys);
                }
                authTokenList.forEach(item -> redisTemplate.delete(AuthorizationCenterConstant.TOKEN_PREFIX + item));
                logger.debug("[授权中心]kick当前已登录所有来源{}的token：{}", keys, authTokenList);
            }
            return effective;
        }
    }


    /**
     * 生成登录token
     * @param effective, loginSource
     * @return
     */
    @Override
    public String generatorToken(ValidateLoginEffectivePOJO effective, LoginSourceEnum loginSource){
        String jwtToken = this.generatorJtwToken(effective.getCurrentUser());
        String userId = effective.getUserId();
        String loginSourceKey = loginSource.getKey();
        if(StringUtils.isEmpty(jwtToken)){
            return "";
        }
        String token = Pbkdf2Util.entryptPassword(userId + loginSourceKey);
        token = MD5Util.MD5Encode(token, StandardCharsets.UTF_8.name()).toLowerCase();
        strRedisTemplate.opsForValue().set(AuthorizationCenterConstant.TOKEN_PREFIX + token, jwtToken,
                authorizationConfigProperties.getTokenExpire(), TimeUnit.SECONDS);
        strRedisTemplate.opsForValue().set(AuthorizationCenterConstant.LOGINED_USER + loginSourceKey + ":" + userId, token,
                authorizationConfigProperties.getTokenExpire(), TimeUnit.SECONDS);
        return token;
    }

    /**
     * 生成jwt的令牌
     * @return
     */
    @Override
    public String generatorJtwToken(CurrentUser currentUser){
        String token = jwtUtils.createJwt(currentUser.toMap(), "SYS_AUTH",
                currentUser.getUserId(), authorizationConfigProperties.getTokenExpire() * 1000);
        if(token == null){
            return "";
        }
        return token;
    }


    /**
     * 解密前端密码密文并分解加密部分
     * DecodePassword
     * @param passwordSecret
     * @return java.lang.String
     */
    protected String decodePassword(String passwordSecret, String randomStr, long loginTime){
        String passwordJsonStr;
        try {
            passwordSecret = URLDecoder.decode(passwordSecret, StandardCharsets.UTF_8.name());
            passwordJsonStr = AESUtil.decode(passwordSecret, secretConfigProperties.getAesKey());
        }catch (Exception e){
            logger.error("[授权中心]解密密码密文发生错误：{}---{}", e.getClass().getSimpleName(), e.getMessage());
            throw new SystemException(e);
        }
        PasswordJsonBO bo = JacksonUtils.toObj(passwordJsonStr, new TypeReference<PasswordJsonBO>() {});
        if(!randomStr.equals(bo.getRandomStr())){
            throw new BusinessException("随机码与实际请求不符，不允许登录");
        }
        if(authorizationConfigProperties.getTimeZoneCheck()
                && (bo.getTime() == null || Math.abs(loginTime - bo.getTime()) > (authorizationConfigProperties.getTimeExact() * 1000))){
            throw new BusinessException("您的登录请求时间与服务器接收时间已超过限制，请检查本地时间是否正确，默认为北京时间");
        }
        if(StringUtils.isEmpty(bo.getPassword())){
            throw new BusinessException("密码值为空，请重新尝试");
        }
        return bo.getPassword();
    }


    /**
     *
     * 以下方法判断登录类型是否可用逻辑，非特殊需求可直接调用不需要再次重写
     */

    /**
     * 新增错误次数过多之后的验证码发送
     * @param userId
     */
    protected String addLoginErrorCodeAndSend(String userId){
        String code = RandomUtil.randomInt(4);
        Long expire = authorizationConfigProperties.getLoginErrLock().getErrorCodeExpire();
        strRedisTemplate.opsForValue().set(AuthorizationCenterConstant.ERR_CODE_PREFIX + userId, code, expire, TimeUnit.MINUTES);
        return code;
    }
    /**
     * 校验验证码是否正确
     */
    protected int checkErrorCode(String code, String userId){
        String errCode = strRedisTemplate.opsForValue().get(AuthorizationCenterConstant.ERR_CODE_PREFIX + userId);
        if(StringUtils.isEmpty(errCode)){
            return 2;
        }
        if(!code.equals(errCode)){
            return 1;
        }
        return 0;
    }

    /**
     * 新增一次登录错误的记录
     * @param userId
     * @param reason
     * @param ip
     */
    protected void addLoginErrRecord(String userId, String reason, String ip){
        Long time = System.currentTimeMillis();
        ObjectNode json = JacksonUtils.createObjectNode();
        json.put("userId", userId);
        json.put("errorReason", reason);
        json.put("errorTime", time);
        json.put("errorIp", ip);
        String jsonStr = JacksonUtils.toJsonStr(json);
        Long expire = authorizationConfigProperties.getLoginErrLock().getLockTime();
        redisTemplate.opsForList().leftPush(AuthorizationCenterConstant.LOCK_LOGIN + userId, jsonStr);
        if(expire > 0L){
            redisTemplate.expire(AuthorizationCenterConstant.LOCK_LOGIN + userId, expire, TimeUnit.MINUTES);
        }
    }

    private void removeErrRecord(String userId){
        redisTemplate.delete(AuthorizationCenterConstant.LOCK_LOGIN + userId);
        redisTemplate.delete(AuthorizationCenterConstant.ERR_CODE_PREFIX + userId);
    }

    protected Long getErrorLoginSize(String userId){
        return redisTemplate.opsForList().size(AuthorizationCenterConstant.LOCK_LOGIN + userId);
    }

    /**
     * @Descriptioin 处理登录类型校验
     * @name HandleLoginType
     * @params [bo, nowLoginType]
     * @return com.xqoo.authorization.service.base.impl.AuthorizationBaseServiceImpl.LoginTypeHandler
     */
    protected LoginTypeHandler handleLoginType(LoginModelBO bo, LoginTypeEnum nowLoginType){
        LoginTypeHandler typeResult = new LoginTypeHandler();
        typeResult.setAllowedType(true);
        typeResult.setMessage("模式可用");
        LoginTypeEnum loginType = bo.getLoginType();
        if(!nowLoginType.getKey().equalsIgnoreCase(loginType.getKey())){
            typeResult.setAllowedType(false);
            typeResult.setMessage("登录方式与校验程序处理不匹配，无法登录");
            return typeResult;
        }
        return CheckLoginTypeAllowed(loginType, typeResult);
    }

    /**
     * 校验登录类型是否允许
     * @param loginType
     * @param typeResult
     * @return
     */
    protected LoginTypeHandler CheckLoginTypeAllowed(LoginTypeEnum loginType, LoginTypeHandler typeResult){
        Optional<LoginTypeSwitchDTO> find = authorizationConfigProperties.getLoginTypeSwitch().stream()
                .filter(item -> loginType.getKey().equalsIgnoreCase(item.getType())).findAny();
        if(find.isPresent()){
            if(!find.get().isActive()){
                typeResult.setAllowedType(false);
                typeResult.setMessage("【" + loginType.getValue() + "】已被禁用");
            }
        }
        return typeResult;
    }

    /**
     * 增加登录记录和登录时间
     * @param userId
     * @param bo
     */
    protected void AddLoginRecord(String userId, LoginModelBO bo){
        sysUserService.SetLastLoginTime(userId, bo.getLoginCurrentTimes());
        SysUserLoginHistoryEntity loginHistoryEntity = new SysUserLoginHistoryEntity();
        loginHistoryEntity.setLoginDate(bo.getLoginCurrentTimes());
        loginHistoryEntity.setLoginEnv(bo.getLoginEnv());
        loginHistoryEntity.setLoginIp(bo.getLoginIp());
        loginHistoryEntity.setLoginSource(bo.getLoginSource().getKey());
        loginHistoryEntity.setLoginSourceName(bo.getLoginSource().getValue());
        loginHistoryEntity.setLoginType(bo.getLoginType().getKey());
        loginHistoryEntity.setLoginTypeName(bo.getLoginType().getValue());
        loginHistoryEntity.setUserId(userId);

        // 增加登录记录
        sysUserLoginHistoryService.AddLoginHistory(loginHistoryEntity);
    }


    /**
     * 登录类型校验实体类
     */
    protected static class LoginTypeHandler {
        // 允许的类型
        private boolean allowedType;
        // 返回的消息
        private String message;

        @Override
        public String toString() {
            return "LoginTypeHandler{" +
                    "allowedType=" + allowedType +
                    ", message='" + message + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            LoginTypeHandler that = (LoginTypeHandler) o;
            return allowedType == that.allowedType &&
                    Objects.equals(message, that.message);
        }

        @Override
        public int hashCode() {
            return Objects.hash(allowedType, message);
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public boolean isAllowedType() {
            return allowedType;
        }

        public boolean notAllowedType() {
            if(this.allowedType){
                return false;
            }
            return true;
        }

        public void setAllowedType(boolean allowedType) {
            this.allowedType = allowedType;
        }
    }
}
