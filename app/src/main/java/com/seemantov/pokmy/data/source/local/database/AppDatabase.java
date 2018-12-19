package com.seemantov.pokmy.data.source.local.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.seemantov.pokmy.data.source.local.converter.Converters;
import com.seemantov.pokmy.data.source.local.dao.UserPOKDao;
import com.seemantov.pokmy.data.source.local.entity.UserPOK;
import com.seemantov.pokmy.utils.ApplicationUtils;

import static com.seemantov.pokmy.data.source.local.database.AppDatabase.VERSION;

@Database(entities = {UserPOK.class}, version = VERSION, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    static final int VERSION = 4;
    static final String NAME = ApplicationUtils.NAME + ".db";

    public abstract UserPOKDao getUserPOKDao();
    //public abstract MessagesDao getMessagesDao();


}