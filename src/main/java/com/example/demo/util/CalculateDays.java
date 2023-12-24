package com.example.demo.util;

import com.example.demo.model.History;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CalculateDays {
    public static int calculate(History history1, History history2) {
        LocalDate startDate1 = history1.getStartDate();
        LocalDate endDate1 = history1.getEndDate().equals("null") ? LocalDate.now() : history1.getEndDate();
        LocalDate startDate2 = history2.getStartDate();
        LocalDate endDate2 = history2.getEndDate().equals("null") ? LocalDate.now() : history2.getEndDate();

        LocalDate overlapStartDate = startDate1.isAfter(startDate2) ? startDate1 : startDate2;
        LocalDate overlapEndDate = endDate1.isBefore(endDate2) ? endDate1 : endDate2;

        return (int) ChronoUnit.DAYS.between(overlapStartDate, overlapEndDate);
    }
}
