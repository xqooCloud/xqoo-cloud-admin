package com.xqoo.filemanager.feign;

import cn.hutool.core.collection.CollUtil;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.filemanager.service.FileRecordService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author gaoyang
 */
@RestController
@RequestMapping("/fileManagerFeign")
public class FileManagerFeign {

    @Autowired
    private FileRecordService fileRecordService;

    @ApiOperation("删除文件")
    @PostMapping("/removeFileByFileIds")
    public ResultEntity<String> removeFileByFileIds(@RequestBody List<String> fileIds){
        if(CollUtil.isEmpty(fileIds)){
            return new ResultEntity<>(HttpStatus.OK, "ok");
        }
        return fileRecordService.removeFileByFileIds(fileIds);
    }
}
