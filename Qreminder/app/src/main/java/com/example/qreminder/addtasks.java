package com.example.qreminder;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qreminder.databinding.FragmentAddtasksBinding;

public class addtasks extends Fragment {

    private FragmentAddtasksBinding binding;
    private TaskViewModel tvm;



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
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {


        tvm.getAllTasks().observe(getViewLifecycleOwner(), tasks -> {
            // Update the cached copy of the words in the adapter.

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

