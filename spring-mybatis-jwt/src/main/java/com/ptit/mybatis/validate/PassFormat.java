/**
 * Copyright 2023 NguyenDacTung
 */
package com.ptit.mybatis.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PassFormatValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PassFormat {
    String message() default "Enter password in incorrect format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
