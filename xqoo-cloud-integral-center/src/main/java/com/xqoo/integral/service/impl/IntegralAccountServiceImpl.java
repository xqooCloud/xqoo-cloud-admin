package com.xqoo.integral.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.xqoo.common.core.config.propetes.xqoo.SecretConfigProperties;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.core.utils.AESUtil;
import com.xqoo.common.core.utils.StringUtils;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageRequestBean;
import com.xqoo.common.page.PageResponseBean;
import com.xqoo.common.qrcode.QRCodeUtil;
import com.xqoo.integral.constants.IntegralModuleConstant;
import com.xqoo.integral.entity.IntegralAccountEntity;
import com.xqoo.integral.mapper.IntegralAccountMapper;
import com.xqoo.integral.service.IntegralAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 数据源表(integral_account)表服务实现类
 *
 * @author xqoo-code-gen
 * @date 2021-01-26 11:17:12
 */
@Service("integralAccountService")
public class IntegralAccountServiceImpl extends ServiceImpl<IntegralAccountMapper, IntegralAccountEntity> implements IntegralAccountService {

    private final static Logger logger = LoggerFactory.getLogger(IntegralAccountServiceImpl.class);


    @Autowired
    private IntegralAccountMapper integralAccountMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private SecretConfigProperties secretConfigProperties;

    private final static String path = "/home/gaoyang/qrcode/";

    @Override
    public ResultEntity<PageResponseBean<IntegralAccountEntity>> pageGetList(PageRequestBean page){
        LambdaQueryWrapper<IntegralAccountEntity> queryWrapper = new LambdaQueryWrapper<>();
        Integer count = integralAccountMapper.selectCount(queryWrapper);
        PageResponseBean<IntegralAccountEntity> result = new PageResponseBean<>(page.getPage(), page.getPageSize(), count);
        if(count == null || count < 1){
            result.setContent(Collections.emptyList());
            return new ResultEntity<>(HttpStatus.OK, "ok", result);
        }
        PageHelper.startPage(page.getPage(), page.getPageSize());
        List<IntegralAccountEntity> list = integralAccountMapper.selectList(queryWrapper);
        result.setContent(list);
        return new ResultEntity<>(HttpStatus.OK, "ok", result);
    }

    @Override
    public ResultEntity insertList(List<IntegralAccountEntity> list, CurrentUser currentUser){
        if(CollUtil.isEmpty(list)){
            return new ResultEntity<>(HttpStatus.OK, "新增完成");
        }
        try{
            integralAccountMapper.insertList(list);
            return new ResultEntity<>(HttpStatus.OK, "新增完成");
        }catch (RuntimeException e){
            logger.error("[com.xqoo.codegen]数据库批量新增错误，错误原因：{}，错误信息：{}", e.getClass().getSimpleName(), e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "新增失败");
        }
    }

    @Override
    public IntegralAccountEntity getOneIntegralAccountEntityByPrimaryKey(String accountId){
        LambdaQueryWrapper<IntegralAccountEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(IntegralAccountEntity::getAccountId, accountId);
        IntegralAccountEntity entity = integralAccountMapper.selectOne(queryWrapper);
        if(entity != null){
            return entity;
        }
        return new IntegralAccountEntity();
    }

    @Override
    public ResultEntity<IntegralAccountEntity> getPersonAccountInfo(String userId) {
        IntegralAccountEntity entity = integralAccountMapper.selectOne(new LambdaQueryWrapper<IntegralAccountEntity>()
                .eq(IntegralAccountEntity::getAccountOwner, userId));
        if(entity == null){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "未找到相关积分账户信息");
        }
        return new ResultEntity<>(HttpStatus.OK, "OK", entity);
    }

    @Override
    public ResultEntity<String> generatorQrcode(String userId) {
        IntegralAccountEntity account = integralAccountMapper.selectOne(new LambdaQueryWrapper<IntegralAccountEntity>()
                .eq(IntegralAccountEntity::getAccountOwner, userId));
        if(account == null || StringUtils.isEmpty(account.getAccountId())){
            createNewAccount(userId);
        }
        String content = userId + "_" + System.currentTimeMillis();
        String key = IntegralModuleConstant.SHARE_INTEGRAL_KEY + content;
        String qrCodeFileName= DateUtil.format(new Date(), "yyyyMMddHHmmss") + "_" + userId + "_qr.jpg";
        File codeFile = new File(path + qrCodeFileName);
        try{
            BufferedImage qrCodeImage = QRCodeUtil.generateQRImageBufferedImg(IntegralModuleConstant.QRCODE_CONTENT_PREFIX
                            + content, path, qrCodeFileName, "jpg",
                    230,230);
            if(qrCodeImage == null){
                return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "生成二维码发生错误，请稍后重试");
            }
            ImageIO.write(qrCodeImage, "jpg",codeFile);
        }catch (Exception e){
            e.printStackTrace();
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "生成二维码发生错误，请稍后重试");
        }
        redisTemplate.opsForValue().set(key, userId, 1, TimeUnit.HOURS);

        return new ResultEntity<>(HttpStatus.OK, "", content);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultEntity<String> scanQrcode(String keyword, String userId) {
        String content;
        try{
            content = AESUtil.decode(keyword, secretConfigProperties.getAesKey());
        }catch (Exception e){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "识别规则错误，请稍后再试");
        }
        if(StringUtils.isEmpty(content)){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "识别规则错误，请稍后再试");
        }
        Object shareUserId = redisTemplate.opsForValue().get(IntegralModuleConstant.SHARE_INTEGRAL_KEY + content);
        if(shareUserId == null){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "此二维码被使用或过期，请重新生成");
        }
        if(userId.equals(shareUserId.toString())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "此二维码为您本人生成，扫码无效");
        }
        IntegralAccountEntity shareAccount = integralAccountMapper.selectOne(new LambdaQueryWrapper<IntegralAccountEntity>()
                .eq(IntegralAccountEntity::getAccountOwner, shareUserId.toString()));

        IntegralAccountEntity scanAccount = integralAccountMapper.selectOne(new LambdaQueryWrapper<IntegralAccountEntity>()
                .eq(IntegralAccountEntity::getAccountOwner, userId));
        try {
            if(shareAccount == null || StringUtils.isEmpty(shareAccount.getAccountOwner())){
                shareAccount = createNewAccount(shareUserId.toString());
            }
            if(scanAccount == null || StringUtils.isEmpty(scanAccount.getAccountOwner())){
                scanAccount = createNewAccount(userId);
            }
            shareAccount.setAccountAmount(shareAccount.getAccountAmount().add(BigDecimal.valueOf(20)));
            scanAccount.setAccountAmount(scanAccount.getAccountAmount().add(BigDecimal.valueOf(20)));
            integralAccountMapper.updateById(shareAccount);
            integralAccountMapper.updateById(scanAccount);
            redisTemplate.delete(IntegralModuleConstant.SHARE_INTEGRAL_KEY + content);
            return new ResultEntity<>(HttpStatus.OK, "扫码完成");
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            return new ResultEntity<String>(HttpStatus.NOT_ACCEPTABLE, "扫码执行失败，请稍后重试");
        }
    }

    public IntegralAccountEntity createNewAccount(String userId){
        IntegralAccountEntity entity = new IntegralAccountEntity();
        entity.setAccountId(IntegralModuleConstant.ACCOUNT_PREFIX + userId);
        entity.setAccountOwner(userId);
        entity.setAccountAmount(BigDecimal.ZERO);
        try{
            integralAccountMapper.insert(entity);
            return entity;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
