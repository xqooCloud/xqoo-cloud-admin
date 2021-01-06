package com.xqoo.codegen.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xqoo.codegen.bean.CodeGenCenterProperties;
import com.xqoo.codegen.constants.CodeGeneratorModuleConstant;
import com.xqoo.codegen.dto.DataBasePropertiesDTO;
import com.xqoo.codegen.entity.DataSourceInfoEntity;
import com.xqoo.codegen.entity.TableColumnsEntity;
import com.xqoo.codegen.entity.TableEntity;
import com.xqoo.codegen.pojo.DataBaseTypePropertiesPOJO;
import com.xqoo.codegen.utils.MySqlDataBaseUtil;
import com.xqoo.common.core.config.propetes.xqoo.SecretConfigProperties;
import com.xqoo.common.core.utils.AESUtil;
import com.xqoo.common.core.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("codeGeneratorBaseServiceImpl")
public class CodeGeneratorBaseServiceImpl {

    private final static Logger logger = LoggerFactory.getLogger(CodeGeneratorBaseServiceImpl.class);

    @Autowired
    private CodeGenCenterProperties codeGenCenterProperties;

    @Autowired
    private SecretConfigProperties secretConfigProperties;

    String getDataBaseUrl(String host, String port, String dataBaseName, String properties, String type){
        if(CollUtil.isEmpty(codeGenCenterProperties.getDataBaseType())){
            return null;
        }
        Optional<DataBaseTypePropertiesPOJO> find = codeGenCenterProperties.getDataBaseType().stream().filter(item ->
                item.getType().equals(type)).findFirst();
        StringBuilder sb = new StringBuilder();
        if(find.isPresent()){
            String factPort = StringUtils.isNotEmpty(port) ? port : find.get().getPort();
            String factProperties = StringUtils.isNotEmpty(properties) ? port : find.get().getProperties();
            sb.append(find.get().getPrefix());
            sb.append(host).append(":");
            sb.append(factPort).append("/");
            sb.append(dataBaseName);
            sb.append("?").append(factProperties);
            return sb.toString();
        }
        return null;
    }

    String getClassDriver(String type){
        if(CollUtil.isEmpty(codeGenCenterProperties.getDataBaseType())){
            return null;
        }
        Optional<DataBaseTypePropertiesPOJO> find = codeGenCenterProperties.getDataBaseType().stream().filter(item ->
                item.getType().equals(type)).findFirst();
        if(find.isPresent()){
            return find.get().getDriver();
        }
        return null;
    }

    <T> List<T> convertObjectValue(List<Object> list, Class<T> cls){
        if(CollUtil.isEmpty(list)){
            return Collections.emptyList();
        }
        return list.stream().map(item -> {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.convertValue(item, cls);
        }).collect(Collectors.toList());
    }

    List<TableEntity> getTableList(DataSourceInfoEntity entity){
        String password = decodePassword(entity.getDataBasePassword());
        if(StringUtils.isEmpty(password)){
            return Collections.emptyList();
        }
        DataBasePropertiesDTO dto = this.getDto(entity, password);
        List<TableEntity> tableList = new ArrayList<>();
        // 这里只做mysql的，其他数据库还没处理
        if(CodeGeneratorModuleConstant.DataSourceType.MY_SQL.equals(entity.getDataBaseType())){
            tableList = MySqlDataBaseUtil.getTableNames(dto);
        }
        return tableList;
    }

    List<TableColumnsEntity> getTableColumnList(DataSourceInfoEntity entity, String tableName){
        String password = decodePassword(entity.getDataBasePassword());
        if(StringUtils.isEmpty(password)){
            return Collections.emptyList();
        }
        DataBasePropertiesDTO dto = this.getDto(entity, password);
        dto.setTableName(tableName);
        List<TableColumnsEntity> tableColunmList = new ArrayList<>();
        // 这里只做mysql的，其他数据库还没处理
        if(CodeGeneratorModuleConstant.DataSourceType.MY_SQL.equals(entity.getDataBaseType())){
            tableColunmList = MySqlDataBaseUtil.getColumns(dto);
        }
        return tableColunmList;
    }

    DataBasePropertiesDTO getDto(DataSourceInfoEntity entity, String password){
        DataBasePropertiesDTO dto = new DataBasePropertiesDTO();
        dto.setDataBaseUserName(entity.getDataBaseUserName());
        dto.setDataBasePassword(password);
        dto.setDriverClass(getClassDriver(entity.getDataBaseType()));
        dto.setUrl(getDataBaseUrl(entity.getDataBaseHost(), entity.getDataBasePort(),
                entity.getDataBaseName(), entity.getDataBaseProperties(), entity.getDataBaseType()));
        dto.setDataBaseName(entity.getDataBaseName());
        dto.setDataBaseHost(entity.getDataBaseHost());
        dto.setDataBaseHost(entity.getDataBasePort());
        return dto;
    }

    String decodePassword(String password){
        String bk = null;
        try {
            bk = AESUtil.decode(password, secretConfigProperties.getAesKey());
        }catch (Exception e){
            logger.error("[代码生成中心]aes解析数据源密码错误，密文{}\n", password);
        }
        return bk;
    }
}
