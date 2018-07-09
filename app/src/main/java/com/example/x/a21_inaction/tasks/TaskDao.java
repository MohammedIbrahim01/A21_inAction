package com.example.x.a21_inaction.tasks;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM tasks")
    List<Task> getAllTasks();

    @Query("SELECT * FROM tasks WHERE id = :id")
    Task getTask(int id);

    @Insert
    void insertTask();

    @Delete
    void deleteTask();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTask();
}
