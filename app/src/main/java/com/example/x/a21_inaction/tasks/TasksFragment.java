package com.example.x.a21_inaction.tasks;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.x.a21_inaction.R;
import com.example.x.a21_inaction.app_database.AppDatabase;
import com.example.x.a21_inaction.app_database.AppExecutors;
import com.example.x.a21_inaction.tasks.data.Task;
import com.example.x.a21_inaction.tasks.data.TaskAdapter;
import com.example.x.a21_inaction.tasks.view_model.TasksViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TasksFragment extends Fragment implements TaskAdapter.OnListItemClickListener {

    public static final String KEY_TASK_ID = "key-task-id";
    TaskAdapter adapter = new TaskAdapter(this);
    AppDatabase database;
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

    private void setupViewModel() {
        TasksViewModel tasksViewModel = ViewModelProviders.of(getActivity()).get(TasksViewModel.class);
        tasksViewModel.getTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(@Nullable List<Task> tasks) {
                adapter.setTasks(tasks);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void setupSwipeFunctionality() {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                List<Task> tasks = adapter.getTasks();
                final Task task = tasks.get(viewHolder.getAdapterPosition());

                //delete from tasks
                AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        database.taskDao().deleteTask(task);
                    }
                });
            }
        }).attachToRecyclerView(tasksRecyclerView);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tasks, container, false);
        ButterKnife.bind(this, view);

        //get AppDatabase
        database = AppDatabase.getInstance(getActivity().getApplicationContext());

        //setup divider and recyclerView
        DividerItemDecoration decoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        decoration.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.tasks_divider));

        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        tasksRecyclerView.addItemDecoration(decoration);
        tasksRecyclerView.setAdapter(adapter);
        setupSwipeFunctionality();

        setupViewModel();

        return view;
    }



    @Override
    public void onListItemClick(int taskId) {
        goEditTask(taskId);
    }
}
