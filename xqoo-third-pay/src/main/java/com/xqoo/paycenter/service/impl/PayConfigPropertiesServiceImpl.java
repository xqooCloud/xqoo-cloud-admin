package com.xqoo.paycenter.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.paycenter.bean.AliPayPropertiesBean;
import com.xqoo.paycenter.bean.WxPayPropertiesBean;
import com.xqoo.paycenter.bo.PayConfigPropertiesQueryBO;
import com.xqoo.paycenter.bo.PayConfigQueryBO;
import com.xqoo.paycenter.constant.PayModuleConstant;
import com.xqoo.paycenter.entity.PayConfigEntity;
import com.xqoo.paycenter.entity.PayConfigPropertiesEntity;
import com.xqoo.paycenter.enums.PayPlatType;
import com.xqoo.paycenter.mapper.PayConfigPropertiesMapper;
import com.xqoo.paycenter.service.PayConfigPropertiesService;
import com.xqoo.paycenter.service.PayConfigService;
import com.xqoo.paycenter.vo.PayConfigPropertiesVO;
import com.xqoo.rocket.constant.MqTagsConstant;
import com.xqoo.rocket.util.InitProducer;
import org.apache.commons.lang.StringUtils;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * (PayConfigProperties)表服务实现类
 *
 * @author makejava
 * @since 2020-03-14 16:01:09
 */
@Service("payConfigPropertiesService")
public class PayConfigPropertiesServiceImpl extends ServiceImpl<PayConfigPropertiesMapper, PayConfigPropertiesEntity> implements PayConfigPropertiesService {

    private final static Logger logger = LoggerFactory.getLogger(PayConfigPropertiesServiceImpl.class);

    @Autowired
    private InitProducer initProducer;

    @Autowired
    private PayConfigPropertiesMapper payConfigPropertiesMapper;

    @Autowired
    private PayConfigService payConfigService;

    @Autowired
    @Qualifier("AliPayConfig")
    private AliPayPropertiesBean aliPayPropertiesBean;

    @Autowired
    @Qualifier("WxPayConfig")
    private WxPayPropertiesBean wxPayPropertiesBean;


    @Override
    public List<PayConfigPropertiesVO> queryPayConfigProperties(PayConfigPropertiesQueryBO queryBO) {
        LambdaQueryWrapper<PayConfigPropertiesEntity> queryWrapper = new LambdaQueryWrapper<>();
        if(queryBO.getParentId() != null){
            queryWrapper.eq(PayConfigPropertiesEntity::getParentId, queryBO.getParentId());
        }
        if(queryBO.getParentVersion() != null){
            queryWrapper.eq(PayConfigPropertiesEntity::getParentVersion, queryBO.getParentVersion());
        }
        if(StringUtils.isNotEmpty(queryBO.getPropertiesLabel())){
            queryWrapper.eq(PayConfigPropertiesEntity::getPropertiesLabel, queryBO.getPropertiesLabel());
        }
        int count = 0;
        count = payConfigPropertiesMapper.selectCount(queryWrapper);
        if(count < 1){
            return Collections.emptyList();
        }
        List<PayConfigPropertiesEntity> list = payConfigPropertiesMapper.selectList(queryWrapper);
        List<PayConfigPropertiesVO> voList = new ArrayList<>(list.size());
        list.forEach(item -> {
            PayConfigPropertiesVO vo = new PayConfigPropertiesVO();
            BeanUtils.copyProperties(item, vo);
            voList.add(vo);
        });
        return voList;
    }

