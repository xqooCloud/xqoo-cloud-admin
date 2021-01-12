package com.xqoo.email.serviceImpl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xqoo.email.service.EmailTemplateService;
import com.xqoo.email.entity.EmailTemplateEntity;
import com.xqoo.email.mapper.EmailTemplateMapper;
import com.github.pagehelper.PageHelper;
import com.xqoo.common.constants.SqlQueryConstant;
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
 * 数据源表(email_template)表服务实现类
 *
 * @author xqoo-code-gen
 * @date 2021-01-11 17:06:49
 */
@Service("EmailTemplateService")
public class EmailTemplateServiceImpl extends ServiceImpl<EmailTemplateMapper, EmailTemplateEntity> implements EmailTemplateService {

    private final static Logger logger = LoggerFactory.getLogger(EmailTemplateServiceImpl.class);


    @Autowired
    private EmailTemplateMapper EmailTemplateMapper;

    @Override
    public ResultEntity<PageResponseBean<EmailTemplateEntity>> pageGetList(PageRequestBean page){
        LambdaQueryWrapper<EmailTemplateEntity> queryWrapper = new LambdaQueryWrapper<>();
         queryWrapper.eq(EmailTemplateEntity::getDelFlag, SqlQueryConstant.NOT_LOGIC_DEL);
        Integer count = EmailTemplateMapper.selectCount(queryWrapper);
        PageResponseBean<EmailTemplateEntity> result = new PageResponseBean<>(page.getPage(), page.getPageSize(), count);
        if(count == null || count < 1){
            result.setContent(Collections.emptyList());
            return new ResultEntity<>(HttpStatus.OK, "ok", result);
        }
        PageHelper.startPage(page.getPage(), page.getPageSize());
        List<EmailTemplateEntity> list = EmailTemplateMapper.selectList(queryWrapper);
        result.setContent(list);
        return new ResultEntity<>(HttpStatus.OK, "ok", result);
    }

    @Override
    public ResultEntity insertList(List<EmailTemplateEntity> list, CurrentUser currentUser){
        if(CollUtil.isEmpty(list)){
            return new ResultEntity<>(HttpStatus.OK, "新增完成");
        }
        list.forEach(item -> {
            item.setCreateBy(currentUser.getUserId());
            item.setCreateDate(new Date());
        });
        try{
            EmailTemplateMapper.insertList(list);
            return new ResultEntity<>(HttpStatus.OK, "新增完成");
        }catch (RuntimeException e){
            logger.error("[com.xqoo.email]数据库批量新增错误，错误原因：{}，错误信息：{}", e.getClass().getSimpleName(), e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "新增失败");
        }
    }

    public EmailTemplateEntity getOneEmailTemplateEntityByPrimaryKey(Integer id){
        LambdaQueryWrapper<EmailTemplateEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EmailTemplateEntity::getId, id);
        queryWrapper.eq(EmailTemplateEntity::getDelFlag, SqlQueryConstant.NOT_LOGIC_DEL);
        EmailTemplateEntity entity = EmailTemplateMapper.selectOne(queryWrapper);
        if(entity != null){
            return entity;
        }
        return new EmailTemplateEntity();
    }
}
