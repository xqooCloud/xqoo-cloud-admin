package com.xqoo.codegen.bo;

import com.xqoo.common.page.PageRequestBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

@ApiModel("查询数据源信息实体")
public class QueryDataSourceBO extends PageRequestBean {


    private static final long serialVersionUID = 6077138806762990916L;
    @ApiModelProperty("数据源库类型")
    private String dataBaseType;

    @ApiModelProperty("数据源地址")
    private String dataBaseHost;

    @ApiModelProperty("数据源名字")
    private String dataBaseShowName;

    @Override
    public String toString() {
        return "QueryDataSourceBO{" +
                "dataBaseType='" + dataBaseType + '\'' +
                ", dataBaseHost='" + dataBaseHost + '\'' +
                ", dataBaseShowName='" + dataBaseShowName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        QueryDataSourceBO that = (QueryDataSourceBO) o;
        return Objects.equals(dataBaseType, that.dataBaseType) &&
                Objects.equals(dataBaseHost, that.dataBaseHost) &&
                Objects.equals(dataBaseShowName, that.dataBaseShowName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dataBaseType, dataBaseHost, dataBaseShowName);
    }

    public String getDataBaseType() {
        return dataBaseType;
    }

    public void setDataBaseType(String dataBaseType) {
        this.dataBaseType = dataBaseType;
    }

    public String getDataBaseHost() {
        return dataBaseHost;
    }

    public void setDataBaseHost(String dataBaseHost) {
        this.dataBaseHost = dataBaseHost;
    }

    public String getDataBaseShowName() {
        return dataBaseShowName;
    }

    public void setDataBaseShowName(String dataBaseShowName) {
        this.dataBaseShowName = dataBaseShowName;
    }
}
