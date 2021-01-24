package com.xqoo.filemanager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageResponseBean;
import com.xqoo.filemanager.entity.FileRecordEntity;
import com.xqoo.filemanager.enums.UploadBucketTypeEnum;
import com.xqoo.filemanager.enums.UploadPlatEnum;
import com.xqoo.filemanager.pojo.UploadRecordQueryPOJO;

import java.util.List;

/**
 * 数据源表(file_record)表服务接口
 *
 * @author xqoo-code-gen
 * @date 2021-01-22 23:37:25
 */

public interface FileRecordService extends IService<FileRecordEntity> {

    /**
    * 分页查询file_record
    * @param pojo
    * @return
    */
    ResultEntity<PageResponseBean<FileRecordEntity>> pageGetList(UploadRecordQueryPOJO pojo);

    /**
    * 批量插入file_record
    * @param list
    * @param currentUser
    * @return
    */
    ResultEntity<String> insertList(List<FileRecordEntity> list, CurrentUser currentUser);

    /**
     * 删除文件
     * @param fileId
     * @return
     */
    ResultEntity<String> removeFile(String fileId);

    /**
    * 获取file_record主键记录了
     * @param id
    *   @return
    */
    FileRecordEntity getOneFileRecordEntityByPrimaryKey(String id);

    /**
     * 新增一条预上传记录
     * @param fileId
     * @param uploadPlat
     * @param uploadBucketTypeEnum
     * @return
     */
    boolean addFilePreUploadRecord(String fileId, UploadPlatEnum uploadPlat, UploadBucketTypeEnum uploadBucketTypeEnum);

    /**
     * 删除文件
     * @param fileId
     * @return
     */
    boolean deleteFileRecord(String fileId);

    /**
     * 上传完成更新记录状态
     * @param entity
     */
    void finishUploadRecord(FileRecordEntity entity);
}
