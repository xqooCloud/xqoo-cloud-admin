package com.xqoo.feign.factory.uidgenerator;

import com.xqoo.feign.service.uidgenerator.UidGeneratorFeign;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UidgeneratorFeignFactory implements FallbackFactory<UidGeneratorFeign> {

    private static Logger log = LoggerFactory.getLogger(UidgeneratorFeignFactory.class);

    @Override
    public UidGeneratorFeign create(Throwable throwable){
        log.error("[UidGenerator]流水号生成服务不可用，请检查服务是否正常运行" + throwable.getMessage());
        return new UidGeneratorFeign() {
            @Override
            public String getUid(String type) {
                return null;
            }
        };
    }
}
