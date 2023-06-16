/**
 * Copyright 2023 NguyenDacTung
 */
package com.ptit.mybatis.validate;

import com.ptit.mybatis.utils.ConstantValidator;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TelFormatValidator implements ConstraintValidator<TelFormat, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(StringUtils.isEmpty(value)) {
            return false;
        }
        return value.matches(ConstantValidator.REGX_TEL);
    }
}
