package com.obviousnasapictures.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.obviousnasapictures.R;
import com.obviousnasapictures.constant.BaseActivity;
import com.obviousnasapictures.databinding.ActivitySplashBinding;
import com.obviousnasapictures.model.ResponseData;
import com.obviousnasapictures.util.LoggerUtil;
import com.obviousnasapictures.util.NetworkUtils;

import java.io.IOException;
import java.io.InputStream;

public class Splash extends BaseActivity {
    ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Animation animation = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        binding.imgLogo.startAnimation(animation);

        if (NetworkUtils.getConnectivityStatus(context) != 0) {
            new Handler().postDelayed(() -> goToActivityWithFinish(context, DashboardGallery.class, null), 2000);
        } else {
            showNoInternet();
        }

    }

    private void showNoInternet() {
        Snackbar snackbar = Snackbar
                .make(binding.main, getString(R.string.alert_internet), Snackbar.LENGTH_LONG)
                .setAction("Retry", view -> {
                    if (NetworkUtils.getConnectivityStatus(context) != 0)
                        showMessage("Coming Soon");
                    else {
                        showNoInternet();
                    }
                });
        snackbar.show();
    }
}