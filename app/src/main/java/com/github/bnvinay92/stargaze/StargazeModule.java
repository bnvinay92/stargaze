package com.github.bnvinay92.stargaze;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.github.bnvinay92.stargaze.services.ApodService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Vinay on 12/10/16.
 */
@Module
public class StargazeModule {

    public static final String API_BASE_URL = "https://api.nasa.gov/";
    private final String apiKey;

    private final Application application;

    public StargazeModule(Application application, String apiKey) {
        this.application = application;
        this.apiKey = apiKey;
    }

    @Provides Application application() {
        return application;
    }

    @Provides Context context() {
        return application;
    }

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    OkHttpClient providesOkHttpClient() {

        return new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request original = chain.request();
                    HttpUrl url = original.url().newBuilder()
                            .addQueryParameter("api_key", apiKey)
                            .build();
                    return chain.proceed(original.newBuilder()
                            .url(url)
                            .build());
                })
                .build();
    }

    @Provides
    Retrofit providesRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(API_BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    @Provides
    ApodService apodService(Retrofit retrofit) {
        return retrofit.create(ApodService.class);
    }

}
