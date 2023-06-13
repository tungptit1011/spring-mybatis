package com.ptit.mybatis.validate;

import com.ptit.mybatis.utli.ConstantValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailFormatValidator implements ConstraintValidator<EmailFormat, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value != null) {
            if (value.length() > ConstantValidator.MAX_LENGTH_EMAIL) {
                return false;
            }
        }
        return value.matches(ConstantValidator.REGX_EMAIL);
    }
}
