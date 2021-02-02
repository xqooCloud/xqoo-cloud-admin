package com.xqoo.salecenter.controller;

import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageRequestBean;
import com.xqoo.common.page.PageResponseBean;
import com.xqoo.salecenter.service.SaleInfoHandleService;
import com.xqoo.salecenter.vo.SaleGoodsInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gaoyang
 */
@RestController
@RequestMapping("/publicSaleInfo")
@Api(tags = "销售中心公开无需登录接口")
@Validated
public class SaleCenterNoLoginController {

    @Autowired
    private SaleInfoHandleService saleInfoHandleService;

    @ApiOperation("分页获取产品售卖信息表表数据")
    @PostMapping("/pageGetList")
    public ResultEntity<PageResponseBean<SaleGoodsInfoVO>> pageGetList(@RequestBody PageRequestBean page){
        return saleInfoHandleService.getSaleGoodsPageInfo(page);
    }
}
