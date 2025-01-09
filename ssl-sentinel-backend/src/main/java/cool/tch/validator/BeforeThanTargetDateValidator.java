package cool.tch.validator;

import cool.tch.annotations.BeforeThanTargetDate;
import cool.tch.exception.BusinessException;
import cool.tch.util.DateUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.util.Date;

/**
 * @author denchouka
 * @description 注解BeforeThanTargetDate的自定义验证器
 * @date 2024/12/7 17:15
 */
public class BeforeThanTargetDateValidator implements ConstraintValidator<BeforeThanTargetDate, Object> {

    // 校验的日期
    private String checkedField;

    // 被校验的指定的日期
    private String[] targetFields;

    @Override
    public void initialize(BeforeThanTargetDate constraintAnnotation) {
        checkedField = constraintAnnotation.checkedField();
        targetFields = constraintAnnotation.targetFields();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {

        // 此处不用判断null,所有的Date类型数据都已做null检查

        try {
            // 获取校验的日期
            Date checkedDate = getDateByFieldName(object, checkedField);

            for(String targetField : targetFields) {
                // 获取指定日期
                Date targetDate = getDateByFieldName(object, targetField);

                // 作比较
                if (!DateUtils.isBefore(checkedDate, targetDate)) {
                    return false;
                }
            }

            return true;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new BusinessException("类 " + object.getClass().getName() + " 自定义注解使用有误: " + e.getMessage());
        }
    }

    /**
     * 使用反射获取对象中执行字段的值
     * @param object
     * @param fieldName
     * @return
     */
    private Date getDateByFieldName(Object object, String fieldName) throws NoSuchFieldException, IllegalAccessException {

            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return (Date) field.get(object);
    }

}
