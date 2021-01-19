package com.xqoo.filemanager.service.serviceImpl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xqoo.common.constants.SqlQueryConstant;
import com.xqoo.filemanager.service.FileManagerConfigService;
import com.xqoo.filemanager.entity.FileManagerConfigEntity;
import com.xqoo.filemanager.mapper.FileManagerConfigMapper;
import com.github.pagehelper.PageHelper;
import com.xqoo.common.page.PageRequestBean;
import com.xqoo.common.page.PageResponseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.entity.ResultEntity;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 数据源表(file_manager_config)表服务实现类
 *
 * @author xqoo-code-gen
 * @date 2021-01-19 20:16:13
 */
@Service("fileManagerConfigService")
public class FileManagerConfigServiceImpl extends ServiceImpl<FileManagerConfigMapper, FileManagerConfigEntity> implements FileManagerConfigService {

    private final static Logger logger = LoggerFactory.getLogger(FileManagerConfigServiceImpl.class);


    @Autowired
    private FileManagerConfigMapper fileManagerConfigMapper;

    @Override
    public ResultEntity<PageResponseBean<FileManagerConfigEntity>> pageGetList(PageRequestBean page){
        LambdaQueryWrapper<FileManagerConfigEntity> queryWrapper = new LambdaQueryWrapper<>();
        Integer count = fileManagerConfigMapper.selectCount(queryWrapper);
        PageResponseBean<FileManagerConfigEntity> result = new PageResponseBean<>(page.getPage(), page.getPageSize(), count);
        if(count == null || count < 1){
            result.setContent(Collections.emptyList());
            return new ResultEntity<>(HttpStatus.OK, "ok", result);
        }
        PageHelper.startPage(page.getPage(), page.getPageSize());
        List<FileManagerConfigEntity> list = fileManagerConfigMapper.selectList(queryWrapper);
        result.setContent(list);
        return new ResultEntity<>(HttpStatus.OK, "ok", result);
    }

    @Override
    public ResultEntity<String> insertList(List<FileManagerConfigEntity> list, CurrentUser currentUser){
        if(CollUtil.isEmpty(list)){
            return new ResultEntity<>(HttpStatus.OK, "新增完成");
        }
        list.forEach(item -> {
            item.setCreateBy(currentUser.getUserId());
            item.setCreateDate(new Date());
        });
        try{
            fileManagerConfigMapper.insertList(list);
            return new ResultEntity<>(HttpStatus.OK, "新增完成");
        }catch (RuntimeException e){
            logger.error("[com.xqoo.filemanager]数据库批量新增错误，错误原因：{}，错误信息：{}", e.getClass().getSimpleName(), e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "新增失败");
        }
    }

    @Override
    public FileManagerConfigEntity getOneFileManagerConfigEntityByPrimaryKey(Integer id){
        LambdaQueryWrapper<FileManagerConfigEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FileManagerConfigEntity::getId, id);
        FileManagerConfigEntity entity = fileManagerConfigMapper.selectOne(queryWrapper);
        if(entity != null){
            return entity;
        }
        return new FileManagerConfigEntity();
    }

    @Override
    public List<FileManagerConfigEntity> getFileManagerConfig() {
        return fileManagerConfigMapper.selectList(new LambdaQueryWrapper<FileManagerConfigEntity>()
                .eq(FileManagerConfigEntity::getConfigStatus, SqlQueryConstant.LOGIC_DEL));
    }
}
