package com.xqoo.common.core.exception;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpStatus;

import java.util.Objects;

public class SystemException extends RuntimeException {

    private static final long serialVersionUID = -7100752697402887805L;

    private static final String DEFAULT_MSG = "系统运行中发生了BUG";

    private HttpStatus errorCode;

    private String errorMessage;

    private String detailMessage;

    private JsonNode data;

    public SystemException(String message, String detailMessage, HttpStatus errorCode, JsonNode data) {
        this.errorMessage = message;
        this.data = data;
        this.detailMessage = detailMessage;
        this.errorCode = errorCode;
    }

    public SystemException(String message) {
        this.errorCode = HttpStatus.SERVICE_UNAVAILABLE;
        this.errorMessage = message;
    }

    public SystemException(String message, String detailMessage, Throwable e) {
        super(message, e);
        this.errorCode = HttpStatus.SERVICE_UNAVAILABLE;
        this.errorMessage = message;
        this.detailMessage = detailMessage;
    }

    public SystemException(String message, Throwable e) {
        super(message, e);
        this.errorMessage = message;
    }

    public SystemException(Throwable e) {
        super(DEFAULT_MSG, e);
        this.errorCode = HttpStatus.SERVICE_UNAVAILABLE;
        this.errorMessage = DEFAULT_MSG;
    }

    public SystemException(JsonNode data) {
        this.errorCode = HttpStatus.SERVICE_UNAVAILABLE;
        this.errorMessage = DEFAULT_MSG;
        this.data = data;
    }

    public SystemException(HttpStatus statusCode) {
        this.errorCode = statusCode;
        this.errorMessage = DEFAULT_MSG;
    }

    public HttpStatus getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(HttpStatus errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

    public void setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
    }

    public JsonNode getData() {
        return data;
    }

    public void setData(JsonNode data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SystemException that = (SystemException) o;
        return errorCode == that.errorCode &&
                Objects.equals(errorMessage, that.errorMessage) &&
                Objects.equals(detailMessage, that.detailMessage) &&
                Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errorCode, errorMessage, detailMessage, data);
    }

    @Override
    public String toString() {
        return "SystemException{" +
                "errorCode=" + errorCode.value() +
                ", errorMessage='" + errorMessage + '\'' +
                ", detailMessage='" + detailMessage + '\'' +
                ", data=" + data +
                '}';
    }
}
