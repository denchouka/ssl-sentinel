package cool.tch.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static cool.tch.common.Constant.ZONEID_ASIA_SHANGHAI;

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
        ZoneId shanghaiZone = ZoneId.of(ZONEID_ASIA_SHANGHAI);
        // 日期转换为LocalDate
        LocalDate localDate = date.toInstant().atZone(shanghaiZone).toLocalDate();
        // 使用DateTimeFormatter格式化
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return localDate.format(formatter);
    }

    /**
     * 判断日期1是否早于日期2
     * @param date1 日期1
     * @param date2 日期2
     * @return
     */
    public static boolean isBefore(Date date1, Date date2) {

        // 指定时区为Asia/Shanghai
        ZoneId shanghaiZone = ZoneId.of(ZONEID_ASIA_SHANGHAI);
        // date2转换为LocalDate
        LocalDate localDate1 = date1.toInstant().atZone(shanghaiZone).toLocalDate();
        // date2转换为LocalDate
        LocalDate localDate2 = date2.toInstant().atZone(shanghaiZone).toLocalDate();

        return localDate1.isBefore(localDate2);
    }
}
