package cool.tch.annotations;

import cool.tch.validator.FutureOrPresentDayValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author denchouka
 * @description 必须是现在或未来的日期（以天为精度）
 * @date 2024/12/4 22:52
 */
@Documented
@Constraint(validatedBy = FutureOrPresentDayValidator.class)
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
public @interface FutureOrPresentDay {

    String message() default "必须是现在或未来的日期（以天为精度）";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
