package cool.tch.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author denchouka
 * @description 加密工具类，提供密码加密和校验功能。
 * @date 2024/10/21 22:03
 */
public class EncryptUtils {

    // 创建一个单例的BCryptPasswordEncoder实例
    private static final BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder();

    // 私有化构造函数，防止外部实例化
    private EncryptUtils() {
        throw new AssertionError("工具类不允许实例化");
    }

    /**
     * 校验密码
     * @param rawPassword 用户输入的原始密码
     * @param encodedPassword 数据库中存储的加密后密码
     * @return 校验结果
     */
    public static boolean validate(String rawPassword, String encodedPassword) {
        return ENCODER.matches(rawPassword, encodedPassword);
    }

    /**
     * 加密
     * @param password：密码
     * @return 加密后的密码
     */
    public static String aesEncrypt(String password){
        return ENCODER.encode(password);
    }
}
