package com.xqoo.annex.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import cn.hutool.core.bean.BeanUtil;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Map;
import java.util.Objects;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * agreement_info实体类
 *
 * @author xqoo-code-gen
 * @date 2021-01-14 14:56:53
 */
@ApiModel("系统内协议信息表实体")
@TableName(value = "agreement_info")
public class AgreementInfoEntity extends Model<AgreementInfoEntity> {

    private static final long serialVersionUID = -632133335442437084L;

    @ApiModelProperty("协议key值，不可重复")
    @TableId(value = "agreement_key")
    private String agreementKey;

    @ApiModelProperty("协议内容，html格式文本，最大160000字符")
    private String agreementContent;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date createDate;

    @ApiModelProperty("创建人user_id,系统则为system")
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_date", fill = FieldFill.UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date updateDate;

    @ApiModelProperty("更新人")
    @TableField(value = "update_by", fill = FieldFill.UPDATE)
    private String updateBy;

    @ApiModelProperty("备注说明")
    private String remarkTips;

    public void setAgreementKey(String agreementKey) {
        this.agreementKey = agreementKey;
    }

    public String getAgreementKey(){
        return this.agreementKey;
    }

    public void setAgreementContent(String agreementContent) {
        this.agreementContent = agreementContent;
    }

    public String getAgreementContent(){
        return this.agreementContent;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCreateDate(){
        return this.createDate;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateBy(){
        return this.createBy;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getUpdateDate(){
        return this.updateDate;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateBy(){
        return this.updateBy;
    }

    public void setRemarkTips(String remarkTips) {
        this.remarkTips = remarkTips;
    }

    public String getRemarkTips(){
        return this.remarkTips;
    }

    public Map<String, Object> toMap(){
        return BeanUtil.beanToMap(this);
    }

    /**
     * 将map直接赋值到bean中并返回一个新的bean
     * @param valueMap 需要转换的map
     */
    public AgreementInfoEntity fromMap(Map<String, Object> valueMap) {
        return BeanUtil.mapToBean(valueMap, this.getClass(), false, null);
    }

    @Override
    public String toString(){
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("agreementKey", getAgreementKey())
                .append("agreementContent", getAgreementContent())
                .append("createDate", getCreateDate())
                .append("createBy", getCreateBy())
                .append("updateDate", getUpdateDate())
                .append("updateBy", getUpdateBy())
                .append("remarkTips", getRemarkTips())
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AgreementInfoEntity that = (AgreementInfoEntity) o;
        return
                Objects.equals(agreementKey, that.agreementKey) &&
                        Objects.equals(agreementContent, that.agreementContent) &&
                        Objects.equals(createDate, that.createDate) &&
                        Objects.equals(createBy, that.createBy) &&
                        Objects.equals(updateDate, that.updateDate) &&
                        Objects.equals(updateBy, that.updateBy) &&
                        Objects.equals(remarkTips, that.remarkTips);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                agreementKey,
                agreementContent,
                createDate,
                createBy,
                updateDate,
                updateBy,
                remarkTips
        );
    }
}
