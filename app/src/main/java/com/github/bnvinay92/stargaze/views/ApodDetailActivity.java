package com.github.bnvinay92.stargaze.views;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.bnvinay92.stargaze.R;
import com.github.bnvinay92.stargaze.databinding.ActivityApodDetailBinding;
import com.github.bnvinay92.stargaze.values.ApodViewModel;

public class ApodDetailActivity extends AppCompatActivity {

    private ApodViewModel item;
    private ActivityApodDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_apod_detail);
        item = getIntent().getParcelableExtra(ApodViewModel.EXTRA);
        Glide.with(this)
                .load(item.url())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(binding.image);
        binding.title.setText(item.title());
        binding.date.setText(item.date());
        binding.copyright.setText(item.copyright());
        binding.explanation.setText(item.explanation());
    }
}
