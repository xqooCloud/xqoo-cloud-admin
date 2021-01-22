package com.xqoo.filemanager.service.aliyun.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.xqoo.common.constants.SqlQueryConstant;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageRequestBean;
import com.xqoo.common.page.PageResponseBean;
import com.xqoo.filemanager.constants.FileManagerConstant;
import com.xqoo.filemanager.entity.FileRecordEntity;
import com.xqoo.filemanager.enums.UploadBucketTypeEnum;
import com.xqoo.filemanager.enums.UploadPlatEnum;
import com.xqoo.filemanager.enums.UploadStatusEnum;
import com.xqoo.filemanager.mapper.FileRecordMapper;
import com.xqoo.filemanager.service.FileRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 数据源表(file_record)表服务实现类
 *
 * @author xqoo-code-gen
 * @date 2021-01-22 23:37:25
 */
@Service("fileRecordService")
public class FileRecordServiceImpl extends ServiceImpl<FileRecordMapper, FileRecordEntity> implements FileRecordService {

    private final static Logger logger = LoggerFactory.getLogger(FileRecordServiceImpl.class);


    @Autowired
    private FileRecordMapper fileRecordMapper;

    @Override
    public ResultEntity<PageResponseBean<FileRecordEntity>> pageGetList(PageRequestBean page){
        LambdaQueryWrapper<FileRecordEntity> queryWrapper = new LambdaQueryWrapper<>();
         queryWrapper.eq(FileRecordEntity::getDelFlag, SqlQueryConstant.NOT_LOGIC_DEL);
        Integer count = fileRecordMapper.selectCount(queryWrapper);
        PageResponseBean<FileRecordEntity> result = new PageResponseBean<>(page.getPage(), page.getPageSize(), count);
        if(count == null || count < 1){
            result.setContent(Collections.emptyList());
            return new ResultEntity<>(HttpStatus.OK, "ok", result);
        }
        PageHelper.startPage(page.getPage(), page.getPageSize());
        List<FileRecordEntity> list = fileRecordMapper.selectList(queryWrapper);
        result.setContent(list);
        return new ResultEntity<>(HttpStatus.OK, "ok", result);
    }

    @Override
    public ResultEntity insertList(List<FileRecordEntity> list, CurrentUser currentUser){
        if(CollUtil.isEmpty(list)){
            return new ResultEntity<>(HttpStatus.OK, "新增完成");
        }
        list.forEach(item -> {
            item.setCreateBy(currentUser.getUserId());
            item.setCreateDate(new Date());
        });
        try{
            fileRecordMapper.insertList(list);
            return new ResultEntity<>(HttpStatus.OK, "新增完成");
        }catch (RuntimeException e){
            logger.error("[com.xqoo.filemanager]数据库批量新增错误，错误原因：{}，错误信息：{}", e.getClass().getSimpleName(), e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "新增失败");
        }
    }

    @Override
    public FileRecordEntity getOneFileRecordEntityByPrimaryKey(String id){
        LambdaQueryWrapper<FileRecordEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FileRecordEntity::getId, id);
        queryWrapper.eq(FileRecordEntity::getDelFlag, SqlQueryConstant.NOT_LOGIC_DEL);
        FileRecordEntity entity = fileRecordMapper.selectOne(queryWrapper);
        if(entity != null){
            return entity;
        }
        return new FileRecordEntity();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addFilePreUploadRecord(String fileId, UploadPlatEnum uploadPlat, UploadBucketTypeEnum uploadBucketTypeEnum) {
        FileRecordEntity entity = new FileRecordEntity();
        entity.setId(fileId);
        entity.setDelFlag(SqlQueryConstant.NOT_LOGIC_DEL);
        entity.setUploadPlat(uploadPlat.getKey());
        entity.setUploadType(FileManagerConstant.SERVER_SIGN_UPLOAD);
        entity.setUploadStatus(UploadStatusEnum.INIT.getKey());
        entity.setFileInitTime(System.currentTimeMillis());
        entity.setAccessType(uploadBucketTypeEnum.getKey());
        fileRecordMapper.insert(entity);
        return true;
    }

    @Override
    public boolean deleteFileRecord(String fileId) {
        FileRecordEntity entity = fileRecordMapper.selectOne(new LambdaQueryWrapper<FileRecordEntity>().eq(FileRecordEntity::getId, fileId));
        if(ObjectUtil.isEmpty(entity)){
            return true;
        }
        try{
            entity.setDelFlag(SqlQueryConstant.LOGIC_DEL);
            fileRecordMapper.updateById(entity);
            return true;
        }catch (Exception e){
            logger.error("[文件管理模块]删除oss文件发生错误，文件ID：{},请核对结果", fileId);
            return false;
        }
    }

    @Override
    public void finishUploadRecord(FileRecordEntity entity) {
        try{
            fileRecordMapper.updateById(entity);
        }catch (Exception e){
            logger.error("[文件管理模块]文件上传完成回调更新记录状态发生错误，错误原因：{},错误信息：{}",
                    e.getClass().getSimpleName(), e.getMessage());
        }
    }
}
