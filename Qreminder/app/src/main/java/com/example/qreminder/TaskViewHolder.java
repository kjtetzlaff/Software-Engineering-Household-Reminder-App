package com.example.qreminder;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

public class TaskViewHolder extends RecyclerView.ViewHolder {

    private int type;
    TaskViewModel tvm;

    private TaskViewHolder(View itemView, int type) {
        super(itemView);
        this.type = type;
    }

    public void bind(Task t, TaskViewModel tvm) {
        CheckBox checkBox = itemView.findViewById(R.id.task_checkbox);
        checkBox.setText(t.getName());

        if (type == 1) {
            TextView taskItemView = itemView.findViewById(R.id.textView);
            taskItemView.setText(t.dateString());
        }

        if (type == 2) {
            ImageButton b = itemView.findViewById(R.id.edit_button);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name = t.getName();
                    EditTasksFragmentDirections.ActionEditTasksToCustomTask action
                            = EditTasksFragmentDirections.actionEditTasksToCustomTask(name);
                    Navigation.findNavController(view).navigate(action);
                }
            });
            ImageButton delete = itemView.findViewById(R.id.delete_button);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    buildPopUp(t, view, tvm);
                }
            });
        }


    }

    static TaskViewHolder create(ViewGroup parent, int type) {
        if (type == 1) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.mytasks_task_item, parent, false);
            return new TaskViewHolder(view, 1);
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.edittasks_task_item, parent, false);
            return new TaskViewHolder(view, 2);
        }
    }

    public void buildPopUp(Task task, View view, TaskViewModel tvm) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

        builder.setCancelable(false);
        builder.setTitle("!WARNING!");
        builder.setMessage("Are you sure you would like to delete "+ task.getName()+"?");
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();

            }
        });

        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Action based on pressing delete
                tvm.delete(task.getName());

            }

        });

        builder.show();

    }
}
