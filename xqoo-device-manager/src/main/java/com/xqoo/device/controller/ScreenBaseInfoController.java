package com.xqoo.device.controller;

import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.core.utils.StringUtils;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageResponseBean;
import com.xqoo.device.entity.ScreenBaseInfoEntity;
import com.xqoo.device.pojo.DeviceInfoPageQueryPOJO;
import com.xqoo.device.service.ScreenBaseInfoService;
import com.xqoo.device.vo.DeviceDetailInfoVO;
import com.xqoo.device.vo.DeviceInfoVO;
import com.xqoo.device.vo.ScreenConfigPropertiesVO;
import com.xqoo.feign.annotations.LoginUser;
import com.xqoo.feign.annotations.OperationLog;
import com.xqoo.feign.dto.device.DeviceInfoDetailDTO;
import com.xqoo.feign.enums.operlog.OperationTypeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * ScreenBaseInfoController
 *
 * @author xqoo-code-gen
 * @date 2021-01-27 10:50:14
 */

@RestController
@RequestMapping("/deviceInfoHandle")
@Api(tags = "屏幕信息表控制器")
@Validated
public class ScreenBaseInfoController{

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
    private ScreenBaseInfoService screenBaseInfoService;

                                                                                                                                                
    @ApiOperation("分页获取屏幕信息表表数据")
    @PostMapping("/pageGetList")
    public ResultEntity<PageResponseBean<DeviceInfoVO>> pageGetList(@RequestBody DeviceInfoPageQueryPOJO page){
        return screenBaseInfoService.pageGetList(page);
    }

    @ApiOperation("批量新增数据")
    @PostMapping("/addRecordByList")
    @OperationLog(tips="批量新增screen_base_info表数据", operatorType = OperationTypeEnum.ADD, isSaveRequestData = true)
    public ResultEntity<String> addRecordByList(@ApiIgnore @LoginUser CurrentUser currentUser,
                                        @RequestBody List<ScreenBaseInfoEntity> list){
        if(StringUtils.isEmpty(currentUser.getUserId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "未找到当前登录人信息，请重新登录重试");
        }
        return screenBaseInfoService.insertList(list, currentUser);
    }

    @ApiOperation("更改状态为正在部署")
    @GetMapping("/deviceToDeploy")
    @OperationLog(tips = "更改屏幕状态为正在部署", operatorType = OperationTypeEnum.EDIT)
    public ResultEntity<String> deviceToDeploy(@ApiIgnore @LoginUser CurrentUser currentUser,
                                               @RequestParam(value = "deviceId", required = false) @NotBlank(message = "设备id不能为空") String deviceId){
        if(StringUtils.isEmpty(currentUser.getUserId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "未找到当前登录人信息，请重新登录重试");
        }
        return screenBaseInfoService.deviceToDeploy(deviceId);
    }

    @ApiOperation("更改状态为部署完成")
    @GetMapping("/deviceToFinish")
    @OperationLog(tips = "更改屏幕状态为部署完成", operatorType = OperationTypeEnum.EDIT)
    public ResultEntity<String> deviceToFinish(@ApiIgnore @LoginUser CurrentUser currentUser,
                                               @RequestParam(value = "deviceId", required = false) @NotBlank(message = "设备id不能为空") String deviceId){
        if(StringUtils.isEmpty(currentUser.getUserId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "未找到当前登录人信息，请重新登录重试");
        }
        return screenBaseInfoService.deviceToFinish(deviceId);
    }

    @ApiOperation("更改状态为故障停机")
    @GetMapping("/deviceToStop")
    @OperationLog(tips = "更改屏幕状态为故障停机", operatorType = OperationTypeEnum.EDIT)
    public ResultEntity<String> deviceToStop(@ApiIgnore @LoginUser CurrentUser currentUser,
                                               @RequestParam(value = "deviceId", required = false) @NotBlank(message = "设备id不能为空") String deviceId){
        if(StringUtils.isEmpty(currentUser.getUserId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "未找到当前登录人信息，请重新登录重试");
        }
        return screenBaseInfoService.deviceToStop(deviceId);
    }

    @ApiOperation("更改状态为拆除")
    @GetMapping("/deviceToRemove")
    @OperationLog(tips = "更改屏幕状态为拆除", operatorType = OperationTypeEnum.EDIT)
    public ResultEntity<String> deviceToRemove(@ApiIgnore @LoginUser CurrentUser currentUser,
                                             @RequestParam(value = "deviceId", required = false) @NotBlank(message = "设备id不能为空") String deviceId){
        if(StringUtils.isEmpty(currentUser.getUserId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "未找到当前登录人信息，请重新登录重试");
        }
        return screenBaseInfoService.deviceToRemove(deviceId);
    }

    @ApiOperation("删除设备记录")
    @GetMapping("/deleteDevice")
    @OperationLog(tips = "删除了设备信息记录", operatorType = OperationTypeEnum.EDIT)
    public ResultEntity<String> deleteDevice(@ApiIgnore @LoginUser CurrentUser currentUser,
                                               @RequestParam(value = "deviceId", required = false) @NotBlank(message = "设备id不能为空") String deviceId){
        if(StringUtils.isEmpty(currentUser.getUserId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "未找到当前登录人信息，请重新登录重试");
        }
        return screenBaseInfoService.deleteDevice(deviceId);
    }

    @ApiOperation("根据主键查询单条记录")
    @GetMapping("/getRecordByPrimaryKey")
    public ResultEntity<DeviceDetailInfoVO> getRecordByPrimaryKey(@RequestParam(required = false, value = "id")
                                                                      @NotNull(message = "主键值不能为空") String id){
        DeviceDetailInfoVO vo = screenBaseInfoService.getOneScreenBaseInfoEntityByPrimaryKey(id);
        if(vo == null){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "未找到记录信息，请重试");
        }
        return new ResultEntity<>(HttpStatus.OK, "查询成功", vo);
    }

    @ApiOperation("获取屏幕信息参数")
    @GetMapping("getScreenConfigProperties")
    public ResultEntity<ScreenConfigPropertiesVO> getScreenConfigProperties(){
        return screenBaseInfoService.getScreenConfigProperties();
    }

    @ApiOperation("新增或修改屏幕信息")
    @PostMapping("/updateDeviceBaseInfo")
    @OperationLog(tips = "新增/修改屏幕信息", operatorType = OperationTypeEnum.EDIT)
    public ResultEntity<String> updateDeviceBaseInfo(@RequestBody @Valid DeviceDetailInfoVO vo, @ApiIgnore @LoginUser CurrentUser currentUser){
        if(StringUtils.isEmpty(vo.getId())){
            return screenBaseInfoService.addDeviceBaseInfo(vo, currentUser);
        }
        return screenBaseInfoService.updateDeviceBaseInfo(vo, currentUser);
    }

    @ApiOperation("外部调用获取屏幕信息整合结构")
    @GetMapping("/getDeviceInfoForPublic")
    public ResultEntity<DeviceInfoDetailDTO> getDeviceInfoForPublic(@RequestParam(value = "id", required = false) @NotBlank(message = "id不能为空") String id){
        DeviceInfoDetailDTO dto = screenBaseInfoService.getDeviceInfoForPrivate(id);
        if(dto == null || StringUtils.isEmpty(dto.getScreenName())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "获取屏幕信息失败，请重试");
        }
        return new ResultEntity<>(HttpStatus.OK, "ok", dto);
    }
}
