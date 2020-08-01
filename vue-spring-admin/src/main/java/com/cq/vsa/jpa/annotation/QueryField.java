package com.cq.vsa.jpa.annotation;

import com.cq.vsa.jpa.enumobj.QueryType;

import java.lang.annotation.*;

/**
 * @Author: chengqi
 * @Date: Created in 17:25 2019/4/19
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface QueryField {

    QueryType type() default QueryType.EQUAL;

    String name() default "";

    boolean nullAble() default true;
}
