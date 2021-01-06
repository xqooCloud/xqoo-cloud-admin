package com.xqoo.gateway.bean;

import com.xqoo.common.page.PageRequestBean;

import java.io.Serializable;
import java.util.Objects;

public class GatewayInterceptQueryBO extends PageRequestBean implements Serializable {
    private static final long serialVersionUID = 7653768271834972027L;

    // 拦截类型
    private String interceptType;

    // 拦截的ip，只支持右模糊
    private String requestIp;

    @Override
    public String toString() {
        return "GatewayInterceptQueryBO{" +
                "interceptType='" + interceptType + '\'' +
                ", requestIp='" + requestIp + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        GatewayInterceptQueryBO that = (GatewayInterceptQueryBO) o;
        return Objects.equals(interceptType, that.interceptType) &&
                Objects.equals(requestIp, that.requestIp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), interceptType, requestIp);
    }

    public String getInterceptType() {
        return interceptType;
    }

    public void setInterceptType(String interceptType) {
        this.interceptType = interceptType;
    }

    public String getRequestIp() {
        return requestIp;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }
}
