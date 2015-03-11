package com.zte.hxfirefox.strategy;

import com.zte.hxfirefox.booking.BookInfo;

import java.util.Date;

import static com.zte.hxfirefox.utils.DayOfWeekUtils.*;

public class WeekdayHotelCharge implements HotelCharge {
    private final String price;

    public WeekdayHotelCharge(String price) {
        this.price = price;
    }

    @Override
    public int getHotelCharge(BookInfo bookInfo) {
        int sum = 0;
        for (Date date : bookInfo.getBookDates()) {
            sum += isWeekday(date) ? Integer.valueOf(price) : 0;
        }
        return sum;
    }
}
