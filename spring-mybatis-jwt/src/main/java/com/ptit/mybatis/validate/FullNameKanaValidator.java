/**
 * Copyright 2023 NguyenDacTung
 */
package com.ptit.mybatis.validate;

import com.ptit.mybatis.utils.ConstantValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FullNameKanaValidator implements ConstraintValidator<FullNameKanaFormat, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value != null) {
            if (value.length() > ConstantValidator.MAX_LENGTH_FULL_NAME_KANA) {
                return false;
            }
            return value.matches(ConstantValidator.REGX_FULL_NAME_KANA);
        }
        return true;
    }
}
