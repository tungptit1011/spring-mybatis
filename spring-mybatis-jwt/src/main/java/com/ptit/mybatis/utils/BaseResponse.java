/**
 * Copyright 2023 NguyenDacTung
 */
package com.ptit.mybatis.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse<T> extends Meta {
    private T data;

    public BaseResponse(Meta meta, T data) {
        this.data = data;
        code = meta.code;
        message = meta.getMessage();
    }

    public BaseResponse(Meta meta) {
        code = meta.getCode();
        message = meta.getMessage();
    }
}
