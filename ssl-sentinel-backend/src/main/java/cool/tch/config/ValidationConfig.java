package cool.tch.config;

import cool.tch.exception.BusinessException;
import cool.tch.validator.CustomConstraintValidator;
import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.internal.constraintvalidators.bv.time.futureorpresent.FutureOrPresentValidatorForDate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.*;

/**
 * @author denchouka
 * @description 自定义校验配置
 * @date 2024/12/1 15:46
 */
@Configuration
public class ValidationConfig {

    @Bean
    public Validator validator() {
        ValidatorFactory factory = Validation.byProvider(HibernateValidator.class)
                .configure()
                // 设置自定义的CustomConstraintValidatorFactory，用于创建约束验证器实例
                .constraintValidatorFactory(new CustomConstraintValidatorFactory())
                .buildValidatorFactory();

        return factory.getValidator();
    }
}

/**
 * 通过自定义的 ConstraintValidatorFactory 替换默认的 FutureOrPresentValidatorForDate 验证器为 CustomConstraintValidator。
 * 当应用中使用 @FutureOrPresent 注解时，实际使用的是自定义验证器CustomConstraintValidator。
 */
class CustomConstraintValidatorFactory implements ConstraintValidatorFactory {

    @Override
    public <T extends ConstraintValidator<?, ?>> T getInstance(Class<T> key) {
        try {
            // FutureOrPresentValidatorForDate时替换成自定义校验器
            if (key.equals(FutureOrPresentValidatorForDate.class)) {
                return (T) new CustomConstraintValidator();
            }
            return key.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            // 抛出自定义异常，用于全局异常捕获
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public void releaseInstance(ConstraintValidator<?, ?> instance) {

    }
}
