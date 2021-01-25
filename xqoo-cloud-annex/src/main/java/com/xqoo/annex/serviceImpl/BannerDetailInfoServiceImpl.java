package com.xqoo.annex.serviceImpl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xqoo.annex.bo.QueryBannerDetailInfoBO;
import com.xqoo.annex.service.BannerDetailInfoService;
import com.xqoo.annex.entity.BannerDetailInfoEntity;
import com.xqoo.annex.mapper.BannerDetailInfoMapper;
import com.github.pagehelper.PageHelper;
import com.xqoo.annex.vo.BannerDetailVO;
import com.xqoo.common.constants.SqlQueryConstant;
import com.xqoo.common.core.utils.StringUtils;
import com.xqoo.common.page.PageRequestBean;
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
    public ResultEntity<PageResponseBean<BannerDetailInfoEntity>> pageGetList(QueryBannerDetailInfoBO page){
        LambdaQueryWrapper<BannerDetailInfoEntity> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(page.getGroupId())) {
            queryWrapper.like(BannerDetailInfoEntity::getGroupId, page.getGroupId());
        }
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

    @Override
    public ResultEntity<String> deleteBannerDetailInfo(Integer id) {
        LambdaQueryWrapper<BannerDetailInfoEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BannerDetailInfoEntity::getId, id);
        BannerDetailInfoEntity entity = bannerDetailInfoMapper.selectOne(queryWrapper);
        entity.setActiveCode(SqlQueryConstant.LOGIC_DEL);
        bannerDetailInfoMapper.update(entity, queryWrapper);
        return new ResultEntity<>(HttpStatus.OK, "删除成功");
    }

    @Override
    public ResultEntity<String> addBannerDetailInfo(BannerDetailVO vo) {
        BannerDetailInfoEntity entity = new BannerDetailInfoEntity();
        BeanUtils.copyProperties(vo, entity);
        try {
            bannerDetailInfoMapper.insert(entity);
            return new ResultEntity<>(HttpStatus.OK, "新增完成");
        } catch (Exception e) {
            logger.error("[轮播详情]新增数据源参数发生错误，错误原因：{}，错误信息：{}", e.getClass().getSimpleName(), e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "新增数据时发生错误，请稍后重试");
        }
    }

    @Override
    public ResultEntity<String> updateBannerDetailInfo(BannerDetailVO vo) {
        if (vo.getId() == null) {
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "编辑失败，丢失资源id");
        }
        BannerDetailInfoEntity entity = new BannerDetailInfoEntity();
        BeanUtils.copyProperties(vo, entity);
        LambdaQueryWrapper<BannerDetailInfoEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BannerDetailInfoEntity::getId, entity.getId());
        try {
            bannerDetailInfoMapper.update(entity,queryWrapper);
            return new ResultEntity<>(HttpStatus.OK, "修改完成");
        } catch (Exception e) {
            logger.error("[轮播详情]变更数据源参数发生错误，错误原因：{}，错误信息：{}", e.getClass().getSimpleName(), e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "修改数据时发生错误，请稍后重试");
        }
    }
}
