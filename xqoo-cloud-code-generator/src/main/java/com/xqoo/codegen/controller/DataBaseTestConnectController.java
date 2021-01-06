package com.xqoo.codegen.controller;

import com.xqoo.codegen.dto.DataBasePropertiesDTO;
import com.xqoo.codegen.service.DataBaseTestConnectService;
import com.xqoo.common.entity.ResultEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/testConnect")
@Api(tags = "测试数据源连接")
@Validated
public class DataBaseTestConnectController {

    @Autowired
    private DataBaseTestConnectService dataBaseTestConnectService;

    @PostMapping("/testDataBaseConnect")
    @ApiOperation("测试数据库连接是否可用")
    public ResultEntity<String> testDataBaseConnect(@RequestBody @Valid DataBasePropertiesDTO dto,
                                            @RequestParam(required = false, value = "type") @NotBlank(message = "类型不能为空") String type){
        return dataBaseTestConnectService.testDataBaseConnect(dto, type);
    }
}
