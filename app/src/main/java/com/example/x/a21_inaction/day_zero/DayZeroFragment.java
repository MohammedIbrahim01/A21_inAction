package com.example.x.a21_inaction.day_zero;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.x.a21_inaction.R;
import com.example.x.a21_inaction.day_zero.data.Benefit;
import com.example.x.a21_inaction.day_zero.data.BenefitAdapter;
import com.example.x.a21_inaction.day_zero.view_model.BenefitViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DayZeroFragment extends Fragment {

    @BindView(R.id.benefits_recyclerView)
    RecyclerView benefitsRecyclerView;
    @OnClick(R.id.add_benefit_fab)
    void goAddBenefit(){
        Intent intent = new Intent(getActivity(), AddBenefitActivity.class);
        startActivity(intent);
    }

    private BenefitAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_day_zero, container, false);
        ButterKnife.bind(this, view);

        adapter = new BenefitAdapter();

        benefitsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        benefitsRecyclerView.setAdapter(adapter);

        setupViewModel();

        return view;
    }

    private void setupViewModel() {
        BenefitViewModel viewModel = ViewModelProviders.of(getActivity()).get(BenefitViewModel.class);
        viewModel.getBenefits().observe(getActivity(), new Observer<List<Benefit>>() {
            @Override
            public void onChanged(@Nullable List<Benefit> benefits) {
                adapter.setBenefits(benefits);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
