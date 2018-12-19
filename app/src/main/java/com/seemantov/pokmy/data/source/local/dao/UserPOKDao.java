package com.seemantov.pokmy.data.source.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.seemantov.pokmy.data.source.local.entity.UserPOK;

import java.util.List;


@Dao
public interface UserPOKDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserPOK userPOK);

    // Adds a movie to the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<UserPOK> usersPOK);

    // Removes a movie from the database

    @Query("DELETE FROM userPOK")
    void delete();

    // Gets all people in the database
    @Query("SELECT * FROM userPOK")
    LiveData<List<UserPOK>> findAll();

    @Query("SELECT token FROM userPOK")
    String getConnectedUserPOKToken();

    @Query("SELECT token FROM userPOK")
    LiveData<String> getConnectedUserPOKToken2();
}