package com.xqoo.common.core.exception;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpStatus;

import java.util.Objects;

public class AccessDenyException extends RuntimeException {

    private static final long serialVersionUID = 5565760508056698922L;

    private static final String DEFAULT_MSG = "您的权限不足以支持当前操作";

    private HttpStatus errorCode;

    private String errorMessage;

    private JsonNode data;

    public AccessDenyException(String message, HttpStatus errorCode, JsonNode data) {
        this.errorMessage = message;
        this.errorCode = errorCode;
        this.data = data;
    }

    public AccessDenyException(String message) {
        this.errorCode = HttpStatus.UNAUTHORIZED;
        this.errorMessage = message;
    }


    public AccessDenyException(String message, Throwable e) {
        super(message, e);
        this.errorCode = HttpStatus.UNAUTHORIZED;
        this.errorMessage = message;
    }

    public AccessDenyException(Throwable e) {
        super(DEFAULT_MSG, e);
        this.errorCode = HttpStatus.UNAUTHORIZED;
        this.errorMessage = DEFAULT_MSG;
    }

    public AccessDenyException(JsonNode data) {
        this.errorCode = HttpStatus.UNAUTHORIZED;
        this.errorMessage = DEFAULT_MSG;
        this.data = data;
    }

    public AccessDenyException(HttpStatus statusCode) {
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
        AccessDenyException that = (AccessDenyException) o;
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
        return "AccessDenyException{" +
                "errorCode=" + errorCode.value() +
                ", errorMessage='" + errorMessage + '\'' +
                ", data=" + data +
                '}';
    }
}
