package com.xqoo.annex.serviceImpl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xqoo.annex.service.BannerGroupInfoService;
import com.xqoo.annex.entity.BannerGroupInfoEntity;
import com.xqoo.annex.mapper.BannerGroupInfoMapper;
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
 * 数据源表(banner_group_info)表服务实现类
 *
 * @author xqoo-code-gen
 * @date 2021-01-14 15:45:34
 */
@Service("BannerGroupInfoService")
public class BannerGroupInfoServiceImpl extends ServiceImpl<BannerGroupInfoMapper, BannerGroupInfoEntity> implements BannerGroupInfoService {

    private final static Logger logger = LoggerFactory.getLogger(BannerGroupInfoServiceImpl.class);


    @Autowired
    private BannerGroupInfoMapper BannerGroupInfoMapper;

    @Override
    public ResultEntity<PageResponseBean<BannerGroupInfoEntity>> pageGetList(PageRequestBean page){
        LambdaQueryWrapper<BannerGroupInfoEntity> queryWrapper = new LambdaQueryWrapper<>();
        Integer count = BannerGroupInfoMapper.selectCount(queryWrapper);
        PageResponseBean<BannerGroupInfoEntity> result = new PageResponseBean<>(page.getPage(), page.getPageSize(), count);
        if(count == null || count < 1){
            result.setContent(Collections.emptyList());
            return new ResultEntity<>(HttpStatus.OK, "ok", result);
        }
        PageHelper.startPage(page.getPage(), page.getPageSize());
        List<BannerGroupInfoEntity> list = BannerGroupInfoMapper.selectList(queryWrapper);
        result.setContent(list);
        return new ResultEntity<>(HttpStatus.OK, "ok", result);
    }

    @Override
    public ResultEntity insertList(List<BannerGroupInfoEntity> list, CurrentUser currentUser){
        if(CollUtil.isEmpty(list)){
            return new ResultEntity<>(HttpStatus.OK, "新增完成");
        }
        list.forEach(item -> {
            item.setCreateBy(currentUser.getUserId());
            item.setCreateDate(new Date());
        });
        try{
            BannerGroupInfoMapper.insertList(list);
            return new ResultEntity<>(HttpStatus.OK, "新增完成");
        }catch (RuntimeException e){
            logger.error("[com.xqoo.annex]数据库批量新增错误，错误原因：{}，错误信息：{}", e.getClass().getSimpleName(), e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "新增失败");
        }
    }

    public BannerGroupInfoEntity getOneBannerGroupInfoEntityByPrimaryKey(Integer id){
        LambdaQueryWrapper<BannerGroupInfoEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BannerGroupInfoEntity::getId, id);
        BannerGroupInfoEntity entity = BannerGroupInfoMapper.selectOne(queryWrapper);
        if(entity != null){
            return entity;
        }
        return new BannerGroupInfoEntity();
    }
}
