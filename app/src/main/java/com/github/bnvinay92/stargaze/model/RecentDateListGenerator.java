package com.github.bnvinay92.stargaze.model;

import com.github.bnvinay92.stargaze.model.DateListGenerator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Vinay on 12/10/16.
 */
public class RecentDateListGenerator implements DateListGenerator {

    public static final int APODS_PER_PAGE = 20;
    private final SimpleDateFormat dateFormat;

    @Inject public RecentDateListGenerator(SimpleDateFormat dateFormat) {
        this.dateFormat = dateFormat;

    }

    @Override public Observable<String> execute(Observable<Integer> pages) {
        return Observable.from(generateMostRecentDates(APODS_PER_PAGE));
    }

    private Iterable<String> generateMostRecentDates(int pageOffset) {
        Date today = new Date();
        List<String> dates = new ArrayList<>();
        for (int numDays = 0; numDays < pageOffset; numDays++) {
            dates.add(dateFormat.format(subtractDays(today, numDays)));
        }
        return dates;
    }

    public static Date subtractDays(Date date, int numDays) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, -numDays);
        return cal.getTime();
    }
}
