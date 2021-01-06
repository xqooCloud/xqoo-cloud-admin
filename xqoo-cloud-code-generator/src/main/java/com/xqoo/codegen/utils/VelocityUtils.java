package com.xqoo.codegen.utils;

import cn.hutool.core.date.DateUtil;
import com.google.common.base.Splitter;
import com.xqoo.codegen.dto.TemplateHandleDTO;
import com.xqoo.codegen.entity.JavaTableColumnsEntity;
import com.xqoo.codegen.pojo.TemplateDataPOJO;
import com.xqoo.codegen.vo.GeneratorCodeVO;
import com.xqoo.codegen.vo.PreviewCodeVO;
import com.xqoo.common.core.utils.RandomUtil;
import org.apache.commons.io.IOUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class VelocityUtils {

    private final static Logger logger = LoggerFactory.getLogger(VelocityUtils.class);

    public final static List<String> stringType = Arrays.asList("blob", "char", "json", "longblob", "longtext",
            "mediumblob", "mediumtext", "text", "tinyblob", "tinytext", "varchar");
    public final static List<String> integerType = Arrays.asList("int", "integer", "smallint", "tinyint");
    public final static List<String> longType = Collections.singletonList("bigint");
    public final static List<String> floatType = Arrays.asList("float", "real");
    public final static List<String> doubleType =  Collections.singletonList("double");
    public final static List<String> decimalType =  Collections.singletonList("decimal");
    public final static List<String> dateType = Arrays.asList("date", "datetime", "timestamp", "time");
    public final static List<String> boolType = Arrays.asList("boolean", "bit");
    public final static List<String> byteArrType = Arrays.asList("binary", "varbinary");

    /**
     * 获取模板信息
     *
     * @return 模板列表
     */
    public static List<String> getTemplateList(List<String> templateValue, List<TemplateDataPOJO> templates)
    {
        List<String> templatePath = new ArrayList<String>();
        templates.forEach(item -> {
            if(templateValue.contains(item.getValue())){
                templatePath.addAll(item.getUrl());
            }
        });
        return templatePath;
    }

    public static GeneratorCodeVO generatorCodeZip(List<TemplateHandleDTO> templateHandleList, VelocityContext context, String basePath, String zipFileName){
        String date = DateUtil.format(new Date(), "yyyyMMdd");
        String path = basePath + File.separator + date;
        String fileName = zipFileName + ".zip";
        String downloadKey = path + File.separator + fileName;
        File pathFile = new File(path);
        if(!pathFile.exists()){
            pathFile.mkdirs();
        }
        File file = new File(downloadKey);
        try{
            if(file.exists()){
                if(!file.delete()){
                    logger.error("[代码生成中心]删除文件失败{}", downloadKey);
                    return new GeneratorCodeVO();
                }
            }
            if (file.createNewFile()) {
                logger.info("[代码生成中心]创建压缩文件{}" , downloadKey);
            } else {
                return new GeneratorCodeVO();
            }
            FileOutputStream fOutputStream = new FileOutputStream(file);
            ZipOutputStream zip = new ZipOutputStream(fOutputStream);
            GeneratorCodeVO vo = generatorCode(templateHandleList, context, zip, true);
            IOUtils.closeQuietly(zip);
            vo.setDownLoadKey(downloadKey);
            return vo;
        }catch (IOException e){
            e.printStackTrace();
            logger.error("[代码生成中心]zip压缩文件生成时发生错误，压缩文件{}", downloadKey);
            logger.info("[代码生成中心]生成失败，删除文件{}", file.delete());
            return new GeneratorCodeVO();
        }
    }

    public static GeneratorCodeVO generatorCode(List<TemplateHandleDTO> templateHandleList, VelocityContext context, ZipOutputStream zip, Boolean needDownload){
        List<PreviewCodeVO> previewList = templateHandleList.stream().map(item -> {
            // 渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(item.getTemplatePath(), StandardCharsets.UTF_8.name());
            tpl.merge(context, sw);
            PreviewCodeVO previewVo = getPreviewVo(item.getTemplatePath(), sw.toString());
            previewVo.setIndex(templateHandleList.indexOf(item));
            if(needDownload){
                try {
                    zip.putNextEntry(new ZipEntry(item.getTemplateFilePath()));
                    IOUtils.write(sw.toString(), zip, StandardCharsets.UTF_8);
                    IOUtils.closeQuietly(sw);
                    zip.flush();
                    zip.closeEntry();

                } catch (IOException e) {
                    logger.error("[代码生成中心]模板文件生成时发生错误，模板文件{}", item);
                }
            }
            return previewVo;
        }).collect(Collectors.toList());
        GeneratorCodeVO vo = new GeneratorCodeVO();
        vo.setDownLoadKey(null);
        vo.setPreviewCodeList(previewList);
        return vo;
    }

    public static PreviewCodeVO getPreviewVo(String templatePath, String content){
        PreviewCodeVO vo = new PreviewCodeVO();
        templatePath = templatePath.replace(".vm", "").trim();
        List<String> nameArr = Splitter.on("/").trimResults().splitToList(templatePath);
        String fileName = nameArr.size() > 1 ? nameArr.get(nameArr.size() - 1) : nameArr.get(0);
        nameArr = Splitter.on(".").trimResults().splitToList(fileName);
        vo.setContent(content);
        vo.setFileName(fileName);
        vo.setFileType(nameArr.size() > 1 ? nameArr.get(nameArr.size() - 1) : nameArr.get(0));
        return vo;
    }

    /**
     * 根据列类型获取导入包
     *
     * @param columns 列集合
     * @return 返回需要导入的包列表
     */
    public static HashSet<String> getImportList(List<JavaTableColumnsEntity> columns) {
        HashSet<String> importList = new HashSet<String>();
        columns.forEach(item ->{
            if ("Date".equals(item.getJavaFieldType())) {
                importList.add("java.util.Date");
                importList.add("com.fasterxml.jackson.annotation.JsonFormat");
            }
            if ("BigDecimal".equals(item.getJavaFieldType())) {
                importList.add("java.math.BigDecimal");
            }
            if ("Integer".equals(item.getJavaFieldType())) {
                importList.add("java.lang.Integer");
            }
            if ("Long".equals(item.getJavaFieldType())) {
                importList.add("java.lang.Long");
            }
            if ("Float".equals(item.getJavaFieldType())) {
                importList.add("java.lang.Float");
            }
            if ("Double".equals(item.getJavaFieldType())) {
                importList.add("java.lang.Double");
            }
            if ("Boolean".equals(item.getJavaFieldType())) {
                importList.add("java.lang.Boolean");
            }
            if ("List".equals(item.getJavaFieldType())) {
                importList.add("java.util.List");
            }
            if ("Set".equals(item.getJavaFieldType())) {
                importList.add("java.util.Set");
            }
            if ("ObjectNode".equals(item.getJavaFieldType())) {
                importList.add("com.fasterxml.jackson.databind.node.ObjectNode");
            }
            if ("JsonNode".equals(item.getJavaFieldType())) {
                importList.add("com.fasterxml.jackson.databind.JsonNode");
            }
            if ("Object".equals(item.getJavaFieldType())) {
                importList.add("java.lang.Object");
            }
        });
        return importList;
    }

    public static Long getSerUid(){
        String base = RandomUtil.randomInt(18);
        String random = RandomUtil.randomInt(1);
        if(Integer.parseInt(random) % 2 == 0){
            return Long.parseLong(base);
        }
        return -1L * Long.parseLong(base);
    }
}