    @Override
    public ResultEntity<PayConfigPropertiesEntity> addPropertiesInfo(PayConfigPropertiesEntity payConfigPropertiesEntity) throws Exception {
        LambdaQueryWrapper<PayConfigPropertiesEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PayConfigPropertiesEntity::getParentId, payConfigPropertiesEntity.getParentId());
        queryWrapper.eq(PayConfigPropertiesEntity::getParentVersion, payConfigPropertiesEntity.getParentVersion());
        queryWrapper.eq(PayConfigPropertiesEntity::getPropertiesLabel, payConfigPropertiesEntity.getPropertiesLabel());
        int count = payConfigPropertiesMapper.selectCount(queryWrapper);
        if(count > 0){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE,
                    "新增支付参数[" + payConfigPropertiesEntity.getPropertiesLabel() + "]标签已存在，请勿重复增加");
        }
        payConfigPropertiesMapper.insert(payConfigPropertiesEntity);
        return new ResultEntity<>(HttpStatus.OK,
                "新增参数[" + payConfigPropertiesEntity.getPropertiesLabel() + "]成功", payConfigPropertiesEntity);
    }

    @Override
    public ResultEntity<PayConfigPropertiesEntity> updatePropertiesInfo(PayConfigPropertiesEntity payConfigPropertiesEntity) throws Exception {
        LambdaQueryWrapper<PayConfigPropertiesEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PayConfigPropertiesEntity::getParentVersion, payConfigPropertiesEntity.getParentVersion());
        queryWrapper.eq(PayConfigPropertiesEntity::getPropertiesLabel, payConfigPropertiesEntity.getPropertiesLabel());
        queryWrapper.eq(PayConfigPropertiesEntity::getParentId, payConfigPropertiesEntity.getParentId());
        List<PayConfigPropertiesEntity> existList = payConfigPropertiesMapper.selectList(queryWrapper);
        if(existList.size() > 1){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE,
                    "修改支付参数[" + payConfigPropertiesEntity.getPropertiesLabel() + "]标签已存在，无法修改为对应标签");
        }
        if(CollUtil.isNotEmpty(existList) && !existList.get(0).getId().equals(payConfigPropertiesEntity.getId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE,
                    "修改支付参数[" + payConfigPropertiesEntity.getPropertiesLabel() + "]标签已存在，无法修改为对应标签");
        }
        queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PayConfigPropertiesEntity::getId,payConfigPropertiesEntity.getId());
        payConfigPropertiesMapper.update(payConfigPropertiesEntity, queryWrapper);
        return new ResultEntity<>(HttpStatus.OK, "修改参数成功", payConfigPropertiesEntity);
    }

    @Override
    public Map<String, String> getAliConigInit(String type) {
        // type 参数预留用来决定使用的是测试还是正式或是开发等情况
        PayConfigQueryBO queryMainBO = new PayConfigQueryBO();
        queryMainBO.setPayPlat(PayPlatType.ALIBABA.getValue());
        queryMainBO.setActiveState(PayModuleConstant.ACTIVE_CONFIG);
        queryMainBO.setConfigStatus(PayModuleConstant.CONFIG_PUBLIC);
        List<PayConfigEntity> mainConfigList = payConfigService.queryPayConfig(queryMainBO);
        if(CollUtil.isEmpty(mainConfigList) || mainConfigList.size() > 1){
            return new HashMap<>(2);
        }
        PayConfigEntity mainConfig = mainConfigList.get(0);
        LambdaQueryWrapper<PayConfigPropertiesEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PayConfigPropertiesEntity::getParentId, mainConfig.getId());
        queryWrapper.eq(PayConfigPropertiesEntity::getParentVersion, mainConfig.getConfigVersion());
        List<PayConfigPropertiesEntity> configList = payConfigPropertiesMapper.selectList(queryWrapper);
        if(CollUtil.isEmpty(configList)){
            return new HashMap<>(2);
        }
        Map<String, String> rtnMap = new HashMap<>(configList.size()<<1);
        configList.forEach(item -> {
            rtnMap.put(item.getPropertiesLabel(), item.getPropertiesValue());
        });
        return rtnMap;
    }

    @Override
    public Map<String, String> getWxConigInit(String type) {
        // type 参数预留用来决定使用的是测试还是正式或是开发等情况
        PayConfigQueryBO queryMainBO = new PayConfigQueryBO();
        queryMainBO.setPayPlat(PayPlatType.WEIXIN.getValue());
        queryMainBO.setConfigStatus(PayModuleConstant.CONFIG_PUBLIC);
        queryMainBO.setActiveState(PayModuleConstant.ACTIVE_CONFIG);
        List<PayConfigEntity> mainConfigList = payConfigService.queryPayConfig(queryMainBO);
        if(CollUtil.isEmpty(mainConfigList) || mainConfigList.size() > 1){
            return new HashMap<>(2);
        }
        PayConfigEntity mainConfig = mainConfigList.get(0);
        LambdaQueryWrapper<PayConfigPropertiesEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PayConfigPropertiesEntity::getParentId, mainConfig.getId());
        queryWrapper.eq(PayConfigPropertiesEntity::getParentVersion, mainConfig.getConfigVersion());
        List<PayConfigPropertiesEntity> configList = payConfigPropertiesMapper.selectList(queryWrapper);
        if(CollUtil.isEmpty(configList)){
            return new HashMap<>(2);
        }
        Map<String, String> rtnMap = new HashMap<>(configList.size()<<1);
        configList.forEach(item -> {
            rtnMap.put(item.getPropertiesLabel(), item.getPropertiesValue());
        });
        return rtnMap;
    }

    @Override
    public void updatePayConfig(String refreshPlat) throws Exception{

        //创建一个消息实例，包含 topic、tag 和 消息体
        //如下：topic 为 "TopicTest"，tag 为 "push"
        Message message = new Message();
        message.setTopic(MqTagsConstant.THIRD_PAY_TOPIC);
        message.setTags(MqTagsConstant.PayModuleTopicTags.CONFIG_REFRESH);
        message.setBody(refreshPlat.getBytes(RemotingHelper.DEFAULT_CHARSET));
        initProducer.getProducer().send(message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                logger.info("[支付模块]配置刷新参数更新消息发送结果={}, msg={} ", sendResult.getSendStatus(), sendResult.toString());
            }

            @Override
            public void onException(Throwable e) {
                logger.error("[支付模块]配置刷新参数更新消息发送失败，失败原因{}, 失败信息{}", e.getClass().getSimpleName(), e.getMessage());
                //补偿机制，根据业务情况进行使用，看是否进行重试
            }
        }, 3000L);
    }
}
