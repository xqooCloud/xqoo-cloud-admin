package com.xqoo.codegen.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.google.common.base.Splitter;
import com.google.common.collect.Ordering;
import com.xqoo.codegen.bean.CodeGenCenterProperties;
import com.xqoo.codegen.bo.EntityGenCodeBo;
import com.xqoo.codegen.bo.MicroServiceGeneratorBO;
import com.xqoo.codegen.bo.TableGeneratorBO;
import com.xqoo.codegen.constants.CodeGeneratorModuleConstant;
import com.xqoo.codegen.dto.TemplateHandleDTO;
import com.xqoo.codegen.entity.DataSourceInfoEntity;
import com.xqoo.codegen.entity.TableColumnsEntity;
import com.xqoo.codegen.entity.TableEntity;
import com.xqoo.codegen.pojo.TemplateDataPOJO;
import com.xqoo.codegen.service.CodeGenCenterService;
import com.xqoo.codegen.service.DataSourceInfoService;
import com.xqoo.codegen.utils.*;
import com.xqoo.codegen.vo.GeneratorCodeVO;
import com.xqoo.codegen.vo.IndexTreeVO;
import com.xqoo.common.core.utils.StringUtils;
import com.xqoo.common.entity.ResultEntity;
import org.apache.velocity.VelocityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service("codeGenCenterService")
public class CodeGenCenterServiceImpl extends CodeGeneratorBaseServiceImpl implements CodeGenCenterService {

    private final static Logger logger = LoggerFactory.getLogger(CodeGenCenterServiceImpl.class);

    @Value("${nacos.public.namespace}")
    private String defaultNacosNameSpace;

    @Autowired
    private CodeGenCenterProperties codeGenCenterProperties;

    @Autowired
    private DataSourceInfoService dataSourceInfoService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;



