package com.cq.vsa.enumobj.permission;

/**
 * @Author: chengqi
 * @Date: Created in 9:10 2019/4/10
 */
public enum MenuType {

    /**
     *
     */
    FIRST_LEVEL(1, "一级菜单"),
    SECOND_LEVEL(2, "二级菜单");

    private int level;
    private String name;

    MenuType(int level, String name) {
        this.level = level;
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }
}
