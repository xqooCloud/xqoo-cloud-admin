package com.xqoo.email.controller;

import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.email.bo.EmailSendBO;
import com.xqoo.email.constants.EmailConstant;
import com.xqoo.email.entity.EmailTemplateEntity;
import com.xqoo.email.service.EmailConfigService;
import com.xqoo.email.service.EmailTemplateService;
import com.xqoo.feign.annotations.LoginUser;
import com.xqoo.feign.annotations.OperationLog;
import com.xqoo.feign.enums.operlog.OperationTypeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * EmailTemplateController
 *
 * @author xqoo-code-gen
 * @date 2021-01-11 17:06:49
 */

@RestController
@RequestMapping("/emailSender")
@Api(tags = "邮件发送控制器")
@Validated
public class EmailSenderController {

    @Autowired
    private EmailTemplateService emailTemplateService;
    @Autowired
    private EmailConfigService emailConfigService;
    @Resource
    RedisTemplate redisTemplate;
    private final static Logger logger = LoggerFactory.getLogger(EmailSenderController.class);


    @ApiOperation("邮件发送")
    @PostMapping("/send")
    @OperationLog(tips = "邮件发送", operatorType = OperationTypeEnum.ADD, isSaveRequestData = true)
    public ResultEntity addRecordByList(@ApiIgnore @LoginUser CurrentUser currentUser,@RequestBody @Valid EmailSendBO sendBO) {
        if (StringUtils.isEmpty(currentUser.getUserId())) {
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "未找到当前登录人信息，请重新登录重试");
        }
        String host = redisTemplate.opsForHash().get(EmailConstant.EMAIL_SEND_CONFIG, EmailConstant.EMAIL_HOST) + "";
        String from = redisTemplate.opsForHash().get(EmailConstant.EMAIL_SEND_CONFIG, EmailConstant.EMAIL_USERNAME) + "";
        String password = redisTemplate.opsForHash().get(EmailConstant.EMAIL_SEND_CONFIG, EmailConstant.EMAIL_AUTH_CODE) + "";
        if (StringUtils.isEmpty(host) || StringUtils.isEmpty(from) || StringUtils.isEmpty(password)) {
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "缺少邮件参数配置，请重配置后重试");
        }
        EmailTemplateEntity emailTemplateEntity = emailTemplateService.getOneEmailTemplateEntityByPrimaryKey(sendBO.getTemplateId());
        if (null == emailTemplateEntity) {
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "未找到对应模板配置，请重配置后重试");
        }
        String text = emailTemplateEntity.getEmailText();
        List<String> keys = getKey(text);
        for (int i = 0; i < keys.size(); i++) {
            if ( !sendBO.getSendMap().containsKey(keys.get(i))) {
                return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "缺少模板占位符参数，请传入正确参数");
            }
            text = text.replace("$[" + keys.get(i) + "]", sendBO.getSendMap().get(keys.get(i)) + "");
        }
        JavaMailSenderImpl jms = new JavaMailSenderImpl();
        jms.setHost(host);
        jms.setUsername(from);
        jms.setPassword(password);
        jms.setDefaultEncoding("Utf-8");
        Properties p = new Properties();
        p.setProperty("mail.smtp.auth", "true");
        jms.setJavaMailProperties(p);
        try {
            MimeMessage message = jms.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);                //sender为自定义显示发件人名称
            helper.setTo(sendBO.getReceiverEmail());
            helper.setSubject(emailTemplateEntity.getEmailSubject());
            helper.setText(text, true);
            jms.send(message);
        } catch (MessagingException e) {
            logger.error("[邮件中心]邮件发送错误，错误原因：{}，错误信息：{}", e.getClass().getSimpleName(), e.getMessage());
            return new ResultEntity<>(HttpStatus.INTERNAL_SERVER_ERROR, "发送失败");
        }
        return new ResultEntity<>(HttpStatus.OK, "发送成功");
    }

    private List<String> getKey(String text) {
        List<String> ls = new ArrayList<>();
        Pattern pattern = Pattern.compile("(?<=\\[)(.+?)(?=\\])");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            ls.add(matcher.group());
        }
        return ls;
    }

}
