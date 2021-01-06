package com.xqoo.codegen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.xqoo.codegen.bo.QueryDataSourceBO;
import com.xqoo.codegen.entity.DataSourceInfoEntity;
import com.xqoo.codegen.mapper.DataSourceInfoMapper;
import com.xqoo.codegen.service.DataSourceInfoService;
import com.xqoo.codegen.vo.DataSourceInfoVO;
import com.xqoo.common.constants.SqlQueryConstant;
import com.xqoo.common.core.utils.StringUtils;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageResponseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 数据源表(DataSourceInfo)表服务实现类
 *
 * @author makejava
 * @since 2020-12-23 13:37:41
 */
@Service("dataSourceInfoService")
public class DataSourceInfoServiceImpl extends ServiceImpl<DataSourceInfoMapper, DataSourceInfoEntity> implements DataSourceInfoService {

    private final static Logger logger = LoggerFactory.getLogger(DataSourceInfoServiceImpl.class);

    @Autowired
    private DataSourceInfoMapper dataSourceInfoMapper;

    @Override
    public ResultEntity<PageResponseBean<DataSourceInfoVO>> pageGetDataSourceInfo(QueryDataSourceBO bo) {
        LambdaQueryWrapper<DataSourceInfoEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DataSourceInfoEntity::getDelFlag, SqlQueryConstant.NOT_LOGIC_DEL);
        if(StringUtils.isNotEmpty(bo.getDataBaseHost())){
            queryWrapper.likeRight(DataSourceInfoEntity::getDataBaseHost, bo.getDataBaseHost());
        }
        if(StringUtils.isNotEmpty(bo.getDataBaseShowName())){
            queryWrapper.like(DataSourceInfoEntity::getDataBaseShowName, bo.getDataBaseShowName());
        }
        if(StringUtils.isNotEmpty(bo.getDataBaseType())){
            queryWrapper.eq(DataSourceInfoEntity::getDataBaseType, bo.getDataBaseType());
        }
        Integer count = dataSourceInfoMapper.selectCount(queryWrapper);
        PageResponseBean<DataSourceInfoVO> result = new PageResponseBean<>(bo.getPage(), bo.getPageSize(), count);
        if(count == null || count < 1){
            result.setContent(Collections.emptyList());
            return new ResultEntity<>(HttpStatus.OK, "ok", result);
        }
        PageHelper.startPage(bo.getPage(), bo.getPageSize());
        List<DataSourceInfoEntity> list = dataSourceInfoMapper.selectList(queryWrapper);

        List<DataSourceInfoVO> voList = list.stream().map(item -> {
            DataSourceInfoVO vo = new DataSourceInfoVO();
            BeanUtils.copyProperties(item, vo);
            vo.setDataBasePassword("*********");
            return vo;
        }).collect(Collectors.toList());
        result.setContent(voList);
        return new ResultEntity<>(HttpStatus.OK, "ok", result);
    }

    @Override
    public ResultEntity<String> deleteDataSource(Integer id) {
        DataSourceInfoEntity entity = dataSourceInfoMapper.selectById(id);
        if(entity == null){
            return new ResultEntity<>(HttpStatus.OK, "删除完成");
        }
        entity.setDelFlag(SqlQueryConstant.LOGIC_DEL);
        try {
            dataSourceInfoMapper.updateById(entity);
            return new ResultEntity<>(HttpStatus.OK, "删除完成");
        }catch (Exception e){
            logger.error("[代码生成中心]删除数据源时发生错误，原因:{},信息：{}", e.getClass().getSimpleName(), e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "删除数据时发生错误，请稍后重试");
        }
    }

    @Override
    public ResultEntity<String> updateDataSource(DataSourceInfoVO vo) {
        if(vo.getId() == null){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "编辑失败，丢失资源id");
        }
        DataSourceInfoEntity entity = new DataSourceInfoEntity();
        BeanUtils.copyProperties(vo, entity);
        try {
            dataSourceInfoMapper.updateById(entity);
            return new ResultEntity<>(HttpStatus.OK, "修改完成");
        }catch (Exception e){
            logger.error("[代码生成中心]变更数据源参数发生错误，错误原因：{}，错误信息：{}", e.getClass().getSimpleName(), e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "修改数据时发生错误，请稍后重试");
        }
    }

    @Override
    public ResultEntity<String> addDataSource(DataSourceInfoVO vo) {
        DataSourceInfoEntity entity = new DataSourceInfoEntity();
        BeanUtils.copyProperties(vo, entity);
        try {
            dataSourceInfoMapper.insert(entity);
            return new ResultEntity<>(HttpStatus.OK, "新增完成");
        }catch (Exception e){
            logger.error("[代码生成中心]新增数据源参数发生错误，错误原因：{}，错误信息：{}", e.getClass().getSimpleName(), e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "新增数据时发生错误，请稍后重试");
        }
    }
}
