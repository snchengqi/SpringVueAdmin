package com.cq.vsa.jpa.domain;

/**
 * @Author: chengqi
 * @Date: Created in 16:36 2019/4/21
 */
public interface OrgNexus<C> {

    Iterable<C> getNexus();
}
