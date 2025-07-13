package cool.tch;


import cool.tch.enums.TaskStatusEnum;
import cool.tch.util.DateUtils;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static cool.tch.common.Constant.ZONEID_SHANGHAI;

@SpringBootTest
public class SslSentinelBackendApplicationTests {

    @Test
    public void contextLoads() {
        Assertions.assertTrue(true);
    }

    @Test
    public void testDateUtils() {
        // 指定时区为Asia/Shanghai
        ZoneId shanghaiZone = ZoneId.of(ZONEID_SHANGHAI);

        // 获取今天
        LocalDate today = LocalDate.now();

        // 转换为Date
        Date todayDate = Date.from(today.atStartOfDay(shanghaiZone).toInstant());
        Date yesterdayDate = Date.from(today.minusDays(1).atStartOfDay(shanghaiZone).toInstant());
        Date tomorrowDate = Date.from(today.plusDays(1).atStartOfDay(shanghaiZone).toInstant());

        System.out.println("昨天: " + yesterdayDate);
        System.out.println("今天: " + todayDate);
        System.out.println("明天: " + tomorrowDate);

        //Assertions.assertTrue(!DateUtils.isTodayBeforeThanDate(yesterdayDate), "yesterdayDate");
        //Assertions.assertTrue(!DateUtils.isTodayBeforeThanDate(todayDate), "todayDate");
        //Assertions.assertTrue(DateUtils.isTodayBeforeThanDate(tomorrowDate), "tomorrowDate");

        mthd(yesterdayDate);
        mthd(todayDate);
        mthd(tomorrowDate);

    }

    public void mthd (Date ddl) {
        System.out.println("ddl: " + ddl);
        if (DateUtils.isTodayBeforeThanDate(ddl)) {
            // 当“今天<ddl”时，只更新db
            System.out.println("只更新db " + TaskStatusEnum.COMPLETED.getStatus());
        } else {// 更新db
            System.out.println("更新db " + TaskStatusEnum.IN_PROGRESS.getStatus());
            // 插入新的执行历史
            System.out.println("插入新的执行历史");
            // 发邮件提醒
            System.out.println("发邮件提醒");

        }
    }

}
