/**
 * Copyright 2023 NguyenDacTung
 */
package com.ptit.mybatis.validate;

import com.ptit.mybatis.utils.ConstantValidator;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PassFormatValidator implements ConstraintValidator<PassFormat, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        int length = value.length();
        if (length < ConstantValidator.MIN_LENGTH_PASSWORD || length > ConstantValidator.MAX_LENGTH_PASSWORD) {
            return false;
        }
        return value.matches(ConstantValidator.REGX_PASSWORD);
    }
}
