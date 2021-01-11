import com.xqoo.ApplicationXqooCloudSms;
import com.xqoo.sms.service.SendSmsService;
import com.xqoo.sms.vo.SendSmsVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationXqooCloudSms.class)
public class SendSmsServiceTest {


        @Resource
    SendSmsService sendSmsService;


    @Test
    public void contextLoads() {
        SendSmsVo sendSmsVo = new SendSmsVo();
        sendSmsVo.setSign("1");
        SendSmsVo sendSmsVo1 = new SendSmsVo();
        sendSmsVo1.setSign("2");
        sendSmsService.sendShortMessage("1",sendSmsVo);
        sendSmsService.sendShortMessage("2",sendSmsVo1);
    }
    @Test
    public void init() {
        System.out.println(1);
    }

}