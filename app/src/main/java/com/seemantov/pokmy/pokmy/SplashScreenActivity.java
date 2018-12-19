package com.seemantov.pokmy.pokmy;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.WindowManager;

import com.seemantov.pokmy.App;
import com.seemantov.pokmy.R;
import com.seemantov.pokmy.base.BaseActivity;
import com.seemantov.pokmy.data.source.local.UserPOKLocalDataSource;
import com.seemantov.pokmy.data.source.local.database.SharedPreference;
import com.seemantov.pokmy.data.source.remote.params.LoginParams;
import com.seemantov.pokmy.data.source.remote.response.LoginPokResponse;
import com.seemantov.pokmy.pokmy.login.LoginActivity;
import com.seemantov.pokmy.pokmy.login.LoginViewModel;
import com.seemantov.pokmy.utils.AlertDialogUtils;
import com.seemantov.pokmy.utils.Constants;
import com.seemantov.pokmy.utils.Logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class SplashScreenActivity extends BaseActivity {

    private SharedPreferences preferences;

    @Inject
    SharedPreference mSharedPreference;

    private LoginViewModel mSignInViewModel;

    private final int SPLASH_DISPLAY_LENGTH = 4000;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        preferences = PreferenceManager.getDefaultSharedPreferences(mContext);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        mSignInViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);


        if(preferences.getBoolean(Constants.IS_LOGGED_IN,false)==true)
            getToken();


        new Handler().postDelayed(() -> {
            Intent mainIntent2 = new Intent(SplashScreenActivity.this, LoginActivity.class);
            Intent dhasboardIntent = new Intent(SplashScreenActivity.this, DashboardActivity.class);

            if(preferences.getBoolean(Constants.IS_LOGGED_IN,false)==false)
                SplashScreenActivity.this.startActivity(mainIntent2);
            else
                SplashScreenActivity.this.startActivity(dhasboardIntent);

            SplashScreenActivity.this.finish();
        }, SPLASH_DISPLAY_LENGTH);
    }


    private void getToken()
    {
        mSignInViewModel.getConnectedUserToken2().observe(this,a ->
            App.token =  a
        );
    }
}
