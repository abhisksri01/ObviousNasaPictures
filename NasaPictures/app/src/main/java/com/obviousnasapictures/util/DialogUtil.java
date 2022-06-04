package com.obviousnasapictures.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.obviousnasapictures.R;


public class DialogUtil {
    private static final String TAG = "DialogUtil";

    public static Dialog dialog;
    public static ProgressDialog progressDialog;

    public DialogUtil(Activity activity) {
        // This utility class is not publicly instantiable
    }

    public static Dialog showLoadingDialog(Activity activity) {
        dialog = new Dialog(activity);
        dialog.show();
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        dialog.setContentView(R.layout.progress_dialog_layout);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        dialog.setOnCancelListener(DialogInterface::dismiss);

        return dialog;


    }


    public static ProgressDialog showLoadingDialog(Activity activity, String callingPlace) {
        progressDialog = new ProgressDialog(activity);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.setContentView(R.layout.progress_dialog_layout);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setOnCancelListener(DialogInterface::dismiss);
        return progressDialog;

    }

    public static void hideDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.hide();
        }
    }


    public static void showInfoDialog(Context context, String title, String info) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle(title);
        dialog.setMessage(info);
        dialog.setCancelable(true);
        dialog.setPositiveButton("OK", (dialog1, which) -> dialog1.dismiss());
        dialog.show();
    }

}