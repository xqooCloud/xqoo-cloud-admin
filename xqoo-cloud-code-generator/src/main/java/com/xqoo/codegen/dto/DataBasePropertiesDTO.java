package com.xqoo.codegen.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

public class DataBasePropertiesDTO implements Serializable {

    private static final long serialVersionUID = -4597331425715651948L;
    private String url;

    @NotBlank(message = "数据库连接地址不能为空")
    @NotNull(message = "数据库连接地址不能为空")
    private String dataBaseHost;

    @NotBlank(message = "数据库端口不能为空")
    @NotNull(message = "数据库端口不能为空")
    private String dataBasePort;

    @NotBlank(message = "数据库用户名不能为空")
    @NotNull(message = "数据库用户名不能为空")
    private String dataBaseUserName;

    @NotBlank(message = "数据库密码不能为空")
    @NotNull(message = "数据库密码不能为空")
    private String dataBasePassword;

    @NotBlank(message = "数据库名不能为空")
    @NotNull(message = "数据库名不能为空")
    private String dataBaseName;

    private String properties;

    private String driverClass;

    private String tableName;

    @Override
    public String toString() {
        return "DataBasePropertiesDTO{" +
                "url='" + url + '\'' +
                ", dataBaseHost='" + dataBaseHost + '\'' +
                ", dataBasePort='" + dataBasePort + '\'' +
                ", dataBaseUserName='" + dataBaseUserName + '\'' +
                ", dataBasePassword='" + dataBasePassword + '\'' +
                ", dataBaseName='" + dataBaseName + '\'' +
                ", properties='" + properties + '\'' +
                ", driverClass='" + driverClass + '\'' +
                ", tableName='" + tableName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataBasePropertiesDTO that = (DataBasePropertiesDTO) o;
        return Objects.equals(url, that.url) &&
                Objects.equals(dataBaseHost, that.dataBaseHost) &&
                Objects.equals(dataBasePort, that.dataBasePort) &&
                Objects.equals(dataBaseUserName, that.dataBaseUserName) &&
                Objects.equals(dataBasePassword, that.dataBasePassword) &&
                Objects.equals(dataBaseName, that.dataBaseName) &&
                Objects.equals(properties, that.properties) &&
                Objects.equals(driverClass, that.driverClass) &&
                Objects.equals(tableName, that.tableName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, dataBaseHost, dataBasePort, dataBaseUserName, dataBasePassword, dataBaseName, properties, driverClass, tableName);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDataBaseHost() {
        return dataBaseHost;
    }

    public void setDataBaseHost(String dataBaseHost) {
        this.dataBaseHost = dataBaseHost;
    }

    public String getDataBasePort() {
        return dataBasePort;
    }

    public void setDataBasePort(String dataBasePort) {
        this.dataBasePort = dataBasePort;
    }

    public String getDataBaseUserName() {
        return dataBaseUserName;
    }

    public void setDataBaseUserName(String dataBaseUserName) {
        this.dataBaseUserName = dataBaseUserName;
    }

    public String getDataBasePassword() {
        return dataBasePassword;
    }

    public void setDataBasePassword(String dataBasePassword) {
        this.dataBasePassword = dataBasePassword;
    }

    public String getDataBaseName() {
        return dataBaseName;
    }

    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
