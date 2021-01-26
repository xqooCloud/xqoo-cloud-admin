package com.xqoo.feign.factory;

import com.xqoo.feign.service.filemanager.FileManagerFeign;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author gaoyang
 */
@Component
public class FileManagerFeignFactory implements FallbackFactory<FileManagerFeign> {

    private final static Logger logger = LoggerFactory.getLogger(FileManagerFeignFactory.class);

    @Override
    public FileManagerFeign create(Throwable throwable) {
        logger.error("[文件模块]服务不可用，请检查服务是否正常运行" + throwable.getMessage());
        return new FileManagerFeign() {
            @Override
            public String uploadTmpFile(MultipartFile file, String path, Boolean isProtected) {
                return null;
            }
        };
    }
}
