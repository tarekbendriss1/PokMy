package com.seemantov.pokmy.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.seemantov.pokmy.base.BaseActivity;
import com.seemantov.pokmy.base.BaseFragment;

public class ActivityUtils {

    public static FragmentManager getManager(Context context) {
        if (context != null) {
            try {
                return ((BaseActivity) context).getSupportFragmentManager();
            } catch (Exception error) {
                return null;
            }
        }
        return null;
    }

    public static void hideStatusBar(Activity activity) {
        // Hide the Action Bar
        getWindow(activity).setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void showStatusBar(Activity activity) {
        getWindow(activity).setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
    }

    public static Window getWindow(@NonNull Activity activity) {
        return activity.getWindow();
    }

    public static View getRootView(@NonNull Activity activity) {
        return getWindow(activity).getDecorView().getRootView();
    }

    public <T extends BaseFragment> void commitFragment(@NonNull BaseActivity activity, @IdRes int layout, @NonNull Class<T> clazz) {
        if (activity != null && !activity.isFinishing() && layout >= 0 && clazz != null && StringUtils.isNotEmpty(clazz.getName())) {
            try {
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                if (fragmentManager != null) {
                    Fragment fragment = clazz.newInstance();
                    if (fragment != null) {
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.replace(layout, fragment, clazz.getName());
                        transaction.commitAllowingStateLoss();
                    }
                }
            } catch (Exception exp) {
                Logger.error(this, exp);
            }
        }
    }
}
