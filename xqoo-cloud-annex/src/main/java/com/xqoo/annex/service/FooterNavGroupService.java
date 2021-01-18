package com.xqoo.annex.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xqoo.annex.bo.QueryFooterNavGroupInfoBO;
import com.xqoo.annex.entity.FooterNavGroupEntity;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageRequestBean;
import com.xqoo.common.page.PageResponseBean;

import java.util.List;

/**
 * 数据源表(footer_nav_group)表服务接口
 *
 * @author xqoo-code-gen
 * @date 2021-01-14 15:42:57
 */

public interface FooterNavGroupService extends IService<FooterNavGroupEntity> {

    ResultEntity<PageResponseBean<FooterNavGroupEntity>> pageGetList(QueryFooterNavGroupInfoBO page);

    ResultEntity insertList(List<FooterNavGroupEntity> list, CurrentUser currentUser);
    ResultEntity insert(FooterNavGroupEntity footerNavGroupEntity);

    FooterNavGroupEntity getOneFooterNavGroupEntityByPrimaryKey(Integer id);
}

