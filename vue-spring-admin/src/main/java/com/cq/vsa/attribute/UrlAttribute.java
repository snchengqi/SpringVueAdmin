package com.cq.vsa.attribute;

/**
 * @Author: chengqi
 * @Date: Created in 14:29 2019/4/8
 */
public class UrlAttribute {

    public static class Request {
        public String url;
        public String method;

        public Request(String url, String method) {
            this.url = url;
            this.method = method;
        }
    }

    public static class Permission {
        public static final Request LOGIN = new Request("/login", "post");
    }
}
