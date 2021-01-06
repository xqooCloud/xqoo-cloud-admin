package com.xqoo.baidu.controller;

import com.xqoo.baidu.service.UidGenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author gaoyang
 * @create 2019/11/13
 * 获取流水号，白嫖百度的算法，需要建表，源码在fsg包里
 */
@RestController
@RequestMapping(value = "/generatorUid")
public class UidGenController {

    //redLock锁注入这个，高并发使用
//    @Autowired
//    private DistributedLocker distributedLocker;

    @Autowired
    private UidGenService uidGenService;

    private String uid = null;

    @GetMapping(value = "/getUid")
    public String getUid(@RequestParam(value = "type") String type) throws Exception {
        /*return distributedLocker.lock("getUid-" + type, new AquiredLockWorker<String>() {
            @Override
            public String invokeAfterLockAquire() {
                try {
                    return String.valueOf(uidGenService.getUid());
                } catch (Exception e) {
                    System.out.print(e.getMessage());
                    return null;
                }
            }
        });*/
        return String.valueOf(uidGenService.getUid());
    }
}
