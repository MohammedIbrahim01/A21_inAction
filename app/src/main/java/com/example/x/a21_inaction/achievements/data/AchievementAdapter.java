package com.example.x.a21_inaction.achievements.data;

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

public class AchievementAdapter extends RecyclerView.Adapter<AchievementAdapter.ViewHolder> {

    private List<Achievement> achievements;

    public List<Achievement> getAchievements() {
        return achievements;
    }

    public void setAchievements(List<Achievement> achievements) {
        this.achievements = achievements;
    }

    @NonNull
    @Override
    public AchievementAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.achievement_raw, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AchievementAdapter.ViewHolder holder, int position) {
        Achievement currentAchievement = achievements.get(position);
        holder.achievementTitleTextView.setText(currentAchievement.getTitle());
    }

    @Override
    public int getItemCount() {
        if (achievements == null)
            return 0;
        else
            return achievements.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.achievement_title_textView)
        TextView achievementTitleTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
