package com.xqoo.salecenter.feign;

import cn.hutool.core.collection.CollUtil;
import com.xqoo.common.core.utils.StringUtils;
import com.xqoo.salecenter.service.SaleGoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * @author gaoyang
 */
@RestController
@RequestMapping("/saleCenterHandleFeign")
public class SaleCenterFeign {

    @Autowired
    private SaleGoodsInfoService saleGoodsInfoService;

    @PostMapping("/getHasSaleInfoScreen")
    public List<String> getHasSaleInfoScreen(@RequestBody List<String> screenIds){
        if(CollUtil.isEmpty(screenIds)){
            return Collections.emptyList();
        }
        return saleGoodsInfoService.getHasSaleInfoScreen(screenIds);
    }

    @GetMapping("/getExistSaleInfoByScreenId")
    public Boolean getExistSaleInfoByScreenId(@RequestParam(value = "screenId") String screenId){
        if(StringUtils.isEmpty(screenId)){
            return false;
        }
        return saleGoodsInfoService.getExistSaleInfoByScreenId(screenId);
    }
}
