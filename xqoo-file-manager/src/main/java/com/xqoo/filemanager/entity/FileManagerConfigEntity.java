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

import java.util.Date;
import java.util.Map;

/**
 * file_manager_config实体类
 *
 * @author xqoo-code-gen
 * @date 2021-01-19 20:16:13
 */
@ApiModel("文件管理配置总表实体")
@TableName(value = "file_manager_config")
public class FileManagerConfigEntity extends Model<FileManagerConfigEntity> {

    private static final long serialVersionUID = -708168875183174032L;

    @ApiModelProperty("自增长id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("上传平台，ALI-阿里云，TENCENT-腾讯云，QINIU-七牛云，LOCAL-本地服务器")
    private String uploadPlat;

    @ApiModelProperty("产品状态，0-不可用，1-可用")
    private Integer configStatus;

    @ApiModelProperty("平台名称")
    private String uploadPlatName;

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

    public void setUploadPlat(String uploadPlat) {
        this.uploadPlat = uploadPlat;
    }

    public String getUploadPlat(){
        return this.uploadPlat;
    }

    public void setConfigStatus(Integer configStatus) {
        this.configStatus = configStatus;
    }

    public Integer getConfigStatus(){
        return this.configStatus;
    }

    public void setUploadPlatName(String uploadPlatName) {
        this.uploadPlatName = uploadPlatName;
    }

    public String getUploadPlatName(){
        return this.uploadPlatName;
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
    public FileManagerConfigEntity fromMap(Map<String, Object> valueMap) {
        return BeanUtil.mapToBean(valueMap, this.getClass(), false, null);
    }

    @Override
    public String toString(){
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("uploadPlat", getUploadPlat())
            .append("configStatus", getConfigStatus())
            .append("uploadPlatName", getUploadPlatName())
            .append("createBy", getCreateBy())
            .append("createDate", getCreateDate())
            .append("updateBy", getUpdateBy())
            .append("updateDate", getUpdateDate())
            .append("remarkTips", getRemarkTips())
            .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FileManagerConfigEntity that = (FileManagerConfigEntity) o;
        return new EqualsBuilder()
                .append(id, that.id)
                .append(uploadPlat, that.uploadPlat)
                .append(configStatus, that.configStatus)
                .append(uploadPlatName, that.uploadPlatName)
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
                .append(uploadPlat)
                .append(configStatus)
                .append(uploadPlatName)
                .append(createBy)
                .append(createDate)
                .append(updateBy)
                .append(updateDate)
                .append(remarkTips)
                .toHashCode();
    }
}
