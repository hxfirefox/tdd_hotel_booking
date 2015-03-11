package com.zte.hxfirefox.strategy;

import com.zte.hxfirefox.booking.BookInfo;
import org.junit.Before;
import org.junit.Test;

import static com.zte.hxfirefox.utils.CustomType.NORMAL;
import static com.zte.hxfirefox.utils.DateUtils.getDate;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class WeekdayHotelChargeTest {
    private WeekdayHotelCharge weekdayHotelCharge;

    @Before
    public void setUp() throws Exception {
        weekdayHotelCharge = new WeekdayHotelCharge("100");
    }

    @Test
    public void should_only_calculate_weekday_fare() throws Exception {
        //given

        //when
        final int hotelCharge = weekdayHotelCharge.getHotelCharge(new BookInfo(NORMAL, getDate("2015-2-27"), getDate("2015-2-27"), getDate("2015-2-28")));
        //then
        assertThat(hotelCharge, is(200));
    }
}