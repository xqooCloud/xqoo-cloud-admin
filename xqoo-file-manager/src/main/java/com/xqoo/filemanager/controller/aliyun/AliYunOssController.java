package com.xqoo.filemanager.controller.aliyun;

import com.fasterxml.jackson.databind.JsonNode;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.filemanager.service.aliyun.AliyunPreUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gaoyang
 */
@Api(tags = "阿里云OSS控制器")
@RestController
@RequestMapping("/aliOssHandle")
public class AliYunOssController {

    @Autowired
    private AliyunPreUploadService aliyunPreUploadService;

    @ApiOperation("获取前端上传签名")
    @GetMapping("/getUploadSign")
    public ResultEntity<JsonNode> getUploadSign(@RequestParam(required = false, value = "path") String path){
        return aliyunPreUploadService.getUploadSign(path);
    }

    @ApiOperation("列举oss文件")
    @GetMapping("/showOssFileList")
    public ResultEntity<JsonNode> showOssFileList(){
        return aliyunPreUploadService.showOssFileList();
    }
}
