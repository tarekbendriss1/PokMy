package com.seemantov.pokmy.pokmy.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import io.reactivex.android.schedulers.AndroidSchedulers;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import com.seemantov.pokmy.App;
import com.seemantov.pokmy.R;
import com.seemantov.pokmy.base.BaseActivity;
import com.seemantov.pokmy.data.source.local.UserLocalDataSource;
import com.seemantov.pokmy.data.source.local.UserPOKLocalDataSource;
import com.seemantov.pokmy.data.source.remote.params.LoginParams;
import com.seemantov.pokmy.data.source.remote.response.LoginPokResponse;
import com.seemantov.pokmy.databinding.ContentLoginBinding;
import com.seemantov.pokmy.pokmy.DashboardActivity;
import com.seemantov.pokmy.utils.AlertDialogUtils;
import com.seemantov.pokmy.utils.Constants;
import com.seemantov.pokmy.utils.Logger;
import com.seemantov.pokmy.utils.StringUtils;

import android.arch.lifecycle.ViewModelProviders;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends BaseActivity {

    private CompositeDisposable mDisposable;
    private LoginViewModel mSignInViewModel;
    private Button login;
    private EditText username,password;
    private ContentLoginBinding mBinding;


    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
        initDataBinding();
        initVars();

        App.getDataComponent().inject(this);

        login.setOnClickListener(view1 -> {
            String u,p;
            u= username.getText().toString();
            p= password.getText().toString();
            if(isValid())
                doLogin(u, p);
            else
                AlertDialogUtils.show(mContext, mContext.getResources().getString(R.string.required_fields));
        });
    }

    private void initDataBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.content_login);
        if(mBinding==null)
            Logger.i("null");
        else
            Logger.i("not null");
        login = mBinding.loginBtn;
        username = mBinding.loginEt;
        password = mBinding.passwordEt;
    }

    private void initVars()
    {
        mDisposable = new CompositeDisposable();
        mSignInViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        mDisposable = new CompositeDisposable();
    }

    void doLogin(String u,String p)
    {
        LoginParams lp = new LoginParams(u,p);
        mDisposable.add(mSignInViewModel.signinPok(lp)
                .subscribeOn(Schedulers.computation())
                .doOnSubscribe(disposable -> App.showLoader(mContext))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<LoginPokResponse>() {
                    @Override
                    public void onSuccess(LoginPokResponse response) {
                        Logger.e("SigninResponse", response.toString());

                        editor.putBoolean(Constants.IS_LOGGED_IN,true);
                        editor.putString(Constants.TOKEN,response.getToken());
                        editor.apply();

                        App.token = response.getToken();

                        Intent intent = new Intent(LoginActivity.this,DashboardActivity.class);
                        startActivity(intent);

                    }

                    @Override
                    public void onError(Throwable error) {
                        Logger.e("SigninResponse error", error);
                        App.hideLoader();
                        AlertDialogUtils.showString(mActivity, R.string.error_incorrect_password, getString(R.string.error_incorrect_password_msg), null);

                    }
                }));
    }


    private boolean isValid() {
        return StringUtils.isNotEmpty(username.getText().toString())
                && StringUtils.isNotEmpty(password.getText().toString());
    }
}
