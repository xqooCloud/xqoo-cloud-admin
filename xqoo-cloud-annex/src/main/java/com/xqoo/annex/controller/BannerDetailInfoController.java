package com.xqoo.annex.controller;

import com.xqoo.annex.bo.QueryBannerDetailInfoBO;
import com.xqoo.annex.vo.BannerDetailVO;
import com.xqoo.common.page.PageResponseBean;
import com.xqoo.feign.enums.operlog.OperationTypeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import springfox.documentation.annotations.ApiIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.xqoo.annex.service.BannerDetailInfoService;
import com.xqoo.annex.entity.BannerDetailInfoEntity;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.feign.annotations.LoginUser;
import com.xqoo.feign.annotations.OperationLog;

import java.util.List;

/**
 * BannerDetailInfoController
 *
 * @author xqoo-code-gen
 * @date 2021-01-14 15:31:34
 */

@RestController
@RequestMapping("/bannerDetailInfoHandle")
@Api(tags = "轮播图明细表控制器")
@Validated
public class BannerDetailInfoController{

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
    private BannerDetailInfoService bannerDetailInfoService;


    @ApiOperation("分页获取轮播图明细表表数据")
    @PostMapping("/pageGetList")
    public ResultEntity<PageResponseBean<BannerDetailInfoEntity>> pageGetList(@RequestBody QueryBannerDetailInfoBO page){
        return bannerDetailInfoService.pageGetList(page);
    }

    @ApiOperation("批量新增数据")
    @PostMapping("/addRecordByList")
    @OperationLog(tips="批量新增banner_detail_info表数据", operatorType = OperationTypeEnum.ADD, isSaveRequestData = true)
    public ResultEntity addRecordByList(@ApiIgnore @LoginUser CurrentUser currentUser,
                                        @RequestBody List<BannerDetailInfoEntity> list){
        if(StringUtils.isEmpty(currentUser.getUserId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "未找到当前登录人信息，请重新登录重试");
        }
        return bannerDetailInfoService.insertList(list, currentUser);
    }

    @ApiOperation("根据主键查询单条记录")
    @GetMapping("/getRecordByPrimaryKey")
    public ResultEntity<BannerDetailInfoEntity> getRecordByPrimaryKey(@RequestParam(required = false, value = "id")
                                                                      @NotNull(message = "主键值不能为空") Integer id){
        BannerDetailInfoEntity entity = bannerDetailInfoService.getOneBannerDetailInfoEntityByPrimaryKey(id);
        return new ResultEntity<>(HttpStatus.OK, "查询成功", entity);
    }

    @ApiOperation("新增/编辑轮播信息")
    @PostMapping("/updateBannerDetailInfo")
    @OperationLog(tips = "新增/编辑轮播信息", operatorType = OperationTypeEnum.EDIT, isSaveRequestData = true)
    public ResultEntity<String> updateBannerDetailInfo(@RequestBody @Valid BannerDetailVO vo) {
        BannerDetailInfoEntity entity = bannerDetailInfoService.getOneBannerDetailInfoEntityByPrimaryKey(vo.getId());
        if (entity == null) {
            return bannerDetailInfoService.addBannerDetailInfo(vo);
        }else{
            return bannerDetailInfoService.updateBannerDetailInfo(vo);
        }
    }

    @ApiOperation("删除轮播信息")
    @GetMapping("/deleteBannerDetailInfo")
    @OperationLog(tips = "删除轮播信息", operatorType = OperationTypeEnum.REMOVE, isSaveRequestData = true)
    public ResultEntity<String> detailDetail(@RequestParam(required = false, value = "id") Integer id) {
        return bannerDetailInfoService.deleteBannerDetailInfo(id);
    }
}
