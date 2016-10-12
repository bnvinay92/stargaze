package com.github.bnvinay92.stargaze;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Vinay on 12/10/16.
 */
public class RecentDateListGenerator implements DateListGenerator {

    public static final int APODS_PER_PAGE = 10;
    private final String currentDate;

    @Inject public RecentDateListGenerator(SimpleDateFormat dateFormat) {
        this.currentDate = dateFormat.format(new Date());
    }

    @Override public Observable<String> execute() {
        return Observable.from(generateMostRecentDates(APODS_PER_PAGE));
    }

    //TODO
    private Iterable<String> generateMostRecentDates(int pageOffset) {
        return Arrays.asList(
                "2016-5-21",
                "2016-5-20",
                "2016-5-19",
                "2016-5-18",
                "2016-5-17",
                "2016-5-16",
                "2016-5-15",
                "2016-5-14",
                "2016-5-13",
                "2016-5-12",
                "2016-5-11",
                "2016-5-10"
        );
    }
}
