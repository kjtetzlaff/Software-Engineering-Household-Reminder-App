package com.example.qreminder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qreminder.databinding.FragmentMytasksBinding;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class myTasks extends Fragment {

    private FragmentMytasksBinding binding;
    private TaskViewModel tvm;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {


        binding = FragmentMytasksBinding.inflate(inflater, container, false);
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("My Tasks"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Edit Tasks"));

        binding.tabLayout.getTabAt(0).select();
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition() == 1) {
                    NavHostFragment.findNavController(myTasks.this)
                        .navigate(R.id.action_myTasks_to_editTasks);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        RecyclerView overdueRecyclerView = binding.overdueRecycler;
        final TaskListAdapter overdueAdapter = new TaskListAdapter(new TaskListAdapter.TaskDiff());
        overdueRecyclerView.setAdapter(overdueAdapter);
        overdueRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        RecyclerView upcomingRecyclerView = binding.upcomingRecycler;
        final TaskListAdapter upcomingAdapter = new TaskListAdapter(new TaskListAdapter.TaskDiff());
        upcomingRecyclerView.setAdapter(upcomingAdapter);
        upcomingRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        tvm = new ViewModelProvider(requireActivity()).get(TaskViewModel.class);
        tvm.getAllTasks().observe(getViewLifecycleOwner(), tasks -> {
            // Update the cached copy of the words in the adapter.
            List<Task> upcoming = new ArrayList<Task>();
            List<Task> overdue = new ArrayList<Task>();


            for (Task task:tasks) {
                if (task.getDateDue().before(Calendar.getInstance().getTime())) {
                    overdue.add(task);
                } else {
                    upcoming.add(task);
                }
            }

            overdueAdapter.submitList(overdue);
            upcomingAdapter.submitList(upcoming);
        });

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}