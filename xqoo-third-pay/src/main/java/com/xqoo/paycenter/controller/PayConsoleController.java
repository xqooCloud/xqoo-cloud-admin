package com.xqoo.paycenter.controller;

import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageResponseBean;
import com.xqoo.paycenter.bo.*;
import com.xqoo.paycenter.entity.PayConfigPropertiesEntity;
import com.xqoo.paycenter.service.PayConfigPropertiesService;
import com.xqoo.paycenter.service.PayConfigService;
import com.xqoo.paycenter.service.PayRefundWaterFlowService;
import com.xqoo.paycenter.service.PayWaterFlowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/console")
@Api(tags = "支付模块后台处理控制器")
public class PayConsoleController {

    @Autowired
    private PayConfigService payConfigService;

    @Autowired
    private PayConfigPropertiesService payConfigPropertiesService;

    @Autowired
    private PayWaterFlowService payWaterFlowService;

    @Autowired
    private PayRefundWaterFlowService payRefundWaterFlowService;


    @ApiOperation("查询第三方支付参数版本配置")
    @PostMapping("/queryPayConfig")
    public ResultEntity queryPayConfig(@RequestBody PayConfigQueryBO queryBO){
        return new ResultEntity<>(HttpStatus.OK, "查询成功", payConfigService.queryPayConfig(queryBO));
    }

    @ApiOperation("查询第三方支付参数明细")
    @PostMapping("/queryConfigProperties")
    public ResultEntity queryConfigProperties(@RequestBody PayConfigPropertiesQueryBO queryBO){
        return new ResultEntity<PageResponseBean>(HttpStatus.OK, "查询成功", payConfigPropertiesService.queryPayConfigProperties(queryBO));
    }

    @ApiOperation("更新或新增参数配置")
    @PostMapping("/updatePropertiesInfo")
    public ResultEntity updatePropertiesInfo(@RequestBody PayConfigPropertiesBO updateBO){
        if(updateBO.getParentId() == null){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "更新失败，丢失父级参数");
        }
        if(updateBO.getParentVersion() == null){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "更新失败，丢失版本号");
        }
        if(StringUtils.isEmpty(updateBO.getPropertiesLabel())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "更新失败，缺失参数标签");
        }
        if(StringUtils.isEmpty(updateBO.getPropertiesValue())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "更新失败，缺失参数值");
        }
        PayConfigPropertiesEntity entity = new PayConfigPropertiesEntity();
        if(updateBO.getId() == null){
            BeanUtils.copyProperties(updateBO, entity);
            try{
                return payConfigPropertiesService.addPropertiesInfo(entity);
            }catch (Exception e){
                System.out.print("[支付模块][Exception]新增支付参数时发生错误，错误信息：" + e.getMessage());
                return new ResultEntity<PageResponseBean>(HttpStatus.NOT_ACCEPTABLE, "[系统运行错误]新增参数失败");
            }
        }else{
            BeanUtils.copyProperties(updateBO, entity);
            try{
                return payConfigPropertiesService.updatePropertiesInfo(entity);
            }catch (Exception e){
                System.out.print("[支付模块][Exception]修改支付参数时发生错误，错误信息：" + e.getMessage());
                return new ResultEntity<PageResponseBean>(HttpStatus.NOT_ACCEPTABLE, "[系统运行错误]修改参数失败");
            }
        }
    }

    @ApiOperation("更新配置接口")
    @GetMapping("/updateConfig")
    public ResultEntity updatePayConfig(@RequestParam(value = "refreshPlat", required = true) String refreshPlat){
        try {
            payConfigPropertiesService.updatePayConfig(refreshPlat);
            return new ResultEntity<>(HttpStatus.OK, "已推送最新配置参数");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE,"推送失败，未更新支付配置");
        }
    }

    @ApiOperation("查询支付记录")
    @PostMapping("/queryPayWaterFlow")
    public ResultEntity<PageResponseBean> queryPayWaterFlow(@RequestBody PayWaterFlowQueryBO queryBO){
        return new ResultEntity<PageResponseBean>(HttpStatus.OK, "查询成功",
                payWaterFlowService.queryPagePayWaterFlow(queryBO));
    }
    @ApiOperation("查询退款记录")
    @PostMapping("/queryPayRefundWaterFlow")
    public ResultEntity<PageResponseBean> queryPayRefundWaterFlow(@RequestBody PayRefundWaterFlowQueryBO queryBO){
        return new ResultEntity<PageResponseBean>(HttpStatus.OK, "查询成功",
                payRefundWaterFlowService.queryPageRefundWaterFlow(queryBO));
    }
}
