package com.example.x.a21_inaction.day_zero;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.x.a21_inaction.R;
import com.example.x.a21_inaction.app_database.AppDatabase;
import com.example.x.a21_inaction.app_database.AppExecutors;
import com.example.x.a21_inaction.day_zero.data.Benefit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddBenefitActivity extends AppCompatActivity {

    @BindView(R.id.new_benefit_editText)
    EditText newBenefitEditText;

    AppDatabase database;

    @OnClick(R.id.save_benefit_Button)
    void saveBenefit() {
        final String title = newBenefitEditText.getText().toString();
        AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                database.benefitDao().insertBenefit(new Benefit(title));
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_benefit);
        ButterKnife.bind(this);

        database = AppDatabase.getInstance(getApplicationContext());
    }
}
