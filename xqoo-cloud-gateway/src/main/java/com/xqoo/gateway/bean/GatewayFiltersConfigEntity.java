package com.xqoo.gateway.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import java.util.Objects;

public class GatewayFiltersConfigEntity {

    public GatewayFiltersConfigEntity() {
    }

    private Integer id;

    @ApiModelProperty(value="过滤属性类型，args-[只有一个值，key为_genkey_家数字],map-[值有多个，key为具体属性名称，value为属性值]")
    private String filtersType;

    @ApiModelProperty(value="过滤属性名称")
    private String filtersName;

    @ApiModelProperty(value="过滤器说明文本")
    private String filtersTips;

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

    public GatewayFiltersConfigEntity(Integer id, String filtersType, String filtersName, String filtersTips, String createBy, Date createDate, String updateBy, Date updateDate, String remarkTips) {
        this.id = id;
        this.filtersType = filtersType;
        this.filtersName = filtersName;
        this.filtersTips = filtersTips;
        this.createBy = createBy;
        this.createDate = createDate;
        this.updateBy = updateBy;
        this.updateDate = updateDate;
        this.remarkTips = remarkTips;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFiltersType() {
        return filtersType;
    }

    public void setFiltersType(String filtersType) {
        this.filtersType = filtersType;
    }

    public String getFiltersName() {
        return filtersName;
    }

    public void setFiltersName(String filtersName) {
        this.filtersName = filtersName;
    }

    public String getFiltersTips() {
        return filtersTips;
    }

    public void setFiltersTips(String filtersTips) {
        this.filtersTips = filtersTips;
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
        GatewayFiltersConfigEntity that = (GatewayFiltersConfigEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(filtersType, that.filtersType) &&
                Objects.equals(filtersName, that.filtersName) &&
                Objects.equals(filtersTips, that.filtersTips) &&
                Objects.equals(createBy, that.createBy) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(updateBy, that.updateBy) &&
                Objects.equals(updateDate, that.updateDate) &&
                Objects.equals(remarkTips, that.remarkTips);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, filtersType, filtersName, filtersTips, createBy, createDate, updateBy, updateDate, remarkTips);
    }

    @Override
    public String toString() {
        return "GatewayFiltersConfigEntity{" +
                "id=" + id +
                ", filtersType='" + filtersType + '\'' +
                ", filtersName='" + filtersName + '\'' +
                ", filtersTips='" + filtersTips + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createDate=" + createDate +
                ", updateBy='" + updateBy + '\'' +
                ", updateDate=" + updateDate +
                ", remarkTips='" + remarkTips + '\'' +
                '}';
    }
}
