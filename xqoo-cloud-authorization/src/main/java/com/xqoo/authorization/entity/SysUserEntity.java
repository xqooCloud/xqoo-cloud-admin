package com.xqoo.authorization.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

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

    @ApiModelProperty("用户电话号码")
    private String userPhone;

    @ApiModelProperty("用户邮箱")
    private String userEmail;

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

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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
    public String toString() {
        return new ToStringBuilder(this)
                .append("userId", userId)
                .append("delFlag", delFlag)
                .append("loginId", loginId)
                .append("userStatus", userStatus)
                .append("userType", userType)
                .append("userPhone", userPhone)
                .append("userEmail", userEmail)
                .append("userName", userName)
                .append("password", password)
                .append("salt", salt)
                .append("lastLoginTime", lastLoginTime)
                .append("profileUrl", profileUrl)
                .append("createBy", createBy)
                .append("createDate", createDate)
                .append("updateBy", updateBy)
                .append("updateDate", updateDate)
                .append("remarkTips", remarkTips)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) return false;

        SysUserEntity that = (SysUserEntity) o;

        return new EqualsBuilder().append(userId, that.userId).append(delFlag, that.delFlag).append(loginId, that.loginId).append(userStatus, that.userStatus).append(userType, that.userType).append(userPhone, that.userPhone).append(userEmail, that.userEmail).append(userName, that.userName).append(password, that.password).append(salt, that.salt).append(lastLoginTime, that.lastLoginTime).append(profileUrl, that.profileUrl).append(createBy, that.createBy).append(createDate, that.createDate).append(updateBy, that.updateBy).append(updateDate, that.updateDate).append(remarkTips, that.remarkTips).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(userId).append(delFlag).append(loginId).append(userStatus).append(userType).append(userPhone).append(userEmail).append(userName).append(password).append(salt).append(lastLoginTime).append(profileUrl).append(createBy).append(createDate).append(updateBy).append(updateDate).append(remarkTips).toHashCode();
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
