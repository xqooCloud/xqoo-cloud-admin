package com.xqoo.codegen.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.Objects;

/**
 * @author mumu
 * @date 2019/11/08 上午11:17
 **/
@ApiModel("数据源表")
@TableName(value = "data_source_info")
public class DataSourceInfoEntity extends Model<DataSourceInfoEntity> {


    private static final long serialVersionUID = 3160643224016338954L;
    @ApiModelProperty("自增长id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("是否逻辑删除，0-未删除，1-已删除")
    private Integer delFlag;

    @ApiModelProperty("数据库类型，mysql.oracle.db2等等，目前仅支持mysql")
    private String dataBaseType;

    @ApiModelProperty("数据库显示库名")
    private String dataBaseShowName;

    @ApiModelProperty("数据库地址")
    private String dataBaseHost;

    @ApiModelProperty("数据库端口，默认3306")
    private String dataBasePort;

    @ApiModelProperty("数据库库名")
    private String dataBaseName;

    @ApiModelProperty("数据库连接参数，可不填，将带入系统默认参数")
    private String dataBaseProperties;

    @ApiModelProperty("数据库用户名")
    private String dataBaseUserName;

    @ApiModelProperty("数据库密码")
    private String dataBasePassword;

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

    @Override
    public String toString() {
        return "DataSourceInfoEntity{" +
                "id=" + id +
                ", delFlag=" + delFlag +
                ", dataBaseType='" + dataBaseType + '\'' +
                ", dataBaseShowName='" + dataBaseShowName + '\'' +
                ", dataBaseHost='" + dataBaseHost + '\'' +
                ", dataBasePort='" + dataBasePort + '\'' +
                ", dataBaseName='" + dataBaseName + '\'' +
                ", dataBaseProperties='" + dataBaseProperties + '\'' +
                ", dataBaseUserName='" + dataBaseUserName + '\'' +
                ", dataBasePassword='" + dataBasePassword + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createDate=" + createDate +
                ", updateBy='" + updateBy + '\'' +
                ", updateDate=" + updateDate +
                ", remarkTips='" + remarkTips + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataSourceInfoEntity that = (DataSourceInfoEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(delFlag, that.delFlag) &&
                Objects.equals(dataBaseType, that.dataBaseType) &&
                Objects.equals(dataBaseShowName, that.dataBaseShowName) &&
                Objects.equals(dataBaseHost, that.dataBaseHost) &&
                Objects.equals(dataBasePort, that.dataBasePort) &&
                Objects.equals(dataBaseName, that.dataBaseName) &&
                Objects.equals(dataBaseProperties, that.dataBaseProperties) &&
                Objects.equals(dataBaseUserName, that.dataBaseUserName) &&
                Objects.equals(dataBasePassword, that.dataBasePassword) &&
                Objects.equals(createBy, that.createBy) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(updateBy, that.updateBy) &&
                Objects.equals(updateDate, that.updateDate) &&
                Objects.equals(remarkTips, that.remarkTips);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, delFlag, dataBaseType, dataBaseShowName, dataBaseHost, dataBasePort, dataBaseName, dataBaseProperties, dataBaseUserName, dataBasePassword, createBy, createDate, updateBy, updateDate, remarkTips);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getDataBaseType() {
        return dataBaseType;
    }

    public void setDataBaseType(String dataBaseType) {
        this.dataBaseType = dataBaseType;
    }

    public String getDataBaseShowName() {
        return dataBaseShowName;
    }

    public void setDataBaseShowName(String dataBaseShowName) {
        this.dataBaseShowName = dataBaseShowName;
    }

    public String getDataBaseHost() {
        return dataBaseHost;
    }

    public void setDataBaseHost(String dataBaseHost) {
        this.dataBaseHost = dataBaseHost;
    }

    public String getDataBasePort() {
        return dataBasePort;
    }

    public void setDataBasePort(String dataBasePort) {
        this.dataBasePort = dataBasePort;
    }

    public String getDataBaseName() {
        return dataBaseName;
    }

    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }

    public String getDataBaseProperties() {
        return dataBaseProperties;
    }

    public void setDataBaseProperties(String dataBaseProperties) {
        this.dataBaseProperties = dataBaseProperties;
    }

    public String getDataBaseUserName() {
        return dataBaseUserName;
    }

    public void setDataBaseUserName(String dataBaseUserName) {
        this.dataBaseUserName = dataBaseUserName;
    }

    public String getDataBasePassword() {
        return dataBasePassword;
    }

    public void setDataBasePassword(String dataBasePassword) {
        this.dataBasePassword = dataBasePassword;
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
}
