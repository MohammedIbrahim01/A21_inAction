package com.example.x.a21_inaction.tasks;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.x.a21_inaction.app_database.AppDatabase;

import java.util.List;

public class TasksViewModel extends AndroidViewModel {

    private LiveData<List<Task>> tasks;

    public TasksViewModel(@NonNull Application application) {
        super(application);
        tasks = AppDatabase.getInstance(application.getApplicationContext()).taskDao().getAllTasks();
    }

    public LiveData<List<Task>> getTasks() {
        return tasks;
    }
}
