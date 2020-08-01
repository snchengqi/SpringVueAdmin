package com.cq.vsa.jpa.domain;

/**
 * @Author: chengqi
 * @Date: Created in 17:26 2019/4/20
 */
public class QueryBetween<T extends Comparable<?>> {

    private T before;
    private T after;

    public QueryBetween() {}

    public QueryBetween(T before, T after) {
        this.before = before;
        this.after = after;
    }

    public T getBefore() {
        return before;
    }

    public void setBefore(T before) {
        this.before = before;
    }

    public T getAfter() {
        return after;
    }

    public void setAfter(T after) {
        this.after = after;
    }
}
