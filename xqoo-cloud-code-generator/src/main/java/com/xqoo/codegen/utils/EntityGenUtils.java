package com.xqoo.codegen.utils;

import cn.hutool.core.date.DateUtil;
import com.xqoo.codegen.bo.EntityGenCodeBo;
import com.xqoo.codegen.entity.JavaTableColumnsEntity;
import com.xqoo.common.core.utils.StringUtils;
import org.apache.velocity.VelocityContext;
import org.springframework.beans.BeanUtils;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class EntityGenUtils {

    /**
     * 获得表生成java文件context
     */
    public static VelocityContext getEntityGenCodeContext(EntityGenCodeBo bo, String author){
        String className = bo.getClassName();
        String classComment = bo.getClassComment();
        String dateTime = DateUtil.now();
        List<JavaTableColumnsEntity> columns = getColumns(bo.getProperties());
        HashSet<String> importList = VelocityUtils.getImportList(columns);
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("className", StringUtils.firstUpperCase(className));
        velocityContext.put("classComment", classComment);
        velocityContext.put("author", StringUtils.isEmpty(author) ? "code-gen" : author);
        velocityContext.put("genDate", dateTime);
        velocityContext.put("columns", columns);
        velocityContext.put("importList", importList);
        velocityContext.put("serialVersionUid", VelocityUtils.getSerUid());
        return velocityContext;
    }

    public static List<JavaTableColumnsEntity> getColumns(List<JavaTableColumnsEntity> columns){
        return columns.stream().map(item -> {
            JavaTableColumnsEntity entity = new JavaTableColumnsEntity();
            BeanUtils.copyProperties(item, entity);
            entity.setJavaFieldName(StringUtils.firstLowerCase(StringUtils.convertToCamelCase(item.getJavaFieldName())));
            entity.setJavaAttrName(StringUtils.firstUpperCase(entity.getJavaFieldName()));
            if(StringUtils.isEmpty(item.getComment())){
                entity.setComment("");
            }
            return entity;
        }).collect(Collectors.toList());
    }
}
