package cool.tch.util;

import org.springframework.beans.BeanUtils;

/**
 * @author denchouka
 * @description 对象复制工具类
 * @date 2024/12/17 21:50
 */
public class BeanCopyUtils {

    /**
     * 复制对象
     *
     * @param source 源
     * @param target 目标
     * @return {@link T}
     */
    public static <T> T copyObject(Object source, Class<T> target) {
        T temp = null;
        try {
            temp = target.newInstance();
            if (null != source) {
                BeanUtils.copyProperties(source, temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

}
