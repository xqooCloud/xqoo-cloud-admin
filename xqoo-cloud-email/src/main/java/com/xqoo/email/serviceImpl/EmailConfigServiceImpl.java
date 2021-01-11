package com.xqoo.email.serviceImpl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xqoo.email.service.EmailConfigService;
import com.xqoo.email.entity.EmailConfigEntity;
import com.xqoo.email.mapper.EmailConfigMapper;
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
 * 数据源表(email_config)表服务实现类
 *
 * @author xqoo-code-gen
 * @date 2021-01-11 16:52:01
 */
@Service("EmailConfigService")
public class EmailConfigServiceImpl extends ServiceImpl<EmailConfigMapper, EmailConfigEntity> implements EmailConfigService {

    private final static Logger logger = LoggerFactory.getLogger(EmailConfigServiceImpl.class);


    @Autowired
    private EmailConfigMapper EmailConfigMapper;

    @Override
    public ResultEntity<PageResponseBean<EmailConfigEntity>> pageGetList(PageRequestBean page){
        LambdaQueryWrapper<EmailConfigEntity> queryWrapper = new LambdaQueryWrapper<>();
        Integer count = EmailConfigMapper.selectCount(queryWrapper);
        PageResponseBean<EmailConfigEntity> result = new PageResponseBean<>(page.getPage(), page.getPageSize(), count);
        if(count == null || count < 1){
            result.setContent(Collections.emptyList());
            return new ResultEntity<>(HttpStatus.OK, "ok", result);
        }
        PageHelper.startPage(page.getPage(), page.getPageSize());
        List<EmailConfigEntity> list = EmailConfigMapper.selectList(queryWrapper);
        result.setContent(list);
        return new ResultEntity<>(HttpStatus.OK, "ok", result);
    }

    @Override
    public ResultEntity insertList(List<EmailConfigEntity> list, CurrentUser currentUser){
        if(CollUtil.isEmpty(list)){
            return new ResultEntity<>(HttpStatus.OK, "新增完成");
        }
        try{
            EmailConfigMapper.insertList(list);
            return new ResultEntity<>(HttpStatus.OK, "新增完成");
        }catch (RuntimeException e){
            logger.error("[com.xqoo.email]数据库批量新增错误，错误原因：{}，错误信息：{}", e.getClass().getSimpleName(), e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "新增失败");
        }
    }

    public EmailConfigEntity getOneEmailConfigEntityByPrimaryKey(Integer id){
        LambdaQueryWrapper<EmailConfigEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EmailConfigEntity::getId, id);
        EmailConfigEntity entity = EmailConfigMapper.selectOne(queryWrapper);
        if(entity != null){
            return entity;
        }
        return new EmailConfigEntity();
    }
}
