package com.xqoo.common.core.config.propetes.xqoo;

import com.xqoo.common.core.dto.authorization.LoginTypeSwitchDTO;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author gaoyang
 */
@Component
@ConfigurationProperties(prefix = "xqoo.auth")
public class AuthorizationConfigProperties {

    // jwt 加密秘钥
    private String jwtSecretKey;
    // jwt令牌默认失效时间，实际可根据程序中自定，单位s
    private Long jwtExpire;
    // token失效，单位s
    private Long tokenExpire;
    // token 刷新时间，小于此时间时刷新token持续时间，单位 s,
    // 小于60则按60算，超过token失效时间一半则算作token-expire一半 为0时表示不刷新，到期即失效token
    private Long tokenRefreshLimit;
    // 单一登录限制,true为开启单一登录，一处登录登出其他地方
    private boolean loginSingle;
    // 单一登录类型，all-为全平台不论登录来源，source-每个来源只能登录一个
    private String loginSingleType;
    // 登录方式开开关
    private List<LoginTypeSwitchDTO> loginTypeSwitch;

    // 是否开启登录错误次数过多锁定
    private LoginErrLock loginErrLock;

    // 是否开启时区校验，不同时区账号不允许登录
    private Boolean timeZoneCheck;

    // 时间误差，当登录请求发送时间与服务器接收时间超过此误差值时，不允许登录，关闭时区校验后此项不可用，单位：秒
    private Integer timeExact;

    // 注册访问的临时令牌
    private String registerTmpCode;

    // 注册时的验证码过期时间，单位为秒
    private Long codeExpire;

    // 发送短信验证码的间隔，单位为秒
    private Long sendCodeSpace;

    public String getJwtSecretKey() {
        return jwtSecretKey;
    }

    public void setJwtSecretKey(String jwtSecretKey) {
        this.jwtSecretKey = jwtSecretKey;
    }

    public Long getJwtExpire() {
        return jwtExpire;
    }

    public void setJwtExpire(Long jwtExpire) {
        this.jwtExpire = jwtExpire * 1000;
    }

    public Long getTokenExpire() {
        return tokenExpire;
    }

    public void setTokenExpire(Long tokenExpire) {
        this.tokenExpire = tokenExpire;
    }

    public boolean isCloseTokenRefresh() {
        if(this.tokenRefreshLimit > 0){
            return false;
        }
        return true;
    }

    public Long getTokenRefreshLimit() {
        if(this.tokenRefreshLimit < 60){
            return 60L;
        }
        if(this.tokenRefreshLimit > (this.tokenExpire / 2)){
            return this.tokenExpire / 2;
        }
        return this.tokenRefreshLimit;
    }

    public void setTokenRefreshLimit(Long tokenRefreshLimit) {
        this.tokenRefreshLimit = tokenRefreshLimit;
    }

    public boolean isLoginSingle() {
        return loginSingle;
    }

    public boolean isNotLoginSingle() {
        if(this.loginSingle){
            return false;
        }
        return true;
    }

    public void setLoginSingle(boolean loginSingle) {
        this.loginSingle = loginSingle;
    }

    public String getLoginSingleType() {
        return loginSingleType;
    }

    public void setLoginSingleType(String loginSingleType) {
        this.loginSingleType = loginSingleType;
    }

    public List<LoginTypeSwitchDTO> getLoginTypeSwitch() {
        return loginTypeSwitch;
    }

    public void setLoginTypeSwitch(List<LoginTypeSwitchDTO> loginTypeSwitch) {
        this.loginTypeSwitch = loginTypeSwitch;
    }

    public LoginErrLock getLoginErrLock() {
        return loginErrLock;
    }

    public void setLoginErrLock(LoginErrLock loginErrLock) {
        this.loginErrLock = loginErrLock;
    }

    public Boolean getTimeZoneCheck() {
        return timeZoneCheck;
    }

    public void setTimeZoneCheck(Boolean timeZoneCheck) {
        this.timeZoneCheck = timeZoneCheck;
    }

    public Integer getTimeExact() {
        return timeExact;
    }

    public void setTimeExact(Integer timeExact) {
        this.timeExact = timeExact;
    }

    public String getRegisterTmpCode() {
        return registerTmpCode;
    }

    public void setRegisterTmpCode(String registerTmpCode) {
        this.registerTmpCode = registerTmpCode;
    }

    public Long getCodeExpire() {
        return codeExpire;
    }

    public void setCodeExpire(Long codeExpire) {
        this.codeExpire = codeExpire;
    }

    public Long getSendCodeSpace() {
        return sendCodeSpace;
    }

