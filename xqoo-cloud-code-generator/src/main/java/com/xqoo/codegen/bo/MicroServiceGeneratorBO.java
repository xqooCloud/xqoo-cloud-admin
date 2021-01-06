package com.xqoo.codegen.bo;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class MicroServiceGeneratorBO  implements Serializable {

    private static final long serialVersionUID = -2650966307390271748L;
    @NotBlank(message = "微服务名称不能为空")
    private String moduleName;

    @NotBlank(message = "微服务端口不能为空")
    private String modulePort;

    private String nacosNameSpace;

    @ApiModelProperty("是否需要生成可下载压缩包，默认生成")
    private Boolean needDownload;

    @ApiModelProperty("所选模板")
    private List<String> templateNameArr;

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModulePort() {
        return modulePort;
    }

    public void setModulePort(String modulePort) {
        this.modulePort = modulePort;
    }

    public String getNacosNameSpace() {
        return nacosNameSpace;
    }

    public void setNacosNameSpace(String nacosNameSpace) {
        this.nacosNameSpace = nacosNameSpace;
    }

    public Boolean getNeedDownload() {
        return needDownload;
    }

    public void setNeedDownload(Boolean needDownload) {
        this.needDownload = needDownload;
    }

    public List<String> getTemplateNameArr() {
        return templateNameArr;
    }

    public void setTemplateNameArr(List<String> templateNameArr) {
        this.templateNameArr = templateNameArr;
    }

    @Override
    public String toString() {
        return "MicroServiceGeneratorBO{" +
                "moduleName='" + moduleName + '\'' +
                ", modulePort='" + modulePort + '\'' +
                ", nacosNameSpace='" + nacosNameSpace + '\'' +
                ", needDownload='" + needDownload + '\'' +
                ", templateNameArr='" + templateNameArr + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MicroServiceGeneratorBO that = (MicroServiceGeneratorBO) o;
        return Objects.equals(moduleName, that.moduleName) &&
                Objects.equals(modulePort, that.modulePort) &&
                Objects.equals(nacosNameSpace, that.nacosNameSpace) &&
                Objects.equals(needDownload, that.needDownload) &&
                Objects.equals(templateNameArr, that.templateNameArr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(moduleName, modulePort, nacosNameSpace, needDownload, templateNameArr);
    }
}
