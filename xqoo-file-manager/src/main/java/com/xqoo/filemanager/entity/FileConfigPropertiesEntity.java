package com.xqoo.filemanager.entity;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Map;

/**
 * file_config_properties实体类
 *
 * @author xqoo-code-gen
 * @date 2021-01-19 20:48:15
 */
@ApiModel("上传参数主体表实体")
@TableName(value = "file_config_properties")
public class FileConfigPropertiesEntity extends Model<FileConfigPropertiesEntity> {

    private static final long serialVersionUID = 818274392361371679L;

    @ApiModelProperty("自增长id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("父级id")
    @NotNull(message = "操作失败，父级id不能为空")
    private Integer parentId;

    @ApiModelProperty("")
    @NotBlank(message = "操作失败，参数key名不能为空")
    @Size(min = 2, max = 32, message = "参数key名在2-32个字符之间")
    private String propertiesLabel;

    @ApiModelProperty("属性值，最长2048位")
    @NotBlank(message = "操作失败，参数值不能为空")
    @Size(min = 2, max = 512, message = "参数值在2-512个字符之间")
    private String propertiesValue;

    @ApiModelProperty("属性备注说明，最多64字")
    private String propertiesRemark;

    @ApiModelProperty("创建人")
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date createDate;

    @ApiModelProperty("最近修改人")
    @TableField(value = "update_by", fill = FieldFill.UPDATE)
    private String updateBy;

    @ApiModelProperty("最近修改时间")
    @TableField(value = "update_date", fill = FieldFill.UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date updateDate;

    @ApiModelProperty("备注信息")
    private String remarkTips;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId(){
        return this.id;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getParentId(){
        return this.parentId;
    }

    public void setPropertiesLabel(String propertiesLabel) {
        this.propertiesLabel = propertiesLabel;
    }

    public String getPropertiesLabel(){
        return this.propertiesLabel;
    }

    public void setPropertiesValue(String propertiesValue) {
        this.propertiesValue = propertiesValue;
    }

    public String getPropertiesValue(){
        return this.propertiesValue;
    }

    public void setPropertiesRemark(String propertiesRemark) {
        this.propertiesRemark = propertiesRemark;
    }

    public String getPropertiesRemark(){
        return this.propertiesRemark;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateBy(){
        return this.createBy;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCreateDate(){
        return this.createDate;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateBy(){
        return this.updateBy;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getUpdateDate(){
        return this.updateDate;
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
    public FileConfigPropertiesEntity fromMap(Map<String, Object> valueMap) {
        return BeanUtil.mapToBean(valueMap, this.getClass(), false, null);
    }

    @Override
    public String toString(){
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("parentId", getParentId())
            .append("propertiesLabel", getPropertiesLabel())
            .append("propertiesValue", getPropertiesValue())
            .append("propertiesRemark", getPropertiesRemark())
            .append("createBy", getCreateBy())
            .append("createDate", getCreateDate())
            .append("updateBy", getUpdateBy())
            .append("updateDate", getUpdateDate())
            .append("remarkTips", getRemarkTips())
            .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        FileConfigPropertiesEntity that = (FileConfigPropertiesEntity) o;
        return new EqualsBuilder()
                .append(id, that.id)
                .append(parentId, that.parentId)
                .append(propertiesLabel, that.propertiesLabel)
                .append(propertiesValue, that.propertiesValue)
                .append(propertiesRemark, that.propertiesRemark)
                .append(createBy, that.createBy)
                .append(createDate, that.createDate)
                .append(updateBy, that.updateBy)
                .append(updateDate, that.updateDate)
                .append(remarkTips, that.remarkTips)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(parentId)
                .append(propertiesLabel)
                .append(propertiesValue)
                .append(propertiesRemark)
                .append(createBy)
                .append(createDate)
                .append(updateBy)
                .append(updateDate)
                .append(remarkTips)
                .toHashCode();
    }
}
