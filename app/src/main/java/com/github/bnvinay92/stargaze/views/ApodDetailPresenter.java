package com.github.bnvinay92.stargaze.views;

import com.github.bnvinay92.stargaze.model.AdjacentDatesQuery;

import java.text.ParseException;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.Subscriptions;
import timber.log.Timber;

/**
 * Created by Vinay on 13/10/16.
 */
public class ApodDetailPresenter {

    private final ApodDetailQuery apodDetailQuery;
    private final AdjacentDatesQuery adjacentDateGenerator;

    private ApodDetailView view;
    private Subscription subscription = Subscriptions.unsubscribed();

    @Inject public ApodDetailPresenter(ApodDetailQuery apodDetailQuery,
                                       AdjacentDatesQuery adjacentDateGenerator) {
        this.apodDetailQuery = apodDetailQuery;
        this.adjacentDateGenerator = adjacentDateGenerator;
    }

    public void attachView(ApodDetailView activity) {
        this.view = activity;
        subscription = apodDetailQuery.execute(view.getDate())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        view::showApod,
                        throwable -> Timber.e(throwable, throwable.getMessage())
                );
    }

    void onShowNewerApod() {
        String newerApod;
        try {
            newerApod = adjacentDateGenerator.newer(view.getDate());
        } catch (ParseException e) {
            throw new IllegalStateException("Date corrupted");
        }
        if (newerApod == null) {
            view.showOnMostRecentApodAlready();
        } else {
            view.moveToApod(newerApod);
        }
    }

    void onShowOlderApod() {
        String olderApod;
        try {
            olderApod = adjacentDateGenerator.older(view.getDate());
        } catch (ParseException e) {
            throw new IllegalStateException("Date corrupted");
        }
        if (olderApod == null) {
            view.showOnLastApodAlready();
        } else {
            view.moveToApod(olderApod);
        }
    }

    public void detachView(boolean finishing) {
        subscription.unsubscribe();
    }

}
