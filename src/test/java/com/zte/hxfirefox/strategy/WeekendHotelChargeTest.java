package com.zte.hxfirefox.strategy;

import com.zte.hxfirefox.booking.BookInfo;
import org.junit.Before;
import org.junit.Test;

import static com.zte.hxfirefox.utils.CustomType.NORMAL;
import static com.zte.hxfirefox.utils.DateUtils.getDate;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class WeekendHotelChargeTest {
    private WeekendHotelCharge weekendHotelCharge;

    @Before
    public void setUp() throws Exception {
        weekendHotelCharge = new WeekendHotelCharge("100");
    }

    @Test
    public void should_only_calculate_weekend_fare() throws Exception {
        //given

        //when
        final int hotelCharge = weekendHotelCharge.getHotelCharge(new BookInfo(NORMAL, getDate("2015-2-26"), getDate("2015-2-27"), getDate("2015-2-28")));
        //then
        assertThat(hotelCharge, is(100));
    }
}