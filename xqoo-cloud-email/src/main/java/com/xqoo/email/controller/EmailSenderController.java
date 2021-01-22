package com.xqoo.email.controller;

import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.email.constants.EmailConstant;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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
    public ResultEntity addRecordByList(@ApiIgnore @LoginUser CurrentUser currentUser,
                                        @RequestParam(name = "templateId", required = false) Integer templateId,
                                        @RequestParam(name = "receiverEmail") String receiverEmail,
                                        @RequestParam(name = "sendMap", required = false) HashMap<String, Object> sendMap) {

        redisTemplate.opsForHash().put("hashValue", "map1", "map1-1");
        redisTemplate.opsForHash().put("hashValue", "map2", "map2-2");
        Map<Object, Object> map = redisTemplate.opsForHash().entries("hashValue");
        System.out.println(map);
        System.out.println(map.get("map1"));
        if (StringUtils.isEmpty(currentUser.getUserId())) {
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "未找到当前登录人信息，请重新登录重试");
        }
        String host = redisTemplate.opsForHash().get(EmailConstant.EMAIL_SEND_CONFIG, EmailConstant.EMAIL_HOST) + "";
        String from = redisTemplate.opsForHash().get(EmailConstant.EMAIL_SEND_CONFIG, EmailConstant.EMAIL_USERNAME) + "";
        String password = redisTemplate.opsForHash().get(EmailConstant.EMAIL_SEND_CONFIG, EmailConstant.EMAIL_AUTH_CODE) + "";
        if (StringUtils.isEmpty(host) || StringUtils.isEmpty(from) || StringUtils.isEmpty(password)) {
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "缺少邮件参数配置，请重配置后重试");
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
            helper.setTo(receiverEmail);
            helper.setSubject("");
            helper.setText("", true);
            jms.send(message);
        } catch (MessagingException e) {
            logger.error("[邮件中心]邮件发送错误，错误原因：{}，错误信息：{}", e.getClass().getSimpleName(), e.getMessage());
            return new ResultEntity<>(HttpStatus.INTERNAL_SERVER_ERROR, "发送失败");
        }
        return new ResultEntity<>(HttpStatus.OK, "发送成功");
    }

}
