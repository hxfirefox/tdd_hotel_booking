package com.zte.hxfirefox;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BookingDateTest {
    private BookingDate bookingDate;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


    @Test
    public void should_during_day_is_1_when_given_booking_date_with_same_date() throws Exception {
        // given
        bookingDate = new BookingDate("2015-1-1", "2015-1-1");
        // when
        final long dayInterval = bookingDate.getDaysInterval();
        // then
        assertThat(dayInterval, is(1l));
    }

    @Test
    public void should_return_during_days_when_given_booking_date_with_different_date() throws Exception {
        // given
        bookingDate = new BookingDate("2015-1-1", "2015-1-3");
        // when
        final long dayInterval = bookingDate.getDaysInterval();
        // then
        assertThat(dayInterval, is(3l));
    }

    @Test
    public void should_return_each_date_bewteen_start_and_end_date() throws Exception {
        // given
        bookingDate = new BookingDate("2015-1-1", "2015-1-3");
        // when
        final List<Date> dateInterval = bookingDate.getDateInterval();
        // then
        assertThat(dateInterval.size(), is(3));
        assertThat(dateInterval.get(0).getTime(), is(dateFormat.parse("2015-1-1").getTime()));
        assertThat(dateInterval.get(1).getTime(), is(dateFormat.parse("2015-1-2").getTime()));
        assertThat(dateInterval.get(2).getTime(), is(dateFormat.parse("2015-1-3").getTime()));
    }
}