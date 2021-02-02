package com.xqoo.salecenter.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import cn.hutool.core.bean.BeanUtil;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Map;
import java.util.Objects;

import java.lang.Long;

/**
 * sale_detail_info实体类
 *
 * @author xqoo-code-gen
 * @date 2021-02-01 15:07:13
 */
@ApiModel("售卖产品明细表实体")
@TableName(value = "sale_detail_info")
public class SaleDetailInfoEntity extends Model<SaleDetailInfoEntity> {

    private static final long serialVersionUID = 254357077930322797L;

    @ApiModelProperty("自增长id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("父级id")
    private String parentGoodsId;

    @ApiModelProperty("具体内容，不超过65000字符")
    private String contentDetail;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId(){
        return this.id;
    }

    public void setParentGoodsId(String parentGoodsId) {
        this.parentGoodsId = parentGoodsId;
    }

    public String getParentGoodsId(){
        return this.parentGoodsId;
    }

    public void setContentDetail(String contentDetail) {
        this.contentDetail = contentDetail;
    }

    public String getContentDetail(){
        return this.contentDetail;
    }

    public Map<String, Object> toMap(){
        return BeanUtil.beanToMap(this);
    }

    /**
     * 将map直接赋值到bean中并返回一个新的bean
     * @param valueMap 需要转换的map
     */
    public SaleDetailInfoEntity fromMap(Map<String, Object> valueMap) {
        return BeanUtil.mapToBean(valueMap, this.getClass(), false, null);
    }

    @Override
    public String toString(){
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("parentGoodsId", getParentGoodsId())
            .append("contentDetail", getContentDetail())
            .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        SaleDetailInfoEntity that = (SaleDetailInfoEntity) o;
        return new EqualsBuilder()
                .append(id, that.id)
                .append(parentGoodsId, that.parentGoodsId)
                .append(contentDetail, that.contentDetail)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(parentGoodsId)
                .append(contentDetail)
                .toHashCode();
    }
}
