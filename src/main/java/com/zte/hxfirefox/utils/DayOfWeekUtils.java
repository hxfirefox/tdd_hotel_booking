package com.zte.hxfirefox.utils;

import com.zte.hxfirefox.booking.BookInfo;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static java.util.Calendar.DAY_OF_WEEK;

public class DayOfWeekUtils {
    public static boolean isWeekday(Date date) {
        int dayOfWeek = getDayOfWeek(date).get(DAY_OF_WEEK);
        return dayOfWeek != BookInfo.WEEK_SATURDAY && dayOfWeek != BookInfo.WEEK_SUNDAY;
    }

    private static Calendar getDayOfWeek(Date date) {
        final Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar;
    }
}
