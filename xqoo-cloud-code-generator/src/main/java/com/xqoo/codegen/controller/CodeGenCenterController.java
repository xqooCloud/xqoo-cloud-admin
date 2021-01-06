package com.xqoo.codegen.controller;

import cn.hutool.core.collection.CollUtil;
import com.xqoo.codegen.bean.CodeGenCenterProperties;
import com.xqoo.codegen.bo.EntityGenCodeBo;
import com.xqoo.codegen.bo.MicroServiceGeneratorBO;
import com.xqoo.codegen.bo.QueryDataSourceBO;
import com.xqoo.codegen.bo.TableGeneratorBO;
import com.xqoo.codegen.entity.TableColumnsEntity;
import com.xqoo.codegen.entity.TableEntity;
import com.xqoo.codegen.pojo.DataBaseTypePropertiesPOJO;
import com.xqoo.codegen.pojo.TemplateDataPOJO;
import com.xqoo.codegen.service.CodeGenCenterService;
import com.xqoo.codegen.service.DataSourceInfoService;
import com.xqoo.codegen.vo.DataSourceInfoVO;
import com.xqoo.codegen.vo.GeneratorCodeVO;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageResponseBean;
import com.xqoo.feign.annotations.OperationLog;
import com.xqoo.feign.enums.operlog.OperationTypeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.*;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/handleCenter")
@Api(tags = "代码生成中心处理控制器")
@Validated
public class CodeGenCenterController {

    @Autowired
    private DataSourceInfoService dataSourceInfoService;

    @Autowired
    private CodeGenCenterService codeGenCenterService;

    @Autowired
    private CodeGenCenterProperties codeGenCenterProperties;

    @ApiOperation("获取当前支持的数据库类型")
    @GetMapping("/getDataBaseType")
    public ResultEntity<List<DataBaseTypePropertiesPOJO>> getDataBaseType(){
        if(CollUtil.isEmpty(codeGenCenterProperties.getDataBaseType())){
            return new ResultEntity<>(HttpStatus.OK,"ok", Collections.emptyList());
        }
        return new ResultEntity<>(HttpStatus.OK,"ok", codeGenCenterProperties.getDataBaseType());
    }

    @ApiOperation("分页获取数据源信息")
    @PostMapping("/pageGetDataSourceInfo")
    public ResultEntity<PageResponseBean<DataSourceInfoVO>> pageGetDataSourceInfo(@RequestBody QueryDataSourceBO bo){
        return dataSourceInfoService.pageGetDataSourceInfo(bo);
    }

    @ApiOperation("删除数据源信息")
    @GetMapping("/deleteDataSource")
    @OperationLog(tips = "删除代码生成中心数据源信息", operatorType = OperationTypeEnum.REMOVE, isSaveRequestData = true)
    public ResultEntity<String> deleteDataSource(@RequestParam(required = false, value = "id") Integer id){
        return dataSourceInfoService.deleteDataSource(id);
    }

    @ApiOperation("新增/编辑数据源信息")
    @PostMapping("/updateDataSource")
    @OperationLog(tips = "新增/删除数据源信息", operatorType = OperationTypeEnum.EDIT, isSaveRequestData = true)
    public ResultEntity<String> updateDataSource(@RequestBody @Valid DataSourceInfoVO vo){
        if(vo.getId() == null || vo.getId() == 0){
            return dataSourceInfoService.addDataSource(vo);
        }
        return dataSourceInfoService.updateDataSource(vo);
    }

    @ApiOperation("根据数据源id获取对应当前库下所有表名")
    @GetMapping("/getDataSourceTables")
    public ResultEntity<List<TableEntity>> getDataSourceTables(@RequestParam(required = false, value = "dataSourceId")
                                                                   @NotNull(message = "数据源id不能为空") Integer dataSourceId){
        return codeGenCenterService.getDataSourceTables(dataSourceId);
    }

    @ApiOperation("清空数据表缓存")
    @GetMapping("/removeCacheTables")
    public ResultEntity<String> removeCacheTables(@RequestParam(required = false, value = "dataSourceId")
                                                      @NotNull(message = "数据源id不能为空") Integer dataSourceId){
        return codeGenCenterService.removeCacheTables(dataSourceId);
    }

    @ApiOperation("获得模板内容")
    @GetMapping("/getTemplateInfo")
    public ResultEntity<List<TemplateDataPOJO>> getTemplateInfo(@RequestParam(required = false, value = "type")
                                                                    @NotBlank(message = "模板类型不能为空") String type){
        return codeGenCenterService.getTemplateInfo(type);
    }

    @ApiOperation("根据数据源id和表名获取字段属性")
    @GetMapping("/getTableColumns")
    public ResultEntity<List<TableColumnsEntity>> getTableColumns(@RequestParam(required = false, value = "tableName")
                                                                      @NotNull(message = "数据表名不能为空") String tableName,
                                                                  @RequestParam(required = false, value = "dataSourceId")
                                                                  @NotNull(message = "数据源id不能为空") Integer dataSourceId){
        List<TableColumnsEntity> list = codeGenCenterService.getTableColumns(tableName, dataSourceId);
        if(CollUtil.isEmpty(list)){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "获取字段失败，未找到["
                    + tableName+ "]表相关字段信息", Collections.emptyList());
        }
        return new ResultEntity<>(HttpStatus.OK, "ok", list);
    }

    @ApiOperation("清空数据表字段缓存")
    @GetMapping("/removeCacheColumns")
    public ResultEntity<String> removeCacheColumns(@RequestParam(required = false, value = "tableName")
                                                      @NotNull(message = "数据表名不能为空") String tableName,
                                                  @RequestParam(required = false, value = "dataSourceId")
                                                      @NotNull(message = "数据源id不能为空") Integer dataSourceId){
        return codeGenCenterService.removeCacheColumns(tableName, dataSourceId);
    }

    @ApiOperation("根据表字段生成service等实体")
    @PostMapping("/tableGeneratorCode")
    public ResultEntity<GeneratorCodeVO> tableGeneratorCode(@RequestBody @Valid TableGeneratorBO bo){
        if(CollUtil.isEmpty(bo.getColumnDataArr())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "字段列表为空");
        }
        if(CollUtil.isEmpty(bo.getTemplateNameArr())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "生成模板不能为空");
        }
        return codeGenCenterService.tableGeneratorCode(bo);
    }

    @ApiOperation("生成微服务模块模板代码")
    @PostMapping("/microServiceGeneratorCode")
    public ResultEntity<GeneratorCodeVO> microServiceGeneratorCode(@RequestBody @Valid MicroServiceGeneratorBO bo){
        return codeGenCenterService.microServiceGeneratorCode(bo);
    }

    @ApiOperation("生成一个实体类文件")
    @PostMapping("/entityGeneratorCode")
    public ResultEntity<GeneratorCodeVO> entityGeneratorCode(@RequestBody @Valid EntityGenCodeBo bo){
        return codeGenCenterService.entityGeneratorCode(bo);
    }

    @ApiOperation("下载代码文件")
    @GetMapping("/downloadCodeZip")
    public void downloadCodeZip(HttpServletResponse response, @RequestParam(value = "downloadKey", required = false)
                                @NotBlank(message = "下载key不能为空") String downloadKey) {
        try {
            File file = new File(downloadKey);
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(downloadKey));
            byte[] buffer = new byte[fis.available()];
            int index = fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
