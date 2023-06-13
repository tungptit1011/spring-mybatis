package com.ptit.mybatis.validate;

import com.ptit.mybatis.utli.ConstantValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateFormatValidator implements ConstraintValidator<com.ptit.mybatis.validate.DateFormat, String>  {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            DateFormat df = new SimpleDateFormat(ConstantValidator.DATE_FORMAT);
            df.setLenient(false);
            df.parse(value);
            return true;
        }catch (ParseException e) {
            return false;
        }
    }
}
