package com.example.x.a21_inaction.app_database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.x.a21_inaction.tasks.Task;
import com.example.x.a21_inaction.tasks.TaskDao;

@Database(entities = {Task.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final String NAME_DATABASE = "App_database";
    private static final Object LOCK = new Object();
    private static AppDatabase sInstance;

    public static AppDatabase getInstance(Context applicationContext) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = Room.databaseBuilder(applicationContext, AppDatabase.class, NAME_DATABASE)
                        .build();
            }
        }
        return sInstance;
    }

    public abstract TaskDao taskDao();
}
