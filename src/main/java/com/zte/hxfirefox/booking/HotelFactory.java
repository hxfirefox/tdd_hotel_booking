package com.zte.hxfirefox.booking;

import com.zte.hxfirefox.strategy.ChargeFactory;
import com.zte.hxfirefox.strategy.CustomTypeHotelCharge;
import com.zte.hxfirefox.strategy.HotelCharge;
import com.zte.hxfirefox.strategy.StepsChargeFactory;
import com.zte.hxfirefox.service.HotelConfigService;

public class HotelFactory {
    public HotelCharge getCharge(final String hotel) {
        final HotelConfigService hotelConfigService = HotelConfigService.getInstance();
        final ChargeFactory factory = new StepsChargeFactory(hotelConfigService);
        return new CustomTypeHotelCharge(factory.getNormalCustomCharge(hotel), factory.getVipCustomCharge(hotel));
    }
}
