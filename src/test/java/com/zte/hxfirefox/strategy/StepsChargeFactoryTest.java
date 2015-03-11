package com.zte.hxfirefox.strategy;

import com.zte.hxfirefox.booking.BookInfo;
import com.zte.hxfirefox.service.HotelConfigService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.zte.hxfirefox.utils.CustomType.NORMAL;
import static com.zte.hxfirefox.utils.CustomType.VIP;
import static com.zte.hxfirefox.utils.DateUtils.getDate;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StepsChargeFactoryTest {
    private StepsChargeFactory stepsChargeFactory;
    @Mock
    private HotelConfigService hotelConfigService;

    @Before
    public void setUp() throws Exception {
        stepsChargeFactory = new StepsChargeFactory(hotelConfigService);
    }

    @Test
    public void should_get_normal_custom_charge_base_hotel() throws Exception {
        //given
        final String hotel = "lakewood";
        final String customType = "normal";
        //when
        when(hotelConfigService.getWeekdayByCustom(hotel, customType)).thenReturn("110");
        when(hotelConfigService.getWeekendByCustom(hotel, customType)).thenReturn("90");
        final int hotelCharge = stepsChargeFactory.getNormalCustomCharge(hotel).getHotelCharge(new BookInfo(NORMAL, getDate("2015-2-27"), getDate("2015-2-28")));
        //then
        assertThat(hotelCharge, is(200));
    }
    
    @Test
    public void should_get_vip_custom_charge_base_hotel() throws Exception {
        //given
        final String hotel = "lakewood";
        final String customType = "vip";
        //when
        when(hotelConfigService.getWeekdayByCustom(hotel, customType)).thenReturn("80");
        when(hotelConfigService.getWeekendByCustom(hotel, customType)).thenReturn("80");
        final int hotelCharge = stepsChargeFactory.getVipCustomCharge(hotel).getHotelCharge(new BookInfo(VIP, getDate("2015-2-27"), getDate("2015-2-28")));
        //then
        assertThat(hotelCharge, is(160));
    }
}