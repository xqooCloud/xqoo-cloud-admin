package com.xqoo.salecenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xqoo.salecenter.entity.GoodsCommentEntity;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageRequestBean;
import com.xqoo.common.page.PageResponseBean;

import java.util.List;

/**
 * 数据源表(goods_comment)表服务接口
 *
 * @author xqoo-code-gen
 * @date 2021-02-01 14:46:30
 */

public interface GoodsCommentService extends IService<GoodsCommentEntity> {

    /**
    * 分页查询goods_comment
    * @param page
    * @return
    */
    ResultEntity<PageResponseBean<GoodsCommentEntity>> pageGetList(PageRequestBean page);

    /**
    * 批量插入goods_comment
    * @param list
    * @param currentUser
    * @return
    */
    ResultEntity<String> insertList(List<GoodsCommentEntity> list, CurrentUser currentUser);

    /**
    * 获取goods_comment主键记录了
    * @return
    */
    GoodsCommentEntity getOneGoodsCommentEntityByPrimaryKey(String commentId);
}
