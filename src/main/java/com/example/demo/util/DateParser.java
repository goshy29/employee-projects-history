package com.example.demo.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateParser {
    public static LocalDate parse(String dateString) {
        DateTimeFormatter[] dateTimeFormatters = {
                DateTimeFormatter.ofPattern("yyyy-MM-dd"),
                DateTimeFormatter.ofPattern("MM/dd/yyyy"),
                DateTimeFormatter.ofPattern("dd/MM/yyyy")
        };

        for (DateTimeFormatter formatter : dateTimeFormatters) {
            try {
                return LocalDate.parse(dateString, formatter);
            } catch (Exception exception) {

            }
        }

        throw new IllegalArgumentException("Unsupported date format: " + dateString);
    }
}
