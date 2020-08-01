package com.cq.vsa.jpa.annotation;

import java.lang.annotation.*;

/**
 * @Author: chengqi
 * @Date: Created in 16:39 2019/4/21
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SecurityFilter {

    boolean pageable() default false;
}
