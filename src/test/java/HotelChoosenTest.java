import org.junit.Test;

import java.util.List;

import static com.google.common.collect.ImmutableList.of;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class HotelChoosenTest {
    public static final String LAKEWOOD = "Lakewood";
    public static final String BRIDGEWOOD = "Bridgewood";
    public static final String RIDGEWOOD = "Ridgewood";
    private List<String> hotels = of(LAKEWOOD, BRIDGEWOOD, RIDGEWOOD);

    private static HotelChoosen hotelChoosen;

    @Test
    public void should_choose_the_cheapest_but_the_most_advanced_hotel_when_normal_custom_booking_3_weekday() throws Exception {
        // given
        hotelChoosen = new HotelChoosen("normal",
                of(new BookingDate("2014-12-30", "2015-1-1")), hotels);
        // when
        final Hotel hotel = hotelChoosen.chooseHotel();
        // then
        assertThat(hotel.getLevel(), is(3));
        assertThat(hotel.getHotelName(), is(LAKEWOOD));
    }

    @Test
    public void should_choose_the_cheapest_but_the_most_advanced_hotel_when_vip_custom_booking_3_weekday() throws Exception {
        // given
        hotelChoosen = new HotelChoosen("vip",
                of(new BookingDate("2014-12-30", "2015-1-1")), hotels);
        // when
        final Hotel hotel = hotelChoosen.chooseHotel();
        // then
        assertThat(hotel.getLevel(), is(3));
        assertThat(hotel.getHotelName(), is(LAKEWOOD));
    }

    @Test
    public void should_choose_the_cheapest_but_the_most_advanced_hotel_when_normal_custom_booking_1_weekday_and_2_weekend() throws Exception {
        // given
        hotelChoosen = new HotelChoosen("normal",
                of(new BookingDate("2014-12-28", "2014-12-28"),
                        new BookingDate("2014-12-31", "2014-12-31"),
                        new BookingDate("2015-1-3", "2015-1-3")), hotels);
        // when
        final Hotel hotel = hotelChoosen.chooseHotel();
        // then
        assertThat(hotel.getLevel(), is(4));
        assertThat(hotel.getHotelName(), is(BRIDGEWOOD));
    }

    @Test
    public void should_choose_the_cheapest_but_the_most_advanced_hotel_when_vip_custom_booking_1_weekday_and_2_weekend() throws Exception {
        // given
        hotelChoosen = new HotelChoosen("vip",
                of(new BookingDate("2014-12-28", "2014-12-28"),
                        new BookingDate("2014-12-31", "2014-12-31"),
                        new BookingDate("2015-1-3", "2015-1-3")), hotels);
        // when
        final Hotel hotel = hotelChoosen.chooseHotel();
        // then
        assertThat(hotel.getLevel(), is(5));
        assertThat(hotel.getHotelName(), is(RIDGEWOOD));
    }

    @Test
    public void should_choose_the_cheapest_but_the_most_advanced_hotel_when_normal_custom_booking_2_weekday_and_1_weekend() throws Exception {
        // given
        hotelChoosen = new HotelChoosen("normal",
                of(new BookingDate("2014-12-29", "2014-12-29"),
                        new BookingDate("2014-12-31", "2014-12-31"),
                        new BookingDate("2015-1-3", "2015-1-3")), hotels);
        // when
        final Hotel hotel = hotelChoosen.chooseHotel();
        // then
        assertThat(hotel.getLevel(), is(3));
        assertThat(hotel.getHotelName(), is(LAKEWOOD));
    }

    @Test
    public void should_choose_the_cheapest_but_the_most_advanced_hotel_when_vip_custom_booking_2_weekday_and_1_weekend() throws Exception {
        // given
        hotelChoosen = new HotelChoosen("vip",
                of(new BookingDate("2014-12-29", "2014-12-29"),
                        new BookingDate("2014-12-31", "2014-12-31"),
                        new BookingDate("2015-1-3", "2015-1-3")), hotels);
        // when
        final Hotel hotel = hotelChoosen.chooseHotel();
        // then
        assertThat(hotel.getLevel(), is(5));
        assertThat(hotel.getHotelName(), is(RIDGEWOOD));
    }
}