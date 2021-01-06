package com.xqoo.gateway.bean;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.Objects;

public class GatewayPredicatesConfigEntity {

    public GatewayPredicatesConfigEntity() {
    }

    public GatewayPredicatesConfigEntity(Integer id, String predicatesType, String predicatesName, String predicatesTips, String createBy, Date createDate, String updateBy, Date updateDate, String remarkTips) {
        this.id = id;
        this.predicatesType = predicatesType;
        this.predicatesName = predicatesName;
        this.predicatesTips = predicatesTips;
        this.createBy = createBy;
        this.createDate = createDate;
        this.updateBy = updateBy;
        this.updateDate = updateDate;
        this.remarkTips = remarkTips;
    }

    private Integer id;

    @ApiModelProperty(value="断言属性类型，args-[只有一个值，key为_genkey_家数字],map-[值有多个，key为具体属性名称，value为属性值]")
    private String predicatesType;

    @ApiModelProperty(value="断言属性名称")
    private String predicatesName;

    @ApiModelProperty(value="断言器说明文本")
    private String predicatesTips;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPredicatesType() {
        return predicatesType;
    }

    public void setPredicatesType(String predicatesType) {
        this.predicatesType = predicatesType;
    }

    public String getPredicatesName() {
        return predicatesName;
    }

    public void setPredicatesName(String predicatesName) {
        this.predicatesName = predicatesName;
    }

    public String getPredicatesTips() {
        return predicatesTips;
    }

    public void setPredicatesTips(String predicatesTips) {
        this.predicatesTips = predicatesTips;
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
        GatewayPredicatesConfigEntity that = (GatewayPredicatesConfigEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(predicatesType, that.predicatesType) &&
                Objects.equals(predicatesName, that.predicatesName) &&
                Objects.equals(predicatesTips, that.predicatesTips) &&
                Objects.equals(createBy, that.createBy) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(updateBy, that.updateBy) &&
                Objects.equals(updateDate, that.updateDate) &&
                Objects.equals(remarkTips, that.remarkTips);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, predicatesType, predicatesName, predicatesTips, createBy, createDate, updateBy, updateDate, remarkTips);
    }

    @Override
    public String toString() {
        return "GatewayPredicatesConfigEntity{" +
                "id=" + id +
                ", predicatesType='" + predicatesType + '\'' +
                ", predicatesName='" + predicatesName + '\'' +
                ", predicatesTips='" + predicatesTips + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createDate=" + createDate +
                ", updateBy='" + updateBy + '\'' +
                ", updateDate=" + updateDate +
                ", remarkTips='" + remarkTips + '\'' +
                '}';
    }
}
