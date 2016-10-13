package com.github.bnvinay92.stargaze.views;

import com.github.bnvinay92.stargaze.model.ApodViewModelAdapter;
import com.github.bnvinay92.stargaze.services.ApodService;
import com.github.bnvinay92.stargaze.values.ApodViewModel;

import java.text.ParseException;

import javax.inject.Inject;

import rx.Single;
import rx.exceptions.Exceptions;
import rx.schedulers.Schedulers;

/**
 * Created by Vinay on 13/10/16.
 */
public class ApodDetailQuery {

    private final ApodService apodService;
    private final ApodViewModelAdapter adapter;

    @Inject public ApodDetailQuery(ApodService apodService,
                                   ApodViewModelAdapter adapter) {
        this.apodService = apodService;
        this.adapter = adapter;
    }


    public Single<ApodViewModel> execute(String date) {
        return apodService.getApodByDate(date)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map(apodEntity -> {
                    try {
                        return adapter.execute(apodEntity);
                    } catch (ParseException e) {
                        throw Exceptions.propagate(e);
                    }
                });
    }
}
