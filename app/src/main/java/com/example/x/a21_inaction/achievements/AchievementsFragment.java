package com.example.x.a21_inaction.achievements;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.x.a21_inaction.R;
import com.example.x.a21_inaction.achievements.data.Achievement;
import com.example.x.a21_inaction.achievements.data.AchievementAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AchievementsFragment extends Fragment {

    AchievementAdapter adapter = new AchievementAdapter();

    @BindView(R.id.achievements_recyclerView)
    RecyclerView achievementsRecyclerView;

    private void setupViewModel() {
        AchievementViewModel viewModel = ViewModelProviders.of(getActivity()).get(AchievementViewModel.class);
        viewModel.getAchievements().observe(getActivity(), new Observer<List<Achievement>>() {
            @Override
            public void onChanged(@Nullable List<Achievement> achievements) {
                adapter.setAchievements(achievements);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_achievements, container, false);
        ButterKnife.bind(this, view);

        achievementsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        achievementsRecyclerView.setAdapter(adapter);

        setupViewModel();

        return view;
    }
}
