package com.xqoo.filemanager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageRequestBean;
import com.xqoo.common.page.PageResponseBean;
import com.xqoo.filemanager.entity.FileConfigPropertiesEntity;

import java.util.List;
import java.util.Map;

/**
 * 数据源表(file_config_properties)表服务接口
 *
 * @author xqoo-code-gen
 * @date 2021-01-19 20:48:15
 */

public interface FileConfigPropertiesService extends IService<FileConfigPropertiesEntity> {

    /**
    * 分页查询file_config_properties
    * @param page
    * @return
    */
    ResultEntity<PageResponseBean<FileConfigPropertiesEntity>> pageGetList(PageRequestBean page);

    /**
    * 批量插入file_config_properties
    * @param list
    * @param currentUser
    * @return
    */
    ResultEntity<String> insertList(List<FileConfigPropertiesEntity> list, CurrentUser currentUser);

    /**
    * 获取file_config_properties主键记录了
    * @return
    */
    FileConfigPropertiesEntity getOneFileConfigPropertiesEntityByPrimaryKey(Integer id);

    /**
     * 根据父级id获取参数属性
     * @param parentId
     * @return
     */
    Map<String, String> getPropertiesByParentId(Integer parentId);
}
