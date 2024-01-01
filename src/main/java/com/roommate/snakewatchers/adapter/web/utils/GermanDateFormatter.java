package com.roommate.snakewatchers.adapter.web.utils;

import org.springframework.cglib.core.Local;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class GermanDateFormatter implements Formatter<LocalDateTime> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    @Override
    public LocalDateTime parse(String text, Locale locale) throws ParseException {
        return LocalDateTime.parse(text, FORMATTER);
    }


    @Override
    public String print(LocalDateTime date, Locale locale) {
        return date.format(FORMATTER);
    }
}
