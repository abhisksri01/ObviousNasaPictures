package com.obviousnasapictures.constant;


import static com.obviousnasapictures.constant.AppConfig.PAYLOAD_BUNDLE;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.obviousnasapictures.R;
import com.obviousnasapictures.util.DialogUtil;
import com.obviousnasapictures.util.LoggerUtil;
import com.obviousnasapictures.util.Utils;

import javax.crypto.SecretKey;

import cn.pedant.SweetAlert.SweetAlertDialog;

public abstract class BaseFragment extends Fragment implements MvpView, View.OnClickListener {
    public Activity context;
    public SecretKey encrypt_key;
    public SharedPreferences pref;
    Dialog loadingDialog;

    double latitude = 0.0, longitude = 0.0;
    protected static final int PERMISSION_LOCATION_REQUEST_CODE = 14;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = getActivity();
        PreferencesManager.initializeInstance(context);

        hideKeyboard();
        getAndroidId();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void hideKeyboard() {
        View view = context.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    context.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    public boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }


    public void alertInterNet(Context context) {
        SweetAlertDialog pDialog = new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE);
        pDialog.setTitleText("No Internet");
        pDialog.setContentText(getResources().getString(R.string.alert_internet));
        pDialog.setCancelable(true);
        pDialog.show();
    }


    public void goToActivity(Class<?> classActivity, Bundle bundle) {
        Utils.hideSoftKeyboard(getActivity());
        Intent intent = new Intent(getContext(), classActivity);
        if (bundle != null) intent.putExtra(PAYLOAD_BUNDLE, bundle);
        getActivity().startActivity(intent);
//        getActivity().overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    public void goToActivityForResult(Class<?> classActivity, Bundle bundle, int code) {
        Utils.hideSoftKeyboard(getActivity());
        Intent intent = new Intent(getActivity(), classActivity);
        if (bundle != null) intent.putExtra(PAYLOAD_BUNDLE, bundle);
        getActivity().startActivityForResult(intent, code);
        hideKeyboard();
    }


    public void getAndroidId() {
        try {
            if (PreferencesManager.getInstance(context).getANDROID_ID().equalsIgnoreCase("")) {
                AppConfig.ANDROID_ID = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
                PreferencesManager.getInstance(context).setANDROID_ID(AppConfig.ANDROID_ID);
                LoggerUtil.logItem(AppConfig.ANDROID_ID);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearPreference() {
        PreferencesManager.getInstance(context).clear();
    }

    public void goToActivityWithFinish(Class<?> classActivity, Bundle bundle) {
        Intent intent = new Intent(getContext(), classActivity);
        if (bundle != null) intent.putExtra(PAYLOAD_BUNDLE, bundle);
        Utils.hideSoftKeyboard(getActivity());
        getActivity().startActivity(intent);
        getActivity().finish();
//        getActivity().overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

    }

    @Override
    public void showLoading() {
        loadingDialog = DialogUtil.showLoadingDialog(context);
    }

    @Override
    public void hideLoading() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    @Override
    public void getClickPosition(int position) {

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public void showSomethingwentWrong() {
        Toast.makeText(context, "Something went worng", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessageAnimated(String message) {
        SweetAlertDialog pDialog = new SweetAlertDialog(context, SweetAlertDialog.NORMAL_TYPE);
        pDialog.setTitleText("Message");
        pDialog.setContentText(message);
        pDialog.setCancelable(true);
        pDialog.show();
    }

    public void onMyLocationChange(double latitude, double longitude) {
        Log.e("Location: ", latitude + " - " + longitude);
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public boolean getLocationPermission() {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
            return true;
        } else if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            ActivityCompat.requestPermissions(context,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSION_LOCATION_REQUEST_CODE);
            return false;
        }
    }
}
