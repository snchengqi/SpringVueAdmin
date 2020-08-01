package com.cq.vsa.enumobj.permission;

/**
 * @Author: chengqi
 * @Date: Created in 11:13 2019/4/8
 */
public enum UserLockStatus {

    /**
     *
     */
    NON_LOCK(0, "未锁定"),
    LOCK(1, "已锁定");

    private int status;
    private String name;

    UserLockStatus(int status, String name) {
        this.status = status;
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public static boolean isLock(int status) {
        return LOCK.getStatus() == status;
    }
}
