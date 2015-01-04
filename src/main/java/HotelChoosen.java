import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Created by 黄翔 on 15-1-1.
 */
public class HotelChoosen {
    public Hotel chooseHotel(List<HotelPay> hotelPays, Identifier identifier, List<String> dates) throws ParseException {

        List<Map.Entry<HotelPay, Integer>> sortedSet =
                sortHotelPayByPriceThenStar(getHotelPaySumHashMap(hotelPays, identifier, dates));

        printSortedSet(sortedSet);

        return sortedSet.iterator().next().getKey().getHotel();
    }

    private Map<HotelPay, Integer> getHotelPaySumHashMap(
            List<HotelPay> hotelPays, Identifier identifier,
            List<String> dates) throws ParseException {
        Map<HotelPay, Integer> sums = Maps.newHashMap();
        for (HotelPay hotelPay : hotelPays) {
            sums.put(hotelPay, hotelPay.getPaySum(identifier, dates));
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
