package com.example.x.a21_inaction.tasks;

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

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {


    private List<Task> tasks;
    private final OnListItemClickListener onListItemClickListener;


    //constructor
    public TaskAdapter(OnListItemClickListener onListItemClickListener) {
        this.onListItemClickListener = onListItemClickListener;

    }


    //to set tasks
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;

    }


    //to handle click on List Item
    public interface OnListItemClickListener {
        void onListItemClick(int taskId);

    }


    @NonNull
    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_raw, parent, false);

        return new ViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.ViewHolder holder, int position) {
        Task currentTask = tasks.get(position);
        holder.titleTextView.setText(currentTask.getTitle());

    }


    @Override
    public int getItemCount() {

        if (tasks == null)
            return 0;

        else
            return tasks.size();

    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.title_textView)
        TextView titleTextView;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            Task currentTask = tasks.get(getAdapterPosition());
            onListItemClickListener.onListItemClick(currentTask.getId());
        }
    }

}
