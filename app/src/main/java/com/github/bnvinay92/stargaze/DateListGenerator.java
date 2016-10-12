package com.github.bnvinay92.stargaze;

import rx.Observable;

/**
 * Created by Vinay on 12/10/16.
 */
public interface DateListGenerator {
    Observable<String> execute(Observable<Integer> pages);
}
