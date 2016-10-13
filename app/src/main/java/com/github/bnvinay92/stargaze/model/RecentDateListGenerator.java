package com.github.bnvinay92.stargaze.model;

import com.github.bnvinay92.stargaze.util.Util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        return pages.map(page -> page * APODS_PER_PAGE)
                .map(dateOffsetNumber -> Util.subtractDays(new Date(), dateOffsetNumber))
                .concatMapEager(dateOffset -> Observable.from(generateDates(dateOffset, APODS_PER_PAGE)));
    }

    private Iterable<String> generateDates(Date dateOffset, int apodsPerPage) {
        List<String> dates = new ArrayList<>();
        for (int numDays = 0; numDays < apodsPerPage; numDays++) {
            dates.add(dateFormat.format(Util.subtractDays(dateOffset, numDays)));
        }
        return dates;
    }

}
