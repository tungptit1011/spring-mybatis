/**
 * Copyright 2023 NguyenDacTung
 */
package com.ptit.mybatis.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FullNameKanaValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FullNameKanaFormat {
    String message() default "Enter full name kana in incorrect format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
