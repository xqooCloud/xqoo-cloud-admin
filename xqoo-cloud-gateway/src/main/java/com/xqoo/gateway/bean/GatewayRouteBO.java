package com.xqoo.gateway.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xqoo.common.page.PageRequestBean;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.Objects;

public class GatewayRouteBO extends PageRequestBean {

    private Long id;

    @ApiModelProperty(value="路由状态，0-不可用，1-可用，默认可用")
    private Integer serviceStatus;

    @ApiModelProperty(value="服务id，不可重复")
    private String serviceId;

    @ApiModelProperty(value="服务类型。CLIENT-前台客户端服务，SYS-后台管理系统服务，TOOL-工具类服务")
    private String serviceType;

    @ApiModelProperty(value="转发地址，可以是服务中心的服务id，也可以是常规url")
    private String uri;

    @ApiModelProperty(value="排序顺序，不填默认参数0")
    private Integer orderNo;

    @ApiModelProperty(value="路由中文名，不能为null")
    private String serviceCname;

    @ApiModelProperty(value="记录创建人")
    private String createBy;

    @ApiModelProperty(value="记录创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date createDate;

    @ApiModelProperty(value="最后修改人")
    private String updateBy;

    @ApiModelProperty(value="最后修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date updateDate;

    @ApiModelProperty(value="备注说明")
    private String remarkTips;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(Integer serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public String getServiceCname() {
        return serviceCname;
    }

    public void setServiceCname(String serviceCname) {
        this.serviceCname = serviceCname;
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
        if (!super.equals(o)) return false;
        GatewayRouteBO that = (GatewayRouteBO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(serviceStatus, that.serviceStatus) &&
                Objects.equals(serviceId, that.serviceId) &&
                Objects.equals(serviceType, that.serviceType) &&
                Objects.equals(uri, that.uri) &&
                Objects.equals(orderNo, that.orderNo) &&
                Objects.equals(serviceCname, that.serviceCname) &&
                Objects.equals(createBy, that.createBy) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(updateBy, that.updateBy) &&
                Objects.equals(updateDate, that.updateDate) &&
                Objects.equals(remarkTips, that.remarkTips);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, serviceStatus, serviceId, serviceType, uri, orderNo, serviceCname, createBy, createDate, updateBy, updateDate, remarkTips);
    }

    @Override
    public String toString() {
        return "GatewayRouteBO{" +
                "id=" + id +
                ", serviceStatus=" + serviceStatus +
                ", serviceId='" + serviceId + '\'' +
                ", serviceType='" + serviceType + '\'' +
                ", uri='" + uri + '\'' +
                ", orderNo=" + orderNo +
                ", serviceCname='" + serviceCname + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createDate=" + createDate +
                ", updateBy='" + updateBy + '\'' +
                ", updateDate=" + updateDate +
                ", remarkTips='" + remarkTips + '\'' +
                '}';
    }
}
