package com.xqoo.common.core.dto.authorization;

import java.util.Objects;

public class LoginTypeSwitchDTO {

    // 登录方式 "PASSWORD-账号密码登录，QRCODE-二维码扫码登录，" +
    //         "PHONE-手机号登录，EMAIL-邮箱登录，FINGER-指纹登录，FACE-面部识别登录，" +
    //         "IDENTIFY-证件号登录，THIRDPARTY-第三方登录"
    private String type;

    // 是否开启
    private boolean active;

    @Override
    public String toString() {
        return "LoginTypeSwitchDTO{" +
                "type='" + type + '\'' +
                ", active=" + active +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginTypeSwitchDTO that = (LoginTypeSwitchDTO) o;
        return active == that.active &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, active);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
