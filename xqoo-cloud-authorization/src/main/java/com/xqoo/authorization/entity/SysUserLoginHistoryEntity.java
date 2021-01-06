package com.xqoo.authorization.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author mumu
 * @date 2019/11/08 上午11:17
 **/
@ApiModel("用户登录历史记录")
@TableName(value = "sys_user_login_history")
public class SysUserLoginHistoryEntity extends Model<SysUserLoginHistoryEntity> {


    private static final long serialVersionUID = -9033158766116561676L;
    @ApiModelProperty("自增长id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("对应用户id")
    private String userId;

    @ApiModelProperty("登录时间")
    private Long loginDate;

    @ApiModelProperty("登录ip")
    private String loginIp;

    @ApiModelProperty("使用何种方式登录，具体参见系统中枚举")
    private String loginType;

    @ApiModelProperty("登录方式的名字")
    private String loginTypeName;

    @ApiModelProperty("登录环境，一些文本描述")
    private String loginEnv;

    @ApiModelProperty("登录请求来源，网站，APP等")
    private String loginSource;

    @ApiModelProperty("登录请求来源，网站，APP等")
    private String loginSourceName;

    @Override
    public String toString() {
        return "SysUserLoginHistoryEntity{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", loginDate=" + loginDate +
                ", loginIp='" + loginIp + '\'' +
                ", loginType='" + loginType + '\'' +
                ", loginTypeName='" + loginTypeName + '\'' +
                ", loginEnv='" + loginEnv + '\'' +
                ", loginSource='" + loginSource + '\'' +
                ", loginSourceName='" + loginSourceName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysUserLoginHistoryEntity that = (SysUserLoginHistoryEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(loginDate, that.loginDate) &&
                Objects.equals(loginIp, that.loginIp) &&
                Objects.equals(loginType, that.loginType) &&
                Objects.equals(loginTypeName, that.loginTypeName) &&
                Objects.equals(loginEnv, that.loginEnv) &&
                Objects.equals(loginSource, that.loginSource) &&
                Objects.equals(loginSourceName, that.loginSourceName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, loginDate, loginIp, loginType, loginTypeName, loginEnv, loginSource, loginSourceName);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Long loginDate) {
        this.loginDate = loginDate;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getLoginTypeName() {
        return loginTypeName;
    }

    public void setLoginTypeName(String loginTypeName) {
        this.loginTypeName = loginTypeName;
    }

    public String getLoginEnv() {
        return loginEnv;
    }

    public void setLoginEnv(String loginEnv) {
        this.loginEnv = loginEnv;
    }

    public String getLoginSource() {
        return loginSource;
    }

    public void setLoginSource(String loginSource) {
        this.loginSource = loginSource;
    }

    public String getLoginSourceName() {
        return loginSourceName;
    }

    public void setLoginSourceName(String loginSourceName) {
        this.loginSourceName = loginSourceName;
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
