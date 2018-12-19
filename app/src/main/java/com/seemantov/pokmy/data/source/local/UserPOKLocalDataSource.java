package com.seemantov.pokmy.data.source.local;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.seemantov.pokmy.data.source.local.database.AppDatabase;
import com.seemantov.pokmy.data.source.local.entity.UserPOK;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class UserPOKLocalDataSource {

    private final AppDatabase database;

    @Inject
    public UserPOKLocalDataSource(@NonNull AppDatabase database) {
        this.database = database;
    }

    // save connected user on data base
    public void addConnectedUser(UserPOK user) {
        database.getUserPOKDao().insert(user);
    }

    public void delete() {
        database.getUserPOKDao().delete();
    }




    // get token connected user from database
    public String getConnectedUserToken() {
        return database.getUserPOKDao().getConnectedUserPOKToken();
    }

    // get token connected user from database
    public LiveData<String> getConnectedUserToken2() {
        return database.getUserPOKDao().getConnectedUserPOKToken2();
    }



}
