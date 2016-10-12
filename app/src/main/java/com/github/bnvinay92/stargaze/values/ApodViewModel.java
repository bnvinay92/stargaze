package com.github.bnvinay92.stargaze.values;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

/**
 * Created by Vinay on 12/10/16.
 */
@AutoValue
public abstract class ApodViewModel implements Parcelable {

    public static final String EXTRA = "com.github.bnvinay92.stargaze.extras.ApodViewModel";

    public abstract long id();
    public abstract String url();
    public abstract String date();
    public abstract String title();
    @Nullable public abstract String copyright();
    public abstract String explanation();
    @Nullable public abstract String hdurl();
    public abstract String mediaType();
    public abstract String serviceVersion();


    public static ApodViewModel create(
            long id,
            String url,
            String date,
            String title,
            String copyright,
            String explanation,
            String hdurl,
            String mediaType,
            String serviceVersion
    ) {
        return new AutoValue_ApodViewModel(
                id,
                url,
                date,
                title,
                copyright,
                explanation,
                hdurl,
                mediaType,
                serviceVersion
        );
    }


}
