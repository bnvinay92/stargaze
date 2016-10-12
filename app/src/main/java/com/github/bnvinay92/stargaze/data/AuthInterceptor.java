package com.github.bnvinay92.stargaze.data;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Vinay on 12/10/16.
 */
public class AuthInterceptor implements Interceptor {
    private final String apiKey;

    public AuthInterceptor(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl url = original.url().newBuilder()
                .addQueryParameter("api_key", apiKey)
                .build();
        return chain.proceed(original.newBuilder()
                .url(url)
                .build());
    }
}
