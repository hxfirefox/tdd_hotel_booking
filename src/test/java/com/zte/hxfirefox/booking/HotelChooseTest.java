package com.zte.hxfirefox.booking;

import org.junit.Test;

import java.util.List;

import static com.google.common.collect.ImmutableList.of;
import static com.zte.hxfirefox.utils.DateUtils.getDate;
import static com.zte.hxfirefox.utils.CustomType.NORMAL;
import static com.zte.hxfirefox.utils.CustomType.VIP;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class HotelChooseTest {

    private List<String> hotels = of("lakewood", "bridgewood", "ridgewood");
    private HotelChoose hotelChoose;

    @Test
    public void should_choose_the_cheapest_but_the_most_advanced_hotel_when_normal_custom_booking_3_weekday() throws Exception {
        // given
        hotelChoose = new HotelChoose(hotels, new BookInfo(NORMAL, getDate("2014-12-30"), getDate("2015-1-1")));
        // when
        final String hotel = hotelChoose.chooseHotel();
        // then
        assertThat(hotel, is("lakewood"));
    }

    @Test
    public void should_choose_the_cheapest_but_the_most_advanced_hotel_when_vip_custom_booking_3_weekday() throws Exception {
        // given
        hotelChoose = new HotelChoose(hotels, new BookInfo(VIP, getDate("2014-12-30"), getDate("2015-1-1")));
        // when
        final String hotel = hotelChoose.chooseHotel();
        // then
        assertThat(hotel, is("lakewood"));
    }

    @Test
    public void should_choose_the_cheapest_but_the_most_advanced_hotel_when_normal_custom_booking_1_weekday_and_2_weekend() throws Exception {
        // given
        hotelChoose = new HotelChoose(hotels, new BookInfo(NORMAL, getDate("2014-12-28"), getDate("2014-12-31"), getDate("2015-1-3")));
        // when
        final String hotel = hotelChoose.chooseHotel();
        // then
        assertThat(hotel, is("bridgewood"));
    }

    @Test
    public void should_choose_the_cheapest_but_the_most_advanced_hotel_when_vip_custom_booking_1_weekday_and_2_weekend() throws Exception {
        // given
        hotelChoose = new HotelChoose(hotels, new BookInfo(VIP, getDate("2014-12-28"), getDate("2014-12-31"), getDate("2015-1-3")));
        // when
        final String hotel = hotelChoose.chooseHotel();
        // then
        assertThat(hotel, is("ridgewood"));
    }

    @Test
    public void should_choose_the_cheapest_but_the_most_advanced_hotel_when_normal_custom_booking_2_weekday_and_1_weekend() throws Exception {
        // given
        hotelChoose = new HotelChoose(hotels, new BookInfo(NORMAL, getDate("2014-12-29"), getDate("2014-12-31"), getDate("2015-1-3")));
        // when
        final String hotel = hotelChoose.chooseHotel();
        // then
        assertThat(hotel, is("lakewood"));
    }

    @Test
    public void should_choose_the_cheapest_but_the_most_advanced_hotel_when_vip_custom_booking_2_weekday_and_1_weekend() throws Exception {
        // given
        hotelChoose = new HotelChoose(hotels, new BookInfo(VIP, getDate("2014-12-29"), getDate("2014-12-31"), getDate("2015-1-3")));
        // when
        final String hotel = hotelChoose.chooseHotel();
        // then
        assertThat(hotel, is("ridgewood"));
    }
}
