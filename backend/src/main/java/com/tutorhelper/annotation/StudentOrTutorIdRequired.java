package com.tutorhelper.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StudentOrTutorIdRequiredValidator.class)
public @interface StudentOrTutorIdRequired {

    String message() default "Either studentId or tutorId must be provided";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
