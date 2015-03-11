package com.zte.hxfirefox.booking;

import org.junit.Test;

import java.util.Date;
import java.util.List;

import static com.zte.hxfirefox.utils.DateUtils.getDate;
import static com.zte.hxfirefox.utils.CustomType.NORMAL;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BookInfoTest {
    @Test
    public void should_return_date_list_when_booking_multi_dates() throws Exception {
        //given
        final BookInfo bookInfo = new BookInfo(NORMAL, getDate("2015-2-26"), getDate("2015-2-27"), getDate("2015-2-28"));
        //when
        final List<Date> bookDates = bookInfo.getBookDates();
        //then
        assertThat(bookDates.size(), is(3));
        assertThat(bookDates.get(0).compareTo(getDate("2015-2-26")), is(0));
        assertThat(bookDates.get(1).compareTo(getDate("2015-2-27")), is(0));
        assertThat(bookDates.get(2).compareTo(getDate("2015-2-28")), is(0));
    }
}