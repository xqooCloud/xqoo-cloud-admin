package com.xqoo.codegen.dto;

import java.io.Serializable;
import java.util.Objects;

public class TemplateHandleDTO implements Serializable {

    private static final long serialVersionUID = 5322633870806981239L;
    private String templatePath;

    private String templateFilePath;

    @Override
    public String toString() {
        return "TemplateHandleDTO{" +
                "templatePath='" + templatePath + '\'' +
                ", templateFilePath='" + templateFilePath + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TemplateHandleDTO that = (TemplateHandleDTO) o;
        return Objects.equals(templatePath, that.templatePath) &&
                Objects.equals(templateFilePath, that.templateFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(templatePath, templateFilePath);
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    public String getTemplateFilePath() {
        return templateFilePath;
    }

    public void setTemplateFilePath(String templateFilePath) {
        this.templateFilePath = templateFilePath;
    }
}
