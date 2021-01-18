package com.xqoo.annex.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@ApiModel("协议信息展示")
public class AgreementInfoVO implements Serializable {

    private static final long serialVersionUID = -4547627532037201546L;

    @ApiModelProperty("协议key")
    @NotNull(message = "协议key不能为空")
    private String agreementKey;

    @ApiModelProperty("协议内容")
    @NotNull(message = "协议内容不能为空")
    private String agreementContent;

    @ApiModelProperty("创建人")
    private String createBy;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date createDate;

    @ApiModelProperty("最近修改人")
    private String updateBy;

    @ApiModelProperty("最近修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date updateDate;

    @ApiModelProperty("备注信息")
    private String remarkTips;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AgreementInfoVO)) return false;
        AgreementInfoVO that = (AgreementInfoVO) o;
        return Objects.equals(agreementKey, that.agreementKey) &&
                Objects.equals(getAgreementContent(), that.getAgreementContent()) &&
                Objects.equals(getCreateBy(), that.getCreateBy()) &&
                Objects.equals(getCreateDate(), that.getCreateDate()) &&
                Objects.equals(getUpdateBy(), that.getUpdateBy()) &&
                Objects.equals(getUpdateDate(), that.getUpdateDate()) &&
                Objects.equals(getRemarkTips(), that.getRemarkTips());
    }

    @Override
    public int hashCode() {
        return Objects.hash(agreementKey, getAgreementContent(), getCreateBy(), getCreateDate(), getUpdateBy(), getUpdateDate(), getRemarkTips());
    }

    @Override
    public String toString() {
        return "AgreementInfoVO{" +
                "agreementKey='" + agreementKey + '\'' +
                ", agreementContent='" + agreementContent + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createDate=" + createDate +
                ", updateBy='" + updateBy + '\'' +
                ", updateDate=" + updateDate +
                ", remarkTips='" + remarkTips + '\'' +
                '}';
    }

    public String getAgreementKey() {
        return agreementKey;
    }

    public void setAgreementKey(String agreementKey) {
        this.agreementKey = agreementKey;
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

    public String getAgreementContent() {
        return agreementContent;
    }

    public void setAgreementContent(String agreementContent) {
        this.agreementContent = agreementContent;
    }
}
