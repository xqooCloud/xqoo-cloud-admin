package com.xqoo.authorization.service.register.impl;

import com.xqoo.authorization.bo.AddUserInfoBO;
import com.xqoo.authorization.bo.NormalRegisterBO;
import com.xqoo.authorization.constants.AuthorizationCenterConstant;
import com.xqoo.authorization.service.SysUserService;
import com.xqoo.authorization.service.register.RegisterHandleService;
import com.xqoo.common.core.config.propetes.xqoo.AuthorizationConfigProperties;
import com.xqoo.common.core.config.propetes.xqoo.SecretConfigProperties;
import com.xqoo.common.core.utils.AESUtil;
import com.xqoo.common.core.utils.RandomUtil;
import com.xqoo.common.entity.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author gaoyang
 */
@Service("registerHandleService")
public class RegisterHandleServiceImpl implements RegisterHandleService {

    private final static Logger logger = LoggerFactory.getLogger(RegisterHandleServiceImpl.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private SecretConfigProperties secretConfigProperties;

    @Autowired
    private AuthorizationConfigProperties authorizationConfigProperties;

    @Autowired
    private SysUserService sysUserService;

    @Override
    public ResultEntity<String> getRegisterPhoneCode(String phonenumber, String tmpCode, String requestIp) {
        try {
            String tmpCodeJson = AESUtil.decode(tmpCode, secretConfigProperties.getAesKey());
            if(System.currentTimeMillis() - Long.parseLong(tmpCodeJson)
                    > (authorizationConfigProperties.getTimeExact() * 1000)){
                return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "临时令牌不正确，无法获取验证码");
            }
        } catch (Exception e) {
            logger.error("[授权中心-注册]解析临时验证码发生错误，无法执行后续操作,请求ip:{}", requestIp);
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "临时令牌不正确，无法获取验证码");
        }
        String key = AuthorizationCenterConstant.REGISTER_CODE_PREFIX + phonenumber;
        Long continueTime = authorizationConfigProperties.getCodeExpire();
        Long expireExists = redisTemplate.getExpire(key);
        if(expireExists != null
                && (continueTime - expireExists < authorizationConfigProperties.getSendCodeSpace())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "您才刚刚发送过验证码，请稍等一会再发送");
        }
        String code = RandomUtil.randomInt(6);
        redisTemplate.opsForValue().set(key, code, continueTime, TimeUnit.SECONDS);
        // TODO: 2021/1/25  此处补充发送短信的逻辑,测试时先将验证码直接返回

        return new ResultEntity<>(HttpStatus.OK, "验证码已成功发送，有效时间为" + continueTime + "秒", code);
    }

    @Override
    public ResultEntity<String> normalRegister(NormalRegisterBO bo) {
        String phonenumber = bo.getPhonenumber();
        String key = AuthorizationCenterConstant.REGISTER_CODE_PREFIX + phonenumber;
        Object code = redisTemplate.opsForValue().get(key);
        if(code == null){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "验证码已过期，请重新发送验证码");
        }
        if(!code.toString().equals(bo.getCode())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "验证码错误");
        }
        AddUserInfoBO addBo = new AddUserInfoBO();
        try {
            String password = AESUtil.decode(bo.getPassword(), secretConfigProperties.getAesKey());
            String confirmPassword = AESUtil.decode(bo.getConfirmPassword(), secretConfigProperties.getAesKey());
            if(!password.equals(confirmPassword)){
                return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "两次密码不一致，请重新输入");
            }
            addBo.setPassword(password);
            addBo.setConfirmPassword(confirmPassword);
        } catch (Exception e) {
            logger.error("[授权中心-注册]解密密码发生错误，密码密文为: {}, confirm密文为：{}", bo.getPassword(), bo.getConfirmPassword());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "系统运行发生了一点故障，请稍后再试");
        }
        addBo.setLoginId(phonenumber);
        addBo.setUserName("系统用户" + RandomUtil.randomStr(5));
        addBo.setUserPhone(phonenumber);
        addBo.setUserType(10);

        ResultEntity<String> addUser = sysUserService.addUserInfo(addBo);
        if(addUser.getCode().equals(HttpStatus.OK.value())){
            return new ResultEntity<>(HttpStatus.OK, "注册成功！");
        }else{
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, addUser.getMessage());
        }
    }
}
