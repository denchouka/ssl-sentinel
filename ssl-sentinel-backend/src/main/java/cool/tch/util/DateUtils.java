package cool.tch.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static cool.tch.common.Constant.*;

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
        ZoneId shanghaiZone = ZoneId.of(ZONEID_SHANGHAI);
        // 日期转换为LocalDate
        LocalDate localDate = date.toInstant().atZone(shanghaiZone).toLocalDate();
        // 使用DateTimeFormatter格式化
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATTER_DATE);

        return localDate.format(formatter);
    }

    /**
     * 时间格式化
     * @param dateTime 时间数据
     * @return 格式化后的时间数据
     */
    public static String parseDateTime(Date dateTime) {
        // 指定时区为Asia/Shanghai
        ZoneId shanghaiZone = ZoneId.of(ZONEID_SHANGHAI);
        // 日期转换为LocalDate
        LocalDateTime localDateTime = dateTime.toInstant().atZone(shanghaiZone).toLocalDateTime();
        // 使用DateTimeFormatter格式化
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATTER_DATETIME);

        return localDateTime.format(formatter);
    }

    /**
     * 判断日期1是否早于日期2
     * @param date1 日期1
     * @param date2 日期2
     * @return
     */
    public static boolean isBefore(Date date1, Date date2) {

        // 指定时区为Asia/Shanghai
        ZoneId shanghaiZone = ZoneId.of(ZONEID_SHANGHAI);
        // date1转换为LocalDate
        LocalDate localDate1 = date1.toInstant().atZone(shanghaiZone).toLocalDate();
        // date2转换为LocalDate
        LocalDate localDate2 = date2.toInstant().atZone(shanghaiZone).toLocalDate();

        return localDate1.isBefore(localDate2);
    }

    /**
     * 今天是否早于指定日期（不包括今天）
     * @param date 指定日期
     * @return 判断结果
     */
    public static boolean isTodayBeforeThanDate(Date date) {
        // 指定时区为Asia/Shanghai
        ZoneId shanghaiZone = ZoneId.of(ZONEID_SHANGHAI);
        // 获取当前日期
        LocalDate today = LocalDate.now(shanghaiZone);

        // date转换为LocalDate
        LocalDate localDate = date.toInstant().atZone(shanghaiZone).toLocalDate();

        return today.isBefore(localDate);
    }

    /**
     * 获取当前的系统时间
     * @return 当前的系统时间
     */
    public static String now() {
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();

        // 格式化输出
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATTER_DATETIME);
        return now.format(formatter);
    }

    /**
     * 获取当前的年
     * @return 当前年
     */
    public static int thisYear() {
        return Year.now().getValue();
    }

}
