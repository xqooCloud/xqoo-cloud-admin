package com.xqoo.integral.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xqoo.integral.entity.IntegralAccountEntity;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageRequestBean;
import com.xqoo.common.page.PageResponseBean;

import java.util.List;

/**
 * 数据源表(integral_account)表服务接口
 *
 * @author xqoo-code-gen
 * @date 2021-01-26 11:17:12
 */

public interface IntegralAccountService extends IService<IntegralAccountEntity> {

    /**
    * 分页查询integral_account
    * @param page
    * @return
    */
    ResultEntity<PageResponseBean<IntegralAccountEntity>> pageGetList(PageRequestBean page);

    /**
    * 批量插入integral_account
    * @param list
    * @param currentUser
    * @return
    */
    ResultEntity<String> insertList(List<IntegralAccountEntity> list, CurrentUser currentUser);

    /**
    * 获取integral_account主键记录了
    * @return
    */
    IntegralAccountEntity getOneIntegralAccountEntityByPrimaryKey(String accountId);

    /**
     * 获取当前登录人的积分信息
     * @param userId
     * @return
     */
    ResultEntity<IntegralAccountEntity> getPersonAccountInfo(String userId);

    /**
     * 生成分享二维码
     * @param userId
     * @return
     */
    ResultEntity<String> generatorQrcode(String userId);

    /**
     * 扫码关键词
     * @param keyword
     * @param userId
     * @return
     */
    ResultEntity<String> scanQrcode(String keyword, String userId);
}
