package com.cq.vsa.jpa.enumobj;

/**
 * @Author: chengqi
 * @Date: Created in 17:21 2019/4/19
 */
public enum QueryType {

    /**
     *
     */
    EQUAL,
    BETWEEN,
    LESS_THAN,
    LESS_THAN_EQUAL,
    GREATER_THAN,
    GREATER_THAN_EQUAL,
    NOT_EQUAL,
    IS_NULL,
    IS_NOT_NULL,
    RIGHT_LIKE,
    LEFT_LIKE,
    FULL_LIKE,
    DEFAULT_LIKE,
    NOT_LIKE,
    IN;
}
