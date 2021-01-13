package com.xqoo.sms.controller;

import com.xqoo.sms.service.SmsSignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (SmsSign)表控制层
 *
 * @author makejava
 * @since 2021-01-11 14:41:26
 */
@RestController
@RequestMapping("smsSign")
public class SmsSignController {
    /**
     * 服务对象
     */
    @Resource
    private SmsSignService smsSignService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public String selectOne(Integer id) {
        return "";
    }

}