package com.zte.hxfirefox.booking;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.zte.hxfirefox.booking.HotelFactory;
import com.zte.hxfirefox.booking.BookInfo;
import com.zte.hxfirefox.service.HotelConfigService;

import java.util.List;
import java.util.Map;

public class HotelChoose {
    private final List<String> hotels;
    private final BookInfo bookInfo;
    private HotelConfigService hotelConfigService;

    public HotelChoose(List<String> hotels, BookInfo bookInfo) {
        this.hotels = hotels;
        this.bookInfo = bookInfo;
        this.hotelConfigService = HotelConfigService.getInstance();
    }

    public String chooseHotel() {
        final List<Map.Entry<String, Integer>> sortedSet = sortByFareThenStar(calcChoosenHotelFare());
        return getPreferredHotel(sortedSet);
    }

    private String getPreferredHotel(List<Map.Entry<String, Integer>> sortedSet) {
        return sortedSet.iterator().next().getKey();
    }

    private Map<String, Integer> calcChoosenHotelFare() {
        Map<String, Integer> sums = Maps.newHashMap();
        for (String hotel : hotels) {
            sums.put(hotel, new HotelFactory().getCharge(hotel).getHotelCharge(bookInfo));
        }
        return sums;
    }

    private List<Map.Entry<String, Integer>> sortByFareThenStar(
            Map<String, Integer> hotelFareMap) {
        Function<Map.Entry<String, Integer>, Integer> getHotelFare =
                new Function<Map.Entry<String, Integer>, Integer>() {
                    @Override
                    public Integer apply(Map.Entry<String, Integer> integerHotelFareEntry) {
                        return integerHotelFareEntry.getValue();
                    }
                };

        Function<Map.Entry<String, Integer>, Integer> getStarLevel =
                new Function<Map.Entry<String, Integer>, Integer>() {
                    @Override
                    public Integer apply(Map.Entry<String, Integer> integerHotelFareEntry) {
                        final String starLevel = hotelConfigService.getStarLevel(integerHotelFareEntry.getKey());
                        return Integer.valueOf(starLevel);
                    }
                };

        return getSortedEntries(hotelFareMap, getHotelFare, getStarLevel);
    }

    private List<Map.Entry<String, Integer>> getSortedEntries(Map<String, Integer> hotelPaySumMap, Function<Map.Entry<String, Integer>, Integer> getHotelFare, Function<Map.Entry<String, Integer>, Integer> getStarLevel) {
        Ordering<Map.Entry<String, Integer>> payOrdering =
                Ordering.natural().onResultOf(getHotelFare);
        Ordering<Map.Entry<String, Integer>> starOrdering =
                Ordering.natural().reverse().onResultOf(getStarLevel);
        Ordering<Map.Entry<String, Integer>> payAndStarOrdering =
                payOrdering.compound(starOrdering);

        return payAndStarOrdering.sortedCopy(hotelPaySumMap.entrySet());
    }
}
