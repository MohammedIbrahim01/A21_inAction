package com.example.x.a21_inaction.tasks.view_model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.x.a21_inaction.app_database.AppDatabase;
import com.example.x.a21_inaction.tasks.data.Task;

import java.util.List;

public class TasksViewModel extends AndroidViewModel {

    private LiveData<List<Task>> tasks;

    public TasksViewModel(@NonNull Application application) {
        super(application);

        Log.i("www", "onChanged: from viewModel");
        tasks = AppDatabase.getInstance(application.getApplicationContext()).taskDao().getAllTasks();

    }


    public LiveData<List<Task>> getTasks() {
        return tasks;

    }
}
