package com.xqoo.codegen.utils;

import cn.hutool.core.date.DateUtil;
import com.google.common.base.Splitter;
import com.xqoo.codegen.bo.TableGeneratorBO;
import com.xqoo.codegen.entity.JavaTableColumnsEntity;
import com.xqoo.codegen.entity.TableColumnsEntity;
import com.xqoo.codegen.vo.PreviewCodeVO;
import com.xqoo.common.core.utils.StringUtils;
import org.apache.velocity.VelocityContext;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;


public class TableGenUtils {

    /**
     * 获得表生成java文件context
     */
    public static VelocityContext getTableGenCodeContext(TableGeneratorBO bo, String author){
        String handleName = bo.getHandleName();
        String className = StringUtils.isEmpty(bo.getClassName()) ? StringUtils.convertToCamelCase(bo.getTableName())
                : bo.getClassName();
        String classNameFirstLower = StringUtils.isEmpty(bo.getClassName()) ? StringUtils.toCamelCase(bo.getTableName())
                : bo.getClassName();
        String packageName = removeLastPoint(bo.getPackageName());
        String dateTime = DateUtil.now();
        String tableComment = StringUtils.isEmpty(bo.getTableItem().getComment()) ? bo.getTableName()
                : bo.getTableItem().getComment();
        String schemaName = bo.getTableItem().getSchemeName();
        List<JavaTableColumnsEntity> columns = getJavaColumns(bo.getColumnDataArr());
        HashSet<String> importList = VelocityUtils.getImportList(columns);

        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("tableName", bo.getTableName());
        velocityContext.put("handleName", handleName);
        velocityContext.put("className", className);
        velocityContext.put("classNameFirstLower", classNameFirstLower);
        velocityContext.put("packageName", packageName);
        velocityContext.put("dateTime", dateTime);
        velocityContext.put("tableComment", tableComment);
        velocityContext.put("schemaName", schemaName);
        velocityContext.put("author", StringUtils.isEmpty(author) ? "code-gen" : author);
        velocityContext.put("columns", columns);
        velocityContext.put("importList", importList);
        velocityContext.put("serialVersionUid", VelocityUtils.getSerUid());

        return velocityContext;
    }


    public static String getGeneratorFileName(String template, String className){
        PreviewCodeVO vo = VelocityUtils.getPreviewVo(template, null);
        List<String> nameArr = Splitter.on(".").trimResults().splitToList(vo.getFileName());
        String fileMidFolder = nameArr.size() > 0 ? nameArr.get(0) : vo.getFileType();
        return StringUtils.format("{}/{}/{}{}", vo.getFileType(), fileMidFolder, className,
                StringUtils.firstUpperCase(vo.getFileName()));
    }

    private static List<JavaTableColumnsEntity> getJavaColumns(List<TableColumnsEntity> list){
        return list.stream().map(item -> {
            JavaTableColumnsEntity bean = new JavaTableColumnsEntity();
            BeanUtils.copyProperties(item, bean);
            bean.setJavaFieldType(handleJavaType(item.getColumnsTypeName()));
            bean.setJavaAttrName(StringUtils.convertToCamelCase(item.getColumnName()));
            bean.setJavaFieldName(StringUtils.toCamelCase(item.getColumnName()));
            return bean;
        }).collect(Collectors.toList());
    }


    private static String removeLastPoint(String str){
        if(str.lastIndexOf(".") == str.length() - 1){
            return str.substring(0, str.length() - 1);
        }
        return str;
    }

    private static String handleJavaType(String sqlType){
        if(VelocityUtils.stringType.contains(sqlType)){
            return "String";
        }
        if(VelocityUtils.integerType.contains(sqlType)){
            return "Integer";
        }
        if(VelocityUtils.longType.contains(sqlType)){
            return "Long";
        }
        if(VelocityUtils.floatType.contains(sqlType)){
            return "Float";
        }
        if(VelocityUtils.doubleType.contains(sqlType)){
            return "Double";
        }
        if(VelocityUtils.decimalType.contains(sqlType)){
            return "BigDecimal";
        }
        if(VelocityUtils.dateType.contains(sqlType)){
            return "Date";
        }
        if(VelocityUtils.boolType.contains(sqlType)){
            return "Boolean";
        }
        if(VelocityUtils.byteArrType.contains(sqlType)){
            return "byte";
        }
        return "Object";
    }
}
