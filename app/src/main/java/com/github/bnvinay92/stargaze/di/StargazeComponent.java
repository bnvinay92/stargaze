package com.github.bnvinay92.stargaze.di;

import com.github.bnvinay92.stargaze.views.ApodDetailActivity;
import com.github.bnvinay92.stargaze.views.ApodListActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Vinay on 12/10/16.
 */
@Singleton
@Component(modules = {StargazeModule.class})
public interface StargazeComponent {
    void inject(ApodListActivity target);
    void inject(ApodDetailActivity target);
}
