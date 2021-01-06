package com.xqoo.feign.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
@ApiModel("操作日志返回信息")
@TableName(value = "sys_operation_log_response_info")
public class SysOperationLogResponseInfoEntity extends Model<SysOperationLogResponseInfoEntity> {


    @ApiModelProperty("自增长id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("父级日志信息id")
    private String parentId;

    @ApiModelProperty("响应数据json")
    private String messageContent;

    @Override
    public String toString() {
        return "SysOperationLogResponseInfoEntity{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", messageContent='" + messageContent + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysOperationLogResponseInfoEntity that = (SysOperationLogResponseInfoEntity) o;
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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
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
