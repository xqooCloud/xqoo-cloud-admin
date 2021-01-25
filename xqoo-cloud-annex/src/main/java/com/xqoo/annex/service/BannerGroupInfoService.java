package com.xqoo.annex.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xqoo.annex.bo.QueryBannerGroupInfoBO;
import com.xqoo.annex.entity.BannerGroupInfoEntity;
import com.xqoo.annex.vo.BannerGroupInfoVO;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageResponseBean;

import java.util.List;

/**
 * 数据源表(banner_group_info)表服务接口
 *
 * @author xqoo-code-gen
 * @date 2021-01-14 15:45:34
 */

public interface BannerGroupInfoService extends IService<BannerGroupInfoEntity> {

    ResultEntity<PageResponseBean<BannerGroupInfoEntity>> pageGetList(QueryBannerGroupInfoBO page);

    ResultEntity insertList(List<BannerGroupInfoEntity> list, CurrentUser currentUser);

    BannerGroupInfoEntity getOneBannerGroupInfoEntityByPrimaryKey(Integer id);

    ResultEntity<String> deleteBannerGroupInfo(Integer id);

    ResultEntity<String> addBannerGroupInfo(BannerGroupInfoVO vo);

    ResultEntity<String> updateBannerGroupInfo(BannerGroupInfoVO vo);
}
