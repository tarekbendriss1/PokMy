package com.seemantov.pokmy;

import android.app.Application;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.multidex.MultiDexApplication;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.seemantov.pokmy.data.DaggerDataComponent;
import com.seemantov.pokmy.data.DataComponent;
import com.seemantov.pokmy.data.source.local.UserPOKLocalDataSource;
import com.seemantov.pokmy.data.source.local.database.StorageModule;
import com.seemantov.pokmy.data.source.remote.service.NetworkModule;
import com.seemantov.pokmy.data.source.remote.service.ServiceEndpoint;
import com.seemantov.pokmy.utils.Constants;
import com.seemantov.pokmy.widgets.LoadingDialog;


import javax.inject.Inject;

import io.fabric.sdk.android.Fabric;

public class App extends MultiDexApplication {

    private static DataComponent dataComponent;
    public static Application app;
    private static LoadingDialog loader;
    private static LoadingDialog secondLoader;
    public static Double lastDose;
    public static Context context;
    public static String token;


    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    public static DataComponent getDataComponent() {
        return dataComponent;
    }




    public static boolean isDialogShowed() {
        return loader.isAdded();
    }


    public static void hideLoader() {
        LoadingDialog.dismiss(loader);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        loader = LoadingDialog.newInstance();
        Log.e("onCreate App",""+loader);
        initAppComponent();
        app=this;
        /*
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        if(token==null || token.equals(""))
        {
            token = preferences.getString(Constants.TOKEN,"nk");
        }
        */
    }

    public static void showLoader(Context context) {
        LoadingDialog.show(context, loader);
    }


    private void initAppComponent() {
        dataComponent = DaggerDataComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule(ServiceEndpoint.BASE_URL))
                .storageModule(new StorageModule())
                .build();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
