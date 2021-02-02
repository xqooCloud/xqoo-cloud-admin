package com.xqoo.salecenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xqoo.salecenter.entity.GoodsPropertiesEntity;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageRequestBean;
import com.xqoo.common.page.PageResponseBean;

import java.util.List;

/**
 * 数据源表(goods_properties)表服务接口
 *
 * @author xqoo-code-gen
 * @date 2021-02-01 15:02:42
 */

public interface GoodsPropertiesService extends IService<GoodsPropertiesEntity> {

    /**
    * 分页查询goods_properties
    * @param page
    * @return
    */
    ResultEntity<PageResponseBean<GoodsPropertiesEntity>> pageGetList(PageRequestBean page);

    /**
    * 批量插入goods_properties
    * @param list
    * @param currentUser
    * @return
    */
    ResultEntity<String> insertList(List<GoodsPropertiesEntity> list, CurrentUser currentUser);

    /**
    * 获取goods_properties主键记录了
    * @return
    */
    GoodsPropertiesEntity getOneGoodsPropertiesEntityByPrimaryKey(Long id);
}
