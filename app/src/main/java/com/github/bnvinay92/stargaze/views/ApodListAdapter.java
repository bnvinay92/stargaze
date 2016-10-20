package com.github.bnvinay92.stargaze.views;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.bnvinay92.stargaze.R;
import com.github.bnvinay92.stargaze.databinding.ItemApodBinding;
import com.github.bnvinay92.stargaze.values.ApodViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vinay on 12/10/16.
 */
public class ApodListAdapter extends RecyclerView.Adapter<ApodListAdapter.ApodViewHolder> {

    private List<ApodViewModel> apodList = new ArrayList<>();
    private final LayoutInflater inflater;
    private OnItemClickListener listener;

    public void add(ApodViewModel apodViewModel) {
        long id = apodViewModel.id();
        boolean inserted = false;
        for (int i = apodList.size() - 1; i >= 0; i--) {
            if (apodList.get(i).id() > id) {
                apodList.add(i + 1, apodViewModel);
                notifyItemInserted(i + 1);
                inserted = true;
                break;
            }
        }
        if (!inserted) {
            apodList.add(0, apodViewModel);
            notifyItemInserted(0);
        }
    }

    public void clear() {
        apodList = new ArrayList<>();
        notifyDataSetChanged();
    }

    public List<ApodViewModel> items() {
        return apodList;
    }


    public interface OnItemClickListener {
        void onItemClick(ApodViewModel item);
    }

    public ApodListAdapter(Activity activity) {
        inflater = activity.getLayoutInflater();
        listener = (OnItemClickListener) activity;
    }

    @Override public ApodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ApodViewHolder(DataBindingUtil.inflate(inflater, R.layout.item_apod, parent, false));
    }

    @Override public void onBindViewHolder(ApodViewHolder holder, int position) {
        ApodViewModel item = apodList.get(position);
        Glide.with(holder.itemView.getContext())
                .load(item.url())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder.binding.image);
    }

    @Override public int getItemCount() {
        return apodList.size();
    }

    public class ApodViewHolder extends RecyclerView.ViewHolder {

        private final ItemApodBinding binding;

        public ApodViewHolder(ItemApodBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            itemView.setOnClickListener(v -> {
                // Triggers click upwards to the adapter on click
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(apodList.get(position));
                    }
                }
            });
        }
    }
}
