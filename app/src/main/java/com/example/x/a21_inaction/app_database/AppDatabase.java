package com.example.x.a21_inaction.app_database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.x.a21_inaction.achievements.data.Achievement;
import com.example.x.a21_inaction.achievements.data.AchievementDao;
import com.example.x.a21_inaction.day_zero.data.Benefit;
import com.example.x.a21_inaction.day_zero.data.BenefitDao;
import com.example.x.a21_inaction.tasks.data.Task;
import com.example.x.a21_inaction.tasks.data.TaskDao;

@Database(entities = {Task.class, Achievement.class, Benefit.class}, version = 3)
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

    public abstract AchievementDao achievementDao();

    public abstract BenefitDao benefitDao();
}
