package com.example.demo.util;

import java.time.LocalDate;

public class CheckCommonPeriod {
    public static boolean check(LocalDate start1, LocalDate end1, LocalDate start2, LocalDate end2) {
        LocalDate endDate1 = (end1 == null || end1.equals("null")) ? LocalDate.now() : end1;
        LocalDate endDate2 = (end2 == null || end2.equals("null")) ? LocalDate.now() : end2;

        return start1.isBefore(endDate2) && endDate1.isAfter(start2);
    }
}
