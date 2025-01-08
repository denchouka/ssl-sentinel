package cool.tch.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author denchouka
 * @description 日期工具类
 * @date 2025/1/8 20:45
 */
public class DateUtils {

    /**
     * 日期格式化
     * @param date 日期数据
     * @return 格式化后的日期数据
     */
    public static String parseDate(Date date) {
        // 指定时区为Asia/Shanghai
        ZoneId shanghaiZone = ZoneId.of("Asia/Shanghai");
        // 日期转换为LocalDate
        LocalDate localDate = date.toInstant().atZone(shanghaiZone).toLocalDate();
        // 使用DateTimeFormatter格式化
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return localDate.format(formatter);
    }
}
