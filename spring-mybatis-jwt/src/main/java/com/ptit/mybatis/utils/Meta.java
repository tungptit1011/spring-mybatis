/**
 * Copyright 2023 NguyenDacTung
 */
package com.ptit.mybatis.utils;

import org.apache.commons.lang3.StringUtils;

public class Meta {
    String code;

    String message;

    public Meta() {
    }

    public Meta(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class Builder {
        public static Meta success() {
            return new Meta(ConstantStatus.OK.toString(), "Success");
        }

        public static Meta success(String message) {
            if (StringUtils.isEmpty(message)) {
                return success();
            } else return new Meta(ConstantStatus.OK, "Success");
        }

        public static Meta findNotFound() {
            return new Meta(ConstantStatus.NOT_FOUND, "Find not found");
        }

        public static Meta serverError() {
            return new Meta(ConstantStatus.INTERNAL_SERVER_ERROR, "Find not found");
        }

        public static Meta build(String code, String message) {
            return new Meta(code, message);
        }
    }
}
