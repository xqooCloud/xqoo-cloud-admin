package com.xqoo.operlog.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Objects;

@ApiModel("操作日志vo实体")
public class OperationLogVO implements Serializable {

    private static final long serialVersionUID = 5540443770577261819L;
    @ApiModelProperty("日志id")
    private String logId;

    @ApiModelProperty("操作结果，0-正常，1-异常")
    private Integer operationStatus;

    @ApiModelProperty("操作结果中文，0-正常，1-异常")
    private String operationStatusName;

    @ApiModelProperty("操作类型，1-查询，2-增加，3-修改，4-删除，0-其他")
    private Integer operationType;

    @ApiModelProperty("操作类型中文，1-查询，2-增加，3-修改，4-删除，0-其他")
    private String operationTypeName;

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

    @ApiModelProperty("操作时间")
    private String createTime;

    @ApiModelProperty("登录来源")
    private String loginSource;

    @ApiModelProperty("登录来源中文")
    private String loginSourceName;

    @ApiModelProperty("是否有请求数据")
    private Boolean hasRequestData;

    @ApiModelProperty("是否有相应数据")
    private Boolean hasResponseData;

    @Override
    public String toString() {
        return "OperationLogVO{" +
                "logId='" + logId + '\'' +
                ", operationStatus=" + operationStatus +
                ", operationStatusName='" + operationStatusName + '\'' +
                ", operationType=" + operationType +
                ", operationTypeName='" + operationTypeName + '\'' +
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
                ", loginSourceName='" + loginSourceName + '\'' +
                ", hasRequestData=" + hasRequestData +
                ", hasResponseData=" + hasResponseData +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperationLogVO that = (OperationLogVO) o;
        return Objects.equals(logId, that.logId) &&
                Objects.equals(operationStatus, that.operationStatus) &&
                Objects.equals(operationStatusName, that.operationStatusName) &&
                Objects.equals(operationType, that.operationType) &&
                Objects.equals(operationTypeName, that.operationTypeName) &&
                Objects.equals(requestMethod, that.requestMethod) &&
                Objects.equals(methodName, that.methodName) &&
                Objects.equals(requestUrl, that.requestUrl) &&
                Objects.equals(operatorId, that.operatorId) &&
                Objects.equals(operatorName, that.operatorName) &&
                Objects.equals(operatorRemoteIp, that.operatorRemoteIp) &&
                Objects.equals(operatorMessage, that.operatorMessage) &&
                Objects.equals(tipsMessage, that.tipsMessage) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(loginSource, that.loginSource) &&
                Objects.equals(loginSourceName, that.loginSourceName) &&
                Objects.equals(hasRequestData, that.hasRequestData) &&
                Objects.equals(hasResponseData, that.hasResponseData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logId, operationStatus, operationStatusName, operationType, operationTypeName, requestMethod, methodName, requestUrl, operatorId, operatorName, operatorRemoteIp, operatorMessage, tipsMessage, createTime, loginSource, loginSourceName, hasRequestData, hasResponseData);
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

    public String getOperationStatusName() {
        return operationStatusName;
    }

    public void setOperationStatusName(String operationStatusName) {
        this.operationStatusName = operationStatusName;
    }

    public Integer getOperationType() {
        return operationType;
    }

    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }

    public String getOperationTypeName() {
        return operationTypeName;
    }

    public void setOperationTypeName(String operationTypeName) {
        this.operationTypeName = operationTypeName;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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

    public Boolean getHasRequestData() {
        return hasRequestData;
    }

    public void setHasRequestData(Boolean hasRequestData) {
        this.hasRequestData = hasRequestData;
    }

    public Boolean getHasResponseData() {
        return hasResponseData;
    }

    public void setHasResponseData(Boolean hasResponseData) {
        this.hasResponseData = hasResponseData;
    }
}
