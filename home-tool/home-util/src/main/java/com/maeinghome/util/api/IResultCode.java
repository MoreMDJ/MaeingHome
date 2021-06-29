package com.maeinghome.util.api;

import java.io.Serializable;

public interface IResultCode extends Serializable {
    /**
     * @return 信息
     */
    String getMessage();

    /**
     * @return 状态码
     */
    Integer getCode();
}
