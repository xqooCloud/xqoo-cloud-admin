package com.xqoo.device.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageRequestBean;
import com.xqoo.common.page.PageResponseBean;
import com.xqoo.device.entity.ScreenPropertiesEntity;

import java.util.List;

/**
 * 数据源表(screen_properties)表服务接口
 *
 * @author xqoo-code-gen
 * @date 2021-01-28 15:12:35
 */

public interface ScreenPropertiesService extends IService<ScreenPropertiesEntity> {

    /**
    * 分页查询screen_properties
    * @param page
    * @return
    */
    ResultEntity<PageResponseBean<ScreenPropertiesEntity>> pageGetList(PageRequestBean page);

    /**
    * 批量插入screen_properties
    * @param list
    * @param currentUser
    * @return
    */
    ResultEntity<String> insertList(List<ScreenPropertiesEntity> list, CurrentUser currentUser);

    /**
     * 批量刪除
     * @param ids
     * @throws Exception
     */
    void removeList(List<Long> ids) throws Exception;

    /**
     * 批量更新
     * @param list
     * @throws Exception
     */
    void updateList(List<ScreenPropertiesEntity> list) throws Exception;

    /**
    * 获取screen_properties主键记录了
    * @return
    */
    ScreenPropertiesEntity getOneScreenPropertiesEntityByPrimaryKey(Long id);

    /**
     * 根据父级id获取列表数据
     * @param parentId
     * @return
     */
    List<ScreenPropertiesEntity> getListByParentId(String parentId);

    /**
     * 根据父级id获取参数id列表
     * @param parentId
     * @return
     */
    List<Long> getIdsByParentId(String parentId);
}
