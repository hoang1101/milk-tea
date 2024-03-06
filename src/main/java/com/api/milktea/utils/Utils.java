package com.api.milktea.utils;

import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
@Service
public class Utils {


    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    public static java.sql.Date convertStringToDate(String dateString) {
        try {
            java.util.Date utilDate = DATE_FORMAT.parse(dateString);
            long timeInMillis = utilDate.getTime();
            return new java.sql.Date(timeInMillis);
        } catch (ParseException e) {
            e.printStackTrace();
            // Handle the exception gracefully, return null or throw a custom exception
            return null;
        }
    }

}
