import org.junit.Before;
import org.junit.Test;

import static com.google.common.collect.ImmutableList.of;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by 黄翔 on 15-1-1.
 */
public class HotelPayTest {
    public static final String LAKEWOOD = "Lakewood";
    public static final String BRIDGEWOOD = "Bridgewood";
    public static final String RIDGEWOOD = "Ridgewood";

    private HotelPay lakewoodHotelPay;
    private HotelPay bridgewoodHotelPay;
    private HotelPay ridgewoodHotelPay;

    @Before
    public void setUp() throws Exception {
        lakewoodHotelPay = new HotelPay(new Hotel(LAKEWOOD, 3),
                new NormalCustomPrice(110, 90), new VipCustomPrice(80, 80));
        bridgewoodHotelPay = new HotelPay(new Hotel(BRIDGEWOOD, 4),
                new NormalCustomPrice(160, 60), new VipCustomPrice(110, 50));
        ridgewoodHotelPay = new HotelPay(new Hotel(RIDGEWOOD, 5),
                new NormalCustomPrice(220, 150), new VipCustomPrice(100, 40));
    }

    @Test
    public void should_return_normal_weekday_price_when_normal_custom_booking_lakewood_on_weekday() throws Exception {
        // given
        // when
        final int pay1 = lakewoodHotelPay.getPay(Identifier.NORMAL, "2015-1-1");
        final int pay2 = bridgewoodHotelPay.getPay(Identifier.NORMAL, "2015-1-1");
        final int pay3 = ridgewoodHotelPay.getPay(Identifier.NORMAL, "2015-1-1");
        // then
        assertThat(pay1, is(110));
        assertThat(pay2, is(160));
        assertThat(pay3, is(220));
    }

    @Test
    public void should_return_normal_weekend_price_when_normal_custom_booking_lakewood_on_weekend() throws Exception {
        // given
        // when
        final int pay1 = lakewoodHotelPay.getPay(Identifier.NORMAL, "2015-1-3");
        final int pay2 = bridgewoodHotelPay.getPay(Identifier.NORMAL, "2015-1-3");
        final int pay3 = ridgewoodHotelPay.getPay(Identifier.NORMAL, "2015-1-3");
        // then
        assertThat(pay1, is(90));
        assertThat(pay2, is(60));
        assertThat(pay3, is(150));
    }

    @Test
    public void should_return_vip_weekday_price_when_vip_custom_booking_lakewood_on_weekday() throws Exception {
        // given
        // when
        final int pay1 = lakewoodHotelPay.getPay(Identifier.VIP, "2015-1-1");
        final int pay2 = bridgewoodHotelPay.getPay(Identifier.VIP, "2015-1-1");
        final int pay3 = ridgewoodHotelPay.getPay(Identifier.VIP, "2015-1-1");
        // then
        assertThat(pay1, is(80));
        assertThat(pay2, is(110));
        assertThat(pay3, is(100));
    }

    @Test
    public void should_return_vip_weekend_price_when_vip_custom_booking_lakewood_on_weekend() throws Exception {
        // given
        // when
        final int pay1 = lakewoodHotelPay.getPay(Identifier.VIP, "2015-1-3");
        final int pay2 = bridgewoodHotelPay.getPay(Identifier.VIP, "2015-1-3");
        final int pay3 = ridgewoodHotelPay.getPay(Identifier.VIP, "2015-1-3");
        // then
        assertThat(pay1, is(80));
        assertThat(pay2, is(50));
        assertThat(pay3, is(40));
    }

    @Test
    public void should_return_sum_pay_when_normal_custom_booking_3_weekday() throws Exception {
        // given
        // when
        final int paySum1 = lakewoodHotelPay.getPaySum(Identifier.NORMAL,
                of("2014-12-30", "2014-12-31", "2015-1-1"));
        final int paySum2 = bridgewoodHotelPay.getPaySum(Identifier.NORMAL,
                of("2014-12-30", "2014-12-31", "2015-1-1"));
        final int paySum3 = ridgewoodHotelPay.getPaySum(Identifier.NORMAL,
                of("2014-12-30", "2014-12-31", "2015-1-1"));
        // then
        assertThat(paySum1, is(330));
        assertThat(paySum2, is(480));
        assertThat(paySum3, is(660));
    }

