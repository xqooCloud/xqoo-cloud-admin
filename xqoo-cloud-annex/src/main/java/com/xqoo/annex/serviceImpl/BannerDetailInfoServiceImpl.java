package com.xqoo.annex.serviceImpl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xqoo.annex.service.BannerDetailInfoService;
import com.xqoo.annex.entity.BannerDetailInfoEntity;
import com.xqoo.annex.mapper.BannerDetailInfoMapper;
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
 * 数据源表(banner_detail_info)表服务实现类
 *
 * @author xqoo-code-gen
 * @date 2021-01-14 15:31:34
 */
@Service("BannerDetailInfoService")
public class BannerDetailInfoServiceImpl extends ServiceImpl<BannerDetailInfoMapper, BannerDetailInfoEntity> implements BannerDetailInfoService {

    private final static Logger logger = LoggerFactory.getLogger(BannerDetailInfoServiceImpl.class);


    @Autowired
    private BannerDetailInfoMapper bannerDetailInfoMapper;

    @Override
    public ResultEntity<PageResponseBean<BannerDetailInfoEntity>> pageGetList(PageRequestBean page){
        LambdaQueryWrapper<BannerDetailInfoEntity> queryWrapper = new LambdaQueryWrapper<>();
        Integer count = bannerDetailInfoMapper.selectCount(queryWrapper);
        PageResponseBean<BannerDetailInfoEntity> result = new PageResponseBean<>(page.getPage(), page.getPageSize(), count);
        if(count == null || count < 1){
            result.setContent(Collections.emptyList());
            return new ResultEntity<>(HttpStatus.OK, "ok", result);
        }
        PageHelper.startPage(page.getPage(), page.getPageSize());
        List<BannerDetailInfoEntity> list = bannerDetailInfoMapper.selectList(queryWrapper);
        result.setContent(list);
        return new ResultEntity<>(HttpStatus.OK, "ok", result);
    }

    @Override
    public ResultEntity insertList(List<BannerDetailInfoEntity> list, CurrentUser currentUser){
        if(CollUtil.isEmpty(list)){
            return new ResultEntity<>(HttpStatus.OK, "新增完成");
        }
        list.forEach(item -> {
            item.setCreateBy(currentUser.getUserId());
            item.setCreateDate(new Date());
        });
        try{
            bannerDetailInfoMapper.insertList(list);
            return new ResultEntity<>(HttpStatus.OK, "新增完成");
        }catch (RuntimeException e){
            logger.error("[com.xqoo.annex]数据库批量新增错误，错误原因：{}，错误信息：{}", e.getClass().getSimpleName(), e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "新增失败");
        }
    }

    public BannerDetailInfoEntity getOneBannerDetailInfoEntityByPrimaryKey(Integer id){
        LambdaQueryWrapper<BannerDetailInfoEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BannerDetailInfoEntity::getId, id);
        BannerDetailInfoEntity entity = bannerDetailInfoMapper.selectOne(queryWrapper);
        if(entity != null){
            return entity;
        }
        return new BannerDetailInfoEntity();
    }
}
