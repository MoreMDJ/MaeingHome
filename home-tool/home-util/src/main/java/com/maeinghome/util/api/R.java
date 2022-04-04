package com.maeinghome.util.api;

import com.maeinghome.util.constant.MaeingConstant;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class R<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 返回的信息
     */
    private String msg;

    /**
     * 返回的数据
     */
    private T data;

    public R() {
    }

    private R(IResultCode resultCode) {
        this(resultCode, null, resultCode.getMessage());
    }

    private R(IResultCode resultCode, String msg) {
        this(resultCode, null, msg);
    }

    private R(IResultCode resultCode, T data) {
        this(resultCode, data, resultCode.getMessage());
    }

    private R(IResultCode resultCode, T data, String msg) {
        this(resultCode.getCode(), data, msg);
    }

    private R(Integer code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.success = ResultCode.SUCCESS.code.equals(code);
    }

    public static <T> R<T> data(T data) {
        return data(data, MaeingConstant.DEFAULT_MESSAGE_SUCCESS);
    }

    public static <T> R<T> data(T data, String msg) {
        return data(200, data, msg);
    }

    public static <T> R<T> data(Integer code, T data, String msg) {
        return new R(code, data, data == null ? MaeingConstant.DEFAULT_MESSAGE_NULL : msg);
    }

    public static <T> R<T> success(String msg) {
        return new R(ResultCode.SUCCESS, msg);
    }

    public static <T> R<T> success(IResultCode resultCode) {
        return new R(resultCode);
    }

    public static <T> R<T> success(IResultCode resultCode, String msg) {
        return new R(resultCode, msg);
    }

    public static <T> R<T> fail(String msg) {
        return new R(ResultCode.FAILURE, msg);
    }

    public static <T> R<T> fail(Integer code, String msg) {
        return new R(code, null, msg);
    }

    public static <T> R<T> fail(IResultCode resultCode) {
        return new R(resultCode);
    }

    public static <T> R<T> fail(IResultCode resultCode, String msg) {
        return new R(resultCode, msg);
    }

    public static <T> R<T> status(boolean flag) {
        return flag ? success(MaeingConstant.DEFAULT_MESSAGE_SUCCESS) : fail(MaeingConstant.DEFAULT_MESSAGE_FAILURE);
    }

}
