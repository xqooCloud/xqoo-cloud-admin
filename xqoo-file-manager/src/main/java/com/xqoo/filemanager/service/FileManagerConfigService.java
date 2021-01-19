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

    ResultEntity<PageResponseBean<FileManagerConfigEntity>> pageGetList(PageRequestBean page);

    ResultEntity<String> insertList(List<FileManagerConfigEntity> list, CurrentUser currentUser);

    FileManagerConfigEntity getOneFileManagerConfigEntityByPrimaryKey(Integer id);

    List<FileManagerConfigEntity> getFileManagerConfig();
}
