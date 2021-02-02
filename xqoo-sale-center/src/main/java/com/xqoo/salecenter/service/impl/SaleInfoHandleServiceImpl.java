package com.xqoo.salecenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageRequestBean;
import com.xqoo.common.page.PageResponseBean;
import com.xqoo.salecenter.entity.GoodsPictureInfoEntity;
import com.xqoo.salecenter.entity.SaleGoodsInfoEntity;
import com.xqoo.salecenter.enums.SaleGoodsStatusEnum;
import com.xqoo.salecenter.mapper.GoodsPictureInfoMapper;
import com.xqoo.salecenter.mapper.SaleGoodsInfoMapper;
import com.xqoo.salecenter.service.SaleInfoHandleService;
import com.xqoo.salecenter.vo.SaleGoodsInfoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gaoyang
 */
@Service("saleInfoHandleService")
public class SaleInfoHandleServiceImpl implements SaleInfoHandleService {

    @Autowired
    private SaleGoodsInfoMapper saleGoodsInfoMapper;

    @Autowired
    private GoodsPictureInfoMapper goodsPictureInfoMapper;

    @Override
    public ResultEntity<PageResponseBean<SaleGoodsInfoVO>> getSaleGoodsPageInfo(PageRequestBean page) {
        LambdaQueryWrapper<SaleGoodsInfoEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SaleGoodsInfoEntity::getStatus, SaleGoodsStatusEnum.PUBLISH.getKey());
        Integer count = saleGoodsInfoMapper.selectCount(queryWrapper);
        PageResponseBean<SaleGoodsInfoVO> result = new PageResponseBean<>(page.getPage(), page.getPageSize(), count);
        if(count == null || count < 1){
            result.setContent(Collections.emptyList());
            return new ResultEntity<>(HttpStatus.OK, "ok", result);
        }
        PageHelper.startPage(page.getPage(), page.getPageSize());
        List<SaleGoodsInfoEntity> list = saleGoodsInfoMapper.selectList(queryWrapper);
        List<String> goodsIds = list.stream().map(SaleGoodsInfoEntity::getGoodsId).collect(Collectors.toList());
        List<GoodsPictureInfoEntity> picList = goodsPictureInfoMapper.selectList(new LambdaQueryWrapper<GoodsPictureInfoEntity>()
                .in(GoodsPictureInfoEntity::getParentGoodsId, goodsIds));

        List<SaleGoodsInfoVO> voList = list.stream().map(item -> {
            SaleGoodsInfoVO vo = new SaleGoodsInfoVO();
            BeanUtils.copyProperties(item, vo);
            vo.setPictureList(picList.stream().filter(picItem -> picItem.getParentGoodsId()
                    .equals(item.getGoodsId())).sorted(Comparator.comparing(GoodsPictureInfoEntity::getSortNo))
                    .collect(Collectors.toList()));
            return vo;
        }).collect(Collectors.toList());
        result.setContent(voList);
        return new ResultEntity<>(HttpStatus.OK, "ok", result);
    }
}
