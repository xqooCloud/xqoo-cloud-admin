package com.xqoo.filemanager.pojo;

import com.xqoo.common.page.PageRequestBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author gaoyang
 */
@ApiModel("文件上传记录分页查询")
public class UploadRecordQueryPOJO extends PageRequestBean {

    @ApiModelProperty("上传平台，ALI-阿里云，TENCENT-腾讯云，QINIU-七牛云，LOCAL-本地服务器")
    private String uploadPlat;

    @ApiModelProperty("上传方式，0-前端直传，1-后台上传")
    private Integer uploadType;

    @ApiModelProperty("上传状态，INIT-初始化，UPLOADING-上传中，OFFLINE-异常中断，CANCEL-取消，FINISH-已上传")
    private String uploadStatus;

    @ApiModelProperty("所传文件权限，public-公共读， protected-私有读，需要签名访问")
    private String accessType;

    @ApiModelProperty("文件相对路径，")
    private String fileRelativePath;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("uploadPlat", uploadPlat)
                .append("uploadType", uploadType)
                .append("uploadStatus", uploadStatus)
                .append("accessType", accessType)
                .append("fileRelativePath", fileRelativePath)
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

        UploadRecordQueryPOJO that = (UploadRecordQueryPOJO) o;

        return new EqualsBuilder().appendSuper(super.equals(o)).append(uploadPlat, that.uploadPlat).append(uploadType, that.uploadType).append(uploadStatus, that.uploadStatus).append(accessType, that.accessType).append(fileRelativePath, that.fileRelativePath).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).appendSuper(super.hashCode()).append(uploadPlat).append(uploadType).append(uploadStatus).append(accessType).append(fileRelativePath).toHashCode();
    }

    public String getUploadPlat() {
        return uploadPlat;
    }

    public void setUploadPlat(String uploadPlat) {
        this.uploadPlat = uploadPlat;
    }

    public Integer getUploadType() {
        return uploadType;
    }

    public void setUploadType(Integer uploadType) {
        this.uploadType = uploadType;
    }

    public String getUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(String uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public String getFileRelativePath() {
        return fileRelativePath;
    }

    public void setFileRelativePath(String fileRelativePath) {
        this.fileRelativePath = fileRelativePath;
    }
}
