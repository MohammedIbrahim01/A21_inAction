package com.example.x.a21_inaction.achievements.view_model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.x.a21_inaction.achievements.data.Achievement;
import com.example.x.a21_inaction.app_database.AppDatabase;

import java.util.List;

public class AchievementViewModel extends AndroidViewModel {

    private LiveData<List<Achievement>> achievements;

    public AchievementViewModel(@NonNull Application application) {
        super(application);

        AppDatabase database = AppDatabase.getInstance(application.getApplicationContext());
        achievements = database.achievementDao().getAllAchievements();
    }

    public LiveData<List<Achievement>> getAchievements() {
        return achievements;
    }
}
