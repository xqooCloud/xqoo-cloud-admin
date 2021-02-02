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
 * goods_picture_info实体类
 *
 * @author xqoo-code-gen
 * @date 2021-02-01 15:01:19
 */
@ApiModel("商品预览图片表实体")
@TableName(value = "goods_picture_info")
public class GoodsPictureInfoEntity extends Model<GoodsPictureInfoEntity> {

    private static final long serialVersionUID = 851636602812076331L;

    @ApiModelProperty("自增长主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("父级id")
    private String parentGoodsId;

    @ApiModelProperty("文件的名称")
    private String fileName;

    @ApiModelProperty("文件的地址")
    private String filePath;

    @ApiModelProperty("文件在系统内记录的id")
    private String fileId;

    @ApiModelProperty("排序顺序")
    private Integer sortNo;

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

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName(){
        return this.fileName;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath(){
        return this.filePath;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileId(){
        return this.fileId;
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
    public GoodsPictureInfoEntity fromMap(Map<String, Object> valueMap) {
        return BeanUtil.mapToBean(valueMap, this.getClass(), false, null);
    }

    @Override
    public String toString(){
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("parentGoodsId", getParentGoodsId())
            .append("fileName", getFileName())
            .append("filePath", getFilePath())
            .append("fileId", getFileId())
            .append("sortNo", getSortNo())
            .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        GoodsPictureInfoEntity that = (GoodsPictureInfoEntity) o;
        return new EqualsBuilder()
                .append(id, that.id)
                .append(parentGoodsId, that.parentGoodsId)
                .append(fileName, that.fileName)
                .append(filePath, that.filePath)
                .append(fileId, that.fileId)
                .append(sortNo, that.sortNo)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(parentGoodsId)
                .append(fileName)
                .append(filePath)
                .append(fileId)
                .append(sortNo)
                .toHashCode();
    }
}
