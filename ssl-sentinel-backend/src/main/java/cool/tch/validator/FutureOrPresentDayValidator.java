package cool.tch.validator;

import cool.tch.annotations.FutureOrPresentDay;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static cool.tch.common.Constant.ZONEID_SHANGHAI;

/**
 * @author denchouka
 * @description 注解FutureOrPresentDay的自定义验证器
 * @date 2024/12/1 15:28
 */
public class FutureOrPresentDayValidator implements ConstraintValidator<FutureOrPresentDay, Date> {

    @Override
    public boolean isValid(Date date, ConstraintValidatorContext context) {
        // 指定时区为Asia/Shanghai
        ZoneId shanghaiZone = ZoneId.of(ZONEID_SHANGHAI);
        // 获取当前日期
        LocalDate now = LocalDate.now(shanghaiZone);
        // 要校验的日期转换为LocalDate
        LocalDate compare = date.toInstant().atZone(shanghaiZone).toLocalDate();

        // 判断校验的日期是否是今天或以后
        return !now.isAfter(compare);
    }
}
