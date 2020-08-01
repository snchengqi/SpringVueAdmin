package com.cq.vsa.validator.machine;

import com.cq.vsa.validator.annotation.IntegerEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

/**
 * 整型枚举校验器
 * @Author: chengqi
 * @Date: Created in 14:31 2019/4/11
 */
public class IntegerEnumValidator implements ConstraintValidator<IntegerEnum, Integer> {

    private int[] value;
    private boolean nullAble;

    @Override
    public void initialize(IntegerEnum constraintAnnotation) {
        this.value = constraintAnnotation.value();
        this.nullAble = constraintAnnotation.nullAble();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return this.nullAble;
        }
        return Arrays.stream(this.value).anyMatch(item -> item == value);
    }
}
