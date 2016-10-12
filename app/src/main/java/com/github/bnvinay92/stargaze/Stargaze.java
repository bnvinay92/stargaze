package com.github.bnvinay92.stargaze;

import android.app.Application;

import hu.supercluster.paperwork.Paperwork;
import timber.log.Timber;

/**
 * Created by Vinay on 12/10/16.
 */
public class Stargaze extends Application {

    public static final String NASA_API_KEY = "nasa_api_key";

    @Override public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        Paperwork paperwork = new Paperwork(this);
        String API_KEY = paperwork.get(NASA_API_KEY);
    }
}
