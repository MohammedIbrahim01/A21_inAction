package com.example.x.a21_inaction.app_database;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecutors {

    private final Executor diskIO;
    private final Executor networkIO;
    private final Executor mainThreadIO;

    private static final Object LOCK = new Object();
    private static AppExecutors sInstance;

    public AppExecutors(Executor diskIO, Executor networkIO, Executor mainThreadIO) {
        this.diskIO = diskIO;
        this.networkIO = networkIO;
        this.mainThreadIO = mainThreadIO;

    }

    public static AppExecutors getInstance() {
        if (sInstance == null) {
            sInstance = new AppExecutors(Executors.newSingleThreadExecutor(), Executors.newFixedThreadPool(3)
                    , new MainThreadExecutors());

        }
        return sInstance;

    }

    private static class MainThreadExecutors implements Executor {
        Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable runnable) {
            mainThreadHandler.post(runnable);
        }
    }
}
