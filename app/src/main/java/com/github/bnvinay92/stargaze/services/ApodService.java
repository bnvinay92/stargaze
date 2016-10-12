package com.github.bnvinay92.stargaze.services;

import com.github.bnvinay92.stargaze.values.ApodEntity;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Vinay on 12/10/16.
 */

public interface ApodService {
    @GET("planetary/apod")
    Observable<ApodEntity> getApodByDate(@Query("date") String date);
}
