package com.rookie.common.core.dto;

import com.rookie.common.exception.ApiException;
import com.rookie.common.exception.error.ErrorCode;
import lombok.Builder;
import lombok.Data;

/**
 * @author yayee
 */
@Data
@Builder
public class ResponseDTO<T> {

    /**
     * response timestamp.
     */
    private long timestamp;

    /**
     * response code, 200 -> OK.
     */
    private Integer status;

    /**
     * response message.
     */
    private String message;

    /**
     * response data.
     */
    private T data;

    public static <T> ResponseDTO<T> ok() {
        return build(null, ErrorCode.SUCCESS.code(), ErrorCode.SUCCESS.message());
    }

    public static <T> ResponseDTO<T> ok(T data) {
        return build(data, ErrorCode.SUCCESS.code(), ErrorCode.SUCCESS.message());
    }

    public static <T> ResponseDTO<T> fail() {
        return build(null, ErrorCode.FAIL.code(), ErrorCode.FAIL.message());
    }

    public static <T> ResponseDTO<T> fail(T data) {
        return build(data, ErrorCode.FAIL.code(), ErrorCode.FAIL.message());
    }

    public static <T> ResponseDTO<T> fail(ApiException exception) {
        return build(null, exception.getErrorCode().code(), exception.getMessage());
    }

    public static <T> ResponseDTO<T> fail(ApiException exception, T data) {
        return build(data, exception.getErrorCode().code(), exception.getMessage());
    }

    public static <T> ResponseDTO<T> build(T data, Integer code, String msg) {
        return ResponseDTO.<T>builder().data(data).message(msg).status(code).timestamp(System.currentTimeMillis())
            .build();
    }
}
