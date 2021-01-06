package com.xqoo.common.core.vo;

import com.xqoo.common.core.TestBO;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

public class TestClass {

    @NotBlank(message = "卧槽不能为空")
    @ApiModelProperty("测试性别")
    private String wocao;

    @NotNull(message = "测试列表不能为空")
    @Valid
    @ApiModelProperty("测试性别")
    private List<TestBO> seee;

    public String getWocao() {
        return wocao;
    }

    public void setWocao(String wocao) {
        this.wocao = wocao;
    }

    public List<TestBO> getSeee() {
        return seee;
    }

    public void setSeee(List<TestBO> seee) {
        this.seee = seee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestClass testClass = (TestClass) o;
        return Objects.equals(wocao, testClass.wocao) &&
                Objects.equals(seee, testClass.seee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wocao, seee);
    }

    @Override
    public String toString() {
        return "TestClass{" +
                "wocao='" + wocao + '\'' +
                ", seee=" + seee +
                '}';
    }
}
