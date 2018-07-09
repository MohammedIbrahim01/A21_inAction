package com.example.x.a21_inaction._main_glue;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.x.a21_inaction.R;
import com.example.x.a21_inaction.achievements.AchievementsFragment;
import com.example.x.a21_inaction.day_zero.DayZeroFragment;
import com.example.x.a21_inaction.tasks.TasksFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.counter_textView)
    TextView counterTextView;
    @BindView(R.id.main_tabLayout)
    TabLayout mainTabLayout;
    @BindView(R.id.main_viewPager)
    ViewPager mainViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //glue the three fragments
        MainFragmentPagerAdapter adapter = new MainFragmentPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new TasksFragment(), "tasks");
        adapter.addFragment(new AchievementsFragment(), "achievements");
        adapter.addFragment(new DayZeroFragment(), "day zero");

        mainViewPager.setAdapter(adapter);
        mainTabLayout.setupWithViewPager(mainViewPager);
    }
}
