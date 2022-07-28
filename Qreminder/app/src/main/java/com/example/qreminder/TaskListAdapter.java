package com.example.qreminder;

import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;

public class TaskListAdapter extends ListAdapter<Task, TaskViewHolder> {

    private int type;
    TaskViewModel tvm;
    List<Task> checkedTasks;
    List<Task> uncheckedTasks;

    public TaskListAdapter(@NonNull DiffUtil.ItemCallback<Task> diffCallback, int type, TaskViewModel tvm) {
        super(diffCallback);
        this.type = type;
        this.tvm = tvm;
        checkedTasks = new ArrayList<Task>();
        uncheckedTasks = new ArrayList<Task>();

    }

    public List<Task> getCheckedTasks() { return checkedTasks;  };
    public List<Task> getUncheckedTasks() { return uncheckedTasks;  };

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TaskViewHolder tvh = TaskViewHolder.create(parent, type);

        return tvh;
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        Task current = getItem(position);
        holder.bind(current, tvm, checkedTasks, uncheckedTasks);
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