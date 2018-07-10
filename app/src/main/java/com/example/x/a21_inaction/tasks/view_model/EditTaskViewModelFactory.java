package com.example.x.a21_inaction.tasks.view_model;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.x.a21_inaction.app_database.AppDatabase;
import com.example.x.a21_inaction.tasks.view_model.EditTaskViewModel;

public class EditTaskViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final AppDatabase database;
    private final int taskId;

    public EditTaskViewModelFactory(AppDatabase database, int taskId) {
        this.database = database;
        this.taskId = taskId;

    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new EditTaskViewModel(database, taskId);
    }
}
