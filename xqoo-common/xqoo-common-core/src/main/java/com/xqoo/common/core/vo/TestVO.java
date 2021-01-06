package com.xqoo.common.core.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@ApiModel("测试用实体")
public class TestVO {

    @NotBlank(message = "姓名不能为空")
    @ApiModelProperty("测试姓名")
    private String name;

    @NotBlank(message = "性别不能为空")
    @ApiModelProperty("测试性别")
    private String sex;

    @NotNull
    @Valid
    @ApiModelProperty("测试性别")
    private TestClass testClass;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public TestClass getTestClass() {
        return testClass;
    }

    public void setTestClass(TestClass testClass) {
        this.testClass = testClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestVO testVO = (TestVO) o;
        return Objects.equals(name, testVO.name) &&
                Objects.equals(sex, testVO.sex) &&
                Objects.equals(testClass, testVO.testClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sex, testClass);
    }

    @Override
    public String toString() {
        return "TestVO{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", testClass=" + testClass +
                '}';
    }
}
