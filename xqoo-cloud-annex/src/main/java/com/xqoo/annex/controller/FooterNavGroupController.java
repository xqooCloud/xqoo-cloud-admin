package com.xqoo.annex.controller;

import com.xqoo.annex.bo.QueryFooterNavGroupInfoBO;
import com.xqoo.annex.entity.FooterNavGroupEntity;
import com.xqoo.annex.service.FooterNavGroupService;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.core.utils.StringUtils;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageRequestBean;
import com.xqoo.common.page.PageResponseBean;
import com.xqoo.feign.annotations.LoginUser;
import com.xqoo.feign.annotations.OperationLog;
import com.xqoo.feign.enums.operlog.OperationTypeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * FooterNavGroupController
 *
 * @author xqoo-code-gen
 * @date 2021-01-14 15:42:57
 */

@RestController
@RequestMapping("/footerNavGroupHandle")
@Api(tags = "网页页脚展示信息分组控制器")
@Validated
public class FooterNavGroupController{

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
    private FooterNavGroupService footerNavGroupService;


    @ApiOperation("分页获取网页页脚展示信息分组表数据")
    @PostMapping("/pageGetList")
    public ResultEntity<PageResponseBean<FooterNavGroupEntity>> pageGetList(@RequestBody QueryFooterNavGroupInfoBO page){
        return footerNavGroupService.pageGetList(page);
    }

    @ApiOperation("批量新增数据")
    @PostMapping("/addRecordByList")
    @OperationLog(tips="批量新增footer_nav_group表数据", operatorType = OperationTypeEnum.ADD, isSaveRequestData = true)
    public ResultEntity addRecordByList(@ApiIgnore @LoginUser CurrentUser currentUser,
                                        @RequestBody List<FooterNavGroupEntity> list){
        if(StringUtils.isEmpty(currentUser.getUserId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "未找到当前登录人信息，请重新登录重试");
        }
        return footerNavGroupService.insertList(list, currentUser);
    }

    @ApiOperation("新增脚页分组数据")
    @PostMapping("/addFooterNavGroup")
    @OperationLog(tips="新增脚页分组数据", operatorType = OperationTypeEnum.ADD, isSaveRequestData = true)
    public ResultEntity addFooterNavGroup(@ApiIgnore @LoginUser CurrentUser currentUser,
                                        @RequestBody FooterNavGroupEntity footerNavGroupEntity){
        if(StringUtils.isEmpty(currentUser.getUserId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "未找到当前登录人信息，请重新登录重试");
        }
        return footerNavGroupService.insert(footerNavGroupEntity);
    }
    @ApiOperation("更新脚页分组数据")
    @PostMapping("/updateFooterNavGroup")
    @OperationLog(tips="更新脚页分组数据", operatorType = OperationTypeEnum.ADD, isSaveRequestData = true)
    public ResultEntity updateFooterNavGroup(@ApiIgnore @LoginUser CurrentUser currentUser,
                                        @RequestBody FooterNavGroupEntity footerNavGroupEntity){
        if(StringUtils.isEmpty(currentUser.getUserId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "未找到当前登录人信息，请重新登录重试");
        }
        boolean b = footerNavGroupService.updateById(footerNavGroupEntity);
        if (b){
            return new ResultEntity(true);
        }else{
            return new ResultEntity(HttpStatus.NOT_ACCEPTABLE,"更新失败");
        }
    }

    @ApiOperation("删除脚页分组数据")
        @GetMapping("/removeFooterNavGroup/{id}")
    @OperationLog(tips="删除脚页分组数据", operatorType = OperationTypeEnum.ADD, isSaveRequestData = true)
    public ResultEntity removeFooterNavGroup(@ApiIgnore @LoginUser CurrentUser currentUser,
                                             @PathVariable("id")int id){
        if(StringUtils.isEmpty(currentUser.getUserId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "未找到当前登录人信息，请重新登录重试");
        }
        boolean b = footerNavGroupService.removeById(id);
        if (b){
            return new ResultEntity(true);
        }else{
            return new ResultEntity(HttpStatus.NOT_ACCEPTABLE,"删除失败");
        }
    }

    @ApiOperation("根据主键查询单条记录")
    @GetMapping("/getRecordByPrimaryKey")
    public ResultEntity<FooterNavGroupEntity> getRecordByPrimaryKey(@RequestParam(required = false, value = "id")
                                                                    @NotNull(message = "主键值不能为空") Integer id){
        FooterNavGroupEntity entity = footerNavGroupService.getOneFooterNavGroupEntityByPrimaryKey(id);
        return new ResultEntity<>(HttpStatus.OK, "查询成功", entity);
    }
}
