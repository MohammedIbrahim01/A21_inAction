package com.example.x.a21_inaction.tasks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.x.a21_inaction.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddEditTaskActivity extends AppCompatActivity {

    @BindView(R.id.add_edit_task_editText)
    EditText addEditTaskEditText;
    @BindView(R.id.save_Button)
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_task);
        ButterKnife.bind(this);
    }
}
