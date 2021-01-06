package com.xqoo.codegen.pojo;

import java.io.Serializable;
import java.util.Objects;

public class DataBaseTypePropertiesPOJO implements Serializable {

    private static final long serialVersionUID = 4344795587393541769L;
    private String type;

    private String driver;

    private String prefix;

    private String properties;

    private String port;

    @Override
    public String toString() {
        return "DataBaseTypePropertiesPOJO{" +
                "type='" + type + '\'' +
                ", driver='" + driver + '\'' +
                ", prefix='" + prefix + '\'' +
                ", properties='" + properties + '\'' +
                ", port='" + port + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataBaseTypePropertiesPOJO that = (DataBaseTypePropertiesPOJO) o;
        return Objects.equals(type, that.type) &&
                Objects.equals(driver, that.driver) &&
                Objects.equals(prefix, that.prefix) &&
                Objects.equals(properties, that.properties) &&
                Objects.equals(port, that.port);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, driver, prefix, properties, port);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
