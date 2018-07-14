package com.example.x.a21_inaction.counter;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.x.a21_inaction._main_glue.MainActivity;

public class Counter {

    public static final String APP_SHARED_PREFERENCES = "app-shared-preferences";
    public static final String KEY_COUNT = "key=count";
    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private AlarmManager alarmManager;

    public Counter(Context context) {
        this.context = context;
    }

    public void start(){

        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, new Intent(context, Receiver.class), PendingIntent.FLAG_CANCEL_CURRENT);
        alarmManager.setRepeating(AlarmManager.RTC, System.currentTimeMillis() + 2 * 1000, 2 * 1000, pendingIntent);

    }

}
