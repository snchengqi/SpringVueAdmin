package com.cq.vsa.jpa.domain;

import javax.persistence.MappedSuperclass;

/**
 * @Author: chengqi
 * @Date: Created in 16:37 2019/4/21
 */
@MappedSuperclass
public class OrgBelong<T> {

    private T orgCode;

    public T getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(T orgCode) {
        this.orgCode = orgCode;
    }

    public boolean matcheUser() {
        return false;
    }
}
