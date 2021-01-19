package com.xqoo.annex.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xqoo.annex.bo.QueryFooterNavDetailInfoBO;
import com.xqoo.annex.entity.FooterNavDetailEntity;
import com.xqoo.annex.entity.FooterNavGroupEntity;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageRequestBean;
import com.xqoo.common.page.PageResponseBean;

import java.util.List;

/**
 * 数据源表(footer_nav_detail)表服务接口
 *
 * @author xqoo-code-gen
 * @date 2021-01-14 15:29:04
 */

public interface FooterNavDetailService extends IService<FooterNavDetailEntity> {

    ResultEntity<PageResponseBean<FooterNavDetailEntity>> pageGetList(QueryFooterNavDetailInfoBO page);

    ResultEntity insertList(List<FooterNavDetailEntity> list, CurrentUser currentUser);

    ResultEntity insert(FooterNavDetailEntity footerNavDetailEntity);

    FooterNavDetailEntity getOneFooterNavDetailEntityByPrimaryKey(Integer id);
}