package com.xqoo.email.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xqoo.email.entity.EmailConfigEntity;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageRequestBean;
import com.xqoo.common.page.PageResponseBean;

import java.util.List;

/**
 * 数据源表(email_config)表服务接口
 *
 * @author xqoo-code-gen
 * @date 2021-01-11 16:52:01
 */

public interface EmailConfigService extends IService<EmailConfigEntity> {

    ResultEntity<PageResponseBean<EmailConfigEntity>> pageGetList(PageRequestBean page);

    ResultEntity insertList(List<EmailConfigEntity> list, CurrentUser currentUser);

    EmailConfigEntity getOneEmailConfigEntityByPrimaryKey(Integer id);
}
