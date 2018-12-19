package com.seemantov.pokmy.pokmy.login;


import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.seemantov.pokmy.App;
import com.seemantov.pokmy.base.BaseViewModel;
import com.seemantov.pokmy.data.source.remote.params.LoginParams;
import com.seemantov.pokmy.data.source.remote.response.LoginPokResponse;
import com.seemantov.pokmy.data.source.repository.UserRepository;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginViewModel extends BaseViewModel {


    @Inject
    UserRepository mRepository;

    // constructor
    public LoginViewModel(@NonNull Application application) {
        super(application);
        App.getDataComponent().inject(this);
    }

    public Single<LoginPokResponse> signinPok(LoginParams lp) {
        return mRepository.signinPok(lp)
                //
                .subscribeOn(Schedulers.computation())
                //
                .observeOn(AndroidSchedulers.mainThread());
    }


    public String getConnectedUserToken() {
        return mRepository.getConnectedUserToken();
    }

    public LiveData<String> getConnectedUserToken2() {
        return mRepository.getConnectedUserToken2();
    }









}
