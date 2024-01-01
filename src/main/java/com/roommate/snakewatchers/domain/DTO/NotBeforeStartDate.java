package com.roommate.snakewatchers.domain.DTO;

import com.roommate.snakewatchers.adapter.web.validation.NotBeforeStartDateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NotBeforeStartDateValidator.class)
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotBeforeStartDate {

    String message() default "Enddatum muss nach dem Startdatum liegen!";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}