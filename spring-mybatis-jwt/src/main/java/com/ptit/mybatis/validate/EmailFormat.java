/**
 * Copyright 2023 NguyenDacTung
 */
package com.ptit.mybatis.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmailFormatValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailFormat {
    String message() default "Enter email in incorrect format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
