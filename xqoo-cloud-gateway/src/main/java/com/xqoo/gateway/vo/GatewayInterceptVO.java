package com.xqoo.gateway.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class GatewayInterceptVO implements Serializable {

    private static final long serialVersionUID = 373027194213022855L;
    @ApiModelProperty("自增长id")
    private Long id;

    @ApiModelProperty("拦截类型，REMOTE-来源拦截，TARGET-访问拦截")
    private String interceptType;

    @ApiModelProperty("拦截类型，REMOTE-来源拦截，TARGET-访问拦截")
    private String interceptTypeName;

    @ApiModelProperty("请求ip")
    private String requestIp;

    @ApiModelProperty("请求来源端口")
    private String requestPort;

    @ApiModelProperty("请求的资源")
    private String requestUrl;

    @ApiModelProperty("拦截时间戳")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date interceptTime;

    @Override
    public String toString() {
        return "GatewayInterceptVO{" +
                "id=" + id +
                ", interceptType='" + interceptType + '\'' +
                ", interceptTypeName='" + interceptTypeName + '\'' +
                ", requestIp='" + requestIp + '\'' +
                ", requestPort='" + requestPort + '\'' +
                ", requestUrl='" + requestUrl + '\'' +
                ", interceptTime=" + interceptTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GatewayInterceptVO that = (GatewayInterceptVO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(interceptType, that.interceptType) &&
                Objects.equals(interceptTypeName, that.interceptTypeName) &&
                Objects.equals(requestIp, that.requestIp) &&
                Objects.equals(requestPort, that.requestPort) &&
                Objects.equals(requestUrl, that.requestUrl) &&
                Objects.equals(interceptTime, that.interceptTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, interceptType, interceptTypeName, requestIp, requestPort, requestUrl, interceptTime);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInterceptType() {
        return interceptType;
    }

    public void setInterceptType(String interceptType) {
        this.interceptType = interceptType;
    }

    public String getInterceptTypeName() {
        return interceptTypeName;
    }

    public void setInterceptTypeName(String interceptTypeName) {
        this.interceptTypeName = interceptTypeName;
    }

    public String getRequestIp() {
        return requestIp;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }

    public String getRequestPort() {
        return requestPort;
    }

    public void setRequestPort(String requestPort) {
        this.requestPort = requestPort;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public Date getInterceptTime() {
        return interceptTime;
    }

    public void setInterceptTime(Date interceptTime) {
        this.interceptTime = interceptTime;
    }
}
