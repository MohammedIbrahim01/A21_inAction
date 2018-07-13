package com.example.x.a21_inaction.day_zero.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface BenefitDao {

    @Query("SELECT * FROM benefits")
    LiveData<List<Benefit>> getAllBenefits();

    @Insert
    void insertBenefit(Benefit benefit);

    @Delete
    void deleteBenefit(Benefit benefit);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateBenefit(Benefit benefit);

}
