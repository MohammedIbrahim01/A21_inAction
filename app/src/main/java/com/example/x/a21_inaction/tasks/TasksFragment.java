package com.example.x.a21_inaction.tasks;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.x.a21_inaction.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TasksFragment extends Fragment {

    @BindView(R.id.tasks_recyclerView)
    RecyclerView tasksRecyclerView;
    @BindView(R.id.go_add_task_fab)
    FloatingActionButton goAddTaskFab;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tasks, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
