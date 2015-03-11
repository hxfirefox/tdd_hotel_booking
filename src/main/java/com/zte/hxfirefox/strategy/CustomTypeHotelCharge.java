package com.zte.hxfirefox.strategy;

import com.zte.hxfirefox.booking.BookInfo;
import com.zte.hxfirefox.strategy.HotelCharge;
import com.zte.hxfirefox.utils.CustomType;

import static com.zte.hxfirefox.utils.CustomType.*;

public class CustomTypeHotelCharge implements HotelCharge {
    private final HotelCharge normalCustomCharge;
    private final HotelCharge vipCustomCharge;

    public CustomTypeHotelCharge(HotelCharge normalCustomCharge, HotelCharge vipCustomCharge) {
        this.normalCustomCharge = normalCustomCharge;
        this.vipCustomCharge = vipCustomCharge;
    }

    @Override
    public int getHotelCharge(BookInfo bookInfo) {
        return isVipCustom(bookInfo.getCustomType()) ? vipCustomCharge.getHotelCharge(bookInfo) : normalCustomCharge.getHotelCharge(bookInfo);
    }

    private boolean isVipCustom(CustomType customType) {
        return customType == VIP;
    }
}
