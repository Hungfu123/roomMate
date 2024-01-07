package com.roommate.snakewatchers.adapter.web.validation;

import com.roommate.snakewatchers.domain.DTO.BookingDTO;
import com.roommate.snakewatchers.domain.DTO.NotBeforeStartDate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotBeforeStartDateValidator implements ConstraintValidator<NotBeforeStartDate, BookingDTO> {
    @Override
    public boolean isValid(BookingDTO value, ConstraintValidatorContext context) {
        if (value.getDateTimeFrom() == null || value.getDateTimeTo() == null) {
            // Let @NotNull handle this case
            return true;
        }
        return value.getDateTimeFrom().isBefore(value.getDateTimeTo());
    }

}
