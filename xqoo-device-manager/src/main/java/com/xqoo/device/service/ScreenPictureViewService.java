package com.xqoo.device.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageRequestBean;
import com.xqoo.common.page.PageResponseBean;
import com.xqoo.device.entity.ScreenPictureViewEntity;
import com.xqoo.device.vo.ScreenPictureDetailVO;

import java.util.List;

/**
 * 数据源表(screen_picture_view)表服务接口
 *
 * @author xqoo-code-gen
 * @date 2021-01-28 15:10:47
 */

public interface ScreenPictureViewService extends IService<ScreenPictureViewEntity> {

    /**
    * 分页查询screen_picture_view
    * @param page
    * @return
    */
    ResultEntity<PageResponseBean<ScreenPictureViewEntity>> pageGetList(PageRequestBean page);

    /**
    * 批量插入screen_picture_view
    * @param list
    * @param currentUser
    * @return
    */
    ResultEntity<String> insertList(List<ScreenPictureViewEntity> list, CurrentUser currentUser);

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
    void updateList(List<ScreenPictureViewEntity> list) throws Exception;

    /**
    * 获取screen_picture_view主键记录了
    * @return
    */
    ScreenPictureViewEntity getOneScreenPictureViewEntityByPrimaryKey(Long id);

    /**
     * 根据父级id获取参数
     * @param parentId
     * @return
     */
    List<ScreenPictureDetailVO> getListByParentId(String parentId);

    /**
     * 根据父级id获取所有id列表
     * @param parentId
     * @return
     */
    List<Long> getIdsByParentId(String parentId);
}
