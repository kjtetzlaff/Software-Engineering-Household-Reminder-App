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

        RecyclerView recyclerView = binding.overdueRecycler;
        final TaskListAdapter adapter = new TaskListAdapter(new TaskListAdapter.TaskDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        tvm = new ViewModelProvider(this).get(TaskViewModel.class);
        tvm.getAllTasks().observe(getViewLifecycleOwner(), tasks -> {
            // Update the cached copy of the words in the adapter.
            adapter.submitList(tasks);
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