package com.xqoo.common.dto;

import com.xqoo.common.enums.CommunicateStatusEnum;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * 系统内部通信传输实体
 * @author Gaoyang by 2020-12-10
 */
public class SystemCommunicateDTO<T> implements Serializable {
    private static final long serialVersionUID = 3755471352672444458L;

    /**
     * 通信传输返回状态
     */
    private CommunicateStatusEnum status;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 返回的数据类型
     */
    private T result;

    /**
     * 总处理数据量，批量操作时才有用
     */
    private Integer handleCount;

    /**
     * 失败/错误统计量，仅当批量操作时有用
     */
    private Integer errorCount;

    /**
     * 消息列表，批量操作时才有用
     */
    private List<Object> messageArr;

    public SystemCommunicateDTO() {
    }

    public SystemCommunicateDTO(Integer handleCount, Integer errorCount) {
        if(errorCount <= 0){
            this.status = CommunicateStatusEnum.SUCCESS;
            this.message = "总条数【" + handleCount + "】处理成功";
        }
        if(errorCount > 0 && errorCount < handleCount){
            this.status = CommunicateStatusEnum.WARN;
            this.message = "总条数【" + handleCount + "】处理完成，但有【" + errorCount + "】条处理失败";
        }
        if(errorCount >= handleCount){
            this.status = CommunicateStatusEnum.FAIL;
            this.message = "总条数【" + handleCount + "】处理失败";
        }
        this.handleCount = handleCount;
        this.errorCount = errorCount;
    }

    public SystemCommunicateDTO(Integer handleCount) {
        this.status = CommunicateStatusEnum.SUCCESS;
        this.message = "总条数【" + handleCount + "】处理成功";
        this.handleCount = handleCount;
        this.errorCount = 0;
    }

    public SystemCommunicateDTO(CommunicateStatusEnum status, String message, T data) {
        this.status = status;
        this.message = message;
        this.result = data;
    }

    public SystemCommunicateDTO(CommunicateStatusEnum status,String message) {
        this.status = status;
        this.message = message;
    }
    public SystemCommunicateDTO(T data) {
        this.status = CommunicateStatusEnum.SUCCESS;
        this.message = "成功";
        this.result = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SystemCommunicateDTO<?> that = (SystemCommunicateDTO<?>) o;
        return status == that.status &&
                Objects.equals(message, that.message) &&
                Objects.equals(result, that.result) &&
                Objects.equals(handleCount, that.handleCount) &&
                Objects.equals(errorCount, that.errorCount) &&
                Objects.equals(messageArr, that.messageArr);
    }

    @Override
    public String toString() {
        return "SystemCommunicateDTO{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", result=" + result +
                ", handleCount=" + handleCount +
                ", errorCount=" + errorCount +
                ", messageArr=" + messageArr +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, message, result, handleCount, errorCount, messageArr);
    }

    public CommunicateStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CommunicateStatusEnum status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public Integer getHandleCount() {
        return handleCount;
    }

    public void setHandleCount(Integer handleCount) {
        this.handleCount = handleCount;
    }

    public Integer getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(Integer errorCount) {
        this.errorCount = errorCount;
    }

    public List<Object> getMessageArr() {
        return messageArr;
    }

    public void setMessageArr(List<Object> messageArr) {
        this.messageArr = messageArr;
    }
}
