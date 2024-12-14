package cool.tch.annotations;

import cool.tch.validator.BeforeThanTargetDateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author denchouka
 * @description 自定义注解，校验的日期必须早于指定的日期
 * @date 2024/12/7 17:10
 */
@Documented
@Constraint(validatedBy = BeforeThanTargetDateValidator.class)
@Target({ ElementType.TYPE })
@Retention(RUNTIME)
public @interface BeforeThanTargetDate {

    String message() default "校验的日期必须早于指定的日期";

    // 校验的日期
    String checkedField();

    // 被校验的指定的日期
    String[] targetFields();

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
