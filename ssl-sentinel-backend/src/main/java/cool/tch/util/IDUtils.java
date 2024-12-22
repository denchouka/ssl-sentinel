package cool.tch.util;

import cn.hutool.core.util.IdUtil;

/**
 * @author denchouka
 * @description 生成id的工具类
 * @date 2024/12/22 14:02
 */
public class IDUtils {

    /**
     * 生成token用的唯一jti
     * @return
     */
    public static String generateJti() {
        return IdUtil.simpleUUID();
    }
}
