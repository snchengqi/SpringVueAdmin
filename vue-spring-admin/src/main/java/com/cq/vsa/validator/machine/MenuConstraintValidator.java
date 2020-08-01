package com.cq.vsa.validator.machine;

import com.cq.vsa.domain.permission.UpMenu;
import com.cq.vsa.enumobj.permission.MenuType;
import com.cq.vsa.validator.annotation.MenuConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

/**
 * 菜单实体校验器
 * @Author: chengqi
 * @Date: Created in 14:14 2019/4/12
 */
public class MenuConstraintValidator implements ConstraintValidator<MenuConstraint, UpMenu> {

    private static final String MESSAGE = ":必须为0或者1";

    @Override
    public void initialize(MenuConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(UpMenu value, ConstraintValidatorContext context) {
        List<Integer> enums = Arrays.asList(0, 1);
        //菜单等级
        Integer level = value.getLevel();
        if (level == null) {
            return false;
        }
        context.disableDefaultConstraintViolation();
        //一级菜单
        if (MenuType.FIRST_LEVEL.getLevel() == level) {
            if (!enums.contains(value.getAlwaysShow())) {
                context.buildConstraintViolationWithTemplate("alwaysShow" + MESSAGE);
                return false;
            }
        }
        //二级菜单
        if (MenuType.SECOND_LEVEL.getLevel() == level) {
            if (!enums.contains(value.getHidden())) {
                context.buildConstraintViolationWithTemplate("hidden" + MESSAGE);
                return false;
            }
            if (!enums.contains(value.getNoCache())) {
                context.buildConstraintViolationWithTemplate("noCache" + MESSAGE);
                return false;
            }
        }
        return true;
    }
}
