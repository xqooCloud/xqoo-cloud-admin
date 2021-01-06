package com.xqoo.common.core.config;

import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.xqoo.common.constants.JwtConstans;
import com.xqoo.common.core.config.propetes.xqoo.AuthorizationConfigProperties;
import com.xqoo.common.core.entity.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("JwtUtils")
public class JWTUtils {

    private static final Logger logger = LoggerFactory.getLogger(JWTUtils.class);

    @Autowired
    private AuthorizationConfigProperties authorizationConfigProperties;

    /**
     * 生成jwt
     *
     */
    /**
     * @Descriptioin 生成jwt expire传null或者0时采用默认失效时间
     * @name createJwt
     * @params [params, issure, aud, expire] issure为签发放，aud为接收方
     * @return java.lang.String
     */
    public String createJwt(Map<String, Object> params, String issure, String aud, Long expire){
        Long expireFact =  0L;
        if(expire == null || expire == 0){
            expireFact = authorizationConfigProperties.getJwtExpire();
        }else{
            expireFact = expire;
        }
        Algorithm algorithm = Algorithm.HMAC256(authorizationConfigProperties.getJwtSecretKey());
        //设置头信息
        HashMap<String, Object> header = new HashMap<>(2);
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        // 生成 token：头部+载荷+签名
        Date expireDate = new Date(System.currentTimeMillis() + expireFact);
        return JWT.create().withHeader(header)
                .withIssuer(issure)
                .withIssuedAt(new Date())
                .withAudience(aud)
                .withClaim(JwtConstans.PayloadConstants.CUSTOM_INFO_KEY, params)
                .withExpiresAt(expireDate).sign(algorithm);
    }

    /**
     * 获取完整的jwt内容
     */
    public Map<String, Object> getAllJwt(String token) {
        Map<String, Object> claims = new HashMap<>();
        try {

            DecodedJWT jwt = ParseJwt(token);
            claims.put(JwtConstans.PayloadConstants.CUSTOM_INFO_KEY, jwt.getClaim(JwtConstans.PayloadConstants.CUSTOM_INFO_KEY).asMap());
            claims.put("issuerAt", DateUtil.format(jwt.getIssuedAt(), "yyyy-MM-dd HH:mm:ss"));
            claims.put("issuer", jwt.getIssuer());
            return claims;
        } catch (Exception e) {
            logger.error("[jwt]解析令牌发生错误，异常{}, 异常信息{}",
                    e.getClass().getSimpleName(), e.getMessage());
            return null;
        }
    }

    /**
     * @Descriptioin 获取jwt签发方
     * @name GetJwtIssure
     * @params [token]
     * @return java.lang.String
     */
    public String GetJwtIssure(String token){
        try {

            DecodedJWT jwt = ParseJwt(token);
            return jwt.getIssuer();
        } catch (Exception e) {
            logger.error("[jwt]解析令牌发生错误，异常{}, 异常信息{}",
                    e.getClass().getSimpleName(), e.getMessage());
            return null;
        }
    }

    /**
     * @Descriptioin 获取jwt接收方
     * @name GetJwtIssure
     * @params [token]
     * @return java.util.List
     */
    public List<String> GetJwtAudience(String token){
        try {

            DecodedJWT jwt = ParseJwt(token);
            return jwt.getAudience();
        } catch (Exception e) {
            logger.error("[jwt]解析令牌发生错误，异常{}, 异常信息{}",
                    e.getClass().getSimpleName(), e.getMessage());
            return null;
        }
    }

    /**
     * @Descriptioin 获取Jwt自定义信息
     * @name GetJwtIssure
     * @params [token]
     * @return java.util.List
     */
    public CurrentUser GetJwtCustomInfo(String token){
        try {

            DecodedJWT jwt = ParseJwt(token);
            Map<String, Object> map = jwt.getClaim(JwtConstans.PayloadConstants.CUSTOM_INFO_KEY).asMap();
            CurrentUser cu = new CurrentUser();
            cu.fromMap(map);
            return cu;
        } catch (Exception e) {
            logger.error("[jwt]解析令牌发生错误，异常{}, 异常信息{}",
                    e.getClass().getSimpleName(), e.getMessage());
            return null;
        }
    }


    /**
     * @Descriptioin 解析JWTTOKEN
     * @name ParseJwt
     * @params [token]
     * @return com.auth0.jwt.interfaces.DecodedJWT
     */
    private DecodedJWT ParseJwt(String token) throws Exception {
        try {
            Algorithm algorithm = Algorithm.HMAC256(authorizationConfigProperties.getJwtSecretKey());
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt;
        }catch (TokenExpiredException e){
            logger.warn("[jwt]此令牌已过期{}, 过期时间{}",
                    token, e.getMessage());
            throw new Exception(e);
        }
    }
}
