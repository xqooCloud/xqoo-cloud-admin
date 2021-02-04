package com.xqoo.feign.factory.salecenter;

import com.xqoo.common.core.utils.JacksonUtils;
import com.xqoo.feign.service.salecenter.SaleCenterFeign;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * @author gaoyang
 */
@Component
public class SaleCenterFeignFactory implements FallbackFactory<SaleCenterFeign> {

    private final static Logger logger = LoggerFactory.getLogger(SaleCenterFeignFactory.class);

    @Override
    public SaleCenterFeign create(Throwable throwable) {
        logger.error("[销售中心]服务不可用，请检查服务是否正常运行" + throwable.getMessage());
        return new SaleCenterFeign() {
            @Override
            public byte[] getHasSaleInfoScreen(List<String> screenIds) {
                return JacksonUtils.toJsonBytes(Collections.emptyList());
            }

            @Override
            public Boolean getExistSaleInfoByScreenId(String screenId) {
                return null;
            }
        };
    }
}
