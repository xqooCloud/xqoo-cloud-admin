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
import com.xqoo.rocket.constant.MqTagsConstant;
import com.xqoo.rocket.util.InitProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
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
    private InitProducer initProducer;

    @Autowired
    private FileConfigPropertiesMapper fileConfigPropertiesMapper;

    @Override
    public ResultEntity<List<FileConfigPropertiesEntity>> getAllPropertiesByParentId(Integer parentId) {
        List<FileConfigPropertiesEntity> list = fileConfigPropertiesMapper
                .selectList(new LambdaQueryWrapper<FileConfigPropertiesEntity>().eq(FileConfigPropertiesEntity::getParentId, parentId));
        if(CollUtil.isEmpty(list)){
            return new ResultEntity<>(HttpStatus.OK, "ok", Collections.emptyList());
        }
        return new ResultEntity<>(HttpStatus.OK, "ok", list);
    }

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
    public void refreshFileConfig(String refreshPlat) throws Exception {
        //创建一个消息实例，包含 topic、tag 和 消息体
        //如下：topic 为 "TopicTest"，tag 为 "push"
        Message message = new Message();
        message.setTopic(MqTagsConstant.FILE_MANAGER_TOPIC);
        message.setTags(MqTagsConstant.FileModuleTopicTags.CONFIG_REFRESH);
        message.setBody(refreshPlat.getBytes(RemotingHelper.DEFAULT_CHARSET));
        initProducer.getProducer().send(message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                logger.info("[文件模块]配置刷新参数更新消息发送结果={}, msg={} ", sendResult.getSendStatus(), sendResult.toString());
            }

            @Override
            public void onException(Throwable e) {
                logger.error("[文件模块]配置刷新参数更新消息发送失败，失败原因{}, 失败信息{}", e.getClass().getSimpleName(), e.getMessage());
                //补偿机制，根据业务情况进行使用，看是否进行重试
            }
        }, 3000L);
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

    @Override
    public ResultEntity<FileConfigPropertiesEntity> addConfigProperties(FileConfigPropertiesEntity entity) {
        LambdaQueryWrapper<FileConfigPropertiesEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FileConfigPropertiesEntity::getParentId, entity.getParentId());
        queryWrapper.eq(FileConfigPropertiesEntity::getPropertiesLabel, entity.getPropertiesLabel());
        int count = fileConfigPropertiesMapper.selectCount(queryWrapper);
        if(count > 0){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE,
                    "新增支付参数[" + entity.getPropertiesLabel() + "]标签已存在，请勿重复增加");
        }
        fileConfigPropertiesMapper.insert(entity);
        return new ResultEntity<>(HttpStatus.OK,
                "新增参数[" + entity.getPropertiesLabel() + "]成功", entity);
    }

    @Override
    public ResultEntity<FileConfigPropertiesEntity> updatePropertiesInfo(FileConfigPropertiesEntity entity) {
        LambdaQueryWrapper<FileConfigPropertiesEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FileConfigPropertiesEntity::getPropertiesLabel, entity.getPropertiesLabel());
        queryWrapper.eq(FileConfigPropertiesEntity::getParentId, entity.getParentId());
        List<FileConfigPropertiesEntity> existList = fileConfigPropertiesMapper.selectList(queryWrapper);
        if(existList.size() > 1){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE,
                    "修改文件参数[" + entity.getPropertiesLabel() + "]标签已存在，无法修改为对应标签");
        }
        if(CollUtil.isNotEmpty(existList) && !existList.get(0).getId().equals(entity.getId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE,
                    "修改文件参数[" + entity.getPropertiesLabel() + "]标签已存在，无法修改为对应标签");
        }
        queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FileConfigPropertiesEntity::getId,entity.getId());
        fileConfigPropertiesMapper.update(entity, queryWrapper);
        return new ResultEntity<>(HttpStatus.OK, "修改参数成功", entity);
    }
}
