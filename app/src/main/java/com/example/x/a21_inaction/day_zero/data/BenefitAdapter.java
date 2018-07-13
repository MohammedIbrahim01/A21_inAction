package com.example.x.a21_inaction.day_zero.data;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.x.a21_inaction.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BenefitAdapter extends RecyclerView.Adapter<BenefitAdapter.ViewHolder> {

    private List<Benefit> benefits;

    public void setBenefits(List<Benefit> benefits) {
        this.benefits = benefits;
    }

    @NonNull
    @Override
    public BenefitAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.benefit_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BenefitAdapter.ViewHolder holder, int position) {
        holder.benefitTitleTextView.setText(benefits.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        if (benefits == null)
            return 0;
        else
            return benefits.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.benefit_title_textView)
        TextView benefitTitleTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
