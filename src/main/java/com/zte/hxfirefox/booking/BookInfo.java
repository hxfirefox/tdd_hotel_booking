package com.zte.hxfirefox.booking;

import com.google.common.collect.Lists;
import com.zte.hxfirefox.utils.CustomType;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class BookInfo {
    public static final int WEEK_SATURDAY = 7;
    public static final int WEEK_SUNDAY = 1;
    private CustomType customType;
    private List<Date> bookDates = Lists.newArrayList();

    public BookInfo(CustomType customType, Date... dates) {
        this.customType = customType;
        Collections.addAll(bookDates, dates);
    }

    public List<Date> getBookDates() {
        return bookDates;
    }

    public CustomType getCustomType() {
        return customType;
    }
}
