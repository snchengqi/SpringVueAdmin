package com.cq.vsa.validator.annotation;

import com.cq.vsa.validator.machine.MenuConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 菜单实体校验注解
 * @Author: chengqi
 * @Date: Created in 14:12 2019/4/12
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = MenuConstraintValidator.class)
public @interface MenuConstraint {

    String message() default "菜单数据错误";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
