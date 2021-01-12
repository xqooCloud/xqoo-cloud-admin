package com.xqoo.sms.controller;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.xqoo.sms.entity.SysSmsTemplateEntity;
import com.xqoo.sms.service.SysSmsTemplateService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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


    @GetMapping("sendSms")
    public String sendSms(String id) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "<accessKeyId>", "<accessSecret>");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return "";
    }

}