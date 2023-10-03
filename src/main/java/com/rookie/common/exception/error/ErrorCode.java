package com.rookie.common.exception.error;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author yayee
 */
public enum ErrorCode implements ErrorCodeInterface {

    /**
     * 成功
     */
    SUCCESS(200, "success"),
    /**
     * 失败
     */
    FAIL(500, "failed"),

    /**
     * HTTP 状态码
     */
    HTTP_STATUS_200(200, "ok"),
    HTTP_STATUS_400(400, "request error"),
    HTTP_STATUS_401(401, "no authentication"),
    HTTP_STATUS_403(403, "no authorities"),
    HTTP_STATUS_500(500, "server error");

    public static final List<ErrorCode> HTTP_STATUS_ALL = Collections.unmodifiableList(
        Arrays.asList(HTTP_STATUS_200, HTTP_STATUS_400, HTTP_STATUS_401, HTTP_STATUS_403, HTTP_STATUS_500));

    /**
     * response code
     */
    private final int code;

    /**
     * msg
     */
    private final String msg;

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int code() {
        return this.code;
    }

    @Override
    public String message() {
        return this.msg;
    }


    /**
     * 10000~99999 为业务逻辑 错误码 （无代码异常，代码正常流转，并返回提示给用户）
     * 1XX01   XX是代表模块的意思 比如10101   01是Permission模块 错误码的命名最好以模块为开头
     * 比如OBJECT_NOT_FOUND前面加上COMMON = COMMON_OBJECT_NOT_FOUND
     */
    public enum Business implements ErrorCodeInterface {

        // ------------------------------- COMMON -------------------------------------------
        COMMON_OBJECT_NOT_FOUND(10001, "找不到ID为 {} 的 {}"),
        COMMON_UNSUPPORTED_OPERATION(10002, "不支持的操作"),

        // ------------------------------- USER ---------------------------------------------
        USER_NAME_IS_NOT_UNIQUE(10101, "用户名已被其他用户占用"),
        USER_PHONE_IS_NOT_UNIQUE(10102, "该电话号码已被其他用户占用"),
        USER_NEW_PASSWORD_IS_THE_SAME_AS_OLD(10103, "用户新密码与旧密码相同"),
        USER_PASSWORD_IS_NOT_CORRECT(10104, "用户密码错误"),

        // ------------------------------- ROLE ---------------------------------------------
        ROLE_NAME_IS_NOT_UNIQUE(10201, "角色名称已存在");

        private final int code;
        private final String msg;

        Business(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        @Override
        public int code() {
            return this.code;
        }

        @Override
        public String message() {
            return this.msg;
        }
    }
}
