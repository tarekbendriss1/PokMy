package com.seemantov.pokmy.utils;

/**
 * Created by Tarek Ben Driss on 06/03/2018.
 */

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;

import com.seemantov.pokmy.R;

public class AlertDialogUtils {

    public static void show(@NonNull Context context, String message) {
        showString(context, R.string.empty, message, null);
    }

    public static void showString(@NonNull Context context, @StringRes int title, String message, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        //set title alert
        builder.setTitle(title);
        // set message alert
        builder.setMessage(message);
        if (listener != null) {
            builder.setPositiveButton(R.string.ok, listener);
        } else {
            builder.setPositiveButton(R.string.ok, (dialog, which) -> dialog.dismiss());
        }
        // show alert
        builder.create().show();
    }

    public static void show(@NonNull Context context, @StringRes int message, DialogInterface.OnClickListener listener, boolean cancelable) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        // set message alert
        builder.setMessage(message);
        // Sets whether this dialog is canceled when touched outside the window's bounds. or not
        builder.setCancelable(cancelable);
        if (listener != null) {
            builder.setPositiveButton(R.string.ok, listener);
        } else {
            builder.setNegativeButton(R.string.ok, (dialog, which) -> dialog.dismiss());
        }
        // show alert
        builder.create().show();

    }


    public static void show(@NonNull Context context, @StringRes int message, DialogInterface.OnClickListener positive,
                            DialogInterface.OnClickListener negative) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        // set message alert
        builder.setMessage(message);
        // Sets whether this dialog is canceled when touched outside the window's bounds. or not
        builder.setCancelable(false);
        if (positive != null && negative != null) {
            builder.setPositiveButton(R.string.dialog_action_yes, positive);
            builder.setNegativeButton(R.string.dialog_action_no, negative);
        }
        // show alert
        builder.create().show();

    }
}

