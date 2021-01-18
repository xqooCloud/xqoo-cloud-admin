package com.xqoo.sms.controller;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageResponseBean;
import com.xqoo.sms.entity.SysSmsTemplateEntity;
import com.xqoo.sms.service.SysSmsTemplateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SysSmsTemplate)表控制层
 *
 * @author makejava
 * @since 2021-01-11 14:09:59
 */
@RestController
@RequestMapping("sysSmsTemplate")
public class SysSmsTemplateController  {
    /**
     * 服务对象
     */
    @Resource
    private SysSmsTemplateService sysSmsTemplateService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public String selectOne(String id) {
        return "";
    }

    @GetMapping("getListSmsTemplate")
    public ResultEntity<PageResponseBean<SysSmsTemplateEntity>> getListSmsTemplate(){
        QueryWrapper<SysSmsTemplateEntity> queryWrapper = new QueryWrapper<>();
        List<SysSmsTemplateEntity> templateEntities = sysSmsTemplateService.list(queryWrapper);

      return   new ResultEntity<>(templateEntities);
    }

}