    @Test
    public void should_return_sum_pay_when_vip_custom_booking_3_weekday() throws Exception {
        // given
        // when
        final int paySum1 = lakewoodHotelPay.getPaySum(Identifier.VIP,
                of("2014-12-30", "2014-12-31", "2015-1-1"));
        final int paySum2 = bridgewoodHotelPay.getPaySum(Identifier.VIP,
                of("2014-12-30", "2014-12-31", "2015-1-1"));
        final int paySum3 = ridgewoodHotelPay.getPaySum(Identifier.VIP,
                of("2014-12-30", "2014-12-31", "2015-1-1"));
        // then
        assertThat(paySum1, is(240));
        assertThat(paySum2, is(330));
        assertThat(paySum3, is(300));
    }

    @Test
    public void should_return_sum_pay_when_normal_custom_booking_1_weekday_and_2_weekend() throws Exception {
        // given
        // when
        final int paySum1 = lakewoodHotelPay.getPaySum(Identifier.NORMAL,
                of("2014-12-28", "2014-12-31", "2015-1-3"));
        final int paySum2 = bridgewoodHotelPay.getPaySum(Identifier.NORMAL,
                of("2014-12-28", "2014-12-31", "2015-1-3"));
        final int paySum3 = ridgewoodHotelPay.getPaySum(Identifier.NORMAL,
                of("2014-12-28", "2014-12-31", "2015-1-3"));
        // then
        assertThat(paySum1, is(290));
        assertThat(paySum2, is(280));
        assertThat(paySum3, is(520));
    }

    @Test
    public void should_return_sum_pay_when_vip_custom_booking_1_weekday_and_2_weekend() throws Exception {
        // given
        // when
        final int paySum1 = lakewoodHotelPay.getPaySum(Identifier.VIP,
                of("2014-12-28", "2014-12-31", "2015-1-3"));
        final int paySum2 = bridgewoodHotelPay.getPaySum(Identifier.VIP,
                of("2014-12-28", "2014-12-31", "2015-1-3"));
        final int paySum3 = ridgewoodHotelPay.getPaySum(Identifier.VIP,
                of("2014-12-28", "2014-12-31", "2015-1-3"));
        // then
        assertThat(paySum1, is(240));
        assertThat(paySum2, is(210));
        assertThat(paySum3, is(180));
    }

    @Test
    public void should_return_sum_pay_when_normal_custom_booking_2_weekday_and_1_weekend() throws Exception {
        // given
        // when
        final int paySum1 = lakewoodHotelPay.getPaySum(Identifier.NORMAL,
                of("2014-12-29", "2014-12-31", "2015-1-3"));
        final int paySum2 = bridgewoodHotelPay.getPaySum(Identifier.NORMAL,
                of("2014-12-29", "2014-12-31", "2015-1-3"));
        final int paySum3 = ridgewoodHotelPay.getPaySum(Identifier.NORMAL,
                of("2014-12-29", "2014-12-31", "2015-1-3"));
        // then
        assertThat(paySum1, is(310));
        assertThat(paySum2, is(380));
        assertThat(paySum3, is(590));
    }

    @Test
    public void should_return_sum_pay_when_vip_custom_booking_2_weekday_and_1_weekend() throws Exception {
        // given
        // when
        final int paySum1 = lakewoodHotelPay.getPaySum(Identifier.VIP,
                of("2014-12-29", "2014-12-31", "2015-1-3"));
        final int paySum2 = bridgewoodHotelPay.getPaySum(Identifier.VIP,
                of("2014-12-29", "2014-12-31", "2015-1-3"));
        final int paySum3 = ridgewoodHotelPay.getPaySum(Identifier.VIP,
                of("2014-12-29", "2014-12-31", "2015-1-3"));
        // then
        assertThat(paySum1, is(240));
        assertThat(paySum2, is(270));
        assertThat(paySum3, is(240));
    }

}
