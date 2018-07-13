package com.example.x.a21_inaction.day_zero.view_model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.x.a21_inaction.app_database.AppDatabase;
import com.example.x.a21_inaction.app_database.AppExecutors;
import com.example.x.a21_inaction.day_zero.data.Benefit;

import java.util.List;

public class BenefitViewModel extends AndroidViewModel {

    LiveData<List<Benefit>> benefits;

    public BenefitViewModel(@NonNull Application application) {
        super(application);

        benefits = AppDatabase.getInstance(application.getApplicationContext()).benefitDao().getAllBenefits();

    }

    public LiveData<List<Benefit>> getBenefits() {
        return benefits;
    }
}
