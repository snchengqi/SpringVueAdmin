package com.cq.vsa.exception;

/**
 * @Author: chengqi
 * @Date: Created in 13:48 2019/4/8
 */
public class CommonException extends RuntimeException {

    private int status;

    private String message;

    public CommonException() {}

    public CommonException(int status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
