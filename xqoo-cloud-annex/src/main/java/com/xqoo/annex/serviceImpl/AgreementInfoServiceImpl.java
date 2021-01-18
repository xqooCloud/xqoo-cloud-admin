package com.xqoo.annex.serviceImpl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xqoo.annex.bo.QueryAgreementInfoBO;
import com.xqoo.annex.entity.AgreementInfoEntity;
import com.xqoo.annex.mapper.AgreementInfoMapper;
import com.github.pagehelper.PageHelper;
import com.xqoo.annex.service.AgreementInfoService;
import com.xqoo.annex.vo.AgreementInfoVO;
import com.xqoo.common.core.utils.StringUtils;
import com.xqoo.common.page.PageResponseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.entity.ResultEntity;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 数据源表(agreement_info)表服务实现类
 *
 * @author xqoo-code-gen
 * @date 2021-01-14 14:56:53
 */
@Service("AgreementInfoService")
public class AgreementInfoServiceImpl extends ServiceImpl<AgreementInfoMapper, AgreementInfoEntity> implements AgreementInfoService {

    private final static Logger logger = LoggerFactory.getLogger(AgreementInfoServiceImpl.class);


    @Autowired
    private AgreementInfoMapper agreementInfoMapper;

    @Override
    public ResultEntity<PageResponseBean<AgreementInfoEntity>> pageGetList(QueryAgreementInfoBO page) {
        LambdaQueryWrapper<AgreementInfoEntity> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(page.getAgreementKey())) {
            queryWrapper.like(AgreementInfoEntity::getAgreementKey, page.getAgreementKey());
        }
        Integer count = agreementInfoMapper.selectCount(queryWrapper);
        PageResponseBean<AgreementInfoEntity> result = new PageResponseBean<>(page.getPage(), page.getPageSize(), count);
        if (count == null || count < 1) {
            result.setContent(Collections.emptyList());
            return new ResultEntity<>(HttpStatus.OK, "ok", result);
        }
        PageHelper.startPage(page.getPage(), page.getPageSize());
        List<AgreementInfoEntity> list = agreementInfoMapper.selectList(queryWrapper);
        result.setContent(list);
        return new ResultEntity<>(HttpStatus.OK, "ok", result);
    }

    @Override
    public ResultEntity insertList(List<AgreementInfoEntity> list, CurrentUser currentUser) {
        if (CollUtil.isEmpty(list)) {
            return new ResultEntity<>(HttpStatus.OK, "新增完成");
        }
        list.forEach(item -> {
            item.setCreateBy(currentUser.getUserId());
            item.setCreateDate(new Date());
        });
        try {
            agreementInfoMapper.insertList(list);
            return new ResultEntity<>(HttpStatus.OK, "新增完成");
        } catch (RuntimeException e) {
            logger.error("[com.xqoo.annex]数据库批量新增错误，错误原因：{}，错误信息：{}", e.getClass().getSimpleName(), e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "新增失败");
        }
    }

    public AgreementInfoEntity getOneAgreementInfoEntityByPrimaryKey(String agreementKey) {
        LambdaQueryWrapper<AgreementInfoEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AgreementInfoEntity::getAgreementKey, agreementKey);
        AgreementInfoEntity entity = agreementInfoMapper.selectOne(queryWrapper);
        return entity;
    }


    @Override
    public ResultEntity<String> addAgreementInfo(AgreementInfoVO vo) {
        AgreementInfoEntity entity = new AgreementInfoEntity();
        BeanUtils.copyProperties(vo, entity);
        try {
            agreementInfoMapper.insert(entity);
            return new ResultEntity<>(HttpStatus.OK, "新增完成");
        } catch (Exception e) {
            logger.error("[协议]新增数据源参数发生错误，错误原因：{}，错误信息：{}", e.getClass().getSimpleName(), e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "新增数据时发生错误，请稍后重试");
        }
    }

    @Override
    public ResultEntity<String> updateAgreementInfo(AgreementInfoVO vo) {
        if (vo.getAgreementKey() == null) {
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "编辑失败，丢失资源id");
        }
        AgreementInfoEntity entity = new AgreementInfoEntity();
        BeanUtils.copyProperties(vo, entity);
        LambdaQueryWrapper<AgreementInfoEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AgreementInfoEntity::getAgreementKey, entity.getAgreementKey());
        try {
            agreementInfoMapper.update(entity,queryWrapper);
            return new ResultEntity<>(HttpStatus.OK, "修改完成");
        } catch (Exception e) {
            logger.error("[协议]变更数据源参数发生错误，错误原因：{}，错误信息：{}", e.getClass().getSimpleName(), e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "修改数据时发生错误，请稍后重试");
        }
    }

    @Override
    public ResultEntity<String> deleteAgreementInfo(String key) {
        LambdaQueryWrapper<AgreementInfoEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AgreementInfoEntity::getAgreementKey, key);
        try {
            int i = agreementInfoMapper.delete(queryWrapper);
            if (i > 0) {
                return new ResultEntity<>(HttpStatus.OK, "删除完成");
            }
            return new ResultEntity<>(HttpStatus.OK, "删除完成");
        } catch (Exception e) {
            logger.error("[协议]删除数据源时发生错误，原因:{},信息：{}", e.getClass().getSimpleName(), e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "删除数据时发生错误，请稍后重试");
        }
    }
}
