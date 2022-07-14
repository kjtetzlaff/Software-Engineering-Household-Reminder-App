package com.example.qreminder;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qreminder.databinding.FragmentEdittasksBinding;
import com.example.qreminder.databinding.FragmentMytasksBinding;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class EditTasksFragment extends Fragment {

    private FragmentEdittasksBinding binding;
    private TaskViewModel tvm;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentEdittasksBinding.inflate(inflater, container, false);

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("My Tasks"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Edit Tasks"));
        binding.tabLayout.getTabAt(1).select();

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition() == 0) {
                    NavHostFragment.findNavController(EditTasksFragment.this)
                            .navigate(R.id.action_EditTasks_to_MyTasks);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                if(tab.getPosition() == 0) {
                    NavHostFragment.findNavController(EditTasksFragment.this)
                            .navigate(R.id.action_myTasks_to_editTasks);
                }
            }
        });

        tvm = new ViewModelProvider(requireActivity()).get(TaskViewModel.class);
        RecyclerView editRecyclerView = binding.editRecyclerView;
        final TaskListAdapter adapter = new TaskListAdapter(new TaskListAdapter.TaskDiff(), 2, tvm);
        editRecyclerView.setAdapter(adapter);
        editRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        tvm.getAllTasks().observe(getViewLifecycleOwner(), tasks -> {
            // Update the cached copy of the words in the adapter.
            adapter.submitList(tasks);
        });

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(EditTasksFragment.this)
                        .navigate(R.id.action_EditTasks_to_AddTasks);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}