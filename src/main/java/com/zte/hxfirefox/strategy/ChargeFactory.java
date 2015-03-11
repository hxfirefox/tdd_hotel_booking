package com.zte.hxfirefox.strategy;

public interface ChargeFactory {

    public HotelCharge getNormalCustomCharge(String hotel);
    public HotelCharge getVipCustomCharge(String hotel);
}
