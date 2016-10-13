package com.github.bnvinay92.stargaze.model;

import android.util.Pair;

import com.github.bnvinay92.stargaze.util.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

/**
 * Created by Vinay on 13/10/16.
 */
public class AdjacentDatesQuery {

    private final Date maxDate;
    private final Date minDate;
    private final SimpleDateFormat dateFormat;

    @Inject public AdjacentDatesQuery(SimpleDateFormat dateFormat) {
        this.dateFormat = dateFormat;
        maxDate = new Date();
        try {
            minDate = dateFormat.parse("1995-06-16");
        } catch (ParseException e) {
            throw new IllegalStateException("Simple Date Format corrupted");
        }
    }

    public Pair<String, String> execute(String date) throws ParseException {
        String prevDate;
        String nextDate;

        Date today = dateFormat.parse(date);
        Date yesterday = Util.subtractDays(today, 1);
        Date tomorrow = Util.subtractDays(today, -1);

        prevDate = yesterday.before(minDate) ? null : dateFormat.format(yesterday);
        nextDate = tomorrow.after(maxDate) ? null : dateFormat.format(tomorrow);

        return new Pair<>(prevDate, nextDate);
    }
}
