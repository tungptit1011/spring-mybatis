package com.ptit.mybatis.utli;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

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
