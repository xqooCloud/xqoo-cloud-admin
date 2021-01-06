package com.xqoo.operlog.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author mumu
 * @date 2019/11/08 上午11:17
 **/
@ApiModel("操作日志请求信息")
@TableName(value = "sys_operation_log_request_info")
public class SysOperationLogRequestInfoEntity extends Model<SysOperationLogRequestInfoEntity> {


    private static final long serialVersionUID = 2693748032581938710L;
    @ApiModelProperty("自增长id")
    @TableId(value = "id")
    private Long id;

    @ApiModelProperty("父级日志信息id")
    private Long parentId;

    @ApiModelProperty("请求数据json")
    private String messageContent;

    @Override
    public String toString() {
        return "SysOperationLogRequestInfoEntity{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", messageContent='" + messageContent + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysOperationLogRequestInfoEntity that = (SysOperationLogRequestInfoEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(parentId, that.parentId) &&
                Objects.equals(messageContent, that.messageContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, parentId, messageContent);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
