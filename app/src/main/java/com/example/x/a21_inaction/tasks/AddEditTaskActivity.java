package com.example.x.a21_inaction.tasks;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.x.a21_inaction.R;
import com.example.x.a21_inaction.app_database.AppDatabase;
import com.example.x.a21_inaction.app_database.AppExecutors;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddEditTaskActivity extends AppCompatActivity {

    public static final String KEY_TASK_ID = "key-task-id";
    public static final int DEFAULT_VALUE = 0;

    @BindView(R.id.add_edit_task_editText)
    EditText addEditTaskEditText;
    @BindView(R.id.save_Button)
    Button saveButton;


    void saveNewTask() {
        final String title = addEditTaskEditText.getText().toString();
        AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase.getInstance(getApplicationContext()).taskDao().insertTask(new Task(title));
                finish();
            }
        });
    }


    void saveEditedTask(final int taskId) {
        final String title = addEditTaskEditText.getText().toString();

        AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                //call updateTask and pass new Task with same taskId that was clicked
                AppDatabase.getInstance(getApplicationContext()).taskDao().updateTask(new Task(taskId, title));
                finish();

            }

        });

    }

    private void populateUI(String taskTitle) {
        addEditTaskEditText.setText(taskTitle);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_task);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        //if opened for edit >> populateUI and get taskId then set onClickListener that fires saveEditedTask
        if (intent.hasExtra(KEY_TASK_ID)) {

            final int taskId = intent.getIntExtra(KEY_TASK_ID, DEFAULT_VALUE);

            EditTaskViewModelFactory viewModelFactory = new EditTaskViewModelFactory(AppDatabase.getInstance(getApplicationContext()), taskId);
            final EditTaskViewModel viewModel = ViewModelProviders.of(this, viewModelFactory).get(EditTaskViewModel.class);
            viewModel.getTask().observe(this, new Observer<Task>() {
                @Override
                public void onChanged(@Nullable Task task) {
                    populateUI(task.getTitle());
                    viewModel.getTask().removeObserver(this);

                }
            });


            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    saveEditedTask(taskId);

                }
            });

        }

        //if opened for add new Task >> set onClickListener that fires saveNewTask
        else {
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    saveNewTask();
                }
            });

        }

    }
}
