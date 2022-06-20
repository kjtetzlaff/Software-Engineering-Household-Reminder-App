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
import com.google.android.material.tabs.TabLayout;


public class EditTasksFragment extends Fragment {

    private FragmentEdittasksBinding binding;

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
                            .navigate(R.id.action_myTasks_to_editTasks);
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

        binding.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(EditTasksFragment.this)
                        .navigate(R.id.action_EditTasks_to_CustomTask);
            }
        });

        binding.imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(EditTasksFragment.this)
                        .navigate(R.id.action_EditTasks_to_CustomTask);
            }
        });

        binding.imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(EditTasksFragment.this)
                        .navigate(R.id.action_EditTasks_to_CustomTask);
            }
        });

        binding.imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(EditTasksFragment.this)
                        .navigate(R.id.action_EditTasks_to_CustomTask);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}