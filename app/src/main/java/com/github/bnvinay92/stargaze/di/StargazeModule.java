package com.github.bnvinay92.stargaze.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.github.bnvinay92.stargaze.BuildConfig;
import com.github.bnvinay92.stargaze.data.AuthInterceptor;
import com.github.bnvinay92.stargaze.data.CacheInterceptor;
import com.github.bnvinay92.stargaze.services.ApodService;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import timber.log.Timber;

import static okhttp3.logging.HttpLoggingInterceptor.Level.HEADERS;
import static okhttp3.logging.HttpLoggingInterceptor.Level.NONE;

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
    OkHttpClient providesOkHttpClient(Cache cache, AuthInterceptor authInterceptor,
                                      CacheInterceptor cacheInterceptor,
                                      HttpLoggingInterceptor loggingInterceptor) {

        return new OkHttpClient.Builder()
//                .addInterceptor(loggingInterceptor)
                .addInterceptor(authInterceptor)
                .addNetworkInterceptor(cacheInterceptor)
                .cache(cache)
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

    @Provides
    Cache providesCache() {
        Cache cache = null;
        try {
            cache = new Cache(new File(application.getCacheDir(), "http-cache"), 10 * 1024 * 1024);
        } catch (Exception e) {
            Timber.e("Cache creation failed");
        }
        return cache;
    }

    @Provides AuthInterceptor authInterceptor() {
        return new AuthInterceptor(apiKey);
    }

    @Provides HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor =
                new HttpLoggingInterceptor(message -> Timber.d(message));
        httpLoggingInterceptor.setLevel(BuildConfig.DEBUG ? HEADERS : NONE);
        return httpLoggingInterceptor;
    }

    @Provides SimpleDateFormat simpleDateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    }
}
