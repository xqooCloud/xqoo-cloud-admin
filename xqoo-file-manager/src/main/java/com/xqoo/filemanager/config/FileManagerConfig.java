package com.xqoo.filemanager.config;

import com.xqoo.filemanager.bean.FileManagerConfigBean;
import com.xqoo.filemanager.service.FileManagerConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author gaoyang
 */
@Configuration
public class FileManagerConfig {

    private final FileManagerConfigBean fileManagerConfigBean = new FileManagerConfigBean();

    @Autowired
    private FileManagerConfigService fileManagerConfigService;

    @PostConstruct
    public void initFileManagerConfig() {
        fileManagerConfigBean.setBeanList(fileManagerConfigService.getFileManagerConfig());
    }

    @Bean(name = "fileManagerConfigBean")
    public FileManagerConfigBean bean(){
        return fileManagerConfigBean;
    }
}
