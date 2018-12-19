package com.seemantov.pokmy.widgets;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.seemantov.pokmy.R;
import com.seemantov.pokmy.databinding.DialogLoadingBinding;
import com.seemantov.pokmy.utils.ActivityUtils;
import com.seemantov.pokmy.utils.FragmentUtils;
import com.seemantov.pokmy.utils.Logger;

/**
 * Created by Tarek Ben Driss on 06/03/2018.
 */


public class LoadingDialog extends DialogFragment {

    public static void show(@NonNull Context context, @NonNull LoadingDialog dialog) {
        final FragmentManager manager = ActivityUtils.getManager(context);
        Logger.e("show0",manager);
        Logger.e("show1",dialog);
        Logger.e("show2",dialog.isAdded());
        Logger.e("show3",dialog.isVisible());

        if (manager != null && dialog != null && !dialog.isAdded() && !dialog.isVisible()) {
            if (manager.findFragmentByTag(LoadingDialog.class.getName()) != null) return;
            dialog.show(manager, LoadingDialog.class.getName());
        }

    }

    public static LoadingDialog newInstance() {
        LoadingDialog dialog = new LoadingDialog();
        dialog.setCancelable(false);
        return dialog;
    }

    public static void dismiss(@NonNull LoadingDialog dialog) {
        if (dialog != null && dialog.getActivity() != null && !dialog.getActivity().isFinishing() && dialog.isAdded()) {
            dialog.dismiss();
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        if(dialog.getWindow() != null)
            dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return dialog;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DialogLoadingBinding mBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_loading, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        FragmentUtils.setWindowFullSize(this);
    }

}


