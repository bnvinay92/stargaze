package com.github.bnvinay92.stargaze.values;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

/**
 * Created by Vinay on 12/10/16.
 */
@AutoValue
public abstract class ApodViewModel implements Parcelable {
    public abstract String url();
}
