package com.xqoo.integral.controller;

import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.core.utils.StringUtils;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageRequestBean;
import com.xqoo.common.page.PageResponseBean;
import com.xqoo.feign.annotations.LoginUser;
import com.xqoo.feign.annotations.OperationLog;
import com.xqoo.feign.enums.operlog.OperationTypeEnum;
import com.xqoo.integral.entity.IntegralAccountEntity;
import com.xqoo.integral.service.IntegralAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * IntegralAccountController
 *
 * @author xqoo-code-gen
 * @date 2021-01-26 11:17:12
 */

@RestController
@RequestMapping("/integralAccountHandle")
@Api(tags = "积分账户表控制器")
@Validated
public class IntegralAccountController{

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
    private IntegralAccountService integralAccountService;

                                    
    @ApiOperation("分页获取积分账户表表数据")
    @PostMapping("/pageGetList")
    public ResultEntity<PageResponseBean<IntegralAccountEntity>> pageGetList(@RequestBody PageRequestBean page){
        return integralAccountService.pageGetList(page);
    }

    @ApiOperation("批量新增数据")
    @PostMapping("/addRecordByList")
    @OperationLog(tips="批量新增integral_account表数据", operatorType = OperationTypeEnum.ADD, isSaveRequestData = true)
    public ResultEntity<String> addRecordByList(@ApiIgnore @LoginUser CurrentUser currentUser,
                                        @RequestBody List<IntegralAccountEntity> list){
        if(StringUtils.isEmpty(currentUser.getUserId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "未找到当前登录人信息，请重新登录重试");
        }
        return integralAccountService.insertList(list, currentUser);
    }

    @ApiOperation("根据主键查询单条记录")
    @GetMapping("/getRecordByPrimaryKey")
    public ResultEntity<IntegralAccountEntity> getRecordByPrimaryKey(@RequestParam(required = false, value = "accountId")
                                                                      @NotNull(message = "主键值不能为空") String accountId){
        IntegralAccountEntity entity = integralAccountService.getOneIntegralAccountEntityByPrimaryKey(accountId);
        return new ResultEntity<>(HttpStatus.OK, "查询成功", entity);
    }

    @ApiOperation("获取个人积分账户信息")
    @GetMapping("/getPersonAccountInfo")
    public ResultEntity<IntegralAccountEntity> getPersonAccountInfo(@ApiIgnore @LoginUser CurrentUser currentUser){
        if(StringUtils.isEmpty(currentUser.getUserId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "未找到当前登录人信息，请登录后重试");
        }
        return integralAccountService.getPersonAccountInfo(currentUser.getUserId());
    }

    @ApiOperation("生成分享二维码信息")
    @GetMapping("/generatorQrcode")
    public ResultEntity<String> generatorQrcode(@ApiIgnore @LoginUser CurrentUser currentUser){
        if(StringUtils.isEmpty(currentUser.getUserId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "未找到当前登录人信息，请登录后重试");
        }
        return integralAccountService.generatorQrcode(currentUser.getUserId());
    }

    @ApiOperation("扫码增加积分")
    @GetMapping("/scanQrcode")
    public ResultEntity<String> scanQrcode(@RequestParam(required = false, value = "keyWords") @NotBlank(message = "关键词不能为空") String keyWords,
                                           @ApiIgnore @LoginUser CurrentUser currentUser){
        if(StringUtils.isEmpty(currentUser.getUserId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "未找到当前登录人信息，请登录后重试");
        }
        return integralAccountService.scanQrcode(keyWords, currentUser.getUserId());
    }
}
