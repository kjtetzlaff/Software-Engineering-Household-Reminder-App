package com.example.qreminder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class TaskViewHolder extends RecyclerView.ViewHolder {
    private final TextView taskItemView;
    private final CheckBox checkBox;

    private TaskViewHolder(View itemView) {
        super(itemView);
        taskItemView = itemView.findViewById(R.id.textView);
        checkBox = itemView.findViewById(R.id.task_checkbox);

    }

    public void bind(String dateText, String nameText) {
        taskItemView.setText(dateText);
        checkBox.setText(nameText);
    }

    static TaskViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new TaskViewHolder(view);
    }
}
