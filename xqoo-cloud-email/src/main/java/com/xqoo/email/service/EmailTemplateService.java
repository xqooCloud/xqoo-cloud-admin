package com.xqoo.email.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xqoo.email.entity.EmailTemplateEntity;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageRequestBean;
import com.xqoo.common.page.PageResponseBean;

import java.util.List;

/**
 * 数据源表(email_template)表服务接口
 *
 * @author xqoo-code-gen
 * @date 2021-01-11 17:06:49
 */

public interface EmailTemplateService extends IService<EmailTemplateEntity> {

    ResultEntity<PageResponseBean<EmailTemplateEntity>> pageGetList(PageRequestBean page);

    ResultEntity insertList(List<EmailTemplateEntity> list, CurrentUser currentUser);

    EmailTemplateEntity getOneEmailTemplateEntityByPrimaryKey(Integer id);
}
