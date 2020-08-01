package com.cq.vsa.validator.annotation;

import com.cq.vsa.validator.machine.IntegerEnumValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 整型枚举校验注解
 * @Author: chengqi
 * @Date: Created in 14:25 2019/4/11
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = IntegerEnumValidator.class)
public @interface IntegerEnum {

    /**
     * 枚举的值
     * @return
     */
    int[] value() default {};

    boolean nullAble() default true;

    String message() default "类型错误";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
