package com.xqoo.filemanager.config;

import cn.hutool.core.collection.CollUtil;
import com.xqoo.filemanager.bean.FileConfigPropertiesBean;
import com.xqoo.filemanager.entity.FileManagerConfigEntity;
import com.xqoo.filemanager.service.FileConfigPropertiesService;
import com.xqoo.filemanager.service.FileManagerConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class FileManagerPropertiesConifg {

    private FileConfigPropertiesBean fileConfigPropertiesBean = new FileConfigPropertiesBean();

    @Autowired
    private FileManagerConfigService fileManagerConfigService;

    @Autowired
    private FileConfigPropertiesService fileConfigPropertiesService;

    @PostConstruct
    public void initFileConfigPropertie(){
        List<FileManagerConfigEntity> mainList = fileManagerConfigService.getFileManagerConfig();
        Map<String, Map<String, String>> fileConfigMap = new HashMap<>(BigDecimal.valueOf(mainList.size())
                .multiply(BigDecimal.valueOf(1.8)).intValue());
        mainList.forEach(item -> {
            Map<String, String> configPropertiesMap = fileConfigPropertiesService.getPropertiesByParentId(item.getId());
            fileConfigMap.put(item.getUploadPlat(), CollUtil.isEmpty(configPropertiesMap) ? Collections.emptyMap() : configPropertiesMap);
        });
        fileConfigPropertiesBean.setFileManagerConfigBean(fileConfigMap);
    }


    @Bean(name = "fileConfigPropertiesBean")
    public FileConfigPropertiesBean bean(){
        return fileConfigPropertiesBean;
    }
}
