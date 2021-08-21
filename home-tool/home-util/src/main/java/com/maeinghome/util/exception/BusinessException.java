package com.maeinghome.util.exception;

import com.maeinghome.util.api.ResultCode;
import lombok.Data;

/**
 * 业务异常对象
 *
 * @author Eva.Caesar Liu
 * @date 2021/07/08 13:56
 */
@Data
public class BusinessException extends RuntimeException {

    private Integer code;

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(Integer code, String message, Throwable e) {
        super(message, e);
        this.code = code;
    }

    public BusinessException(ResultCode code) {
        super(code.getMessage());
        this.code = code.getCode();
    }

    public BusinessException(ResultCode code, Throwable e) {
        super(code.getMessage(), e);
        this.code = code.getCode();
    }
}
