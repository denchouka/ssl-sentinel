package cool.tch.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.RegisteredClaims;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import cool.tch.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import static cool.tch.common.Constant.*;

/**
 * @author denchouka
 * @description Token工具类
 * @date 2024/10/26 21:07
 */
public class TokenUtils {

    /**
     * 过期token的黑名单
     */
    private static final ConcurrentHashMap<String, Long> blackList = new ConcurrentHashMap<>();

    /**
     * 生成token
     * @param username 用户名
     * @param ip 用户的ip
     * @return 生成的token
     */
    public static String token(String username, String ip) {
        // 参数校验
        if (StringUtils.isBlank(username) || StringUtils.isBlank(ip)) {
            throw new BusinessException("用户名和ip不能为空");
        }

        // Header
        HashMap<String, Object> header = new HashMap<>(2);
        // Token类型
        header.put("typ","JWT");
        // 签名算法类型
        header.put("alg","HS256");

        try{
            // 生成token签名
            String token = JWT.create()
                    .withHeader(header)
                    // 添加声明
                    .withClaim("username", username)
                    .withClaim("ip", ip)
                    .withClaim("time", Instant.now())
                    // 加盐
                    .withClaim("salt", TOKEN_SECRET_SALT)
                    // 1H后过期
                    .withExpiresAt(Date.from(Instant.now().plusSeconds(TOKEN_EXPIRE_DATE)))
                    // 指定jti
                    .withJWTId(IDUtils.generateJti())
                    // 密钥
                    .sign(Algorithm.HMAC256(TOKEN_SECRET_KEY));

            // 返回前对生成的token加密
            return SecureUtils.encrypt(token);
        } catch (Exception e) {
            throw new BusinessException("生成token失败");
        }
    }


    /**
     * 校验token有效性
     * @param token 请求头中携带的token
     * @return 校验不通过
     */
    public static boolean verify(String token) {
        // 判空
        if (StringUtils.isBlank(token)) {
            return false;
        }

        // token解密
        String decrypt = SecureUtils.decrypt(token);

        // 判断是否被手动失效过（必须先解密）
        if (isTokenExpired(decrypt)) {
            return false;
        }

        // 验证token有效性
        try{
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET_KEY)).build();
            verifier.verify(decrypt);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    /**
     * 退出登录的时候手动失效token
     * @param token 要失效的token
     */
    public static void revokeToken(String token) {
        // 如果校验不通过直接返回(在拦截器里不对退出登录请求的token做校验)
        if (!verify(token)) {
            return;
        }

        // token先解密
        String decrypt = SecureUtils.decrypt(token);
        DecodedJWT decode = JWT.decode(decrypt);

        // 获取token的jti
        String jti = decode.getId();
        // 获取token的失效时间
        Long exp = decode.getClaim(RegisteredClaims.EXPIRES_AT).asLong();

        // 加入到黑名单，定时任务清除
        blackList.put(jti, exp);
    }

    /**
     * 校验token时判断token是否失效
     * @param token 要判断的token
     */
    private static boolean isTokenExpired(String token) {
        DecodedJWT decode = JWT.decode(token);

        // 获取token的jti
        String jti = decode.getId();
        // 只要在黑名单里就是手动失效的
        return blackList.containsKey(jti);
    }

    /**
     * 定时清理手动失效的token
     */
    public static void cleanRevokeToken() {
        // 清理已经失效的
        long now = Instant.now().getEpochSecond();
        blackList.entrySet().removeIf(entry -> entry.getValue() <= now);
    }

}
