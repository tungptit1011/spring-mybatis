/**
 * Copyright 2023 NguyenDacTung
 */
package com.ptit.mybatis.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessException extends RuntimeException {
    private String errorStatus;
    private String msg;

    public BusinessException(String errorStatus, String msg) {
        this.errorStatus = errorStatus;
        this.msg = msg;
    }

    public BusinessException(String msg) {
        this.msg = msg;
    }
}

