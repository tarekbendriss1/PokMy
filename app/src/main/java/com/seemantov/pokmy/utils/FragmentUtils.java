package com.seemantov.pokmy.utils;

import android.app.FragmentManager;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

public class FragmentUtils {

    @Nullable
    public static <T> T huskFragment(Class<T> instance, Object object) {
        if (object == null) {
            return null;
        }
        if (object.getClass().equals(instance)) {
            return (T) object;
        }
        return null;
    }

    @Nullable
    public static <T> T huskFragment(Class<T> instance, final int id, final FragmentActivity activity) {
        return huskFragment(instance, activity.getSupportFragmentManager().findFragmentById(id));
    }

    @Nullable
    public static <T> T huskFragment(Class<T> instance, final int id, final FragmentManager childFragmentManager) {
        return huskFragment(instance, childFragmentManager.findFragmentById(id));
    }

    public static <T> T huskFragmentOrCreate(Class<T> instance, final int id, final FragmentManager childFragmentManager) {
        final T fragment = huskFragment(instance, childFragmentManager.findFragmentById(id));
        if (fragment != null) {
            return fragment;
        }
        try {
            return instance.newInstance();
        } catch (Exception ex) {
            throw new RuntimeException("Fix fragment husk!");
        }
    }

    @Nullable
    public static <T> T huskFragment(Class<T> instance, final String tag, final FragmentActivity activity) {
        return huskFragment(instance, activity.getSupportFragmentManager().findFragmentByTag(tag));
    }

    @Nullable
    public static <T> T huskFragment(final Class<T> instance, final String tag, final FragmentManager childFragmentManager) {
        return huskFragment(instance, childFragmentManager.findFragmentByTag(tag));
    }

    public static void setWindowFullSize(final DialogFragment fragment) {
        Window window = fragment.getDialog().getWindow();
        if(window!=null){
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            window.setGravity(Gravity.CENTER);
        }


    }
}
