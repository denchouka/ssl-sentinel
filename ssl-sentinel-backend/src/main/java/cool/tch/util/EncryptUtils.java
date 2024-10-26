package cool.tch.util;

import org.mindrot.jbcrypt.BCrypt;

/**
 * @author denchouka
 * @description 加密工具类，提供密码加密和校验功能。
 * @date 2024/10/21 22:03
 */
public class EncryptUtils {

    /**
     * 校验密码
     * @param rawPassword 用户输入的原始密码
     * @param encodedPassword 数据库中存储的加密后密码
     * @return 校验结果
     */
    public static boolean validate(String rawPassword, String encodedPassword) {
        return BCrypt.checkpw(rawPassword, encodedPassword);
    }

    /**
     * 加密
     * @param password：密码
     * @return 加密后的密码
     */
    public static String hashPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
