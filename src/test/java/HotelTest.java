import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class HotelTest {
    private Hotel lakewoodHotel;
    private Hotel bridgewoodHotel;

    @Before
    public void setUp() throws Exception {
        lakewoodHotel = new Hotel("Lakewood", 3);
        bridgewoodHotel = new Hotel("Bridgewood", 4);
    }

    @Test
    public void should_4_star_bigger_than_3_star() throws Exception {
        // given
        // when
        final Hotel hotel = lakewoodHotel.moreAdvanced(bridgewoodHotel);
        // then
        assertThat(hotel, is(bridgewoodHotel));
    }
}