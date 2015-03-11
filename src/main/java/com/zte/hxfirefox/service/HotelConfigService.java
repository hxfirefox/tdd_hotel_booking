package com.zte.hxfirefox.service;

import java.io.IOException;
import java.util.Properties;

public class HotelConfigService {
    public static final String STAR_LEVEL = "starlevel";
    public static final String WEEKDAY = "weekday";
    public static final String WEEKEND = "weekend";
    public static final String CUSTOM_NORMAL = "normal";
    public static final String CUSTOM_VIP = "vip";
    private static final String HOTEL_CONFIG = "/hotel.properties";
    private static HotelConfigService hotelConfigService = new HotelConfigService();
    private Properties hotelConfig;

    private HotelConfigService() {
    }

    public static HotelConfigService getInstance() {
        return hotelConfigService;
    }

    public String getWeekdayByCustom(String hotel, String customType) {
        return get(hotel, customType, WEEKDAY);
    }

    public String getWeekendByCustom(String hotel, String customType) {
        return get(hotel, customType, WEEKEND);
    }

    public String getStarLevel(String hotel) {
        ensureLoadConfig();
        return hotelConfig.getProperty(String.format("%s.%s", hotel, STAR_LEVEL));
    }

    private String get(String hotel, String customType, String dayOfWeek) {
        ensureLoadConfig();
        final String property = hotelConfig.getProperty(String.format("%s.%s", hotel, customType));
        final String[] split = property.split(",");
        return WEEKDAY.equals(dayOfWeek) ? split[0] : split[1];
    }

    private void ensureLoadConfig() {
        try {
            if (hotelConfig == null) {
                final Properties properties = new Properties();
                properties.load(this.getClass().getResourceAsStream(HOTEL_CONFIG));
                hotelConfig = properties;
            }
        } catch (IOException e) {
            throw new RuntimeException("Can not load taxi config.", e);
        }
    }
}
