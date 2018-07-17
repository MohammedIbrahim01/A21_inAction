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
import com.example.x.a21_inaction.achievements.data.Achievement;
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


    private TaskAdapter adapter = new TaskAdapter(this);
    private AppDatabase database;
    private List<Task> tasksList;


    @BindView(R.id.tasks_recyclerView)
    RecyclerView tasksRecyclerView;


    //when click add task button
    @OnClick(R.id.go_add_task_fab)
    void goAddTask() {

        //go to AddEditTaskActivity
        startActivity(new Intent(getActivity(), AddEditTaskActivity.class));

    }


    //when click on the task to edit it
    private void goEditTask(int taskId) {

        //go to AddEditTaskActivity with extra task id
        Intent intent = new Intent(getActivity(), AddEditTaskActivity.class);
        intent.putExtra(KEY_TASK_ID, taskId);
        startActivity(intent);

    }


    /**
     * setup viewModel to cache the data so we don't have to re-query database  when configuration changed
     */
    private void setupViewModel() {

        TasksViewModel tasksViewModel = ViewModelProviders.of(getActivity()).get(TasksViewModel.class);
        tasksViewModel.getTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(@Nullable List<Task> tasks) {

                tasksList = tasks;
                adapter.setTasks(tasks);
                adapter.notifyDataSetChanged();

            }
        });

    }


    /**
     * setupSwipeFunctionality to the recycler view
     */
    private void setupSwipeFunctionality() {


        // no drag and swipe to the right only
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {


                //get the task that was swiped by adapter position
                final Task task = tasksList.get(viewHolder.getAdapterPosition());

                //delete from tasks and added to achievements
                AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
                    @Override
                    public void run() {

                        database.taskDao().deleteTask(task);
                        database.achievementDao().insertAchievement(new Achievement(task.getTitle()));

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
        setUpRecyclerView();


        setupViewModel();


        return view;
    }

    private void setUpRecyclerView() {

        DividerItemDecoration decoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        decoration.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.tasks_divider));


        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        tasksRecyclerView.addItemDecoration(decoration);
        tasksRecyclerView.setAdapter(adapter);
        setupSwipeFunctionality();

    }


    @Override
    public void onListItemClick(int taskId) {
        goEditTask(taskId);
    }


}
