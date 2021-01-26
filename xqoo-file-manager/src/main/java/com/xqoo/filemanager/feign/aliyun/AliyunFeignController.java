package com.xqoo.filemanager.feign.aliyun;

import com.xqoo.common.core.utils.StringUtils;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.filemanager.service.aliyun.AliyunOssHandleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author gaoyang
 */
@RestController
@RequestMapping("/aliyunOssFeign")
public class AliyunFeignController {

    @Autowired
    private AliyunOssHandleService aliyunOssHandleService;

    @PostMapping("/uploadTmpFile")
    public String uploadTmpFile(@RequestParam(value = "file", required = false) MultipartFile file,
                                              @RequestParam(value = "path", required = false) String path,
                                              @RequestParam(value = "isProtected", required = false) Boolean isProtected){
        if(file.isEmpty()){
            return null;
        }

        if(StringUtils.isEmpty(path)){
            return null;
        }
        return aliyunOssHandleService.uploadFileByMultipartFile(file, path, isProtected);
    }
}
