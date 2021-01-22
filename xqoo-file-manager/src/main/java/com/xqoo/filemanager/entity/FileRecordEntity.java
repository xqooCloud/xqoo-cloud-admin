package com.xqoo.filemanager.entity;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * file_record实体类
 *
 * @author xqoo-code-gen
 * @date 2021-01-22 23:37:25
 */
@ApiModel("文件传输记录实体")
@TableName(value = "file_record")
public class FileRecordEntity extends Model<FileRecordEntity> {

    private static final long serialVersionUID = 45267774241379088L;

    @ApiModelProperty("主键，特定规则生成的md5")
    @TableId(value = "id")
    private String id;

    @ApiModelProperty("是否删除，0-未删除，1-已删除")
    private Integer delFlag;

    @ApiModelProperty("上传平台，ALI-阿里云，TENCENT-腾讯云，QINIU-七牛云，LOCAL-本地服务器")
    private String uploadPlat;

    @ApiModelProperty("上传方式，0-前端直传，1-后台上传")
    private Integer uploadType;

    @ApiModelProperty("上传状态，INIT-初始化，UPLOADING-上传中，OFFLINE-异常中断，CANCEL-取消，FINISH-已上传")
    private String uploadStatus;

    @ApiModelProperty("所传文件权限，public-公共读， protected-私有读，需要签名访问")
    private String accessType;

    @ApiModelProperty("平台保存文件MD5")
    private String platFileMd5;

    @ApiModelProperty("文件名，最长不超过256字")
    private String fileName;

    @ApiModelProperty("文件类型")
    private String fileMimeType;

    @ApiModelProperty("文件md5")
    private String fileMd5;

    @ApiModelProperty("文件相对路径，")
    private String fileRelativePath;

    @ApiModelProperty("文件下载路径，完整路径")
    private String fileUrlPath;

    @ApiModelProperty("文件上传初始化时间戳")
    private Long fileInitTime;

    @ApiModelProperty("文件上传完成时间戳")
    private Long fileFinishTime;

    @ApiModelProperty("文件上传完成的持续时间,单位为秒")
    private Long fileUploadSpendTime;

    @ApiModelProperty("文件上传的bucket")
    private String fileBucket;

    @ApiModelProperty("文件大小，单位字节")
    private Long fileSize;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date createDate;

