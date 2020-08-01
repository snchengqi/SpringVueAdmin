package com.cq.vsa.common;

import com.cq.vsa.exception.CommonException;

/**
 * @Author: chengqi
 * @Date: Created in 15:12 2019/4/11
 */
public enum ErrorStatus {

    /**
     *
     */
    USER_EXIST(400, "用户已经存在"),
    USER_NOT_EXIST(400, "用户不存在"),
    ROLE_EXIST(400, "角色已经存在"),
    ROLE_NOT_EXIST(400, "角色不存在"),
    MENU_EXIST(400, "菜单已经存在"),
    MENU_NOT_EXIST(400, "菜单不存在"),
    UPBACKENDAPI_EXIST(400, "后端接口已存在"),
    UPBACKENDAPI_NOT_EXIST(400, "后端接口不存在"),
    BAD_REQUEST(400, "This is a bad request");

    private int status;
    private String message;

    ErrorStatus(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public void throwEx() {
        throw new CommonException(status, message);
    }
}
