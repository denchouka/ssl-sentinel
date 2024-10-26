package cool.tch.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import cool.tch.common.Constant;
import cool.tch.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;

/**
 * @author denchouka
 * @description Token工具类
 * @date 2024/10/26 21:07
 */
public class TokenUtils {

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
            return JWT.create()
                    .withHeader(header)
                    // 添加声明
                    .withClaim("username", username)
                    .withClaim("ip", ip)
                    .withClaim("time", Instant.now())
                    // 加盐
                    .withClaim("salt", Constant.TOKEN_SECRET_SALT)
                    // 1H后过期
                    .withExpiresAt(Date.from(Instant.now().plusSeconds(Constant.TOKEN_EXPIRE_DATE)))
                    // 密钥
                    .sign(Algorithm.HMAC256(Constant.TOKEN_SECRET_KEY));
        } catch (Exception e) {
            throw new BusinessException("生成token失败");
        }
    }


    /**
     * 校验token
     * @param token 请求头中携带的token
     * @return 校验结果
     */
    public static boolean verify(String token) {

        // 判空
        if (StringUtils.isBlank(token)) {
            return false;
        }

        // 验证token有效性
        try{
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(Constant.TOKEN_SECRET_KEY)).build();
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }
}
