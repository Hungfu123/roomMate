package com.roommate.snakewatchers.domain.DTO;

import com.roommate.snakewatchers.adapter.web.validation.NotBetweenLessOneHourValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NotBetweenLessOneHourValidator.class)
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotBetweenLessOneHour {

    String message() default "Zeitspanne soll nicht weniger als eine Stunde sein!";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}