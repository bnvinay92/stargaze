package com.github.bnvinay92.stargaze.values;

import android.support.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

/**
 * Created by Vinay on 12/10/16.
 * <p>
 * {
 * "copyright": "Sara Wager",
 * "date": "2016-10-11",
 * "explanation": "Sometimes, stars form in walls -- bright walls of interstellar gas. In this vivid skyscape, stars are forming in the W-shaped ridge of emission known as the Cygnus Wall. Part of a larger emission nebula with a distinctive outline popularly called The North America Nebula, the cosmic ridge spans about 20 light-years. Constructed using narrowband data to highlight the telltale reddish glow from ionized hydrogen atoms recombining with electrons, the image mosaic follows an ionization front with fine details of dark, dusty forms in silhouette. Sculpted by energetic radiation from the region's young, hot, massive stars, the dark shapes inhabiting the view are clouds of cool gas and dust with stars likely forming within. The North America Nebula itself, NGC 7000, is about 1,500 light-years away.",
 * "hdurl": "http://apod.nasa.gov/apod/image/1610/CygnusWall_Wager_2000.jpg",
 * "media_type": "image",
 * "service_version": "v1",
 * "title": "The Cygnus Wall of Star Formation",
 * "url": "http://apod.nasa.gov/apod/image/1610/CygnusWall_Wager_960.jpg"
 * }
 */

@AutoValue
public abstract class ApodEntity {

    public abstract String date();
    public abstract String title();
    public abstract String url();

    @Nullable public abstract String copyright();
    public abstract String explanation();
    @Nullable public abstract String hdurl();
    public abstract String mediaType();
    public abstract String serviceVersion();


    @JsonCreator public static ApodEntity create(
            @JsonProperty("date") String date,
            @JsonProperty("title") String title,
            @JsonProperty("url") String url,
            @JsonProperty("copyright") String copyright,
            @JsonProperty("explanation") String explanation,
            @JsonProperty("hdurl") String hdurl,
            @JsonProperty("media_type") String mediaType,
            @JsonProperty("service_version") String serviceVersion) {
        return new AutoValue_ApodEntity(date, title, url, copyright, explanation, hdurl, mediaType, serviceVersion);
    }
}
