import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;

import java.util.List;
import java.util.Map;


/**
 * Created by 黄翔 on 15-1-1.
 */
public class HotelChoosen {
    private final List<BookingDate> bookingDates;
    private CustomType type;

    public static final String LAKEWOOD = "Lakewood";
    public static final String BRIDGEWOOD = "Bridgewood";
    public static final String RIDGEWOOD = "Ridgewood";

    private final HotelPay lakewoodHotelPay = new HotelPay(new Hotel(LAKEWOOD, 3),
            new NormalCustomPrice(110, 90), new VipCustomPrice(80, 80));
    private final HotelPay bridgewoodHotelPay = new HotelPay(new Hotel(BRIDGEWOOD, 4),
            new NormalCustomPrice(160, 60), new VipCustomPrice(110, 50));
    private final HotelPay ridgewoodHotelPay = new HotelPay(new Hotel(RIDGEWOOD, 5),
            new NormalCustomPrice(220, 150), new VipCustomPrice(100, 40));
    private List<HotelPay> hotelPays = Lists.newArrayList();

    public HotelChoosen(String customType, List<BookingDate> dates, List<String> hotels) {
        bookingDates = dates;
        type = customType.equals("vip") ? CustomType.VIP : CustomType.NORMAL;
        InitHotelPay(hotels);
    }

    private void InitHotelPay(List<String> hotels) {
        for (String hotel : hotels) {
            if (hotel.equals(LAKEWOOD))
                hotelPays.add(lakewoodHotelPay);
            else if (hotel.equals(BRIDGEWOOD))
                hotelPays.add(bridgewoodHotelPay);
            else if (hotel.equals(RIDGEWOOD))
                hotelPays.add(ridgewoodHotelPay);
        }
    }

    public Hotel chooseHotel() {
        List<Map.Entry<HotelPay, Integer>> sortedSet =
                sortHotelPayByPriceThenStar(getHotelPaySumHashMap());

        printSortedSet(sortedSet);

        return sortedSet.iterator().next().getKey().getHotel();
    }

    private Map<HotelPay, Integer> getHotelPaySumHashMap() {
        Map<HotelPay, Integer> sums = Maps.newHashMap();
        for (HotelPay hotelPay : hotelPays) {
            sums.put(hotelPay, hotelPay.getPaySum(type, bookingDates));
        }
        return sums;
    }

    private List<Map.Entry<HotelPay, Integer>> sortHotelPayByPriceThenStar(
            Map<HotelPay, Integer> hotelPaySumMap) {
        Function<Map.Entry<HotelPay, Integer>, Integer> getHotelPay =
                new Function<Map.Entry<HotelPay, Integer>, Integer>() {
                    @Override
                    public Integer apply(Map.Entry<HotelPay, Integer> integerHotelPayEntry) {
                        return integerHotelPayEntry.getValue();
                    }
                };

        Function<Map.Entry<HotelPay, Integer>, Integer> getStarLevel =
                new Function<Map.Entry<HotelPay, Integer>, Integer>() {
                    @Override
                    public Integer apply(Map.Entry<HotelPay, Integer> integerHotelPayEntry) {
                        return integerHotelPayEntry.getKey().getHotel().getLevel();
                    }
                };

        return getSortedEntries(hotelPaySumMap, getHotelPay, getStarLevel);
    }

    private List<Map.Entry<HotelPay, Integer>> getSortedEntries(Map<HotelPay, Integer> hotelPaySumMap, Function<Map.Entry<HotelPay, Integer>, Integer> getHotelPay, Function<Map.Entry<HotelPay, Integer>, Integer> getStarLevel) {
        Ordering<Map.Entry<HotelPay, Integer>> payOrdering =
                Ordering.natural().onResultOf(getHotelPay);
        Ordering<Map.Entry<HotelPay, Integer>> starOrdering =
                Ordering.natural().reverse().onResultOf(getStarLevel);
        Ordering<Map.Entry<HotelPay, Integer>> payAndStarOrdering =
                payOrdering.compound(starOrdering);

        return payAndStarOrdering.sortedCopy(hotelPaySumMap.entrySet());
    }

    private void printSortedSet(List<Map.Entry<HotelPay, Integer>> sortedSet) {
        for (Map.Entry<HotelPay, Integer> set : sortedSet) {
            final String format = String.format("Pay: %d in Hotel %s with level: %d",
                    set.getValue(),
                    set.getKey().getHotel().getHotelName(),
                    set.getKey().getHotel().getLevel());
            System.out.println(format);
            System.out.println();
        }
    }
}
