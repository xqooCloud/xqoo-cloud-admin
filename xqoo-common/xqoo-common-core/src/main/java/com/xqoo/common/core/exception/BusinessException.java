package com.xqoo.common.core.exception;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpStatus;

import java.util.Objects;

public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 5565760508056698922L;

    private static final String DEFAULT_MSG = "程序运转出了点小问题,请稍后再试^_^";

    private HttpStatus errorCode;

    private String errorMessage;

    private JsonNode data;

    public BusinessException() {
        super();
    }

    public BusinessException(String message, HttpStatus errorCode, JsonNode data) {
        this.errorMessage = message;
        this.errorCode = errorCode;
        this.data = data;
    }

    public BusinessException(String message) {
        this.errorCode = HttpStatus.SERVICE_UNAVAILABLE;
        this.errorMessage = message;
    }

    public BusinessException(String message, Throwable e) {
        super(message, e);
        this.errorCode = HttpStatus.SERVICE_UNAVAILABLE;
        this.errorMessage = message;
    }

    public BusinessException(Throwable e) {
        super(DEFAULT_MSG, e);
        this.errorCode = HttpStatus.SERVICE_UNAVAILABLE;
        this.errorMessage = DEFAULT_MSG;
    }

    public BusinessException(JsonNode data) {
        this.errorCode = HttpStatus.SERVICE_UNAVAILABLE;
        this.errorMessage = DEFAULT_MSG;
        this.data = data;
    }

    public BusinessException(HttpStatus statusCode) {
        this.errorCode = statusCode;
        this.errorMessage = DEFAULT_MSG;
    }
    protected BusinessException(String message, Throwable cause,
                                boolean enableSuppression,
                                boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
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
        BusinessException that = (BusinessException) o;
        return errorCode == that.errorCode &&
                Objects.equals(errorMessage, that.errorMessage) &&
                Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errorCode, errorMessage, data);
    }

    @Override
    public String toString() {
        return "BusinessException{" +
                "errorCode=" + errorCode.value() +
                ", errorMessage='" + errorMessage + '\'' +
                ", data=" + data +
                '}';
    }
}
