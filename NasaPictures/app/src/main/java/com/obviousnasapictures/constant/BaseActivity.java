package com.obviousnasapictures.constant;


import static com.obviousnasapictures.constant.AppConfig.PAYLOAD_BUNDLE;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.obviousnasapictures.BuildConfig;
import com.obviousnasapictures.R;
import com.obviousnasapictures.util.DialogUtil;
import com.obviousnasapictures.util.LoggerUtil;
import com.obviousnasapictures.util.Utils;

import javax.crypto.SecretKey;

import cn.pedant.SweetAlert.SweetAlertDialog;


public abstract class BaseActivity extends AppCompatActivity implements MvpView, View.OnClickListener {

    public static final int PERMISSIONS_REQUEST_STORAGE = 1001;
    public static String[] PERMISSION_STORAGE = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
    public static String[] PERMISSION_CAMERA = {Manifest.permission.CAMERA};
    public static String[] PERMISSIONS = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    public Activity context;
    public SecretKey encrypt_key;
    public SharedPreferences pref;
    Dialog loadingDialog;

    double latitude = 0.0, longitude = 0.0;
    protected static final int PERMISSION_LOCATION_REQUEST_CODE = 14;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = this;
        PreferencesManager.initializeInstance(context);

        hideKeyboard();
        getAndroidId();

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Const.screenWidth = displayMetrics.widthPixels;
    }

    @Override
    public void onClick(View v) {

    }

    public void logoutDialog(Context context, Class activity) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setTitle("Logout");
        builder1.setMessage("Do you really want to logout?");
        builder1.setCancelable(true);

        builder1.setPositiveButton("Yes", (dialog, id) -> {
            try {
                clearPreference();
                Intent intent1 = new Intent(context, activity);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent1);
                finish();
                dialog.dismiss();

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        builder1.setNegativeButton("No", (dialog, id) -> dialog.dismiss());

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    public void getAndroidId() {
        try {
            if (PreferencesManager.getInstance(context).getANDROID_ID().equalsIgnoreCase("")) {
                AppConfig.ANDROID_ID = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
                PreferencesManager.getInstance(context).setANDROID_ID(AppConfig.ANDROID_ID);
                LoggerUtil.logItem(AppConfig.ANDROID_ID);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void clearPreference() {
        LoggerUtil.logItem("CLEARDATA");
        PreferencesManager.getInstance(context).clear();
    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    public void goToActivity(Activity activity, Class<?> classActivity, Bundle bundle) {
        Utils.hideSoftKeyboard(activity);
        Intent intent = new Intent(activity, classActivity);
        if (bundle != null) intent.putExtra(PAYLOAD_BUNDLE, bundle);
        activity.startActivity(intent);
        hideKeyboard();
//        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    public void goToActivityForResult(Activity activity, Class<?> classActivity, Bundle bundle, int code) {
        Utils.hideSoftKeyboard(activity);
        Intent intent = new Intent(activity, classActivity);
        if (bundle != null) intent.putExtra(PAYLOAD_BUNDLE, bundle);
        activity.startActivityForResult(intent, code);
        hideKeyboard();
//        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    public void goToActivityWithFinish(Activity activity, Class<?> classActivity, Bundle bundle) {
        Intent intent = new Intent(context, classActivity);
        if (bundle != null) intent.putExtra(PAYLOAD_BUNDLE, bundle);
        Utils.hideSoftKeyboard(activity);
        activity.startActivity(intent);
        activity.finish();
//        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

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
    public void showMessage(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
//        SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.NORMAL_TYPE);
//        pDialog.setTitleText("Message");
//        pDialog.setContentText(message);
//        pDialog.setCancelable(true);
//        pDialog.show();

    }

    @Override
    public void getClickPosition(int position) {

    }

    public void showSomethingwentWrong() {
//        Toast.makeText(context,"Something went wrong,try again later" , Toast.LENGTH_SHORT).show();
        SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE);
        pDialog.setTitleText("Error");
        pDialog.setContentText("Something went wrong,try again later");
        pDialog.setCancelable(true);
        pDialog.show();
    }

    public void alertInterNet(Context context) {
//        showMessage(getResources().getString(R.string.alert_internet));

        SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE);
        pDialog.setTitleText("No Internet");
        pDialog.setContentText(getResources().getString(R.string.alert_internet));
        pDialog.setCancelable(true);
        pDialog.show();
    }


    public void showError(String string) {
//        showMessage(getResources().getString(R.string.alert_internet));

        SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE);
        pDialog.setTitleText("Error");
        pDialog.setContentText(string);
        pDialog.setCancelable(true);
        pDialog.show();
    }


    public void createInfoDialog(Context context, String title, String msg) {
        androidx.appcompat.app.AlertDialog.Builder builder1 = new androidx.appcompat.app.AlertDialog.Builder(context);
        builder1.setTitle(title);
        builder1.setMessage(msg);
        builder1.setCancelable(true);

        builder1.setNegativeButton("OK", (dialog, id) -> {
            dialog.cancel();
            finish();
        });

        androidx.appcompat.app.AlertDialog alert11 = builder1.create();
        alert11.show();
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


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_STORAGE) {
            if (grantResults.length <= 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                new AlertDialog.Builder(context).setTitle("Permissions Needed").setMessage("This Permission is needed").setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(context, permissions, PERMISSIONS_REQUEST_STORAGE);
                    }
                }).setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss()).show();
                Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == PERMISSION_LOCATION_REQUEST_CODE) {
            boolean flag = true;
            for (int grantResult : grantResults) {
                if (grantResult != PackageManager.PERMISSION_GRANTED) {
                    flag = false;
                    break;
                }
            }
            if (!flag) {
                new AlertDialog.Builder(context).setTitle("Permissions Needed").setMessage("This Permission is needed").setCancelable(false).setPositiveButton("Enable", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + BuildConfig.APPLICATION_ID));
                        startActivityForResult(i, 500);
                    }
                }).show();
                Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void showMessageAnimated(String message) {
        SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.NORMAL_TYPE);
        pDialog.setTitleText("Message");
        pDialog.setContentText(message);
        pDialog.setCancelable(true);
        pDialog.show();
    }


}
