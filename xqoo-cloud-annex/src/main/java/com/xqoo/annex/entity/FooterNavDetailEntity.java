package com.xqoo.annex.entity;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * footer_nav_detail实体类
 *
 * @author xqoo-code-gen
 * @date 2021-01-14 15:29:04
 */
@ApiModel("页脚导航文字明细实体")
@TableName(value = "footer_nav_detail")
public class FooterNavDetailEntity extends Model<FooterNavDetailEntity> {

    private static final long serialVersionUID = 452834624369882185L;

    @ApiModelProperty("自增长id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("父级分组id")
    private Integer groupId;

    @ApiModelProperty("展示文字")
    private String labelText;

    @ApiModelProperty("辅助说明")
    private String labelTitle;

    @ApiModelProperty("跳转链接")
    private String redirectUrl;

    @ApiModelProperty("显示顺序")
    private Integer sortNo;

    @ApiModelProperty("图标名字")
    private String iconName;

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

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId(){
        return this.id;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getGroupId(){
        return this.groupId;
    }

    public void setLabelText(String labelText) {
        this.labelText = labelText;
    }

    public String getLabelText(){
        return this.labelText;
    }

    public void setLabelTitle(String labelTitle) {
        this.labelTitle = labelTitle;
    }

    public String getLabelTitle(){
        return this.labelTitle;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getRedirectUrl(){
        return this.redirectUrl;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public Integer getSortNo(){
        return this.sortNo;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public String getIconName(){
        return this.iconName;
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
    public FooterNavDetailEntity fromMap(Map<String, Object> valueMap) {
        return BeanUtil.mapToBean(valueMap, this.getClass(), false, null);
    }

    @Override
    public String toString(){
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("groupId", getGroupId())
                .append("labelText", getLabelText())
                .append("labelTitle", getLabelTitle())
                .append("redirectUrl", getRedirectUrl())
                .append("sortNo", getSortNo())
                .append("iconName", getIconName())
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
        FooterNavDetailEntity that = (FooterNavDetailEntity) o;
        return
                Objects.equals(id, that.id) &&
                        Objects.equals(groupId, that.groupId) &&
                        Objects.equals(labelText, that.labelText) &&
                        Objects.equals(labelTitle, that.labelTitle) &&
                        Objects.equals(redirectUrl, that.redirectUrl) &&
                        Objects.equals(sortNo, that.sortNo) &&
                        Objects.equals(iconName, that.iconName) &&
                        Objects.equals(createDate, that.createDate) &&
                        Objects.equals(createBy, that.createBy) &&
                        Objects.equals(updateDate, that.updateDate) &&
                        Objects.equals(updateBy, that.updateBy) &&
                        Objects.equals(remarkTips, that.remarkTips);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                groupId,
                labelText,
                labelTitle,
                redirectUrl,
                sortNo,
                iconName,
                createDate,
                createBy,
                updateDate,
                updateBy,
                remarkTips
        );
    }
}
