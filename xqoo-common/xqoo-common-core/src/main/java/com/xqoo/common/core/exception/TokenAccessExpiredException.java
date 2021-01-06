package com.xqoo.common.core.exception;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpStatus;

import java.util.Objects;

public class TokenAccessExpiredException extends RuntimeException {

    private static final long serialVersionUID = 5565760508056698922L;

    private static final String DEFAULT_MSG = "当前登录令牌已失效";

    private HttpStatus errorCode;

    private String errorMessage;

    private JsonNode data;

    public TokenAccessExpiredException(String message, HttpStatus errorCode, JsonNode data) {
        this.errorMessage = message;
        this.errorCode = errorCode;
        this.data = data;
    }

    public TokenAccessExpiredException(String message, JsonNode data) {
        this.data = data;
        this.errorCode = HttpStatus.FORBIDDEN;
        this.errorMessage = message;
    }

    public TokenAccessExpiredException(String message, Throwable e, JsonNode data) {
        super(message, e);
        this.errorCode = HttpStatus.FORBIDDEN;
        this.data = data;
        this.errorMessage = message;
    }

    public TokenAccessExpiredException(Throwable e, JsonNode data) {
        super(DEFAULT_MSG, e);
        this.errorCode = HttpStatus.FORBIDDEN;
        this.data = data;
        this.errorMessage = DEFAULT_MSG;
    }

    public TokenAccessExpiredException(HttpStatus errorCode, JsonNode data) {
        this.errorCode = errorCode;
        this.data = data;
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
        TokenAccessExpiredException that = (TokenAccessExpiredException) o;
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
        return "TokenAccessExpiredException{" +
                "errorCode=" + errorCode.value() +
                ", errorMessage='" + errorMessage + '\'' +
                ", data=" + data +
                '}';
    }
}
