package com.xqoo.email.serviceImpl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xqoo.common.core.utils.StringUtils;
import com.xqoo.email.bo.EmailConfigBO;
import com.xqoo.email.constants.EmailConstant;
import com.xqoo.email.entity.EmailConfigEntity;
import com.xqoo.email.mapper.EmailConfigMapper;
import com.github.pagehelper.PageHelper;
import com.xqoo.common.page.PageResponseBean;
import com.xqoo.email.service.EmailConfigService;
import com.xqoo.email.vo.EmailConfigInfoVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.entity.ResultEntity;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * 数据源表(email_config)表服务实现类
 *
 * @author xqoo-code-gen
 * @date 2021-01-11 16:52:01
 */
@Service("EmailConfigService")
public class EmailConfigServiceImpl extends ServiceImpl<EmailConfigMapper, EmailConfigEntity> implements EmailConfigService {

    private final static Logger logger = LoggerFactory.getLogger(EmailConfigServiceImpl.class);


    @Autowired
    private EmailConfigMapper emailConfigMapper;

    @Resource
    RedisTemplate redisTemplate;

    @Override
    public ResultEntity<PageResponseBean<EmailConfigEntity>> pageGetList(EmailConfigBO page) {
        LambdaQueryWrapper<EmailConfigEntity> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(page.getConfigKey())) {
            queryWrapper.like(EmailConfigEntity::getConfigKey, page.getConfigKey());
        }
        if (StringUtils.isNotEmpty(page.getEmailGroup())) {
            queryWrapper.like(EmailConfigEntity::getEmailGroup, page.getEmailGroup());
        }
        Integer count = emailConfigMapper.selectCount(queryWrapper);
        PageResponseBean<EmailConfigEntity> result = new PageResponseBean<>(page.getPage(), page.getPageSize(), count);
        if (count == null || count < 1) {
            result.setContent(Collections.emptyList());
            return new ResultEntity<>(HttpStatus.OK, "ok", result);
        }
        PageHelper.startPage(page.getPage(), page.getPageSize());
        List<EmailConfigEntity> list = emailConfigMapper.selectList(queryWrapper);
        result.setContent(list);
        return new ResultEntity<>(HttpStatus.OK, "ok", result);
    }

    @Override
    public ResultEntity insertList(List<EmailConfigEntity> list, CurrentUser currentUser) {
        if (CollUtil.isEmpty(list)) {
            return new ResultEntity<>(HttpStatus.OK, "新增完成");
        }
        try {
            emailConfigMapper.insertList(list);
            return new ResultEntity<>(HttpStatus.OK, "新增完成");
        } catch (RuntimeException e) {
            logger.error("[com.xqoo.email]数据库批量新增错误，错误原因：{}，错误信息：{}", e.getClass().getSimpleName(), e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "新增失败");
        }
    }

    public EmailConfigEntity getOneEmailConfigEntityByPrimaryKey(Integer id) {
        LambdaQueryWrapper<EmailConfigEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EmailConfigEntity::getId, id);
        EmailConfigEntity entity = emailConfigMapper.selectOne(queryWrapper);
        if (entity != null) {
            return entity;
        }
        return new EmailConfigEntity();
    }

    @Override
    public List<EmailConfigEntity> getListByGroup(String group) {
        LambdaQueryWrapper<EmailConfigEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EmailConfigEntity::getEmailGroup, group);
        List<EmailConfigEntity> list = emailConfigMapper.selectList(queryWrapper);
        return list;
    }

    @Override
    public ResultEntity removeByKey(String key) {
        LambdaQueryWrapper<EmailConfigEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EmailConfigEntity::getConfigKey, key);
        emailConfigMapper.delete(queryWrapper);
        return new ResultEntity<>(HttpStatus.OK, "删除成功");
    }

    @Override
    public ResultEntity<String> updateEmailConfig(EmailConfigInfoVO vo) {
        if (vo.getId() == null) {
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "编辑失败，丢失资源id");
        }
        EmailConfigEntity entity = new EmailConfigEntity();
        BeanUtils.copyProperties(vo, entity);
        try {
            emailConfigMapper.updateById(entity);
            redisTemplate.opsForHash().put(EmailConstant.EMAIL_SEND_CONFIG,entity.getConfigKey(),entity.getConfigValue());
            return new ResultEntity<>(HttpStatus.OK, "修改完成");
        } catch (Exception e) {
            logger.error("[邮件中心]变更数据源参数发生错误，错误原因：{}，错误信息：{}", e.getClass().getSimpleName(), e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "修改数据时发生错误，请稍后重试");
        }
    }

    @Override
    public ResultEntity<String> addEmailConfig(EmailConfigInfoVO vo) {
        EmailConfigEntity entity = new EmailConfigEntity();
        BeanUtils.copyProperties(vo, entity);
        try {
            emailConfigMapper.insert(entity);
            redisTemplate.opsForHash().put(EmailConstant.EMAIL_SEND_CONFIG,entity.getConfigKey(),entity.getConfigValue());
            return new ResultEntity<>(HttpStatus.OK, "新增完成");
        } catch (Exception e) {
            logger.error("[邮件中心]新增数据源参数发生错误，错误原因：{}，错误信息：{}", e.getClass().getSimpleName(), e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "新增数据时发生错误，请稍后重试");
        }
    }
}
