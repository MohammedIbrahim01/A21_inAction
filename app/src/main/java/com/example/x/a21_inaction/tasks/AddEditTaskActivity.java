package com.example.x.a21_inaction.tasks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.x.a21_inaction.R;
import com.example.x.a21_inaction.app_database.AppDatabase;
import com.example.x.a21_inaction.app_database.AppExecutors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddEditTaskActivity extends AppCompatActivity {

    @BindView(R.id.add_edit_task_editText)
    EditText addEditTaskEditText;

    @OnClick(R.id.save_Button)
    void saveTask() {
        final String title = addEditTaskEditText.getText().toString();
        AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase.getInstance(getApplicationContext()).taskDao().insertTask(new Task(title));
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_task);
        ButterKnife.bind(this);
    }
}
