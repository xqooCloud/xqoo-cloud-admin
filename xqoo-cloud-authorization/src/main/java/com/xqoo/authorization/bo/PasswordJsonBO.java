package com.xqoo.authorization.bo;

import java.util.Objects;

public class PasswordJsonBO {

    // 密码明文
    private String password;
    // 随机字符串
    private String randomStr;
    // 发送登录请求的时间
    private Long time;

    @Override
    public String toString() {
        return "PasswordJsonBO{" +
                "password='" + password + '\'' +
                ", randomStr='" + randomStr + '\'' +
                ", time=" + time +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PasswordJsonBO that = (PasswordJsonBO) o;
        return Objects.equals(password, that.password) &&
                Objects.equals(randomStr, that.randomStr) &&
                Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(password, randomStr, time);
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

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
