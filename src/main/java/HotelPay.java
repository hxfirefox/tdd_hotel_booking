import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static java.util.Calendar.*;

/**
 * Created by 黄翔 on 15-1-1.
 */
public class HotelPay {
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    private final Hotel hotel;
    private final NormalCustomPrice normalCustomPrice;
    private final VipCustomPrice vipCustomPrice;

    public HotelPay(Hotel hotel, NormalCustomPrice normalCustomPrice, VipCustomPrice vipCustomPrice) {
        this.hotel = hotel;
        this.normalCustomPrice = normalCustomPrice;
        this.vipCustomPrice = vipCustomPrice;
    }

    public int getPay(Identifier identifier, String date) throws ParseException {
        return isWeekDay(date) ? getWeekdayPay(identifier) : getWeekendPay(identifier);
    }

    private int getWeekendPay(Identifier identifier) {
        return isVip(identifier) ? vipCustomPrice.getWeekendPrice()
                : normalCustomPrice.getWeekendPrice();
    }

    private int getWeekdayPay(Identifier identifier) {
        return isVip(identifier) ? vipCustomPrice.getWeekdayPrice()
                : normalCustomPrice.getWeekdayPrice();
    }

    private boolean isVip(Identifier identifier) {
        return identifier == Identifier.VIP;
    }

    private boolean isWeekDay(String dateStr) throws ParseException {
        final int dayOfWeek = getDayOfWeek(dateStr);
        return isDayOfWeekend(dayOfWeek);
    }

    private boolean isDayOfWeekend(int dayOfWeek) {
        return dayOfWeek != 7 && dayOfWeek != 1;
    }

    private int getDayOfWeek(String dateStr) throws ParseException {
        return transDateToCalendar(dateStr).get(DAY_OF_WEEK);
    }

    private Calendar transDateToCalendar(String dateStr) throws ParseException {
        final Calendar calendar = new GregorianCalendar();
        calendar.setTime(new SimpleDateFormat(DATE_FORMAT).parse(dateStr));
        return calendar;
    }

    public int getPaySum(Identifier identifier, List<String> dateList) throws ParseException {
        int sum = 0;
        for (String date : dateList)
            sum += getPay(identifier, date);
        return sum;
    }

    public Hotel getHotel() {
        return hotel;
    }
}
