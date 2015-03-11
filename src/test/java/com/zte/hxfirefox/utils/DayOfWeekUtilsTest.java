package com.zte.hxfirefox.utils;

import org.junit.Test;

import static com.zte.hxfirefox.utils.DateUtils.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DayOfWeekUtilsTest {
    @Test
    public void should_return_true_when_date_is_weekday() throws Exception {
        //given

        //when
        final boolean weekday = DayOfWeekUtils.isWeekday(getDate("2015-2-27"));
        //then
        assertThat(weekday, is(true));
    }
    
    @Test
    public void should_return_false_when_date_is_weekend() throws Exception {
        //given

        //when
        final boolean weekday = DayOfWeekUtils.isWeekday(getDate("2015-2-28"));
        //then
        assertThat(weekday, is(false));
    }
}