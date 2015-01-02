import org.junit.Before;
import org.junit.Test;

import static com.google.common.collect.ImmutableList.of;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class HotelChoosenTest {
    public static final String LAKEWOOD = "Lakewood";
    public static final String BRIDGEWOOD = "Bridgewood";
    public static final String RIDGEWOOD = "Ridgewood";

    private static HotelChoosen hotelChoosen;
    private HotelPay lakewoodHotelPay;
    private HotelPay bridgewoodHotelPay;
    private HotelPay ridgewoodHotelPay;

    @Before
    public void setUp() throws Exception {
        hotelChoosen = new HotelChoosen();
        lakewoodHotelPay = new HotelPay(new Hotel(LAKEWOOD, 3),
                new NormalCustomPrice(110, 90), new VipCustomPrice(80, 80));
        bridgewoodHotelPay = new HotelPay(new Hotel(BRIDGEWOOD, 4),
                new NormalCustomPrice(160, 60), new VipCustomPrice(110, 50));
        ridgewoodHotelPay = new HotelPay(new Hotel(RIDGEWOOD, 5),
                new NormalCustomPrice(220, 150), new VipCustomPrice(100, 40));
    }

    @Test
    public void should_choose_the_cheapest_but_the_most_advanced_hotel_when_normal_custom_booking_3_weekday() throws Exception {
        // given
        // when
        final Hotel hotel = hotelChoosen.chooseHotel(of(lakewoodHotelPay, bridgewoodHotelPay, ridgewoodHotelPay),
                Identifier.NORMAL, of("2014-12-30", "2014-12-31", "2015-1-1"));
        // then
        assertThat(hotel.getLevel(), is(3));
        assertThat(hotel.getHotelName(), is(LAKEWOOD));
    }

    @Test
    public void should_choose_the_cheapest_but_the_most_advanced_hotel_when_vip_custom_booking_3_weekday() throws Exception {
        // given
        // when
        final Hotel hotel = hotelChoosen.chooseHotel(of(lakewoodHotelPay, bridgewoodHotelPay, ridgewoodHotelPay),
                Identifier.VIP, of("2014-12-30", "2014-12-31", "2015-1-1"));
        // then
        assertThat(hotel.getLevel(), is(3));
        assertThat(hotel.getHotelName(), is(LAKEWOOD));
    }

    @Test
    public void should_choose_the_cheapest_but_the_most_advanced_hotel_when_normal_custom_booking_1_weekday_and_2_weekend() throws Exception {
        // given
        // when
        final Hotel hotel = hotelChoosen.chooseHotel(of(lakewoodHotelPay, bridgewoodHotelPay, ridgewoodHotelPay),
                Identifier.NORMAL, of("2014-12-28", "2014-12-31", "2015-1-3"));
        // then
        assertThat(hotel.getLevel(), is(4));
        assertThat(hotel.getHotelName(), is(BRIDGEWOOD));
    }

    @Test
    public void should_choose_the_cheapest_but_the_most_advanced_hotel_when_vip_custom_booking_1_weekday_and_2_weekend() throws Exception {
        // given
        // when
        final Hotel hotel = hotelChoosen.chooseHotel(of(lakewoodHotelPay, bridgewoodHotelPay, ridgewoodHotelPay),
                Identifier.VIP, of("2014-12-28", "2014-12-31", "2015-1-3"));
        // then
        assertThat(hotel.getLevel(), is(5));
        assertThat(hotel.getHotelName(), is(RIDGEWOOD));
    }

    @Test
    public void should_choose_the_cheapest_but_the_most_advanced_hotel_when_normal_custom_booking_2_weekday_and_1_weekend() throws Exception {
        // given
        // when
        final Hotel hotel = hotelChoosen.chooseHotel(of(lakewoodHotelPay, bridgewoodHotelPay, ridgewoodHotelPay),
                Identifier.NORMAL, of("2014-12-29", "2014-12-31", "2015-1-3"));
        // then
        assertThat(hotel.getLevel(), is(3));
        assertThat(hotel.getHotelName(), is(LAKEWOOD));
    }

    @Test
    public void should_choose_the_cheapest_but_the_most_advanced_hotel_when_vip_custom_booking_2_weekday_and_1_weekend() throws Exception {
        // given
        // when
        final Hotel hotel = hotelChoosen.chooseHotel(of(lakewoodHotelPay, bridgewoodHotelPay, ridgewoodHotelPay),
                Identifier.VIP, of("2014-12-29", "2014-12-31", "2015-1-3"));
        // then
        assertThat(hotel.getLevel(), is(5));
        assertThat(hotel.getHotelName(), is(RIDGEWOOD));
    }
}