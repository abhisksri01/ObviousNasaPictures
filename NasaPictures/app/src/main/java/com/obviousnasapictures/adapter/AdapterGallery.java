package com.obviousnasapictures.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.obviousnasapictures.R;
import com.obviousnasapictures.activity.DashboardGallery;
import com.obviousnasapictures.constant.Const;
import com.obviousnasapictures.constant.MvpView;
import com.obviousnasapictures.databinding.AdapterGalleryBinding;
import com.obviousnasapictures.model.DataItem;

import java.util.List;

public class AdapterGallery extends RecyclerView.Adapter<AdapterGallery.MyViewHolder> {
    private Context context;
    private List<DataItem> list;
    private MvpView mvpView;

    public AdapterGallery(Context context, List<DataItem> list, MvpView mvpView) {
        this.context = context;
        this.list = list;
        this.mvpView = mvpView;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(AdapterGalleryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getUrl())
                .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).skipMemoryCache(true)
                        .placeholder(R.drawable.image_not_available).error(R.drawable.image_not_available)).into(holder.binding.imgGallery);

        holder.binding.tvTitle.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        AdapterGalleryBinding binding;

        public MyViewHolder(AdapterGalleryBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
            binding.imgGallery.setMinimumWidth((Const.screenWidth / 2) - 8);
            binding.imgGallery.setMaxWidth((Const.screenWidth / 2) - 8);
            itemView.getRoot().setOnClickListener(v -> {
                mvpView.getClickPosition(getAdapterPosition());
            });
        }
    }
}