package com.xqoo.codegen.bo;

import com.xqoo.codegen.entity.JavaTableColumnsEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@ApiModel("生成实体类参数")
public class EntityGenCodeBo implements Serializable {

    private static final long serialVersionUID = 4645061897083921092L;

    @ApiModelProperty("类名")
    @NotBlank(message = "类名不能为空")
    private String className;

    @ApiModelProperty("类注释")
    @NotBlank(message = "类注释不能为空")
    private String classComment;

    @ApiModelProperty("参数列表")
    @Size(min = 1, message = "参数列表最少一条")
    private List<JavaTableColumnsEntity> properties;

    @ApiModelProperty("所选模板")
    @Size(min = 1, message = "生成模板不能为空")
    private List<String> templateNameArr;

    @Override
    public String toString() {
        return "EntityGenCodeBo{" +
                "className='" + className + '\'' +
                ", classComment='" + classComment + '\'' +
                ", properties=" + properties +
                ", templateNameArr=" + templateNameArr +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityGenCodeBo that = (EntityGenCodeBo) o;
        return Objects.equals(className, that.className) &&
                Objects.equals(classComment, that.classComment) &&
                Objects.equals(properties, that.properties) &&
                Objects.equals(templateNameArr, that.templateNameArr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(className, classComment, properties, templateNameArr);
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassComment() {
        return classComment;
    }

    public void setClassComment(String classComment) {
        this.classComment = classComment;
    }

    public List<JavaTableColumnsEntity> getProperties() {
        return properties;
    }

    public void setProperties(List<JavaTableColumnsEntity> properties) {
        this.properties = properties;
    }

    public List<String> getTemplateNameArr() {
        return templateNameArr;
    }

    public void setTemplateNameArr(List<String> templateNameArr) {
        this.templateNameArr = templateNameArr;
    }
}
