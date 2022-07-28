package com.example.qreminder;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qreminder.databinding.FragmentAddtasksBinding;

import java.util.List;

public class addtasks extends Fragment {

    private FragmentAddtasksBinding binding;
    private TaskViewModel tvm;

    private List<Task> taskList;

    private TaskListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddtasksBinding.inflate(inflater, container, false);
        tvm = new ViewModelProvider(requireActivity()).get(TaskViewModel.class);
        taskList = tvm.getAllTasks().getValue();
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        tvm.getAllTasks().observe(getViewLifecycleOwner(), tasks -> {
            // Update the cached copy of the words in the adapter.
            taskList = tasks;
        });



        RecyclerView addRecyclerView = binding.addRecyclerView;
        adapter = new TaskListAdapter(new TaskListAdapter.TaskDiff(), 3, tvm);
        addRecyclerView.setAdapter(adapter);
        addRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        tvm.getInactiveTasks().observe(getViewLifecycleOwner(), tasks -> {
            // Update the cached copy of the words in the adapter.
            String search = binding.searchText.getText().toString();
            adapter.submitList(TaskViewModel.searchTasks(tasks, search));
            if (tasks != null && tasks.size() <= 0){
                tvm.initializeDatabase();
            }
        });

        binding.Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = binding.searchText.getText().toString();
                adapter.submitList(TaskViewModel.searchTasks(tvm.getInactiveTasks().getValue(), search));
            }
        });


       binding.addCustomTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addtasksDirections.ActionAddTasksToCustomTask action =
                        addtasksDirections.actionAddTasksToCustomTask("");
                Navigation.findNavController(view).navigate(action);
            }

        });

        binding.addTasksDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (Task t:adapter.getCheckedTasks()) {
                    t.setActive(2);
                    tvm.update(t);
                }

                for (Task t:adapter.getUncheckedTasks()) {
                    t.setActive(0);
                    tvm.update(t);
                }

                NavHostFragment.findNavController(addtasks.this)
                        .navigate(R.id.action_Addtasks_to_MyTasks);
            }

        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}

