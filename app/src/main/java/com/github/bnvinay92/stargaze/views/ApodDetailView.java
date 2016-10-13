package com.github.bnvinay92.stargaze.views;

import com.github.bnvinay92.stargaze.values.ApodViewModel;

/**
 * Created by Vinay on 13/10/16.
 */
public interface ApodDetailView {
    String getDate();
    void showApod(ApodViewModel apodViewModel);
    void showOnMostRecentApodAlready();
    void showOnLastApodAlready();
    void moveToApod(String apodDate);
}
