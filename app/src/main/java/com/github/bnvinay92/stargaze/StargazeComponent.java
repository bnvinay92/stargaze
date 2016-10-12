package com.github.bnvinay92.stargaze;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Vinay on 12/10/16.
 */
@Singleton
@Component(modules = {StargazeModule.class})
public interface StargazeComponent {
    void inject(MainActivity target);
}
