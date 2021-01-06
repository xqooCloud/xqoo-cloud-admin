package com.xqoo.authorization.service.base.impl;

import com.xqoo.authorization.constants.AuthorizationCenterConstant;
import com.xqoo.common.enums.LoginSourceEnum;
import com.xqoo.authorization.service.base.AuthenticationBaseService;
import com.xqoo.common.core.config.JWTUtils;
import com.xqoo.common.core.config.propetes.xqoo.AuthorizationConfigProperties;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.core.utils.StringUtils;
import com.xqoo.common.dto.AuthenDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service("authentictionBaseService")
public class AuthenticationBaseServiceImpl implements AuthenticationBaseService {

    private final static Logger logger = LoggerFactory.getLogger(AuthenticationBaseServiceImpl.class);

    @Autowired
    protected StringRedisTemplate stringRedisTemplate;

    @Autowired
    protected AuthorizationConfigProperties authorizationConfigProperties;

    @Autowired
    private JWTUtils jwtUtils;

    /**
     * 检查登录状态
     * @param token
     * @return
     */
    @Override
    public AuthenDTO loginCheck(String token) {
        AuthenDTO dto = new AuthenDTO();
        dto.setStatus(HttpStatus.OK);
        dto.setMessage("已登录");
        dto.setSuccess(true);
        dto.setLoginSource(LoginSourceEnum.UnKnow.getKey());
        Long tokenExpire = stringRedisTemplate.getExpire(AuthorizationCenterConstant.TOKEN_PREFIX + token,
                TimeUnit.SECONDS);
        // token 已失效。直接返回登录失效
        if(tokenExpire == null || tokenExpire < 1){
            dto.setStatus(HttpStatus.UNAUTHORIZED);
            dto.setMessage("当前登录已过期");
            dto.setSuccess(false);
            return dto;
        }
        // 获取当前登录人基本信息
        CurrentUser currentUser = this.getUserBaseInfo(token);
        if(currentUser != null){
            dto.setLoginSource(currentUser.getLoginSource());
            dto.setUserId(currentUser.getUserId());
            dto.setUserName(currentUser.getUserName());
        }
        // 不需要刷新token持续时间，直接返回验证有效
        if(authorizationConfigProperties.isCloseTokenRefresh()){
            return dto;
        }
        // 如果token有效期小于刷新规定时间，刷新token,再返回通过
        if(tokenExpire < authorizationConfigProperties.getTokenRefreshLimit()){
            this.refreshTokenExpire(token, currentUser);
        }
        return dto;
    }

    @Override
    public AuthenDTO resourceCheck(String userId) {
        return null;
    }

    @Override
    public void refreshTokenExpire(String token, CurrentUser currentUser) {
        String jwtToken = getNewJwtToken(currentUser);
        stringRedisTemplate.opsForValue().set(AuthorizationCenterConstant.TOKEN_PREFIX + token, jwtToken,
                authorizationConfigProperties.getTokenExpire(), TimeUnit.SECONDS);
        stringRedisTemplate.expire(AuthorizationCenterConstant.LOGINED_USER + currentUser.getLoginSource() + ":" + currentUser.getUserId(),
                authorizationConfigProperties.getTokenExpire(), TimeUnit.SECONDS);
    }

    @Override
    public CurrentUser getUserBaseInfo(String token){
        String jwtToken = stringRedisTemplate.opsForValue().get(AuthorizationCenterConstant.TOKEN_PREFIX + token);
        if(StringUtils.isEmpty(jwtToken)){
            logger.warn("[鉴权中心]刷新token持续时间发生错误，未获取到token对应的jwt令牌信息，错误token:{}", token);
            return null;
        }else{
            return jwtUtils.GetJwtCustomInfo(jwtToken);
        }
    }

    @Override
    public String getNewJwtToken(CurrentUser currentUser){
        return jwtUtils.createJwt(currentUser.toMap(),
                "SYS_AUTH", currentUser.getUserId(), authorizationConfigProperties.getTokenExpire() * 1000);
    }
}
