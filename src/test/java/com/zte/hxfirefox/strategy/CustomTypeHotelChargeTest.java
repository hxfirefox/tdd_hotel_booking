package com.zte.hxfirefox.strategy;

import com.zte.hxfirefox.booking.BookInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.zte.hxfirefox.utils.DateUtils.getDate;
import static com.zte.hxfirefox.utils.CustomType.NORMAL;
import static com.zte.hxfirefox.utils.CustomType.VIP;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomTypeHotelChargeTest {
    private CustomTypeHotelCharge customCharge;
    @Mock
    private HotelCharge normalCustomCharge;
    @Mock
    private HotelCharge vipCustomCharge;

    @Before
    public void setUp() throws Exception {
        customCharge = new CustomTypeHotelCharge(normalCustomCharge, vipCustomCharge);
    }

    @Test
    public void should_charge_normal_fare_when_custom_is_normal() throws Exception {
        //given
        final BookInfo bookInfo = new BookInfo(NORMAL, getDate("2015-2-27"));
        //when
        when(normalCustomCharge.getHotelCharge(bookInfo)).thenReturn(110);
        final int hotelCharge = customCharge.getHotelCharge(bookInfo);
        //then
        assertThat(hotelCharge, is(110));
    }    
    
    @Test
    public void should_charge_vip_fare_when_custom_is_vip() throws Exception {
        //given
        final BookInfo bookInfo = new BookInfo(VIP, getDate("2015-2-27"));
        //when
        when(vipCustomCharge.getHotelCharge(bookInfo)).thenReturn(90);
        final int hotelCharge = customCharge.getHotelCharge(bookInfo);
        //then
        assertThat(hotelCharge, is(90));
    }
}