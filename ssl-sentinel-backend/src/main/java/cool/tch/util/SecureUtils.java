package cool.tch.util;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import org.mindrot.jbcrypt.BCrypt;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static cool.tch.common.Constant.TOKEN_SECRET_AES_KEY;

/**
 * @author denchouka
 * @description 加密工具类，提供密码加密和校验功能。
 * @date 2024/10/21 22:03
 */
public class SecureUtils {

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
     * 密码加密
     * @param password：密码
     * @return 加密后的密码
     */
    public static String hashPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * aes加密（默认密钥最大长度16，十六进制编码下就是32个字符）
     * @param content 要加密的文本
     * @return 加密后的文本（十六进制）
     */
    public static String encrypt(String content) {
        byte[] aesKey = TOKEN_SECRET_AES_KEY.getBytes(StandardCharsets.UTF_8);
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, aesKey);
        return aes.encryptHex(content);
    }

    /**
     * aes解密（默认密钥最大长度16，十六进制编码下就是32个字符）
     * @param content 要解密的文本（十六进制）
     * @return 解密后的文本
     */
    public static String decrypt(String content) {
        byte[] aesKey = TOKEN_SECRET_AES_KEY.getBytes(StandardCharsets.UTF_8);
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, aesKey);
        return aes.decryptStr(content, StandardCharsets.UTF_8);
    }
}
