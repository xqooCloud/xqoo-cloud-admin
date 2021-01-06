package com.xqoo.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by LiTinggui on 2019/6/15.
 *
 * @author LiTinggui
 * @date 2019/6/15
 */
@ApiModel("通用返回")
public class ResultEntity<T> implements Serializable {


    private static final long serialVersionUID = -5622760569532264159L;
    @ApiModelProperty(value="接口返回code(只有200为正常返回)")
    private Integer code;
    @ApiModelProperty(value="接口返回message(只有非200时返回)")
    private String message;
    @ApiModelProperty(value="接口返回数据实体")
    private T data;

    public ResultEntity(HttpStatus status, String message, T data) {
        this.code = status.value();
        this.message = message;
        this.data = data;
    }

    public ResultEntity(HttpStatus status,String message) {
        this.code = status.value();
        this.message = message;
    }
    public ResultEntity(T data) {
        this.code = HttpStatus.OK.value();
        this.message = "操作成功";
        this.data = data;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultEntity<?> that = (ResultEntity<?>) o;
        return Objects.equals(code, that.code) &&
                Objects.equals(message, that.message) &&
                Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message, data);
    }
}

