package com.github.bnvinay92.stargaze.views;

import com.github.bnvinay92.stargaze.data.ApodListQuery;
import com.github.bnvinay92.stargaze.model.ApodViewModelAdapter;
import com.github.bnvinay92.stargaze.model.RecentDateListGenerator;
import com.jakewharton.rxrelay.PublishRelay;

import java.text.ParseException;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.exceptions.Exceptions;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;
import timber.log.Timber;

/**
 * Created by Vinay on 12/10/16.
 */
public class ApodListPresenter {

    private final ApodListQuery apodListQuery;
    private final RecentDateListGenerator recentDateListGenerator;
    private final ApodViewModelAdapter adapter;
    private final PublishRelay<Integer> pagesIntent = PublishRelay.create();

    private ApodListView view;
    private Subscription subscription = Subscriptions.unsubscribed();

    @Inject public ApodListPresenter(ApodListQuery apodListQuery,
                                     RecentDateListGenerator recentDateListGenerator,
                                     ApodViewModelAdapter adapter) {
        this.apodListQuery = apodListQuery;
        this.recentDateListGenerator = recentDateListGenerator;
        this.adapter = adapter;
    }

    public void attachView(ApodListView activity) {
        this.view = activity;
        subscription = apodListQuery.execute(recentDateListGenerator.execute(pagesIntent.asObservable()))
                .observeOn(Schedulers.computation())
                .filter(apodEntity -> apodEntity.mediaType().equals("image"))
                .map(apodEntity -> {
                    try {
                        return adapter.execute(apodEntity);
                    } catch (ParseException e) {
                        throw Exceptions.propagate(e);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(apodViewModel -> {
                            view.push(apodViewModel);
                        },
                        throwable -> Timber.e(throwable, throwable.getMessage()));
    }

    public void detachView(boolean finishing) {
        subscription.unsubscribe();
    }

    public void loadPage(int page) {
        pagesIntent.call(page);
    }
}
