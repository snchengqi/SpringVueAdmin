package com.cq.vsa.jpa.util;

import com.cq.vsa.jpa.domain.OrgBelong;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author: chengqi
 * @Date: Created in 17:26 2019/4/21
 */
public class UserOrgUtil {

    public static boolean matches(OrgBelong org) {
        return false;
    }

    public static Collection<?> getOrgNexus() {
        List<String> nexus = new ArrayList<>();
        nexus.add("CCO");
        return nexus;
    }
}
