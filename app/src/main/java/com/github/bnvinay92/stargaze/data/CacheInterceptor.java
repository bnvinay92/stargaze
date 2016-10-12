package com.github.bnvinay92.stargaze.data;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by Vinay on 12/10/16.
 */

public class CacheInterceptor implements Interceptor {

    private static final String CACHE_CONTROL = "Cache-Control";

    @Inject public CacheInterceptor() {
    }

    @Override public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        CacheControl cacheControl = new CacheControl.Builder()
                .maxAge(Integer.MAX_VALUE, TimeUnit.DAYS)
                .build();

        return response.newBuilder()
                .header(CACHE_CONTROL, cacheControl.toString())
                .build();
    }
}
