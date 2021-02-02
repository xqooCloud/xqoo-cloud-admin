package com.xqoo.salecenter.entity;

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
 * goods_properties实体类
 *
 * @author xqoo-code-gen
 * @date 2021-02-01 15:02:42
 */
@ApiModel("商品信息参数表实体")
@TableName(value = "goods_properties")
public class GoodsPropertiesEntity extends Model<GoodsPropertiesEntity> {

    private static final long serialVersionUID = -344528848694565828L;

    @ApiModelProperty("自增长id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("父级id")
    private Long parentGoodsId;

    @ApiModelProperty("标签名")
    private String propertiesLabel;

    @ApiModelProperty("标签值")
    private String propertiesValue;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId(){
        return this.id;
    }

    public void setParentGoodsId(Long parentGoodsId) {
        this.parentGoodsId = parentGoodsId;
    }

    public Long getParentGoodsId(){
        return this.parentGoodsId;
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

    public Map<String, Object> toMap(){
        return BeanUtil.beanToMap(this);
    }

    /**
     * 将map直接赋值到bean中并返回一个新的bean
     * @param valueMap 需要转换的map
     */
    public GoodsPropertiesEntity fromMap(Map<String, Object> valueMap) {
        return BeanUtil.mapToBean(valueMap, this.getClass(), false, null);
    }

    @Override
    public String toString(){
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("parentGoodsId", getParentGoodsId())
            .append("propertiesLabel", getPropertiesLabel())
            .append("propertiesValue", getPropertiesValue())
            .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        GoodsPropertiesEntity that = (GoodsPropertiesEntity) o;
        return new EqualsBuilder()
                .append(id, that.id)
                .append(parentGoodsId, that.parentGoodsId)
                .append(propertiesLabel, that.propertiesLabel)
                .append(propertiesValue, that.propertiesValue)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(parentGoodsId)
                .append(propertiesLabel)
                .append(propertiesValue)
                .toHashCode();
    }
}
