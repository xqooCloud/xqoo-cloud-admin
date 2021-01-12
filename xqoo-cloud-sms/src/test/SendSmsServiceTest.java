import com.xqoo.ApplicationXqooCloudSms;
import com.xqoo.feign.service.uidgenerator.UidGeneratorFeign;
import com.xqoo.sms.constant.SmsConstant;
import com.xqoo.sms.service.ErrCodeMessageService;
import com.xqoo.sms.service.SendSmsService;
import com.xqoo.sms.vo.SendSmsVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationXqooCloudSms.class)
public class SendSmsServiceTest {


        @Resource
    SendSmsService sendSmsService;
    @Resource
    ErrCodeMessageService errCodeMessageService;
    @Resource
    UidGeneratorFeign uidGeneratorFeign;
    @Test
    /**
     * 测试阿里发送短信
     */
    public void contextLoads() {
        List<String> list = new ArrayList<>();
        SendSmsVo sendSmsVo = new SendSmsVo();
        list.add("176853026859");
        sendSmsVo.setPhoneNumbers(list);
        Map<String, Object> map = new HashMap<>();
        map.put("code", "123456");
        sendSmsVo.setSign("航天国政");
        sendSmsVo.setTemplateCode("SMS_193100500");
        sendSmsVo.setSmsParamJson(map);
        //调用阿里
        sendSmsService.sendShortMessage(SmsConstant.A_LI_SMS,sendSmsVo);

    }
    @Test
    public void init() {
        System.out.println(1);
    }

    @Test
    public void initErrCodeMessage() {

    }



}