package com.xqoo.authorization.bo;

import com.xqoo.common.enums.LoginSourceEnum;
import com.xqoo.common.enums.LoginTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

@ApiModel("登录信息实体")
public class LoginModelBO {

    @ApiModelProperty("登录ip")
    private String loginIp;

    @ApiModelProperty("登录时间戳")
    private Long loginCurrentTimes;

    @ApiModelProperty("登录环境")
    private String loginEnv;

    @ApiModelProperty("登录异常验证码")
    private String errCode;

    @ApiModelProperty("登录来源，PcBrowser-电脑浏览器，PcClient-电脑客户端，MobileBrowser-移动端浏览器，APP-app，WeChat-公众号，SmallProgram-小程序")
    private LoginSourceEnum loginSource;

    @ApiModelProperty("登录类型: PASSWORD-账号密码登录，QRCODE-二维码扫码登录，" +
            "PHONE-手机号登录，EMAIL-邮箱登录，FINGER-指纹登录，FACE-面部识别登录，" +
            "IDENTIFY-证件扫描登录，THIRDPARTY-第三方登录")
    private LoginTypeEnum loginType;

    @ApiModelProperty("登录用户名，仅PASSWORD模式下有用")
    private String loginId;

    @ApiModelProperty("登录密码，仅PASSWORD模式下有用")
    private String password;

    @ApiModelProperty("随机字符串，仅PASSWORD模式下有用")
    private String randomStr;

    @ApiModelProperty("手机号，仅PHONE模式下有用")
    private String phoneNumber;

    @ApiModelProperty("手机短信验证码，仅PHONE模式下有用")
    private String phoneCode;

    @ApiModelProperty("邮箱地址，仅EMAIL模式下有用")
    private String email;

    @ApiModelProperty("邮件验证码，仅EMAIL模式下有用")
    private String emailCode;


    @Override
    public String toString() {
        return "LoginModelBO{" +
                "loginIp='" + loginIp + '\'' +
                ", loginCurrentTimes=" + loginCurrentTimes +
                ", loginEnv='" + loginEnv + '\'' +
                ", errCode='" + errCode + '\'' +
                ", loginSource=" + loginSource +
                ", loginType=" + loginType +
                ", loginId='" + loginId + '\'' +
                ", password='" + password + '\'' +
                ", randomStr='" + randomStr + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", phoneCode='" + phoneCode + '\'' +
                ", email='" + email + '\'' +
                ", emailCode='" + emailCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginModelBO that = (LoginModelBO) o;
        return Objects.equals(loginIp, that.loginIp) &&
                Objects.equals(loginCurrentTimes, that.loginCurrentTimes) &&
                Objects.equals(loginEnv, that.loginEnv) &&
                Objects.equals(errCode, that.errCode) &&
                loginSource == that.loginSource &&
                loginType == that.loginType &&
                Objects.equals(loginId, that.loginId) &&
                Objects.equals(password, that.password) &&
                Objects.equals(randomStr, that.randomStr) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(phoneCode, that.phoneCode) &&
                Objects.equals(email, that.email) &&
                Objects.equals(emailCode, that.emailCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loginIp, loginCurrentTimes, loginEnv, errCode, loginSource, loginType, loginId, password, randomStr, phoneNumber, phoneCode, email, emailCode);
    }

    /**
     * 后续需要的参数继续加，目前只想到这些
     * @return
     */



    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Long getLoginCurrentTimes() {
        return loginCurrentTimes;
    }

    public void setLoginCurrentTimes(Long loginCurrentTimes) {
        this.loginCurrentTimes = loginCurrentTimes;
    }

    public String getLoginEnv() {
        return loginEnv;
    }

    public void setLoginEnv(String loginEnv) {
        this.loginEnv = loginEnv;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public LoginSourceEnum getLoginSource() {
        return loginSource;
    }

    public void setLoginSource(LoginSourceEnum loginSource) {
        this.loginSource = loginSource;
    }

    public LoginTypeEnum getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginTypeEnum loginType) {
        this.loginType = loginType;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRandomStr() {
        return randomStr;
    }

    public void setRandomStr(String randomStr) {
        this.randomStr = randomStr;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailCode() {
        return emailCode;
    }

    public void setEmailCode(String emailCode) {
        this.emailCode = emailCode;
    }
}
