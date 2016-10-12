package com.github.bnvinay92.stargaze;

import com.github.bnvinay92.stargaze.values.ApodEntity;
import com.github.bnvinay92.stargaze.values.ApodViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.inject.Inject;

/**
 * Created by Vinay on 12/10/16.
 */
public class ApodViewModelAdapter {

    private final SimpleDateFormat dateFormat;

    @Inject public ApodViewModelAdapter(SimpleDateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public ApodViewModel execute(ApodEntity apodEntity) throws ParseException {
        return ApodViewModel.create(
                dateFormat.parse(apodEntity.date()).getTime(),
                apodEntity.url(),
                apodEntity.date(),
                apodEntity.title(),
                apodEntity.copyright(),
                apodEntity.explanation(),
                apodEntity.hdurl(),
                apodEntity.mediaType(),
                apodEntity.serviceVersion()
        );
    }
}
