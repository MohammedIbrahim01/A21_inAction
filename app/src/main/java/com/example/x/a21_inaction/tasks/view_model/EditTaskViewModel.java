package com.example.x.a21_inaction.tasks.view_model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.x.a21_inaction.app_database.AppDatabase;
import com.example.x.a21_inaction.tasks.data.Task;

public class EditTaskViewModel extends ViewModel {

    private LiveData<Task> task;

    public EditTaskViewModel(AppDatabase database, int taskId) {
        task = database.taskDao().getTask(taskId);
    }

    public LiveData<Task> getTask() {
        return task;
    }
}
