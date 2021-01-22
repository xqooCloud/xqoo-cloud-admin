package com.xqoo.filemanager.controller.aliyun;

import com.fasterxml.jackson.databind.JsonNode;
import com.xqoo.common.core.utils.JacksonUtils;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.filemanager.enums.UploadBucketTypeEnum;
import com.xqoo.filemanager.service.aliyun.AliyunPreUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.util.Map;

/**
 * @author gaoyang
 */
@Api(tags = "阿里云OSS控制器")
@RestController
@RequestMapping("/aliOssHandle")
@Validated
public class AliYunOssController {

    @Autowired
    private AliyunPreUploadService aliyunPreUploadService;

    @ApiOperation("获取前端上传签名")
    @GetMapping("/getUploadSign")
    public ResultEntity<JsonNode> getUploadSign(@RequestParam(required = false, value = "accessType")
                                                    @NotBlank(message = "上传文件的权限类型不能为空") String accessType,
                                                @RequestParam(required = false, value = "path") String path){
        UploadBucketTypeEnum bucketTypeEnum = UploadBucketTypeEnum.getEnumsByKey(accessType);
        if(bucketTypeEnum == null){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "上传的文件访问权限错误，只能填写public或protected");
        }
        return aliyunPreUploadService.getUploadSign(path, bucketTypeEnum);
    }

    @ApiOperation("列举oss文件")
    @GetMapping("/showOssFileList")
    public ResultEntity<JsonNode> showOssFileList(){
        return aliyunPreUploadService.showOssFileList();
    }

    @ApiOperation("获得私有桶访问文件签名")
    @GetMapping("/getProtectedFileAccessSign")
    public ResultEntity<String> getFileAccessSign(@RequestParam(value = "pathFile", required = false) @NotBlank(message = "生成签名的对象路径不能为空")
                                                            String pathFile,
                                                            @RequestParam(value = "process", required = false) String process){
        return aliyunPreUploadService.getFileAccessSign(pathFile, process);
    }

    @ApiOperation("获得阿里云的上传回调")
    @PostMapping("/uploadCallback")
    public String uploadCallback(HttpServletRequest request, HttpServletResponse response) {
        try {
            Map<String, String> json = aliyunPreUploadService.handleUploadCallback(request, response);
            return JacksonUtils.toJson(json);
        }catch (IOException e){
            e.printStackTrace();
            return "{\"Status\":\"OK\"}";
        }
    }
}
