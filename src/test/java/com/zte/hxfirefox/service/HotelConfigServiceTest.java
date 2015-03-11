package com.zte.hxfirefox.service;

import org.junit.Before;
import org.junit.Test;

import static com.zte.hxfirefox.service.HotelConfigService.CUSTOM_NORMAL;
import static com.zte.hxfirefox.service.HotelConfigService.getInstance;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class HotelConfigServiceTest {
    private HotelConfigService service;

    @Before
    public void setUp() throws Exception {
        service = getInstance();
    }

    @Test
    public void should_get_star_level_form_resource() throws Exception {
        //given

        //when
        final String weekdayPriceInLakewood = service.getStarLevel("lakewood");
        //then
        assertThat(weekdayPriceInLakewood, is("3"));
    }

    @Test
    public void should_get_weekday_price_form_resource() throws Exception {
        //given

        //when
        final String weekdayPriceInLakewood = service.getWeekdayByCustom("lakewood", CUSTOM_NORMAL);
        //then
        assertThat(weekdayPriceInLakewood, is("110"));
    }

    @Test
    public void should_get_weekend_price_form_resource() throws Exception {
        //given

        //when
        final String weekdayPriceInLakewood = service.getWeekendByCustom("lakewood", CUSTOM_NORMAL);
        //then
        assertThat(weekdayPriceInLakewood, is("90"));
    }
}