import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static java.util.Calendar.DAY_OF_WEEK;

/**
 * Created by 黄翔 on 15-1-1.
 */
public class HotelPay {

    private final Hotel hotel;
    private final NormalCustomPrice normalCustomPrice;
    private final VipCustomPrice vipCustomPrice;

    public HotelPay(Hotel hotel, NormalCustomPrice normalCustomPrice, VipCustomPrice vipCustomPrice) {
        this.hotel = hotel;
        this.normalCustomPrice = normalCustomPrice;
        this.vipCustomPrice = vipCustomPrice;
    }

    private int getWeekendPay(CustomType type) {
        return isVip(type) ? vipCustomPrice.getWeekendPrice()
                : normalCustomPrice.getWeekendPrice();
    }

    private int getWeekdayPay(CustomType type) {
        return isVip(type) ? vipCustomPrice.getWeekdayPrice()
                : normalCustomPrice.getWeekdayPrice();
    }

    private boolean isVip(CustomType type) {
        return type == CustomType.VIP;
    }

    private boolean isDayOfWeekend(int dayOfWeek) {
        return dayOfWeek != 7 && dayOfWeek != 1;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public Integer getPaySum(CustomType type, List<BookingDate> bookingDates) {
        int sum = 0;
        for (BookingDate booking : bookingDates) {
            for (Date date : booking.getDateInterval()) {
                sum += getPay(type, date);
            }
        }
        return sum;
    }

    public int getPay(CustomType type, Date date) {
        return isWeekDay(date) ? getWeekdayPay(type) : getWeekendPay(type);
    }

    private boolean isWeekDay(Date date) {
        final int dayOfWeek = getDayOfWeek(date);
        return isDayOfWeekend(dayOfWeek);
    }

    private int getDayOfWeek(Date date) {
        return transDateToCalendar(date).get(DAY_OF_WEEK);
    }

    private Calendar transDateToCalendar(Date date) {
        final Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar;
    }
}
