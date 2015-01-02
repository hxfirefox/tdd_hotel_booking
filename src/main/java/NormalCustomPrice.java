/**
 * Created by 黄翔 on 15-1-1.
 */
public class NormalCustomPrice {
    private final int weekdayPrice;
    private final int weekendPrice;

    public NormalCustomPrice(int weekdayPrice, int weekendPrice) {

        this.weekdayPrice = weekdayPrice;
        this.weekendPrice = weekendPrice;
    }

    public int getWeekdayPrice() {
        return weekdayPrice;
    }

    public int getWeekendPrice() {
        return weekendPrice;
    }
}
