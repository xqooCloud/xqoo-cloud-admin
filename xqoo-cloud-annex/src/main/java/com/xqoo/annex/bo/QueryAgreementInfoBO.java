package com.xqoo.annex.bo;

import com.xqoo.common.page.PageRequestBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

@ApiModel("查询协议信息实体")
public class QueryAgreementInfoBO extends PageRequestBean {


    private static final long serialVersionUID = 6077138806762990916L;
    @ApiModelProperty("协议KEY")
    private String agreementKey;

    @Override
    public String toString() {
        return "QueryAgreementInfoBO{" +
                "agreementKey='" + agreementKey + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QueryAgreementInfoBO)) return false;
        if (!super.equals(o)) return false;
        QueryAgreementInfoBO that = (QueryAgreementInfoBO) o;
        return Objects.equals(getAgreementKey(), that.getAgreementKey());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getAgreementKey());
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getAgreementKey() {
        return agreementKey;
    }

    public void setAgreementKey(String agreementKey) {
        this.agreementKey = agreementKey;
    }
}
