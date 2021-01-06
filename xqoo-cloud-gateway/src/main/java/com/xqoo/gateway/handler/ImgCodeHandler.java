package com.xqoo.gateway.handler;

import com.google.code.kaptcha.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class ImgCodeHandler implements HandlerFunction<ServerResponse> {

    private static Logger log = LoggerFactory.getLogger(ImgCodeHandler.class);

    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    private final StringRedisTemplate redisTemplate;

    public ImgCodeHandler(Producer captchaProducer, StringRedisTemplate redisTemplate) {
        this.captchaProducer = captchaProducer;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Mono<ServerResponse> handle(ServerRequest serverRequest)
    {
        // 生成验证码
        String capText = captchaProducerMath.createText();
        String capStr = capText.substring(0, capText.lastIndexOf("@"));
        String code = capText.substring(capText.lastIndexOf("@") + 1);
        BufferedImage image = captchaProducerMath.createImage(capStr);
        // 保存验证码信息
        String randomStr = UUID.randomUUID().toString().replaceAll("-", "");
        redisTemplate.opsForValue().set("random_code_" + randomStr, code, 60, TimeUnit.SECONDS);
        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try
        {
            ImageIO.write(image, "jpg", os);
        }
        catch (IOException e)
        {
            log.error("ImageIO write err", e);
            return Mono.error(e);
        }
        return ServerResponse.status(HttpStatus.OK).contentType(MediaType.IMAGE_JPEG).header("randomstr", randomStr)
                .body(BodyInserters.fromResource(new ByteArrayResource(os.toByteArray())));
    }
}
