package com.github.bnvinay92.stargaze;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;

import com.github.bnvinay92.stargaze.databinding.ActivityMainBinding;
import com.github.bnvinay92.stargaze.values.ApodViewModel;

public class MainActivity extends AppCompatActivity implements ApodListAdapter.OnItemClickListener {

    public static final int SPAN_COUNT = 2;
    private ActivityMainBinding binding;
    private ApodListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initRecyclerView();
    }

    private void initRecyclerView() {
        adapter = new ApodListAdapter(this);
        binding.rview.setLayoutManager(new GridLayoutManager(this, SPAN_COUNT));
        binding.rview.setAdapter(adapter);
    }

    @Override public void onItemClick(ApodViewModel item) {

    }
}
