package com.example.qreminder;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class TaskListAdapter extends ListAdapter<Task, TaskViewHolder> {

    private int type;
    public TaskListAdapter(@NonNull DiffUtil.ItemCallback<Task> diffCallback, int type) {
        super(diffCallback);
        this.type = type;
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return TaskViewHolder.create(parent, type);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        Task current = getItem(position);
        holder.bind(current);
    }

    static class TaskDiff extends DiffUtil.ItemCallback<Task> {

        @Override
        public boolean areItemsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
            return oldItem.toString().equals(newItem.toString());
        }
    }
}