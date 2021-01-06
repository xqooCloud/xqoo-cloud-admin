package com.xqoo.authorization.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author mumu
 * @date 2019/11/08 上午11:17
 **/
@ApiModel("系统用户表")
@TableName(value = "sys_user")
public class SysUserEntity extends Model<SysUserEntity> {


    private static final long serialVersionUID = 2650291498489510438L;
    @ApiModelProperty("用户id")
    @TableId(value = "user_id")
    private String userId;

    @ApiModelProperty("逻辑删除标记，0-未删除，1-已删除")
    private Integer delFlag;

    @ApiModelProperty("登录名，为考虑一手机多用户，此处必须与手机并存唯一，不能使用特殊字符")
    private String loginId;

    @ApiModelProperty("用户状态，0-正常，1-封禁，2-停用")
    private Integer userStatus;

    @ApiModelProperty("用户类型，99-超级管理员，88-后台用户(包含前台用户),10-前台用户-不可登录后台管理系统，9-临时用户，后续有新的用户类型继续在中间相加")
    private Integer userType;

    @ApiModelProperty("用户名，最多32字")
    private String userName;

    @ApiModelProperty("密码，允许为空，为空时密码登录将无效")
    private String password;

    @ApiModelProperty("盐，随机8位字母+数字")
    private String salt;

    @ApiModelProperty("上次登录时间")
    private Date lastLoginTime;

    @ApiModelProperty("头像地址")
    private String profileUrl;

    @TableField(value = "create_by", fill = FieldFill.INSERT)
    @ApiModelProperty("创建人")
    private String createBy;

    @TableField(value = "create_date", fill = FieldFill.INSERT)
    @ApiModelProperty("创建时间")
    private Date createDate;

    @TableField(value = "update_by", fill = FieldFill.UPDATE)
    @ApiModelProperty("最近修改人")
    private String updateBy;

    @TableField(value = "update_date", fill = FieldFill.UPDATE)
    @ApiModelProperty("最近修改时间")
    private Date updateDate;

    @ApiModelProperty("备注信息")
    private String remarkTips;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemarkTips() {
        return remarkTips;
    }

    public void setRemarkTips(String remarkTips) {
        this.remarkTips = remarkTips;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysUserEntity that = (SysUserEntity) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(delFlag, that.delFlag) &&
                Objects.equals(loginId, that.loginId) &&
                Objects.equals(userStatus, that.userStatus) &&
                Objects.equals(userType, that.userType) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(password, that.password) &&
                Objects.equals(salt, that.salt) &&
                Objects.equals(lastLoginTime, that.lastLoginTime) &&
                Objects.equals(profileUrl, that.profileUrl) &&
                Objects.equals(createBy, that.createBy) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(updateBy, that.updateBy) &&
                Objects.equals(updateDate, that.updateDate) &&
                Objects.equals(remarkTips, that.remarkTips);
    }

    @Override
    public String toString() {
        return "SysUserEntity{" +
                "userId='" + userId + '\'' +
                ", delFlag=" + delFlag +
                ", loginId='" + loginId + '\'' +
                ", userStatus=" + userStatus +
                ", userType=" + userType +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", lastLoginTime=" + lastLoginTime +
                ", profileUrl='" + profileUrl + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createDate=" + createDate +
                ", updateBy='" + updateBy + '\'' +
                ", updateDate=" + updateDate +
                ", remarkTips='" + remarkTips + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, delFlag, loginId, userStatus, userType, userName, password, salt, lastLoginTime, profileUrl, createBy, createDate, updateBy, updateDate, remarkTips);
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

}
