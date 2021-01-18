package com.xqoo.annex.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xqoo.annex.bo.QueryAgreementInfoBO;
import com.xqoo.annex.entity.AgreementInfoEntity;
import com.xqoo.annex.vo.AgreementInfoVO;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageResponseBean;

import java.util.List;

/**
 * 数据源表(agreement_info)表服务接口
 *
 * @author xqoo-code-gen
 * @date 2021-01-14 14:56:53
 */

public interface AgreementInfoService extends IService<AgreementInfoEntity> {

    ResultEntity<PageResponseBean<AgreementInfoEntity>> pageGetList(QueryAgreementInfoBO page);

    ResultEntity insertList(List<AgreementInfoEntity> list, CurrentUser currentUser);

    AgreementInfoEntity getOneAgreementInfoEntityByPrimaryKey(String agreementKey);

    ResultEntity<String> deleteAgreementInfo(String key);

    ResultEntity<String> addAgreementInfo(AgreementInfoVO vo);

    ResultEntity<String> updateAgreementInfo(AgreementInfoVO vo);
}
