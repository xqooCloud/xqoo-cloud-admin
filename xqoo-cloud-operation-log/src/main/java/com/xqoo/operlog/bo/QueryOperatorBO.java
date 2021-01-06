package com.xqoo.operlog.bo;

import com.xqoo.common.page.PageRequestBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

@ApiModel("操作日志查询bo")
public class QueryOperatorBO extends PageRequestBean {

    private static final long serialVersionUID = -4359694499522900749L;
    @ApiModelProperty("操作结果，0-正常，1-异常")
    private Integer operationStatus;

    @ApiModelProperty("操作类型，1-查询，2-增加，3-修改，4-删除，0-其他")
    private Integer operationType;

    @ApiModelProperty("请求方式，GET, POST,PUT等")
    private String requestMethod;

    @ApiModelProperty("请求地址")
    private String requestUrl;

    @ApiModelProperty("操作人userId")
    private String operatorId;

    @ApiModelProperty("操作人登录名")
    private String operatorName;

    @Override
    public String toString() {
        return "QueryOperatorBO{" +
                "operationStatus=" + operationStatus +
                ", operationType=" + operationType +
                ", requestMethod='" + requestMethod + '\'' +
                ", requestUrl='" + requestUrl + '\'' +
                ", operatorId='" + operatorId + '\'' +
                ", operatorName='" + operatorName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        QueryOperatorBO that = (QueryOperatorBO) o;
        return Objects.equals(operationStatus, that.operationStatus) &&
                Objects.equals(operationType, that.operationType) &&
                Objects.equals(requestMethod, that.requestMethod) &&
                Objects.equals(requestUrl, that.requestUrl) &&
                Objects.equals(operatorId, that.operatorId) &&
                Objects.equals(operatorName, that.operatorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), operationStatus, operationType, requestMethod, requestUrl, operatorId, operatorName);
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
}
