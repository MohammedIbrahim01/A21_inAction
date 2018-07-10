package com.example.x.a21_inaction.achievements.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface AchievementDao {

    @Query("SELECT * FROM achievements")
    LiveData<List<Achievement>> getAllAchievements();

    @Query("SELECT * FROM achievements WHERE id = :id")
    LiveData<Achievement> getAchievement(int id);

    @Insert
    void insertAchievement(Achievement achievement);

    @Delete
    void deleteAchievement(Achievement achievement);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateAchievement(Achievement achievement);
}
