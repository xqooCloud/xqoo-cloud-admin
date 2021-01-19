package com.xqoo.filemanager.service.serviceImpl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageRequestBean;
import com.xqoo.common.page.PageResponseBean;
import com.xqoo.filemanager.entity.FileConfigPropertiesEntity;
import com.xqoo.filemanager.mapper.FileConfigPropertiesMapper;
import com.xqoo.filemanager.service.FileConfigPropertiesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * 数据源表(file_config_properties)表服务实现类
 *
 * @author xqoo-code-gen
 * @date 2021-01-19 20:48:15
 */
@Service("fileConfigPropertiesService")
public class FileConfigPropertiesServiceImpl extends ServiceImpl<FileConfigPropertiesMapper, FileConfigPropertiesEntity> implements FileConfigPropertiesService {

    private final static Logger logger = LoggerFactory.getLogger(FileConfigPropertiesServiceImpl.class);


    @Autowired
    private FileConfigPropertiesMapper fileConfigPropertiesMapper;

    @Override
    public ResultEntity<PageResponseBean<FileConfigPropertiesEntity>> pageGetList(PageRequestBean page){
        LambdaQueryWrapper<FileConfigPropertiesEntity> queryWrapper = new LambdaQueryWrapper<>();
        Integer count = fileConfigPropertiesMapper.selectCount(queryWrapper);
        PageResponseBean<FileConfigPropertiesEntity> result = new PageResponseBean<>(page.getPage(), page.getPageSize(), count);
        if(count == null || count < 1){
            result.setContent(Collections.emptyList());
            return new ResultEntity<>(HttpStatus.OK, "ok", result);
        }
        PageHelper.startPage(page.getPage(), page.getPageSize());
        List<FileConfigPropertiesEntity> list = fileConfigPropertiesMapper.selectList(queryWrapper);
        result.setContent(list);
        return new ResultEntity<>(HttpStatus.OK, "ok", result);
    }

    @Override
    public ResultEntity insertList(List<FileConfigPropertiesEntity> list, CurrentUser currentUser){
        if(CollUtil.isEmpty(list)){
            return new ResultEntity<>(HttpStatus.OK, "新增完成");
        }
        list.forEach(item -> {
            item.setCreateBy(currentUser.getUserId());
            item.setCreateDate(new Date());
        });
        try{
            fileConfigPropertiesMapper.insertList(list);
            return new ResultEntity<>(HttpStatus.OK, "新增完成");
        }catch (RuntimeException e){
            logger.error("[com.xqoo.filemanager]数据库批量新增错误，错误原因：{}，错误信息：{}", e.getClass().getSimpleName(), e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "新增失败");
        }
    }

    @Override
    public FileConfigPropertiesEntity getOneFileConfigPropertiesEntityByPrimaryKey(Integer id){
        LambdaQueryWrapper<FileConfigPropertiesEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FileConfigPropertiesEntity::getId, id);
        FileConfigPropertiesEntity entity = fileConfigPropertiesMapper.selectOne(queryWrapper);
        if(entity != null){
            return entity;
        }
        return new FileConfigPropertiesEntity();
    }

    @Override
    public Map<String, String> getPropertiesByParentId(Integer parentId) {
        LambdaQueryWrapper<FileConfigPropertiesEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FileConfigPropertiesEntity::getParentId, parentId);
        List<FileConfigPropertiesEntity> list = fileConfigPropertiesMapper.selectList(queryWrapper);
        if(CollUtil.isEmpty(list)){
            return Collections.emptyMap();
        }
        Map<String, String> propertiesMap = new HashMap<>(BigDecimal.valueOf(list.size())
                .multiply(BigDecimal.valueOf(1.8)).intValue());
        list.forEach(item -> {
            propertiesMap.put(item.getPropertiesLabel(), item.getPropertiesValue());
        });
        return propertiesMap;
    }
}
