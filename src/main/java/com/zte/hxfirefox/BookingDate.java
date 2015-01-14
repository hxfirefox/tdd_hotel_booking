package com.zte.hxfirefox;

import com.google.common.collect.Lists;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static java.util.Calendar.DATE;

/**
 * Created by 黄翔 on 15-1-11.
 */
public class BookingDate {
    public static final int TIME_FACTOR = 24 * 60 * 60 * 1000;
    public static final int BASE_DAY_INTERVAL = 1;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private Date checkinDate;
    private Date checkoutDate;

    public BookingDate(String checkinDateStr, String checkoutDateStr) {
        try {
            this.checkinDate = dateFormat.parse(checkinDateStr);
            this.checkoutDate = dateFormat.parse(checkoutDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("please input date like following format: year-month-day!");
        }
    }

    public Date getCheckinDate() {
        return checkinDate;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public long getDaysInterval() {
        return (checkoutDate.getTime() - checkinDate.getTime()) / TIME_FACTOR + BASE_DAY_INTERVAL;
    }

    public List<Date> getDateInterval() {
        final long daysInterval = getDaysInterval();
        GregorianCalendar calendar = new GregorianCalendar();
        List<Date> dates = Lists.newArrayList();

        for (int day = 0; day < daysInterval; day++) {
            calendar.setTime(checkinDate);
            calendar.add(DATE, day);
            dates.add(calendar.getTime());
        }

        return dates;
    }
}
