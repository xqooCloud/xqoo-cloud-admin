package com.xqoo.device.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.core.utils.StringUtils;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageRequestBean;
import com.xqoo.common.page.PageResponseBean;
import com.xqoo.device.entity.ScreenPictureViewEntity;
import com.xqoo.device.mapper.ScreenPictureViewMapper;
import com.xqoo.device.service.ScreenPictureViewService;
import com.xqoo.device.vo.ScreenPictureDetailVO;
import com.xqoo.feign.service.filemanager.FileManagerFeign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 数据源表(screen_picture_view)表服务实现类
 *
 * @author xqoo-code-gen
 * @date 2021-01-28 15:10:47
 */
@Service("screenPictureViewService")
public class ScreenPictureViewServiceImpl extends ServiceImpl<ScreenPictureViewMapper, ScreenPictureViewEntity> implements ScreenPictureViewService {

    private final static Logger logger = LoggerFactory.getLogger(ScreenPictureViewServiceImpl.class);

    @Autowired
    private FileManagerFeign fileManagerFeign;

    @Autowired
    private ScreenPictureViewMapper screenPictureViewMapper;

    @Override
    public ResultEntity<PageResponseBean<ScreenPictureViewEntity>> pageGetList(PageRequestBean page){
        LambdaQueryWrapper<ScreenPictureViewEntity> queryWrapper = new LambdaQueryWrapper<>();
        Integer count = screenPictureViewMapper.selectCount(queryWrapper);
        PageResponseBean<ScreenPictureViewEntity> result = new PageResponseBean<>(page.getPage(), page.getPageSize(), count);
        if(count == null || count < 1){
            result.setContent(Collections.emptyList());
            return new ResultEntity<>(HttpStatus.OK, "ok", result);
        }
        PageHelper.startPage(page.getPage(), page.getPageSize());
        List<ScreenPictureViewEntity> list = screenPictureViewMapper.selectList(queryWrapper);
        result.setContent(list);
        return new ResultEntity<>(HttpStatus.OK, "ok", result);
    }

    @Override
    public ResultEntity insertList(List<ScreenPictureViewEntity> list, CurrentUser currentUser){
        if(CollUtil.isEmpty(list)){
            return new ResultEntity<>(HttpStatus.OK, "新增完成");
        }
        try{
            screenPictureViewMapper.insertList(list);
            return new ResultEntity<>(HttpStatus.OK, "新增完成");
        }catch (RuntimeException e){
            logger.error("[com.xqoo.device]数据库批量新增错误，错误原因：{}，错误信息：{}", e.getClass().getSimpleName(), e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "新增失败");
        }
    }

    @Override
    public void removeList(List<Long> ids) throws Exception {
        if(CollUtil.isNotEmpty(ids)){
            LambdaQueryWrapper <ScreenPictureViewEntity> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.in(ScreenPictureViewEntity::getId, ids);
            List<ScreenPictureViewEntity> list = screenPictureViewMapper.selectList(queryWrapper);
            List<String> fileIds = list.stream().map(ScreenPictureViewEntity::getFileId).collect(Collectors.toList());
            fileManagerFeign.removeFileByFileIds(fileIds);
            screenPictureViewMapper.delete(queryWrapper);
        }
    }

    @Override
    public void updateList(List<ScreenPictureViewEntity> list) throws Exception {
        list.forEach(item -> {
            screenPictureViewMapper.updateById(item);
        });
    }

    @Override
    public ScreenPictureViewEntity getOneScreenPictureViewEntityByPrimaryKey(Long id){
        LambdaQueryWrapper<ScreenPictureViewEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ScreenPictureViewEntity::getId, id);
        ScreenPictureViewEntity entity = screenPictureViewMapper.selectOne(queryWrapper);
        if(entity != null){
            return entity;
        }
        return new ScreenPictureViewEntity();
    }

    @Override
    public List<ScreenPictureDetailVO> getListByParentId(String parentId) {
        List<ScreenPictureViewEntity> list = screenPictureViewMapper.selectList(new LambdaQueryWrapper<ScreenPictureViewEntity>()
                .eq(ScreenPictureViewEntity::getParentId, parentId));
        if(CollUtil.isEmpty(list)){
            return Collections.emptyList();
        }
        return list.stream().map(item -> {
            ScreenPictureDetailVO vo = new ScreenPictureDetailVO();
            BeanUtils.copyProperties(item, vo);
            vo.setPercent(100);
            vo.setUrl(item.getFilePath());
            vo.setStatus("done");
            vo.setKey(item.getFileId());
            vo.setUid(item.getFileId());
            return vo;
        }).collect(Collectors.toList());
    }

    @Override
    public List<Long> getIdsByParentId(String parentId) {
        if(StringUtils.isEmpty(parentId)){
            return Collections.emptyList();
        }
        List<Long> list = screenPictureViewMapper.getIdsByParentId(parentId);
        if(CollUtil.isEmpty(list)){
            return Collections.emptyList();
        }
        return list;
    }
}
