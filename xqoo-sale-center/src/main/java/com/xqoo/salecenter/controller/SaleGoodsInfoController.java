package com.xqoo.salecenter.controller;

import cn.hutool.core.collection.CollUtil;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.core.utils.StringUtils;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageResponseBean;
import com.xqoo.feign.annotations.LoginUser;
import com.xqoo.feign.annotations.OperationLog;
import com.xqoo.feign.enums.operlog.OperationTypeEnum;
import com.xqoo.salecenter.entity.SaleGoodsInfoEntity;
import com.xqoo.salecenter.pojo.SaleGoodsInfoQuery;
import com.xqoo.salecenter.service.SaleGoodsInfoService;
import com.xqoo.salecenter.vo.SaleGoodsInfoVO;
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
 * SaleGoodsInfoController
 *
 * @author xqoo-code-gen
 * @date 2021-02-01 15:08:34
 */

@RestController
@RequestMapping("/saleGoodsInfoHandle")
@Api(tags = "产品售卖信息表控制器")
@Validated
public class SaleGoodsInfoController{

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
    private SaleGoodsInfoService saleGoodsInfoService;

                                                                                                    
    @ApiOperation("分页获取产品售卖信息表表数据")
    @PostMapping("/pageGetList")
    public ResultEntity<PageResponseBean<SaleGoodsInfoEntity>> pageGetList(@RequestBody SaleGoodsInfoQuery query){
        return saleGoodsInfoService.pageGetList(query);
    }

    @ApiOperation("批量新增数据")
    @PostMapping("/addRecordByList")
    @OperationLog(tips="批量新增sale_goods_info表数据", operatorType = OperationTypeEnum.ADD, isSaveRequestData = true)
    public ResultEntity<String> addRecordByList(@ApiIgnore @LoginUser CurrentUser currentUser,
                                        @RequestBody List<SaleGoodsInfoEntity> list){
        if(StringUtils.isEmpty(currentUser.getUserId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "未找到当前登录人信息，请重新登录重试");
        }
        return saleGoodsInfoService.insertList(list, currentUser);
    }

    @ApiOperation("提交审核")
    @GetMapping("/sendAudit")
    @OperationLog(tips="提交商品审核审核", operatorType = OperationTypeEnum.EDIT)
    public ResultEntity<String> sendAudit(@ApiIgnore @LoginUser CurrentUser currentUser,
                                          @RequestParam(required = false, value = "goodsId") @NotBlank(message = "商品id不能为空") String goodsId){
        if(StringUtils.isEmpty(currentUser.getUserId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "未找到当前登录人信息，请重新登录重试");
        }
        return saleGoodsInfoService.sendAudit(goodsId);
    }

    @ApiOperation("审核通过")
    @GetMapping("/auditPass")
    @OperationLog(tips="商品审核通过", operatorType = OperationTypeEnum.EDIT)
    public ResultEntity<String> auditPass(@ApiIgnore @LoginUser CurrentUser currentUser,
                                          @RequestParam(required = false, value = "goodsId") @NotBlank(message = "商品id不能为空") String goodsId){
        if(StringUtils.isEmpty(currentUser.getUserId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "未找到当前登录人信息，请重新登录重试");
        }
        return saleGoodsInfoService.auditPass(goodsId);
    }

    @ApiOperation("废弃商品")
    @GetMapping("/aboardGoods")
    @OperationLog(tips="废弃商品", operatorType = OperationTypeEnum.EDIT)
    public ResultEntity<String> aboardGoods(@ApiIgnore @LoginUser CurrentUser currentUser,
                                          @RequestParam(required = false, value = "goodsId") @NotBlank(message = "商品id不能为空") String goodsId){
        if(StringUtils.isEmpty(currentUser.getUserId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "未找到当前登录人信息，请重新登录重试");
        }
        return saleGoodsInfoService.aboardGoods(goodsId);
    }

    @ApiOperation("上架商品")
    @PostMapping("/publishGoods")
    @OperationLog(tips="上架商品", operatorType = OperationTypeEnum.EDIT, isSaveRequestData = true)
    public ResultEntity<String> publishGoods(@ApiIgnore @LoginUser CurrentUser currentUser, @RequestBody List<String> goodsIds){
        if(StringUtils.isEmpty(currentUser.getUserId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "未找到当前登录人信息，请重新登录重试");
        }
        if(CollUtil.isEmpty(goodsIds)){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "上架的商品id为空，请重试");
        }
        return saleGoodsInfoService.publishGoods(goodsIds);
    }

    @ApiOperation("下架商品")
    @PostMapping("/undercarriageGoods")
    @OperationLog(tips="下架商品", operatorType = OperationTypeEnum.EDIT, isSaveRequestData = true)
    public ResultEntity<String> undercarriageGoods(@ApiIgnore @LoginUser CurrentUser currentUser, @RequestBody List<String> goodsIds){
        if(StringUtils.isEmpty(currentUser.getUserId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "未找到当前登录人信息，请重新登录重试");
        }
        if(CollUtil.isEmpty(goodsIds)){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "下架的商品id为空，请重试");
        }
        return saleGoodsInfoService.undercarriageGoods(goodsIds);
    }

    @ApiOperation("根据主键查询单条记录")
    @GetMapping("/getRecordByPrimaryKey")
    public ResultEntity<SaleGoodsInfoEntity> getRecordByPrimaryKey(@RequestParam(required = false, value = "goodsId")
                                                                      @NotNull(message = "主键值不能为空") String goodsId){
        SaleGoodsInfoEntity entity = saleGoodsInfoService.getOneSaleGoodsInfoEntityByPrimaryKey(goodsId);
        return new ResultEntity<>(HttpStatus.OK, "查询成功", entity);
    }

    @ApiOperation("根据主键查询明细记录")
    @GetMapping("/getSaleInfoDetail")
    public ResultEntity<SaleGoodsInfoVO> getSaleInfoDetail(@RequestParam(required = false, value = "goodsId")
                                                                   @NotNull(message = "主键值不能为空") String goodsId){
        return saleGoodsInfoService.getSaleInfoDetail(goodsId);
    }

    @ApiOperation("新增/修改商品信息")
    @PostMapping("/updateSaleInfo")
    public ResultEntity<String> updateSaleInfo(@RequestBody SaleGoodsInfoVO updateVo,
                                               @ApiIgnore @LoginUser CurrentUser currentUser){
        if(StringUtils.isEmpty(currentUser.getUserId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "未找到当前登录人信息，请重新登录重试");
        }
        if(StringUtils.isEmpty(updateVo.getGoodsId())){
            return saleGoodsInfoService.addGoodsInfo(updateVo, currentUser);
        }
        return saleGoodsInfoService.updateGoodsInfo(updateVo, currentUser);
    }
}