    public void setSendCodeSpace(Long sendCodeSpace) {
        this.sendCodeSpace = sendCodeSpace;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("jwtSecretKey", jwtSecretKey)
                .append("jwtExpire", jwtExpire)
                .append("tokenExpire", tokenExpire)
                .append("tokenRefreshLimit", tokenRefreshLimit)
                .append("loginSingle", loginSingle)
                .append("loginSingleType", loginSingleType)
                .append("loginTypeSwitch", loginTypeSwitch)
                .append("loginErrLock", loginErrLock)
                .append("timeZoneCheck", timeZoneCheck)
                .append("timeExact", timeExact)
                .append("registerTmpCode", registerTmpCode)
                .append("codeExpire", codeExpire)
                .append("sendCodeSpace", sendCodeSpace)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AuthorizationConfigProperties that = (AuthorizationConfigProperties) o;

        return new EqualsBuilder().append(loginSingle, that.loginSingle).append(jwtSecretKey, that.jwtSecretKey).append(jwtExpire, that.jwtExpire).append(tokenExpire, that.tokenExpire).append(tokenRefreshLimit, that.tokenRefreshLimit).append(loginSingleType, that.loginSingleType).append(loginTypeSwitch, that.loginTypeSwitch).append(loginErrLock, that.loginErrLock).append(timeZoneCheck, that.timeZoneCheck).append(timeExact, that.timeExact).append(registerTmpCode, that.registerTmpCode).append(codeExpire, that.codeExpire).append(sendCodeSpace, that.sendCodeSpace).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(jwtSecretKey).append(jwtExpire).append(tokenExpire).append(tokenRefreshLimit).append(loginSingle).append(loginSingleType).append(loginTypeSwitch).append(loginErrLock).append(timeZoneCheck).append(timeExact).append(registerTmpCode).append(codeExpire).append(sendCodeSpace).toHashCode();
    }


    public static class LoginErrLock {

        private Boolean active;

        // 最大错误次数 小于等于0为不限制
        private Long maxErrorTime;
        // 错误锁定时间 分钟，-1则为不自动解锁
        private Long lockTime;
        // 密码错误几次开启验证， 0和-1表示不开启
        private Long needCheckErrorTime;
        // 错误验证码失效时间 分钟, 小于等于0时默认5分钟
        private Long errorCodeExpire;

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("active", active)
                    .append("maxErrorTime", maxErrorTime)
                    .append("lockTime", lockTime)
                    .append("needCheckErrorTime", needCheckErrorTime)
                    .append("errorCodeExpire", errorCodeExpire)
                    .toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            LoginErrLock that = (LoginErrLock) o;

            return new EqualsBuilder().append(active, that.active).append(maxErrorTime, that.maxErrorTime).append(lockTime, that.lockTime).append(needCheckErrorTime, that.needCheckErrorTime).append(errorCodeExpire, that.errorCodeExpire).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(active).append(maxErrorTime).append(lockTime).append(needCheckErrorTime).append(errorCodeExpire).toHashCode();
        }

        public Boolean getActive() {
            return this.active;
        }

        public Boolean isNotActive() {
            if(active){
                return false;
            }
            return true;
        }

        public void setActive(Boolean active) {
            this.active = active;
        }

        public Long getMaxErrorTime() {

            if(maxErrorTime == null) {
                this.maxErrorTime = 99999999L;
            }
            if(maxErrorTime < 1){
                this.maxErrorTime = 99999999L;
            }
            return maxErrorTime;
        }

        public void setMaxErrorTime(Long maxErrorTime) {
            this.maxErrorTime = maxErrorTime;
        }

        public Long getLockTime() {
            if(lockTime == null) {
                this.lockTime = 60L;
            }
            if(lockTime < 0){
                this.lockTime = -1L;
            }
            if(lockTime == 0){
                this.lockTime = -1L;
            }
            return lockTime;
        }

        public void setLockTime(Long lockTime) {
            this.lockTime = lockTime;
        }

        public Long getNeedCheckErrorTime() {
            if(needCheckErrorTime == null) {
                this.needCheckErrorTime = 99999999L;
            }
            if(maxErrorTime < 1){
                this.needCheckErrorTime = 99999999L;
            }
            return needCheckErrorTime;
        }

        public void setNeedCheckErrorTime(Long needCheckErrorTime) {
            this.needCheckErrorTime = needCheckErrorTime;
        }

        public Long getErrorCodeExpire() {
            if(errorCodeExpire == null) {
                this.errorCodeExpire = 5 * 60L;
            }
            if(errorCodeExpire < 0){
                this.errorCodeExpire = -1L;
            }
            if(errorCodeExpire == 0){
                this.errorCodeExpire = -1L;
            }
            return errorCodeExpire;
        }

        public void setErrorCodeExpire(Long errorCodeExpire) { ;
            this.errorCodeExpire = errorCodeExpire;
        }
    }
}
