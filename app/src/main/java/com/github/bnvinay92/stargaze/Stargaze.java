package com.github.bnvinay92.stargaze;

import android.app.Application;

import com.github.bnvinay92.stargaze.di.DaggerStargazeComponent;
import com.github.bnvinay92.stargaze.di.StargazeComponent;
import com.github.bnvinay92.stargaze.di.StargazeModule;

import hu.supercluster.paperwork.Paperwork;
import timber.log.Timber;

/**
 * Created by Vinay on 12/10/16.
 */
public class Stargaze extends Application {

    public static final String NASA_API_KEY = "nasa_api_key";
    private StargazeComponent component;
    private String apiKey;

    @Override public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        Paperwork paperwork = new Paperwork(this);
        apiKey = paperwork.get(NASA_API_KEY);
        initComponent();
    }

    public StargazeComponent component() {
        if (component == null) {
            initComponent();
        }
        return component;
    }

    private void initComponent() {
        component = DaggerStargazeComponent.builder()
                .stargazeModule(new StargazeModule(this, apiKey))
                .build();
    }
}
