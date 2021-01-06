package com.xqoo.codegen.bean;

import com.xqoo.codegen.pojo.DataBaseTypePropertiesPOJO;
import com.xqoo.codegen.pojo.TemplateDataPOJO;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@ConfigurationProperties(prefix = "code-gen")
public class CodeGenCenterProperties {

    private String downloadBasePath;

    private String defaultAuthor;

    private List<DataBaseTypePropertiesPOJO> dataBaseType;

    private List<TemplateDataPOJO> template;

    @Override
    public String toString() {
        return "CodeGenCenterProperties{" +
                "downloadBasePath='" + downloadBasePath + '\'' +
                ", defaultAuthor='" + defaultAuthor + '\'' +
                ", dataBaseType=" + dataBaseType +
                ", template=" + template +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodeGenCenterProperties that = (CodeGenCenterProperties) o;
        return Objects.equals(downloadBasePath, that.downloadBasePath) &&
                Objects.equals(defaultAuthor, that.defaultAuthor) &&
                Objects.equals(dataBaseType, that.dataBaseType) &&
                Objects.equals(template, that.template);
    }

    @Override
    public int hashCode() {
        return Objects.hash(downloadBasePath, defaultAuthor, dataBaseType, template);
    }

    public String getDownloadBasePath() {
        return downloadBasePath;
    }

    public void setDownloadBasePath(String downloadBasePath) {
        this.downloadBasePath = downloadBasePath;
    }

    public String getDefaultAuthor() {
        return defaultAuthor;
    }

    public void setDefaultAuthor(String defaultAuthor) {
        this.defaultAuthor = defaultAuthor;
    }

    public List<DataBaseTypePropertiesPOJO> getDataBaseType() {
        return dataBaseType;
    }

    public void setDataBaseType(List<DataBaseTypePropertiesPOJO> dataBaseType) {
        this.dataBaseType = dataBaseType;
    }

    public List<TemplateDataPOJO> getTemplate() {
        return template;
    }

    public void setTemplate(List<TemplateDataPOJO> template) {
        this.template = template;
    }
}
