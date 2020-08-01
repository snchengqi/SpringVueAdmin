package com.cq.vsa.enumobj.permission;

import java.util.Arrays;

/**
 * @Author: chengqi
 * @Date: Created in 14:58 2019/4/8
 */
public enum HttpMethod {

    /**
     *
     */
    GET(1, "GET"),
    POST(2, "POST"),
    PUT(3, "PUT"),
    DELETE(4, "DELETE");

    private int code;
    private String name;

    HttpMethod(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static HttpMethod getInstance(String name) {
        return Arrays.stream(HttpMethod.values()).
                filter(instance -> instance.name.equalsIgnoreCase(name)).findFirst().
                orElse(null);
    }

    public int getCode() {
        return code;
    }
}
