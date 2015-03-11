package com.zte.hxfirefox.strategy;

import com.google.common.collect.Lists;
import com.zte.hxfirefox.booking.BookInfo;

import java.util.List;

public class CompositeHotelCharge implements HotelCharge {

    private List<HotelCharge> charges = Lists.newArrayList();

    public CompositeHotelCharge with(HotelCharge taxiCharge) {
        charges.add(taxiCharge);
        return this;
    }

    @Override
    public int getHotelCharge(BookInfo bookInfo) {
        int sum = 0;
        for (HotelCharge charge : charges) {
            sum += charge.getHotelCharge(bookInfo);
        }
        return sum;
    }

    public CompositeHotelCharge withWeekday(String price) {
        return with(new WeekdayHotelCharge(price));
    }

    public CompositeHotelCharge withWeekend(String price) {
        return with(new WeekendHotelCharge(price));
    }
}
