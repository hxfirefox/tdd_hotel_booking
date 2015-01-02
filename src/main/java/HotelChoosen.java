import com.google.common.base.Function;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Ordering;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by 黄翔 on 15-1-1.
 */
public class HotelChoosen {
    public Hotel chooseHotel(List<HotelPay> hotelPays, Identifier identifier, List<String> dates) throws ParseException {

        ImmutableSortedSet<Map.Entry<Integer, HotelPay>> sortedSet =
                sortHotelPayByPriceAndStar(getHotelPaySumMap(hotelPays, identifier, dates));

        printSortedSet(sortedSet);

        return sortedSet.iterator().next().getValue().getHotel();
    }

    private void printSortedSet(ImmutableSortedSet<Map.Entry<Integer, HotelPay>> sortedSet) {
        for (Map.Entry<Integer, HotelPay> set : sortedSet) {
            final String format = String.format("Pay: %d in Hotel %s with level: %d",
                    set.getKey(),
                    set.getValue().getHotel().getHotelName(),
                    set.getValue().getHotel().getLevel());
            System.out.println(format);
            System.out.println();
        }
    }

    private ImmutableSortedSet<Map.Entry<Integer, HotelPay>> sortHotelPayByPriceAndStar(
            Map<Integer, HotelPay> hotelPaySumMap) {
        Function<Map.Entry<Integer, HotelPay>, Integer> getHotelPay =
                new Function<Map.Entry<Integer, HotelPay>, Integer>() {
                    @Override
                    public Integer apply(Map.Entry<Integer, HotelPay> integerHotelPayEntry) {
                        return integerHotelPayEntry.getKey();
                    }
                };

        Function<Map.Entry<Integer, HotelPay>, Integer> getStarLevel =
                new Function<Map.Entry<Integer, HotelPay>, Integer>() {
                    @Override
                    public Integer apply(Map.Entry<Integer, HotelPay> integerHotelPayEntry) {
                        return integerHotelPayEntry.getValue().getHotel().getLevel();
                    }
                };

        Ordering<Map.Entry<Integer, HotelPay>> payOrdering =
                Ordering.natural().onResultOf(getHotelPay);
        Ordering<Map.Entry<Integer, HotelPay>> starOrdering =
                Ordering.natural().reverse().onResultOf(getStarLevel);
        Ordering<Map.Entry<Integer, HotelPay>> payAndStarOrdering =
                payOrdering.compound(starOrdering);
        // TODO: here is a bug....set will cover the same key!!!
        return ImmutableSortedSet
                .orderedBy(payAndStarOrdering)
                .addAll(hotelPaySumMap.entrySet())
                .build();
    }

    private Map<Integer, HotelPay> getHotelPaySumMap(List<HotelPay> hotelPays, Identifier identifier, List<String> dates) throws ParseException {
        Map<Integer, HotelPay> sums = new TreeMap<Integer, HotelPay>();
        for (HotelPay hotelPay : hotelPays) {
            sums.put(hotelPay.getPaySum(identifier, dates), hotelPay);
        }
        return sums;
    }
}
