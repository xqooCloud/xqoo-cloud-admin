package com.xqoo.common.core;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class TestBO {

    @NotBlank(message = "此项不呢为空")
    private String cixiang;

    public String getCixiang() {
        return cixiang;
    }

    public void setCixiang(String cixiang) {
        this.cixiang = cixiang;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestBO testBO = (TestBO) o;
        return Objects.equals(cixiang, testBO.cixiang);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cixiang);
    }

    @Override
    public String toString() {
        return "TestBO{" +
                "cixiang='" + cixiang + '\'' +
                '}';
    }
}
