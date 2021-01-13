package com.xqoo.email.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xqoo.email.bo.EmailConfigBO;
import com.xqoo.email.entity.EmailConfigEntity;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageResponseBean;
import com.xqoo.email.vo.EmailConfigInfoVO;

import java.util.List;

/**
 * 数据源表(email_config)表服务接口
 *
 * @author xqoo-code-gen
 * @date 2021-01-11 16:52:01
 */

public interface EmailConfigService extends IService<EmailConfigEntity> {

    ResultEntity<PageResponseBean<EmailConfigEntity>> pageGetList(EmailConfigBO page);

    ResultEntity insertList(List<EmailConfigEntity> list, CurrentUser currentUser);

    EmailConfigEntity getOneEmailConfigEntityByPrimaryKey(Integer id);

    List<EmailConfigEntity> getListByGroup(String group);

    ResultEntity removeByKey(String key);

    ResultEntity<String> updateEmailConfig(EmailConfigInfoVO vo);

    ResultEntity<String> addEmailConfig(EmailConfigInfoVO vo);
}
