package com.rookie.common.exception;

import cn.hutool.core.util.StrUtil;
import com.rookie.common.exception.error.ErrorCodeInterface;
import java.util.HashMap;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yayee
 */
@EqualsAndHashCode(callSuper = true)
@Slf4j
@Data
public class ApiException extends RuntimeException {

    protected ErrorCodeInterface errorCode;

    protected String message;

    /**
     * 如果有一些特殊的数据  可以放在这个payload里面
     * 有时候错误的返回信息太少  不便前端处理的话  可以放在这个payload字段当中
     * 比如你做了一个大批量操作，操作ID为1~10的实体， 其中1~5成功   6~10失败
     * 你可以将这些相关信息放在这个payload中
     */
    protected HashMap<String, Object> payload;

    public ApiException(ErrorCodeInterface errorCode) {
        fillErrorCode(errorCode);
    }

    public ApiException(ErrorCodeInterface errorCode, Object... args) {
        fillErrorCode(errorCode, args);
    }

    public ApiException(Throwable e, ErrorCodeInterface errorCode, Object... args) {
        super(e);
        fillErrorCode(errorCode, args);
    }

    private void fillErrorCode(ErrorCodeInterface errorCode, Object... args) {
        this.errorCode = errorCode;
        this.message = StrUtil.format(errorCode.message(), args);
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getLocalizedMessage() {
        return message;
    }
}
