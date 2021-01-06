package com.xqoo.gateway.entity;

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
@ApiModel("网关拦截记录表")
@TableName(value = "sys_gateway_intercept_log")
public class SysGatewayInterceptLogEntity extends Model<SysGatewayInterceptLogEntity> {


    @ApiModelProperty("自增长id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("拦截类型，REMOTE-来源拦截，TARGET-访问拦截")
    private String interceptType;

    @ApiModelProperty("请求ip")
    private String requestIp;

    @ApiModelProperty("请求来源端口")
    private String requestPort;

    @ApiModelProperty("请求的资源")
    private String requestUrl;

    @ApiModelProperty("拦截时间戳")
    private Long interceptTime;

    @Override
    public String toString() {
        return "SysGatewayInterceptLogEntity{" +
                "id=" + id +
                ", interceptType='" + interceptType + '\'' +
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
        SysGatewayInterceptLogEntity that = (SysGatewayInterceptLogEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(interceptType, that.interceptType) &&
                Objects.equals(requestIp, that.requestIp) &&
                Objects.equals(requestPort, that.requestPort) &&
                Objects.equals(requestUrl, that.requestUrl) &&
                Objects.equals(interceptTime, that.interceptTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, interceptType, requestIp, requestPort, requestUrl, interceptTime);
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

    public Long getInterceptTime() {
        return interceptTime;
    }

    public void setInterceptTime(Long interceptTime) {
        this.interceptTime = interceptTime;
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
