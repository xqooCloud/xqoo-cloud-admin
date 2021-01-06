package com.xqoo.operlog.entity;

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
@ApiModel("系统操作日志表")
@TableName(value = "sys_operation_log")
public class SysOperationLogEntity extends Model<SysOperationLogEntity> {

    private static final long serialVersionUID = 1210120945435370099L;
    @ApiModelProperty("日志id")
    @TableId(value = "log_id")
    private String logId;

    @ApiModelProperty("操作结果，0-正常，1-异常")
    private Integer operationStatus;

    @ApiModelProperty("操作类型，1-查询，2-增加，3-修改，4-删除，0-其他")
    private Integer operationType;

    @ApiModelProperty("请求方式，GET, POST,PUT等")
    private String requestMethod;

    @ApiModelProperty("方法名称")
    private String methodName;

    @ApiModelProperty("请求地址")
    private String requestUrl;

    @ApiModelProperty("操作人userId")
    private String operatorId;

    @ApiModelProperty("操作人登录名")
    private String operatorName;

    @ApiModelProperty("操作人的ip和端口")
    private String operatorRemoteIp;

    @ApiModelProperty("操作信息简述，不超过128字")
    private String operatorMessage;

    @ApiModelProperty("提示信息")
    private String tipsMessage;

    @ApiModelProperty("操作时间戳")
    private Long createTime;

    @ApiModelProperty("登录来源")
    private String loginSource;

    @Override
    public String toString() {
        return "SysOperationLogEntity{" +
                "logId='" + logId + '\'' +
                ", operationStatus=" + operationStatus +
                ", operationType=" + operationType +
                ", requestMethod='" + requestMethod + '\'' +
                ", methodName='" + methodName + '\'' +
                ", requestUrl='" + requestUrl + '\'' +
                ", operatorId='" + operatorId + '\'' +
                ", operatorName='" + operatorName + '\'' +
                ", operatorRemoteIp='" + operatorRemoteIp + '\'' +
                ", operatorMessage='" + operatorMessage + '\'' +
                ", tipsMessage='" + tipsMessage + '\'' +
                ", createTime=" + createTime +
                ", loginSource='" + loginSource + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysOperationLogEntity that = (SysOperationLogEntity) o;
        return Objects.equals(logId, that.logId) &&
                Objects.equals(operationStatus, that.operationStatus) &&
                Objects.equals(operationType, that.operationType) &&
                Objects.equals(requestMethod, that.requestMethod) &&
                Objects.equals(methodName, that.methodName) &&
                Objects.equals(requestUrl, that.requestUrl) &&
                Objects.equals(operatorId, that.operatorId) &&
                Objects.equals(operatorName, that.operatorName) &&
                Objects.equals(operatorRemoteIp, that.operatorRemoteIp) &&
                Objects.equals(operatorMessage, that.operatorMessage) &&
                Objects.equals(tipsMessage, that.tipsMessage) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(loginSource, that.loginSource);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logId, operationStatus, operationType, requestMethod, methodName, requestUrl, operatorId, operatorName, operatorRemoteIp, operatorMessage, tipsMessage, createTime, loginSource);
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public Integer getOperationStatus() {
        return operationStatus;
    }

    public void setOperationStatus(Integer operationStatus) {
        this.operationStatus = operationStatus;
    }

    public Integer getOperationType() {
        return operationType;
    }

    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getOperatorRemoteIp() {
        return operatorRemoteIp;
    }

    public void setOperatorRemoteIp(String operatorRemoteIp) {
        this.operatorRemoteIp = operatorRemoteIp;
    }

    public String getOperatorMessage() {
        return operatorMessage;
    }

    public void setOperatorMessage(String operatorMessage) {
        this.operatorMessage = operatorMessage;
    }

    public String getTipsMessage() {
        return tipsMessage;
    }

    public void setTipsMessage(String tipsMessage) {
        this.tipsMessage = tipsMessage;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getLoginSource() {
        return loginSource;
    }

    public void setLoginSource(String loginSource) {
        this.loginSource = loginSource;
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.logId;
    }

}
