package com.ptit.mybatis.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TelFormatValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TelFormat {
    String message() default "Enter tel in incorrect format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
