package com.xqoo.common.core.config.propetes.xqoo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@ConfigurationProperties(prefix = "xqoo.secret")
public class SecretConfigProperties {

    // AES加密默认密钥
    private String aesKey;

    public String getAesKey() {
        return aesKey;
    }

    public void setAesKey(String aesKey) {
        this.aesKey = aesKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SecretConfigProperties that = (SecretConfigProperties) o;
        return Objects.equals(aesKey, that.aesKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aesKey);
    }

    @Override
    public String toString() {
        return "SercretConfiProperties{" +
                "aesKey='" + aesKey + '\'' +
                '}';
    }
}
