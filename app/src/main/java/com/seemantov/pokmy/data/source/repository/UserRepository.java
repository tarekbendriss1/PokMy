package com.seemantov.pokmy.data.source.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.seemantov.pokmy.data.source.local.UserLocalDataSource;
import com.seemantov.pokmy.data.source.local.UserPOKLocalDataSource;
import com.seemantov.pokmy.data.source.local.entity.UserPOK;
import com.seemantov.pokmy.data.source.remote.UserRemoteDataSource;
import com.seemantov.pokmy.data.source.remote.params.LoginParams;
import com.seemantov.pokmy.data.source.remote.response.LoginPokResponse;

import com.seemantov.pokmy.utils.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Created by digitu on 14/02/2018.
 * UserRepository
 */

@Singleton
public class UserRepository {

    private final UserLocalDataSource userLocalDataSource;
    private final UserRemoteDataSource userRemoteDataSource;
    private final UserPOKLocalDataSource userPOKLocalDataSource;

    @Inject
    UserRepository(@NonNull UserLocalDataSource userLocalDataSource, @NonNull UserRemoteDataSource userRemoteDataSource, @NonNull UserPOKLocalDataSource userPOKLocalDataSource) {
        this.userLocalDataSource = userLocalDataSource;
        this.userRemoteDataSource = userRemoteDataSource;
        this.userPOKLocalDataSource = userPOKLocalDataSource;
    }

    // ws signup patient





    // ws signup medecin


    public Single<LoginPokResponse> signinPok(LoginParams lp) {
        return userRemoteDataSource.signinPok(lp)
                // oon Succes do in other thread
                .doOnSuccess(response -> {

                    if (response != null && response.getToken() != null) {
                        UserPOK user = new UserPOK();
                        user.set__v(response.get__v());
                        user.set_id(response.get_id());
                        user.setToken(response.getToken());
                        user.setUpdated(response.getUpdated());
                        user.setUserKey(response.getUserKey());
                        userPOKLocalDataSource.addConnectedUser(user);
                    }

                    //Logger.e("repo0",response.getUser());
                    Logger.e("repo0",response.toString());

                });
    }

    public String getConnectedUserToken() {
        return userPOKLocalDataSource.getConnectedUserToken();
    }


    public LiveData<String> getConnectedUserToken2() {
        return userPOKLocalDataSource.getConnectedUserToken2();
    }











}
