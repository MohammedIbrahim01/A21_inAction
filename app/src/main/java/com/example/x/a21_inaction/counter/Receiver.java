package com.example.x.a21_inaction.counter;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class Receiver extends BroadcastReceiver {

    public static final String APP_SHARED_PREFERENCES = "app-shared-preferences";
    public static final String KEY_COUNT = "key=count";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private AlarmManager alarmManager;

    @Override
    public void onReceive(Context context, Intent intent) {

        sharedPreferences = context.getSharedPreferences(APP_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        int count = sharedPreferences.getInt(KEY_COUNT, 0);

        editor.putInt(KEY_COUNT, ++count).apply();

        if (count == 21) {

            alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, new Intent(context, Receiver.class), PendingIntent.FLAG_CANCEL_CURRENT);
            alarmManager.cancel(pendingIntent);
            Toast.makeText(context, "finish!", Toast.LENGTH_SHORT).show();

        }

    }
}
