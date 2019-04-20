package com.limra.jaipurilohar;

import android.app.Application;
import androidx.room.Room;
import com.limra.jaipurilohar.dao.AppDataBase;

public class LoharApplication extends Application {
    private static final String DATABASE_NAME = "user_db";
    public static AppDataBase mAppDataBase;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppDataBase = Room.databaseBuilder(getApplicationContext(),
                AppDataBase.class, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }
}
