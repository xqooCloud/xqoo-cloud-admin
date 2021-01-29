package com.xqoo.feign.service.filemanager;

import com.xqoo.feign.config.FeignSupportConfig;
import com.xqoo.feign.factory.FileManagerFeignFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author gaoyang
 */
@Component
@FeignClient(name = "xqoo-file-manager", fallbackFactory = FileManagerFeignFactory.class, configuration = FeignSupportConfig.class)
public interface FileManagerFeign {

    /**
     * 上传文件
     * @param file
     * @param path
     * @param isProtected
     * @return
     */
    @PostMapping(value = "/aliyunOssFeign/uploadTmpFile", produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadTmpFile(@RequestPart(value = "file", required = false) MultipartFile file,
                  @RequestParam(value = "path", required = false) String path,
                  @RequestParam(value = "isProtected", required = false) Boolean isProtected);

    @PostMapping(value = "/fileManagerFeign/removeFileByFileIds")
    byte[] removeFileByFileIds(@RequestBody List<String> fileIds);

}
