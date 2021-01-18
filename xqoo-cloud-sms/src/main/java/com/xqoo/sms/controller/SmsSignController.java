package com.xqoo.sms.controller;

import com.xqoo.common.entity.ResultEntity;
import com.xqoo.sms.entity.SmsSignEntity;
import com.xqoo.sms.service.SmsSignService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    @GetMapping("getListSmsSign")
    public ResultEntity<List<SmsSignEntity>> getSmsSign(){
        List<SmsSignEntity> list = smsSignService.list();
        return new ResultEntity(list);
    }

    @PostMapping("addSmsSign")
    public ResultEntity addSmsSign(@RequestBody SmsSignEntity smsSignEntity){
        boolean b = smsSignService.save(smsSignEntity);
        return new ResultEntity(b);
    }

    @PostMapping("updateSmsSign")
    public ResultEntity updateSmsSign(@RequestBody SmsSignEntity smsSignEntity){
        boolean b = smsSignService.updateById(smsSignEntity);
        return new ResultEntity(b);
    }

    @GetMapping("removeSmsSign/{id}")
    public ResultEntity removeSmsSign(@PathVariable("id") String id){
        boolean b = smsSignService.removeById(id);
        return new ResultEntity(b);
    }

}