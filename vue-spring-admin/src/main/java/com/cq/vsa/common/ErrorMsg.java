package com.cq.vsa.common;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: chengqi
 * @Date: Created in 14:51 2019/4/9
 */
public enum ErrorMsg {

    /**
     *
     */
    BAD_REQUEST(400, "服务器不能处理请求"),
    USERNAME_OR_PASSWORD_ERROR(401, "用户名或密码错误"),
    ACCOUNT_LOCK(401, "账号被锁定，请联系管理员"),
    ACCOUNT_DISABLE(401, "账号不可用，请联系管理员"),
    ACCOUNT_EXPIRED(401, "账号已过期"),
    CREDENTIALS_EXPIRED(401, "密码已过期"),
    LOGIN_FAILURE(401, "登录失败"),
    AUTH_FAILURE(401, "认证失败"),
    FORBIDDEN(403, "您没有权限执行该操作");

    private int status;
    private String msg;

    ErrorMsg(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public void renderError(HttpServletResponse response) throws IOException {
        try(PrintWriter writer = response.getWriter()) {
            response.setStatus(status);
            response.setContentType("text/plain;charset=UTF-8");
            writer.write(msg);
            writer.flush();
        }
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}