    @Override
    public ResultEntity<List<TableEntity>> getDataSourceTables(Integer id) {
        String redisKey = CodeGeneratorModuleConstant.TABLE_LIST_PREFIX + id.toString();
        List<TableEntity> tableList;
        Long size = redisTemplate.opsForList()
                .size(redisKey);
        if(size != null && size > 0){
            tableList = super.convertObjectValue(redisTemplate.opsForList().range(redisKey, 0, -1), TableEntity.class);
            return new ResultEntity<>(HttpStatus.OK, "ok", tableList);
        }
        DataSourceInfoEntity entity = dataSourceInfoService.getById(id);
        if(entity == null){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE,
                    "获取数据库表信息失败，为找到数据源信息", Collections.emptyList());
        }
        tableList = super.getTableList(entity);
        if(CollUtil.isEmpty(tableList)){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "获取数据库表信息失败,表信息列表为空", Collections.emptyList());
        }
        tableList.forEach(item -> {
            redisTemplate.opsForList().rightPush(redisKey, item);
        });
        redisTemplate.expire(redisKey, 1, TimeUnit.DAYS);
        return new ResultEntity<>(HttpStatus.OK, "ok", tableList);
    }

    @Override
    public ResultEntity<String> removeCacheTables(Integer id) {
        String redisKey = CodeGeneratorModuleConstant.TABLE_LIST_PREFIX + id.toString();
        Long size = redisTemplate.opsForList()
                .size(redisKey);
        if(size != null && size > 0){
            redisTemplate.delete(redisKey);
            return new ResultEntity<>(HttpStatus.OK, "ok", "ok");
        }
        return new ResultEntity<>(HttpStatus.OK, "ok", "ok");
    }

    @Override
    public ResultEntity<List<TemplateDataPOJO>> getTemplateInfo(String type) {
        if(CollUtil.isEmpty(codeGenCenterProperties.getTemplate())){
            return new ResultEntity<>(HttpStatus.OK, "ok", Collections.emptyList());
        }
        List<TemplateDataPOJO> list = codeGenCenterProperties.getTemplate().stream()
                .map(item -> {
                    List<String> typeArr = Splitter.on("|").trimResults().splitToList(item.getType());
                    if(typeArr.contains(type) || typeArr.contains("any")){
                        return item;
                    }
                    return null;
                }).filter(Objects::nonNull).collect(Collectors.toList());
        return new ResultEntity<>(HttpStatus.OK, "ok", list);
    }

    @Override
    public List<TableColumnsEntity> getTableColumns(String tableName, Integer id) {
        String redisKey = CodeGeneratorModuleConstant.TABLE_COLUMN_LIST_PREFIX + tableName + ":" + id.toString();
        List<TableColumnsEntity> tableColumnList;
        Long size = redisTemplate.opsForList()
                .size(redisKey);
        if(size != null && size > 0){
            tableColumnList = super.convertObjectValue(redisTemplate.opsForList().range(redisKey, 0, -1), TableColumnsEntity.class);
            return tableColumnList;
        }
        DataSourceInfoEntity entity = dataSourceInfoService.getById(id);
        if(entity == null){
            return Collections.emptyList();
        }
        tableColumnList = super.getTableColumnList(entity, tableName);
        if(CollUtil.isEmpty(tableColumnList)){
            return Collections.emptyList();
        }
        tableColumnList.forEach(item -> {
            redisTemplate.opsForList().rightPush(redisKey, item);
        });
        redisTemplate.expire(redisKey, 1, TimeUnit.DAYS);
        return tableColumnList;
    }

    @Override
    public ResultEntity<String> removeCacheColumns(String tableName, Integer id) {
        String redisKey = CodeGeneratorModuleConstant.TABLE_COLUMN_LIST_PREFIX + tableName + ":" + id.toString();
        Long size = redisTemplate.opsForList()
                .size(redisKey);
        if(size != null && size > 0){
            redisTemplate.delete(redisKey);
            return new ResultEntity<>(HttpStatus.OK, "ok", "ok");
        }
        return new ResultEntity<>(HttpStatus.OK, "ok", "ok");
    }

    @Override
    public ResultEntity<GeneratorCodeVO> tableGeneratorCode(TableGeneratorBO bo) {
        VelocityInit.initVelocity();
        List<String> templatesPath = VelocityUtils.getTemplateList(bo.getTemplateNameArr(), codeGenCenterProperties.getTemplate());
        if(CollUtil.isEmpty(templatesPath)){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "生成代码失败，未找到相应模板信息");
        }
        VelocityContext context = TableGenUtils.getTableGenCodeContext(bo, codeGenCenterProperties.getDefaultAuthor());
        List<TemplateHandleDTO> templateHandleList = templatesPath.stream().map(item -> {
            TemplateHandleDTO dto = new TemplateHandleDTO();
            dto.setTemplatePath(item);
            dto.setTemplateFilePath(TableGenUtils.getGeneratorFileName(item, context.get("className").toString()));
            return dto;
        }).collect(Collectors.toList());
        GeneratorCodeVO vo;
        if(bo.getNeedDownload()){
            vo = VelocityUtils.generatorCodeZip(templateHandleList, context,
                    codeGenCenterProperties.getDownloadBasePath(), context.get("className").toString());
        }else{
            vo = VelocityUtils.generatorCode(templateHandleList, context, null, bo.getNeedDownload());
        }
        if(CollUtil.isEmpty(vo.getPreviewCodeList())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "生成代码失败，未获得相应文件信息");
        }
        return new ResultEntity<>(HttpStatus.OK, "ok", vo);
    }

    @Override
    public ResultEntity<GeneratorCodeVO> microServiceGeneratorCode(MicroServiceGeneratorBO bo) {
        VelocityInit.initVelocity();
        if(StringUtils.isEmpty(bo.getNacosNameSpace())){
            bo.setNacosNameSpace(defaultNacosNameSpace);
        }
        List<String> templatesPath = VelocityUtils.getTemplateList(bo.getTemplateNameArr(), codeGenCenterProperties.getTemplate());
        if(CollUtil.isEmpty(templatesPath)){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "生成代码失败，未找到相应模板信息");
        }
        VelocityContext context = MicroServiceGenUtils.getMicroServiceGenCodeContext(bo);
        List<TemplateHandleDTO> templateHandleList = templatesPath.stream().map(item -> {
            TemplateHandleDTO dto = new TemplateHandleDTO();
            dto.setTemplatePath(item);
            dto.setTemplateFilePath(MicroServiceGenUtils.getGeneratorFileName(item, bo.getModuleName()));
            return dto;
        }).collect(Collectors.toList());
        GeneratorCodeVO vo;
        if(bo.getNeedDownload()){
            vo = VelocityUtils.generatorCodeZip(templateHandleList, context, codeGenCenterProperties.getDownloadBasePath(), bo.getModuleName());
        }else{
            vo = VelocityUtils.generatorCode(templateHandleList, context, null, bo.getNeedDownload());
        }
        if(CollUtil.isEmpty(vo.getPreviewCodeList())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "生成代码失败，未获得相应文件信息");
        }
        List<IndexTreeVO> treeList = MicroServiceGenUtils.getFileIndexTree(templatesPath, bo.getModuleName(), vo.getPreviewCodeList());
        treeList = sortByIndexTree(treeList);
        vo.setIndexTree(treeList);
        return new ResultEntity<>(HttpStatus.OK, "ok", vo);
    }

    @Override
    public ResultEntity<GeneratorCodeVO> entityGeneratorCode(EntityGenCodeBo bo) {
        VelocityInit.initVelocity();
        List<String> templatesPath = VelocityUtils.getTemplateList(bo.getTemplateNameArr(), codeGenCenterProperties.getTemplate());
        if(CollUtil.isEmpty(templatesPath)){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "生成代码失败，未找到相应模板信息");
        }
        VelocityContext context = EntityGenUtils.getEntityGenCodeContext(bo, codeGenCenterProperties.getDefaultAuthor());
        List<TemplateHandleDTO> templateHandleList = templatesPath.stream().map(item -> {
            TemplateHandleDTO dto = new TemplateHandleDTO();
            dto.setTemplatePath(item);
            dto.setTemplateFilePath(bo.getClassName() + ".java");
            return dto;
        }).collect(Collectors.toList());
        GeneratorCodeVO vo = VelocityUtils.generatorCodeZip(templateHandleList, context, codeGenCenterProperties.getDownloadBasePath(), bo.getClassName());
        if(CollUtil.isEmpty(vo.getPreviewCodeList())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "生成代码失败，未获得相应文件信息");
        }
        return new ResultEntity<>(HttpStatus.OK, "ok", vo);
    }

    private List<IndexTreeVO> sortByIndexTree(List<IndexTreeVO> treeList){
        treeList.forEach(item -> {
            if (CollUtil.isNotEmpty(item.getChildren())){
                item.setChildren(sortByIndexTree(item.getChildren()));
            }
        });
        treeList = new Ordering<IndexTreeVO>() {
            @Override
            public int compare(IndexTreeVO left, IndexTreeVO right) {
                return left.getKey().compareTo(right.getKey());
            }
        }.immutableSortedCopy(treeList);
        return new Ordering<IndexTreeVO>(){
            @Override
            public int compare(IndexTreeVO left, IndexTreeVO right) {
                if(CollUtil.isEmpty(left.getChildren()) && CollUtil.isNotEmpty(right.getChildren())){
                    return 1;
                }else if(CollUtil.isEmpty(left.getChildren()) && CollUtil.isEmpty(right.getChildren())){
                    return 0;
                }else{
                    return -1;
                }
            }
        }.immutableSortedCopy(treeList);
    }
}
