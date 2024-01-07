package com.roommate.snakewatchers.adapter.web.validation;

import com.roommate.snakewatchers.domain.DTO.BookingDTO;
import com.roommate.snakewatchers.domain.DTO.NotBetweenLessOneHour;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Duration;

public class NotBetweenLessOneHourValidator implements ConstraintValidator<NotBetweenLessOneHour, BookingDTO> {

    @Override
    public boolean isValid(BookingDTO value, ConstraintValidatorContext context) {
        if (value.getDateTimeFrom() == null || value.getDateTimeTo() == null) {
            // Let @NotNull handle this case
            return true;
        }
        if (!value.getDateTimeTo().isAfter(value.getDateTimeFrom())) {
            // Wenn Enddatum nicht nach Startdatum ist, keine Validierung durchführen
            return true;
        }
        Duration duration = Duration.between(value.getDateTimeFrom(), value.getDateTimeTo());
        return duration.toMinutes() >= 60;
    }

}
