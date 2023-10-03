package com.rookie.common.exception;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

/**
 * @author yayee
 */
@Data
@Builder
public class ExceptionData {

    @Singular
    private final List<Object> errors;
}
