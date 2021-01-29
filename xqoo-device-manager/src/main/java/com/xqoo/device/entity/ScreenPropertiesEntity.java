package com.xqoo.device.entity;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Map;

/**
 * screen_properties实体类
 *
 * @author xqoo-code-gen
 * @date 2021-01-28 15:12:35
 */
@ApiModel("屏幕信息参数表实体")
@TableName(value = "screen_properties")
public class ScreenPropertiesEntity extends Model<ScreenPropertiesEntity> {

    private static final long serialVersionUID = -18395912150565534L;

    @ApiModelProperty("自增长id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("父级id")
    private String parentId;

    @ApiModelProperty("标签名")
    private String propertiesLabel;

    @ApiModelProperty("标签值")
    private String propertiesValue;

    @ApiModelProperty("显示排序")
    private Integer sortNo;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId(){
        return this.id;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentId(){
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

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public Map<String, Object> toMap(){
        return BeanUtil.beanToMap(this);
    }

    /**
     * 将map直接赋值到bean中并返回一个新的bean
     * @param valueMap 需要转换的map
     */
    public ScreenPropertiesEntity fromMap(Map<String, Object> valueMap) {
        return BeanUtil.mapToBean(valueMap, this.getClass(), false, null);
    }

    @Override
    public String toString(){
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("parentId", getParentId())
            .append("propertiesLabel", getPropertiesLabel())
            .append("propertiesValue", getPropertiesValue())
            .append("sortNo", getSortNo())
            .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        ScreenPropertiesEntity that = (ScreenPropertiesEntity) o;
        return new EqualsBuilder()
                .append(id, that.id)
                .append(parentId, that.parentId)
                .append(propertiesLabel, that.propertiesLabel)
                .append(propertiesValue, that.propertiesValue)
                .append(sortNo, that.sortNo)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(parentId)
                .append(propertiesLabel)
                .append(propertiesValue)
                .append(sortNo)
                .toHashCode();
    }
}