    @ApiModelProperty("创建人uid")
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty("最近修改")
    @TableField(value = "update_date", fill = FieldFill.UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date updateDate;

    @ApiModelProperty("最近修改人")
    @TableField(value = "update_by", fill = FieldFill.UPDATE)
    private String updateBy;

    @ApiModelProperty("")
    private String remarkTips;

    public void setId(String id) {
        this.id = id;
    }

    public String getId(){
        return this.id;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getDelFlag(){
        return this.delFlag;
    }

    public void setUploadPlat(String uploadPlat) {
        this.uploadPlat = uploadPlat;
    }

    public String getUploadPlat(){
        return this.uploadPlat;
    }

    public void setUploadType(Integer uploadType) {
        this.uploadType = uploadType;
    }

    public Integer getUploadType(){
        return this.uploadType;
    }

    public void setUploadStatus(String uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    public String getUploadStatus(){
        return this.uploadStatus;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public void setPlatFileMd5(String platFileMd5) {
        this.platFileMd5 = platFileMd5;
    }

    public String getPlatFileMd5(){
        return this.platFileMd5;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName(){
        return this.fileName;
    }

    public void setFileMimeType(String fileMimeType) {
        this.fileMimeType = fileMimeType;
    }

    public String getFileMimeType(){
        return this.fileMimeType;
    }

    public void setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5;
    }

    public String getFileMd5(){
        return this.fileMd5;
    }

    public void setFileRelativePath(String fileRelativePath) {
        this.fileRelativePath = fileRelativePath;
    }

    public String getFileRelativePath(){
        return this.fileRelativePath;
    }

    public void setFileUrlPath(String fileUrlPath) {
        this.fileUrlPath = fileUrlPath;
    }

    public String getFileUrlPath(){
        return this.fileUrlPath;
    }

    public void setFileInitTime(Long fileInitTime) {
        this.fileInitTime = fileInitTime;
    }

    public Long getFileInitTime(){
        return this.fileInitTime;
    }

    public void setFileFinishTime(Long fileFinishTime) {
        this.fileFinishTime = fileFinishTime;
    }

    public Long getFileFinishTime(){
        return this.fileFinishTime;
    }

    public void setFileUploadSpendTime(Long fileUploadSpendTime) {
        this.fileUploadSpendTime = fileUploadSpendTime;
    }

    public Long getFileUploadSpendTime(){
        return this.fileUploadSpendTime;
    }

    public void setFileBucket(String fileBucket) {
        this.fileBucket = fileBucket;
    }

    public String getFileBucket(){
        return this.fileBucket;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Long getFileSize(){
        return this.fileSize;
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
    public FileRecordEntity fromMap(Map<String, Object> valueMap) {
        return BeanUtil.mapToBean(valueMap, this.getClass(), false, null);
    }

    @Override
    public String toString(){
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("delFlag", getDelFlag())
            .append("uploadPlat", getUploadPlat())
            .append("uploadType", getUploadType())
            .append("uploadStatus", getUploadStatus())
            .append("accessType", getAccessType())
            .append("platFileMd5", getPlatFileMd5())
            .append("fileName", getFileName())
            .append("fileMimeType", getFileMimeType())
            .append("fileMd5", getFileMd5())
            .append("fileRelativePath", getFileRelativePath())
            .append("fileUrlPath", getFileUrlPath())
            .append("fileInitTime", getFileInitTime())
            .append("fileFinishTime", getFileFinishTime())
            .append("fileUploadSpendTime", getFileUploadSpendTime())
            .append("fileBucket", getFileBucket())
            .append("fileSize", getFileSize())
            .append("createDate", getCreateDate())
            .append("createBy", getCreateBy())
            .append("updateDate", getUpdateDate())
            .append("updateBy", getUpdateBy())
            .append("remarkTips", getRemarkTips())
            .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        FileRecordEntity that = (FileRecordEntity) o;
        return new EqualsBuilder()
                .append(id, that.id)
                .append(delFlag, that.delFlag)
                .append(uploadPlat, that.uploadPlat)
                .append(uploadType, that.uploadType)
                .append(uploadStatus, that.uploadStatus)
                .append(accessType, that.accessType)
                .append(platFileMd5, that.platFileMd5)
                .append(fileName, that.fileName)
                .append(fileMimeType, that.fileMimeType)
                .append(fileMd5, that.fileMd5)
                .append(fileRelativePath, that.fileRelativePath)
                .append(fileUrlPath, that.fileUrlPath)
                .append(fileInitTime, that.fileInitTime)
                .append(fileFinishTime, that.fileFinishTime)
                .append(fileUploadSpendTime, that.fileUploadSpendTime)
                .append(fileBucket, that.fileBucket)
                .append(fileSize, that.fileSize)
                .append(createDate, that.createDate)
                .append(createBy, that.createBy)
                .append(updateDate, that.updateDate)
                .append(updateBy, that.updateBy)
                .append(remarkTips, that.remarkTips)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(delFlag)
                .append(uploadPlat)
                .append(uploadType)
                .append(uploadStatus)
                .append(accessType)
                .append(platFileMd5)
                .append(fileName)
                .append(fileMimeType)
                .append(fileMd5)
                .append(fileRelativePath)
                .append(fileUrlPath)
                .append(fileInitTime)
                .append(fileFinishTime)
                .append(fileUploadSpendTime)
                .append(fileBucket)
                .append(fileSize)
                .append(createDate)
                .append(createBy)
                .append(updateDate)
                .append(updateBy)
                .append(remarkTips)
                .toHashCode();
    }
}
