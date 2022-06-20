package com.example.qreminder;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qreminder.databinding.FragmentEdittasksBinding;
import com.example.qreminder.databinding.FragmentMytasksBinding;


public class EditTasksFragment extends Fragment {

    private FragmentEdittasksBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentEdittasksBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        binding.myTasksTab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(EditTasksFragment.this)
//                        .navigate(R.id.action_EditTasks_to_MyTasks);
//            }
//        });

        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(EditTasksFragment.this)
                        .navigate(R.id.action_EditTasks_to_AddTasks);
            }
        });

//        binding.imageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(EditTasksFragment.this)
//                        .navigate(R.id.action_EditTasks_to_CustomTask);
//            }
//        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}