package com.xqoo.salecenter.service;

import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageRequestBean;
import com.xqoo.common.page.PageResponseBean;
import com.xqoo.salecenter.vo.SaleGoodsInfoVO;

/**
 * @author gaoyang
 */
public interface SaleInfoHandleService {

    /**
     * 获取分页显示数据
     * @param page
     * @return
     */
    ResultEntity<PageResponseBean<SaleGoodsInfoVO>> getSaleGoodsPageInfo(PageRequestBean page);
}
