package com.example.x.a21_inaction._main_glue;

import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.x.a21_inaction.R;
import com.example.x.a21_inaction.achievements.AchievementsFragment;
import com.example.x.a21_inaction.counter.Counter;
import com.example.x.a21_inaction.day_zero.DayZeroFragment;
import com.example.x.a21_inaction.tasks.TasksFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {


    public static final String APP_SHARED_PREFERENCES = "app-shared-preferences";
    public static final String KEY_COUNT = "key=count";


    private SharedPreferences sharedPreferences;


    @BindView(R.id.counter_textView)
    TextView counterTextView;
    @BindView(R.id.main_tabLayout)
    TabLayout mainTabLayout;
    @BindView(R.id.main_viewPager)
    ViewPager mainViewPager;


    private void setCounter() {

        //get count from shared preferences and set counterTextView text to it
        counterTextView.setText(String.valueOf(sharedPreferences.getInt(KEY_COUNT, 0)));

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //get shared preferences with name app-shared-preferences
        sharedPreferences = getSharedPreferences(APP_SHARED_PREFERENCES, MODE_PRIVATE);

        //set count in counterTextView
        setCounter();

        //glue the three fragments
        MainFragmentPagerAdapter adapter = new MainFragmentPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new TasksFragment(), "tasks");
        adapter.addFragment(new AchievementsFragment(), "achievements");
        adapter.addFragment(new DayZeroFragment(), "day zero");

        mainViewPager.setAdapter(adapter);
        mainTabLayout.setupWithViewPager(mainViewPager);

    }


    @Override
    protected void onResume() {
        super.onResume();
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }


    /**
     * menu stuff
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (item.getItemId() == R.id.action_start) {

            Counter counter = new Counter(this);
            counter.start();

        }


        return super.onOptionsItemSelected(item);


    }


    /**
     * when shared preferences changed reset the counter
     *
     * @param sharedPreferences
     * @param s
     */
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {


        if (s == KEY_COUNT) {

            setCounter();

        }


    }
}
