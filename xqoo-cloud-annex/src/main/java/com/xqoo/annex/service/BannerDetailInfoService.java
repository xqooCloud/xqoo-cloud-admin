package com.xqoo.annex.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xqoo.annex.bo.QueryBannerDetailInfoBO;
import com.xqoo.annex.entity.BannerDetailInfoEntity;
import com.xqoo.annex.vo.BannerDetailVO;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageRequestBean;
import com.xqoo.common.page.PageResponseBean;

import java.util.List;

/**
 * 数据源表(banner_detail_info)表服务接口
 *
 * @author xqoo-code-gen
 * @date 2021-01-14 15:31:34
 */

public interface BannerDetailInfoService extends IService<BannerDetailInfoEntity> {

    ResultEntity<PageResponseBean<BannerDetailInfoEntity>> pageGetList(QueryBannerDetailInfoBO page);

    ResultEntity insertList(List<BannerDetailInfoEntity> list, CurrentUser currentUser);

    BannerDetailInfoEntity getOneBannerDetailInfoEntityByPrimaryKey(Integer id);

    ResultEntity<String> deleteBannerDetailInfo(Integer id);

    ResultEntity<String> addBannerDetailInfo(BannerDetailVO vo);

    ResultEntity<String> updateBannerDetailInfo(BannerDetailVO vo);
}
