package com.xqoo.salecenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xqoo.salecenter.entity.GoodsPictureInfoEntity;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageRequestBean;
import com.xqoo.common.page.PageResponseBean;

import java.util.List;

/**
 * 数据源表(goods_picture_info)表服务接口
 *
 * @author xqoo-code-gen
 * @date 2021-02-01 15:01:19
 */

public interface GoodsPictureInfoService extends IService<GoodsPictureInfoEntity> {

    /**
    * 分页查询goods_picture_info
    * @param page
    * @return
    */
    ResultEntity<PageResponseBean<GoodsPictureInfoEntity>> pageGetList(PageRequestBean page);

    /**
    * 批量插入goods_picture_info
    * @param list
    * @param currentUser
    * @return
    */
    ResultEntity<String> insertList(List<GoodsPictureInfoEntity> list, CurrentUser currentUser);

    /**
    * 获取goods_picture_info主键记录了
    * @return
    */
    GoodsPictureInfoEntity getOneGoodsPictureInfoEntityByPrimaryKey(Long id);
}
