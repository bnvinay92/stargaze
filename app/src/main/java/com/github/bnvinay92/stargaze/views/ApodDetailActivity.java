package com.github.bnvinay92.stargaze.views;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.bnvinay92.stargaze.R;
import com.github.bnvinay92.stargaze.Stargaze;
import com.github.bnvinay92.stargaze.databinding.ActivityApodDetailBinding;
import com.github.bnvinay92.stargaze.values.ApodViewModel;

import javax.inject.Inject;

import uk.co.senab.photoview.PhotoViewAttacher;

public class ApodDetailActivity extends AppCompatActivity implements ApodDetailView {

    private String apodDate;
    private ActivityApodDetailBinding binding;
    private PhotoViewAttacher imageViewAttacher;

    @Inject ApodDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Stargaze) getApplication()).component().inject(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_apod_detail);
        if (savedInstanceState == null) {
            apodDate = getApodDate();
        } else {
            apodDate = savedInstanceState.getString(ApodViewModel.EXTRA, getApodDate());
        }
        imageViewAttacher = new PhotoViewAttacher(binding.image);
        binding.left.setOnClickListener(v -> presenter.onShowNewerApod());
        binding.right.setOnClickListener(v -> presenter.onShowOlderApod());
        binding.image.setOnClickListener(v -> finish());
    }

    private String getApodDate() {
        return getIntent().getStringExtra(ApodViewModel.EXTRA);
    }

    @Override protected void onStart() {
        super.onStart();
        presenter.attachView(this);
    }

    @Override public String getDate() {
        return apodDate;
    }

    @Override public void showApod(ApodViewModel viewModel) {
        String mediaType = viewModel.mediaType();
        if (mediaType.equals("image")) {
            Glide.with(this)
                    .load(viewModel.url())
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(binding.image);
        } else if (mediaType.equals("loading")) {
            binding.image.setImageResource(R.drawable.ic_hourglass_empty_black_24dp);
        } else {
            binding.image.setImageResource(R.drawable.ic_error_black_24dp);
        }
        binding.title.setText(viewModel.title());
        binding.date.setText(viewModel.date());
        binding.copyright.setText(viewModel.copyright());
        binding.explanation.setText(viewModel.explanation());
    }

    @Override public void showOnMostRecentApodAlready() {
        Toast.makeText(this, "On the latest Apod", Toast.LENGTH_SHORT).show();
    }

    @Override public void showOnLastApodAlready() {
        Toast.makeText(this, "On the last Apod", Toast.LENGTH_SHORT).show();
    }

    @Override public void moveToApod(String apodDate) {
        this.apodDate = apodDate;
        presenter.detachView(false);
        showApod(ApodViewModel.LOADING);
        presenter.attachView(this);
    }

    @Override protected void onStop() {
        super.onStop();
        presenter.detachView(isFinishing());
    }


}
