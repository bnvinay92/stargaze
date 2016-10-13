package com.github.bnvinay92.stargaze.data;

import com.github.bnvinay92.stargaze.services.ApodService;
import com.github.bnvinay92.stargaze.values.ApodEntity;

import javax.inject.Inject;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by Vinay on 12/10/16.
 */
public class ApodListQuery {

    private final ApodService apodService;

    @Inject public ApodListQuery(ApodService apodService) {
        this.apodService = apodService;
    }

    /**
     * Runs on IO Scheduler
     * @param dates
     * @return
     */
    public Observable<ApodEntity> execute(Observable<String> dates) {
        return dates.observeOn(Schedulers.io())
                .flatMap(date -> apodService.getApodByDate(date)
                        .toObservable());
    }
}
