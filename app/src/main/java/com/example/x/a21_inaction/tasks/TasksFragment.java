package com.example.x.a21_inaction.tasks;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.x.a21_inaction.R;
import com.example.x.a21_inaction.app_database.AppDatabase;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TasksFragment extends Fragment implements TaskAdapter.OnListItemClickListener {

    public static final String KEY_TASK_ID = "key-task-id";
    @BindView(R.id.tasks_recyclerView)
    RecyclerView tasksRecyclerView;

    @OnClick(R.id.go_add_task_fab)
    void goAddTask() {
        Intent intent = new Intent(getActivity(), AddEditTaskActivity.class);
        startActivity(intent);

    }

    private void goEditTask(int taskId) {
        Intent intent = new Intent(getActivity(), AddEditTaskActivity.class);
        intent.putExtra(KEY_TASK_ID, taskId);
        startActivity(intent);

    }

    TaskAdapter adapter = new TaskAdapter(this);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tasks, container, false);
        ButterKnife.bind(this, view);

        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        tasksRecyclerView.setAdapter(adapter);

        setupViewModel();

        return view;
    }

    private void setupViewModel() {
        TasksViewModel tasksViewModel = ViewModelProviders.of(this).get(TasksViewModel.class);
        tasksViewModel.getTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(@Nullable List<Task> tasks) {
                adapter.setTasks(tasks);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onListItemClick(int taskId) {
        goEditTask(taskId);
    }
}
