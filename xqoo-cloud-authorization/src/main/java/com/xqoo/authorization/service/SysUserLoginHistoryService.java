package com.xqoo.authorization.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xqoo.authorization.bo.QueryUserLoginHistoryBO;
import com.xqoo.authorization.entity.SysUserLoginHistoryEntity;
import com.xqoo.authorization.vo.UserLoginHistoryVO;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageResponseBean;

/**
 * 用户登录历史记录(SysUserLoginHistory)表服务接口
 *
 * @author makejava
 * @since 2020-11-26 17:18:51
 */
public interface SysUserLoginHistoryService extends IService<SysUserLoginHistoryEntity> {

    void AddLoginHistory(SysUserLoginHistoryEntity entity);

    ResultEntity<PageResponseBean<UserLoginHistoryVO>> getLoginUserHistory(QueryUserLoginHistoryBO bo);
}
