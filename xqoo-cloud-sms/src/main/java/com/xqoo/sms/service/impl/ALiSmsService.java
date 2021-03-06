package com.xqoo.sms.service.impl;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xqoo.common.core.utils.JacksonUtils;
import com.xqoo.common.core.utils.StringUtils;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.feign.service.uidgenerator.UidGeneratorFeign;
import com.xqoo.sms.bean.ALiSmsConfigBean;
import com.xqoo.sms.config.SmsConfig;
import com.xqoo.sms.constant.SmsConstant;
import com.xqoo.sms.entity.SendLogEntity;
import com.xqoo.sms.entity.SysSmsTemplateEntity;
import com.xqoo.sms.service.SendLogService;
import com.xqoo.sms.service.SmsSendService;
import com.xqoo.sms.service.SysSmsTemplateService;
import com.xqoo.sms.utils.FreemarkerUtil;
import com.xqoo.sms.vo.SendSmsVo;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class ALiSmsService implements SmsSendService {

    @Resource
    UidGeneratorFeign uidGeneratorFeign;
    @Autowired
    SendLogService sendLogService;
    @Autowired
    SysSmsTemplateService sysSmsTemplateService;
    @Resource
    ALiSmsConfigBean aLiSmsConfigBean;


    @Override

    public String servicePlatformId() {
        return SmsConstant.A_LI_SMS;
    }

    @Override
    public ResultEntity sendShortMessage(SendSmsVo sendSmsVo) {
        DefaultProfile profile = DefaultProfile.getProfile(aLiSmsConfigBean.getRegionId(), aLiSmsConfigBean.getAccessKeyId(), aLiSmsConfigBean.getAccessKeySecret());
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        String phoneNumbers = "";
        for (String phoneNumber : sendSmsVo.getPhoneNumbers()) {
            phoneNumbers = phoneNumbers + phoneNumber + ",";
        }
        if (StringUtils.isEmpty(sendSmsVo.getSign())){
            sendSmsVo.setSign(aLiSmsConfigBean.getSign());
        }
        phoneNumbers = phoneNumbers.substring(0, phoneNumbers.length() - 1);
        SendLogEntity sendLogEntity = new SendLogEntity();
        sendLogEntity.setOutId(uidGeneratorFeign.getUid("sms"));
        sendLogEntity.setPhonenumbers(phoneNumbers);
        sendLogEntity.setSendDate(new Date());
        sendLogEntity.setSign(sendSmsVo.getSign());
        sendLogEntity.setTemplateCode(sendSmsVo.getTemplateCode());
        sendLogEntity.setTemplateParm(JacksonUtils.toJson(sendSmsVo.getSmsParamJson()));
        request.setSysMethod(MethodType.POST);
        request.setSysDomain(aLiSmsConfigBean.getSysDomain());
        request.setSysVersion(aLiSmsConfigBean.getVersion());
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", aLiSmsConfigBean.getRegionId());
        request.putQueryParameter("PhoneNumbers", phoneNumbers);
        request.putQueryParameter("SignName", sendLogEntity.getSign());
        request.putQueryParameter("TemplateCode", sendLogEntity.getTemplateCode());
        request.putQueryParameter("TemplateParam", sendLogEntity.getTemplateParm());
        request.putQueryParameter("OutId", sendLogEntity.getOutId());
        //保存历史记录
        Map<String, String> map = new HashMap<>();
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            map = JacksonUtils.toObj(response.getData(), Map.class);

            if (StringUtils.equals(map.getOrDefault("Code", ""), "OK")) {
                sendLogEntity.setState("发送成功");
            } else {
                sendLogEntity.setState(aLiSmsConfigBean.getErrCodeMap().getOrDefault(map.getOrDefault("Code", ""), "短信发送出错"));
                sendLogService.save(sendLogEntity);
                return new ResultEntity(HttpStatus.METHOD_NOT_ALLOWED, sendLogEntity.getState());
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return new ResultEntity(map.getOrDefault("BizId", ""));
    }

    @Override
    public ResultEntity previewShortMessage(SendSmsVo sendSmsVo) {
        SysSmsTemplateEntity smsTemplateEntity = sysSmsTemplateService.getOne(new QueryWrapper<SysSmsTemplateEntity>().lambda().eq(SysSmsTemplateEntity::getTemplateCode, sendSmsVo.getTemplateCode()).eq(SysSmsTemplateEntity::getServicePlatformId, SmsConstant.A_LI_SMS));
        if (smsTemplateEntity == null || StringUtils.isEmpty(smsTemplateEntity.getTemplateContent())) {
            return new ResultEntity(HttpStatus.METHOD_NOT_ALLOWED, "模板不存在");
        }
        if (StringUtils.isEmpty(sendSmsVo.getSign())){
            sendSmsVo.setSign(aLiSmsConfigBean.getSign());
        }
        String result ;
        try {
            result = FreemarkerUtil.processTemplate(smsTemplateEntity.getTemplateName(), smsTemplateEntity
                    .getTemplateContent(), sendSmsVo.getSmsParamJson());
        } catch (IOException e) {
            return new ResultEntity(HttpStatus.METHOD_NOT_ALLOWED, "模板不存在");
        } catch (TemplateException e) {
            e.printStackTrace();
            return new ResultEntity(HttpStatus.METHOD_NOT_ALLOWED, "模板内容错误");
        }
        return new ResultEntity("【"+sendSmsVo.getSign()+"】"+result);
    }
}
