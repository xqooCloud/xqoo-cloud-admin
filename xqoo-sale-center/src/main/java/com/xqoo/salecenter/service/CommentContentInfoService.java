package com.xqoo.salecenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xqoo.salecenter.entity.CommentContentInfoEntity;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageRequestBean;
import com.xqoo.common.page.PageResponseBean;

import java.util.List;

/**
 * 数据源表(comment_conten_info)表服务接口
 *
 * @author xqoo-code-gen
 * @date 2021-02-01 14:45:10
 */

public interface CommentContentInfoService extends IService<CommentContentInfoEntity> {

    /**
    * 分页查询comment_conten_info
    * @param page
    * @return
    */
    ResultEntity<PageResponseBean<CommentContentInfoEntity>> pageGetList(PageRequestBean page);

    /**
    * 批量插入comment_conten_info
    * @param list
    * @param currentUser
    * @return
    */
    ResultEntity<String> insertList(List<CommentContentInfoEntity> list, CurrentUser currentUser);

    /**
    * 获取comment_conten_info主键记录了
    * @return
    */
    CommentContentInfoEntity getOneCommentContenInfoEntityByPrimaryKey(Long id);
}
