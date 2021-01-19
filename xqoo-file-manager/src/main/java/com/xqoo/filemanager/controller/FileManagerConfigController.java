package com.xqoo.filemanager.controller;

import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.core.utils.StringUtils;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageRequestBean;
import com.xqoo.common.page.PageResponseBean;
import com.xqoo.feign.annotations.LoginUser;
import com.xqoo.feign.annotations.OperationLog;
import com.xqoo.feign.enums.operlog.OperationTypeEnum;
import com.xqoo.filemanager.bean.FileConfigPropertiesBean;
import com.xqoo.filemanager.bean.FileManagerConfigBean;
import com.xqoo.filemanager.entity.FileManagerConfigEntity;
import com.xqoo.filemanager.service.FileManagerConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * FileManagerConfigController
 *
 * @author xqoo-code-gen
 * @date 2021-01-19 20:16:13
 */

@RestController
@RequestMapping("/fileManagerConfigHandle")
@Api(tags = "文件管理配置总表控制器")
@Validated
public class FileManagerConfigController{

    @Autowired
    @Qualifier("fileManagerConfigBean")
    private FileManagerConfigBean fileManagerConfigBean;

    @Autowired
    @Qualifier("fileConfigPropertiesBean")
    private FileConfigPropertiesBean fileConfigPropertiesBean;

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
    private FileManagerConfigService fileManagerConfigService;

                                                                            
    @ApiOperation("分页获取文件管理配置总表表数据")
    @PostMapping("/pageGetList")
    public ResultEntity<PageResponseBean<FileManagerConfigEntity>> pageGetList(@RequestBody PageRequestBean page){
        return fileManagerConfigService.pageGetList(page);
    }

    @ApiOperation("批量新增数据")
    @PostMapping("/addRecordByList")
    @OperationLog(tips="批量新增file_manager_config表数据", operatorType = OperationTypeEnum.ADD, isSaveRequestData = true)
    public ResultEntity<String> addRecordByList(@ApiIgnore @LoginUser CurrentUser currentUser,
                                        @RequestBody List<FileManagerConfigEntity> list){
        if(StringUtils.isEmpty(currentUser.getUserId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "未找到当前登录人信息，请重新登录重试");
        }
        return fileManagerConfigService.insertList(list, currentUser);
    }

    @ApiOperation("根据主键查询单条记录")
    @GetMapping("/getRecordByPrimaryKey")
    public ResultEntity<FileManagerConfigEntity> getRecordByPrimaryKey(@RequestParam(required = false, value = "id")
                                                                      @NotNull(message = "主键值不能为空") Integer id){
        FileManagerConfigEntity entity = fileManagerConfigService.getOneFileManagerConfigEntityByPrimaryKey(id);
        return new ResultEntity<>(HttpStatus.OK, "查询成功", entity);
    }

    @ApiOperation("测试参数")
    @GetMapping("/testProperties")
    public Map testProperties(){
        return fileConfigPropertiesBean.getFileManagerConfigBean();
    }

    @ApiOperation("测试参数主体")
    @GetMapping("/testPropertiesMain")
    public List<FileManagerConfigEntity> testPropertiesMain(){
        return fileManagerConfigBean.getBeanList();
    }
}
