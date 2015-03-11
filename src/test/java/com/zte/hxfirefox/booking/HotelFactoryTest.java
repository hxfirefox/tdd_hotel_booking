package com.zte.hxfirefox.booking;

import org.junit.Before;
import org.junit.Test;

import static com.zte.hxfirefox.utils.DateUtils.getDate;
import static com.zte.hxfirefox.utils.CustomType.NORMAL;
import static com.zte.hxfirefox.utils.CustomType.VIP;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class HotelFactoryTest {
    private HotelFactory hotelFactory;

    @Before
    public void setUp() throws Exception {
        hotelFactory = new HotelFactory();
    }

    @Test
    public void should_return_fare_when_normal_custom_choose_lakewood_on_weekday() throws Exception {
        //given

        //when
        final int charge = hotelFactory.getCharge("lakewood").getHotelCharge(new BookInfo(NORMAL, getDate("2015-2-25")));
        //then
        assertThat(charge,is(110));
    }

    @Test
    public void should_return_fare_when_vip_custom_choose_lakewood_on_weekday() throws Exception {
        //given

        //when
        final int charge = hotelFactory.getCharge("lakewood").getHotelCharge(new BookInfo(VIP, getDate("2015-2-25")));
        //then
        assertThat(charge,is(80));
    }

    @Test
    public void should_return_fare_when_normal_custom_choose_lakewood_within_2_weekdays_and_1_weekend() throws Exception {
        //given

        //when
        final int charge = hotelFactory.getCharge("lakewood")
                .getHotelCharge(new BookInfo(NORMAL, getDate("2015-2-22"), getDate("2015-2-25"), getDate("2015-2-26")));
        //then
        assertThat(charge,is(310));
    }

    @Test
    public void should_return_fare_when_vip_custom_choose_lakewood_within_2_weekdays_and_1_weekend() throws Exception {
        //given

        //when
        final int charge = hotelFactory.getCharge("lakewood")
                .getHotelCharge(new BookInfo(VIP, getDate("2015-2-22"), getDate("2015-2-25"), getDate("2015-2-26")));
        //then
        assertThat(charge,is(240));
    }
}