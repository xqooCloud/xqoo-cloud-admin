package com.xqoo.salecenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xqoo.salecenter.entity.SaleDetailInfoEntity;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageRequestBean;
import com.xqoo.common.page.PageResponseBean;

import java.util.List;

/**
 * 数据源表(sale_detail_info)表服务接口
 *
 * @author xqoo-code-gen
 * @date 2021-02-01 15:07:13
 */

public interface SaleDetailInfoService extends IService<SaleDetailInfoEntity> {

    /**
    * 分页查询sale_detail_info
    * @param page
    * @return
    */
    ResultEntity<PageResponseBean<SaleDetailInfoEntity>> pageGetList(PageRequestBean page);

    /**
    * 批量插入sale_detail_info
    * @param list
    * @param currentUser
    * @return
    */
    ResultEntity<String> insertList(List<SaleDetailInfoEntity> list, CurrentUser currentUser);

    /**
    * 获取sale_detail_info主键记录了
    * @return
    */
    SaleDetailInfoEntity getOneSaleDetailInfoEntityByPrimaryKey(Long id);
}
