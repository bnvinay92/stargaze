package com.github.bnvinay92.stargaze.views;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;

import com.github.bnvinay92.stargaze.R;
import com.github.bnvinay92.stargaze.Stargaze;
import com.github.bnvinay92.stargaze.databinding.ActivityMainBinding;
import com.github.bnvinay92.stargaze.values.ApodViewModel;

import javax.inject.Inject;

public class ApodListActivity extends AppCompatActivity implements ApodListAdapter.OnItemClickListener, ApodListView {

    public static final int SPAN_COUNT = 2;
    private ActivityMainBinding binding;
    private ApodListAdapter adapter;

    @Inject ApodListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Stargaze) getApplication()).component().inject(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initRecyclerView();
    }

    private void initRecyclerView() {
        adapter = new ApodListAdapter(this);
        binding.rview.setLayoutManager(new GridLayoutManager(this, SPAN_COUNT));
        binding.rview.setAdapter(adapter);
    }

    @Override protected void onStart() {
        super.onStart();
        presenter.attachView(this);
    }

    @Override public void onItemClick(ApodViewModel item) {
        Intent apodDetailViewStarter = new Intent(this, ApodDetailActivity.class);
        apodDetailViewStarter.putExtra(ApodViewModel.EXTRA, item.date());
        startActivity(apodDetailViewStarter);
    }

    @Override protected void onStop() {
        super.onStop();
        presenter.detachView(isFinishing());
    }

    @Override public void push(ApodViewModel apodViewModel) {
        adapter.add(apodViewModel);
    }

    @Override public void showLoading() {
        adapter.clear();
    }
}
