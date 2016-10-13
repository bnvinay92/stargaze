package com.github.bnvinay92.stargaze.views;

import com.github.bnvinay92.stargaze.values.ApodViewModel;

import rx.Observable;

/**
 * Created by Vinay on 12/10/16.
 */
public interface ApodListView {
    void push(ApodViewModel apodViewModel);
    void showLoading();
}
