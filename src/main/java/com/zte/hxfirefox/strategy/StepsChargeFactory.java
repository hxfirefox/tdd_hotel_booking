package com.zte.hxfirefox.strategy;

import com.zte.hxfirefox.service.HotelConfigService;
import com.zte.hxfirefox.strategy.ChargeFactory;
import com.zte.hxfirefox.strategy.CompositeHotelCharge;
import com.zte.hxfirefox.strategy.HotelCharge;

import static com.zte.hxfirefox.service.HotelConfigService.*;

public class StepsChargeFactory implements ChargeFactory {
    private final HotelConfigService hotelConfigService;

    public StepsChargeFactory(HotelConfigService hotelConfigService) {
        this.hotelConfigService = hotelConfigService;
    }

    @Override
    public HotelCharge getNormalCustomCharge(String hotel) {
        return getCustomCharge(hotel, CUSTOM_NORMAL);
    }

    @Override
    public HotelCharge getVipCustomCharge(String hotel) {
        return getCustomCharge(hotel, CUSTOM_VIP);
    }

    private HotelCharge getCustomCharge(String hotel, String customType) {
        final CompositeHotelCharge compositeHotelCharge = new CompositeHotelCharge().withWeekday(hotelConfigService.getWeekdayByCustom(hotel, customType));
        return compositeHotelCharge.withWeekend(hotelConfigService.getWeekendByCustom(hotel, customType));
    }
}
