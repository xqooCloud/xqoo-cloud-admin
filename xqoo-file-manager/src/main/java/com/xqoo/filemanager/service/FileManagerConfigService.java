package com.xqoo.filemanager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xqoo.filemanager.entity.FileManagerConfigEntity;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageRequestBean;
import com.xqoo.common.page.PageResponseBean;

import java.util.List;

/**
 * 数据源表(file_manager_config)表服务接口
 *
 * @author xqoo-code-gen
 * @date 2021-01-19 20:16:13
 */

public interface FileManagerConfigService extends IService<FileManagerConfigEntity> {

    /**
     * 获取分页文件管理参数
     * @param page
     * @return
     */
    ResultEntity<PageResponseBean<FileManagerConfigEntity>> pageGetList(PageRequestBean page);

    /**
     * 批量插入文件
     * @param list
     * @param currentUser
     * @return
     */
    ResultEntity<String> insertList(List<FileManagerConfigEntity> list, CurrentUser currentUser);

    /**
     * 根据主键获取文件管理参数
     * @param id
     * @return
     */
    FileManagerConfigEntity getOneFileManagerConfigEntityByPrimaryKey(Integer id);

    /**
     * 获取全部可用的文件管理参数
     * @return
     */
    List<FileManagerConfigEntity> getFileManagerConfig();
}
