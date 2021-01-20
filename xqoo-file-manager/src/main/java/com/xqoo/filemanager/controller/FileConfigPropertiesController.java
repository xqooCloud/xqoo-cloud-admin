package com.xqoo.filemanager.controller;

import cn.hutool.core.collection.CollUtil;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.core.utils.StringUtils;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageRequestBean;
import com.xqoo.common.page.PageResponseBean;
import com.xqoo.feign.annotations.LoginUser;
import com.xqoo.feign.annotations.OperationLog;
import com.xqoo.feign.enums.operlog.OperationTypeEnum;
import com.xqoo.filemanager.bean.FileConfigPropertiesBean;
import com.xqoo.filemanager.entity.FileConfigPropertiesEntity;
import com.xqoo.filemanager.service.FileConfigPropertiesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * FileConfigPropertiesController
 *
 * @author xqoo-code-gen
 * @date 2021-01-19 20:48:15
 */

@RestController
@RequestMapping("/fileConfigPropertiesHandle")
@Api(tags = "上传参数主体表控制器")
@Validated
public class FileConfigPropertiesController{
    private final static Logger logger = LoggerFactory.getLogger(FileConfigPropertiesController.class);

    /**
    * 注入当前登录人信息请在参数中添加 @ApiIgnore @LoginUser CurrentUser currentUser, @ApiIgnore是隐藏CurrentUser中的参数不在swagger中显示
    * 接口访问自动存储操作日志请使用@OperationLog(tips="插入,更新系统角色信息操作", operatorType = OperationTypeEnum.EDIT, isSaveRequestData = true)
    * tips为简要说明，operatorType为操作类型-枚举，isSaveRequestData默认为false，不存储请求参数，isSaveResponseData默认true，存储响应参数
    * 注解@RequestParam 类的参数无法存储，抓取不到
    * 注解@NotBlank 校验只对文本型参数有效，其余类型使用@NotNull，具体参见java参数校验注解使用
    * 注解@RequestBody 参数校验需要在实体bean前面加注解@Valid
    * 访问来源ip、端口、已登录人userId，userName在请求头中，请求头名字常量在com.xqoo.common.constants.SystemPublicConstant中
    *
    */

    @Autowired
    private FileConfigPropertiesService fileConfigPropertiesService;

    @Autowired
    @Qualifier("fileConfigPropertiesBean")
    private FileConfigPropertiesBean fileConfigPropertiesBean;

    @ApiOperation("根据父级id获取所有下属参数列表")
    @GetMapping("/getAllPropertiesByParentId")
    public ResultEntity<List<FileConfigPropertiesEntity>> getAllPropertiesByParentId(@RequestParam(value = "parentId", required = false)
                                                                                         @NotNull(message = "父级id不能为空") Integer parentId){
        return fileConfigPropertiesService.getAllPropertiesByParentId(parentId);
    }

    @ApiOperation("分页获取上传参数主体表表数据")
    @PostMapping("/pageGetList")
    public ResultEntity<PageResponseBean<FileConfigPropertiesEntity>> pageGetList(@RequestBody PageRequestBean page){
        return fileConfigPropertiesService.pageGetList(page);
    }

    @ApiOperation("发布最新配置接口")
    @GetMapping("/refreshFileConfig")
    @OperationLog(tips = "推送了文件模块的配置参数", operatorType = OperationTypeEnum.OTHER)
    public ResultEntity<String> updatePayConfig(@RequestParam(value = "refreshPlat", required = false) @NotBlank(message = "刷新命令不能为空") String refreshPlat){
        try {
            fileConfigPropertiesService.refreshFileConfig(refreshPlat);
            return new ResultEntity<>(HttpStatus.OK,"文件管理配置参数刷新完成");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE,"推送失败，未更新支付配置");
        }
    }

    @ApiOperation("批量新增数据")
    @PostMapping("/addRecordByList")
    @OperationLog(tips="批量新增file_config_properties表数据", operatorType = OperationTypeEnum.ADD, isSaveRequestData = true)
    public ResultEntity<String> addRecordByList(@ApiIgnore @LoginUser CurrentUser currentUser,
                                        @RequestBody List<FileConfigPropertiesEntity> list){
        if(StringUtils.isEmpty(currentUser.getUserId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "未找到当前登录人信息，请重新登录重试");
        }
        return fileConfigPropertiesService.insertList(list, currentUser);
    }

    @ApiOperation("根据主键查询单条记录")
    @GetMapping("/getRecordByPrimaryKey")
    public ResultEntity<FileConfigPropertiesEntity> getRecordByPrimaryKey(@RequestParam(required = false, value = "id")
                                                                      @NotNull(message = "主键值不能为空") Integer id){
        FileConfigPropertiesEntity entity = fileConfigPropertiesService.getOneFileConfigPropertiesEntityByPrimaryKey(id);
        return new ResultEntity<>(HttpStatus.OK, "查询成功", entity);
    }

    @ApiOperation("获取当前服务器缓存参数")
    @GetMapping("/getNowConfigProperties")
    public ResultEntity<Map> getNowConfigProperties(@RequestParam(required = false, value = "uploadType") String uploadType){
        if(StringUtils.isEmpty(uploadType)){
            return new ResultEntity<>(HttpStatus.OK, "查询成功", fileConfigPropertiesBean.getFileManagerConfigBean());
        }else{
            Map<String, String> rtn = fileConfigPropertiesBean.getFileManagerConfigBean().getOrDefault(uploadType, Collections.emptyMap());
            if(CollUtil.isEmpty(rtn)){
                return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "未找到相应的缓存参数");
            }
            return new ResultEntity<>(HttpStatus.OK, "查询成功", rtn);
        }

    }

    @ApiOperation("更新或新增参数配置")
    @PostMapping("/updatePropertiesInfo")
    @OperationLog(tips = "修改或新增了文件管理模块参数", operatorType = OperationTypeEnum.EDIT, isSaveRequestData = true)
    public ResultEntity<FileConfigPropertiesEntity> updatePropertiesInfo(@RequestBody @Valid FileConfigPropertiesEntity updateBO,
                                                                         @ApiIgnore @LoginUser CurrentUser currentUser){
        if(updateBO.getParentId() == null){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "更新失败，丢失父级参数");
        }
        if(org.apache.commons.lang.StringUtils.isEmpty(updateBO.getPropertiesLabel())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "更新失败，缺失参数标签");
        }
        if(org.apache.commons.lang.StringUtils.isEmpty(updateBO.getPropertiesValue())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "更新失败，缺失参数值");
        }
        if(updateBO.getId() == null || updateBO.getId().compareTo(0) < 1){
            try{
                return fileConfigPropertiesService.addConfigProperties(updateBO);
            }catch (Exception e){
                logger.error("[文件模块][Exception]新增文件管理参数时发生错误，错误信息：{}", e.getMessage());
                return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "[文件模块]新增参数失败");
            }
        }else{
            try{
                return fileConfigPropertiesService.updatePropertiesInfo(updateBO);
            }catch (Exception e){
                logger.error("[文件模块][Exception]修改文件管理参数时发生错误，错误信息：{}", e.getMessage());
                return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "[文件模块]修改参数失败");
            }
        }
    }
}
