package com.xqoo.codegen.utils;

import cn.hutool.core.collection.CollUtil;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.xqoo.codegen.bo.MicroServiceGeneratorBO;
import com.xqoo.codegen.dto.IndexListDTO;
import com.xqoo.codegen.vo.IndexTreeVO;
import com.xqoo.codegen.vo.PreviewCodeVO;
import com.xqoo.common.core.utils.SpringPathMather;
import com.xqoo.common.core.utils.StringUtils;
import org.apache.velocity.VelocityContext;

import java.util.*;
import java.util.stream.Collectors;

public class MicroServiceGenUtils {

    private final static String MAIN_FUNCTION_NAME = "Application.java";
    /**
     * 获得表生成java文件context
     */
    public static VelocityContext getMicroServiceGenCodeContext(MicroServiceGeneratorBO bo){
        String moduleName = bo.getModuleName();
        String modulePort = bo.getModulePort();
        String nacosNameSpace = bo.getNacosNameSpace();
        String moduleNameCamelUpper = StringUtils.subtractToCamelCase(moduleName);
        String moduleNameCamelLower = StringUtils.firstLowerCase(moduleNameCamelUpper);
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("moduleName", moduleName);
        velocityContext.put("modulePort", modulePort);
        velocityContext.put("nacosNameSpace", nacosNameSpace);
        velocityContext.put("moduleNameCamelUpper", moduleNameCamelUpper);
        velocityContext.put("moduleNameCamelLower", moduleNameCamelLower);

        return velocityContext;
    }

    public static String getGeneratorFileName(String template, String moduleName){
        PreviewCodeVO vo = VelocityUtils.getPreviewVo(template, null);
        String format = template.replace("vm/", "").replace(".vm", "");
        List<String> splitPath = Splitter.on("/").trimResults().splitToList(format);
        String moduleNameCamelUpper = StringUtils.subtractToCamelCase(moduleName);
        String fileMidFolder;
        if(MAIN_FUNCTION_NAME.equals(vo.getFileName())){
            fileMidFolder = Joiner.on("/").skipNulls().join(splitPath.subList(1, splitPath.size() - 1));
            fileMidFolder = fileMidFolder + "/Application" + moduleNameCamelUpper + ".java";
        }else{
            fileMidFolder = Joiner.on("/").skipNulls().join(splitPath.subList(1, splitPath.size()));
        }

        return StringUtils.format("{}/{}", moduleName, fileMidFolder);
    }

    public static List<IndexTreeVO> getFileIndexTree(List<String> templates, String moduleName, List<PreviewCodeVO> previewCodeList){
        List<String> factFileList = getFactFilePathList(templates, moduleName);
        Set<IndexListDTO> indexList = getFactIndexList(factFileList);
        return generatorIndexTree(indexList, "none", previewCodeList);
    }

    public static Set<IndexListDTO> getFactIndexList(List<String> factFileList){
        Set<IndexListDTO> indexSet = new HashSet<>();
        factFileList.forEach(item -> {
            indexSet.addAll(handleFilePathToIndexList(item));
        });
        return indexSet;
    }

    public static List<String> getFactFilePathList(List<String> templates, String moduleName){
        return templates.stream().map(item -> getGeneratorFileName(item, moduleName)).collect(Collectors.toList());
    }

    private static Set<IndexListDTO> handleFilePathToIndexList(String filePath){
        List<String> fileLevel = Splitter.on("/").trimResults().splitToList(filePath);
        String[] parentIndexName = {"none"};
        return fileLevel.stream().map(item -> {
            IndexListDTO dto = new IndexListDTO();
            dto.setIndexName(item);
            dto.setParentIndexName(parentIndexName[0]);
            parentIndexName[0] = item;
            return dto;
        }).collect(Collectors.toSet());
    }

    private static List<IndexTreeVO> generatorIndexTree(Set<IndexListDTO> indexList, String parentIndexName, List<PreviewCodeVO> previewCodeList){
        Set<IndexListDTO> childrenSet = indexList.stream().filter(item -> parentIndexName.equals(item.getParentIndexName())).collect(Collectors.toSet());
        return childrenSet.stream().map(item -> {
            if(parentIndexName.equals(item.getParentIndexName())){
                IndexTreeVO vo = new IndexTreeVO();
                vo.setTitle(item.getIndexName());
                vo.setKey(item.getIndexName());
                Optional<PreviewCodeVO> find = previewCodeList.stream().filter(filterItem ->
                {
                    if(filterItem.getFileName().equals(item.getIndexName())){
                        return true;
                    }
                    if(MAIN_FUNCTION_NAME.equals(filterItem.getFileName())){
                        return SpringPathMather.MatchPath("Application**.java" , item.getIndexName());
                    }
                    return false;
                }).findFirst();
                find.ifPresent(previewCodeVO -> vo.setPreviewCodeIndex(previewCodeVO.getIndex()));
                vo.setChildren(generatorIndexTree(indexList, item.getIndexName(), previewCodeList));
                if(CollUtil.isEmpty(vo.getChildren())){
                    vo.setIsLeaf(true);
                }
                return vo;
            }
            return null;
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }

}
