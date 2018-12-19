package com.seemantov.pokmy.data.source.local;

/**
 * Created by Tarek Ben Driss on 02/03/2018.
 */
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.seemantov.pokmy.data.source.local.database.AppDatabase;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserLocalDataSource {

    private final AppDatabase database;

    @Inject
    public UserLocalDataSource(@NonNull AppDatabase database) {
        this.database = database;
    }

    // save connected user on data base


    // get connected from data base


}
