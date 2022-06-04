package com.obviousnasapictures.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.gson.Gson;
import com.obviousnasapictures.adapter.AdapterGallery;
import com.obviousnasapictures.constant.BaseActivity;
import com.obviousnasapictures.constant.Const;
import com.obviousnasapictures.databinding.DashboardGalleryBinding;
import com.obviousnasapictures.model.ResponseData;

import java.io.IOException;
import java.io.InputStream;

public class DashboardGallery extends BaseActivity {
    DashboardGalleryBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DashboardGalleryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.imgBackkkkk.setVisibility(View.INVISIBLE);

        loadDataFromAsset();
    }

    private void loadDataFromAsset() {
        String json = null;
        try {
            InputStream inputStream = getAssets().open("data.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Const.galleryData = new Gson().fromJson(json, ResponseData.class);
        setGalleryAdapter();
    }

    private void setGalleryAdapter() {
        AdapterGallery adapterGallery = new AdapterGallery(context, Const.galleryData.getData(), DashboardGallery.this);
        binding.rvGallery.setLayoutManager(new GridLayoutManager(context, 2));
        binding.rvGallery.setAdapter(adapterGallery);
    }

    @Override
    public void getClickPosition(int position) {
        super.getClickPosition(position);
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        goToActivity(context, DetailActivity.class, bundle);
    }
}
