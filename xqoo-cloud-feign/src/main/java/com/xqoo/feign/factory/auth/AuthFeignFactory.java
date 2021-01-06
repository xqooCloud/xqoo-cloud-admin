package com.xqoo.feign.factory.auth;

import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.core.utils.JacksonUtils;
import com.xqoo.common.dto.AuthenDTO;
import com.xqoo.feign.service.auth.AuthFeign;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class AuthFeignFactory implements FallbackFactory<AuthFeign> {

    private static Logger log = LoggerFactory.getLogger(AuthFeignFactory.class);

    @Override
    public AuthFeign create(Throwable e) {
        log.warn("[授权/鉴权中心Feign]调用授权中心Feig出错");
        e.printStackTrace();
        return new AuthFeign() {
            @Override
            public byte[] isLogin(String token) {
                AuthenDTO authenDTO = new AuthenDTO();
                authenDTO.setSuccess(false);
                authenDTO.setStatus(HttpStatus.NOT_ACCEPTABLE);
                authenDTO.setMessage("鉴权中心服务发生错误，无法连接");
                log.error("[鉴权中心Feign]调用token鉴权发生错误,异常类{},错误信息{}",
                        e.getClass().getSimpleName(), e.getMessage());
                return JacksonUtils.toJsonBytes(authenDTO);
            }

            @Override
            public byte[] getLoginUserInfo(String token) {
                log.error("[鉴权中心Feign]获取用户基本信息失败,异常类{},错误信息{}",
                        e.getClass().getSimpleName(), e.getMessage());
                return JacksonUtils.toJsonBytes(new CurrentUser());
            }
        };
    }
}
