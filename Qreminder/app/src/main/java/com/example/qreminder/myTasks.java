package com.example.qreminder;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
    //private boolean con = false;

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
        final TaskListAdapter overdueAdapter = new TaskListAdapter(new TaskListAdapter.TaskDiff(), 1);
        overdueRecyclerView.setAdapter(overdueAdapter);
        overdueRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        RecyclerView upcomingRecyclerView = binding.upcomingRecycler;
        final TaskListAdapter upcomingAdapter = new TaskListAdapter(new TaskListAdapter.TaskDiff(), 1);
        upcomingRecyclerView.setAdapter(upcomingAdapter);
        upcomingRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        tvm = new ViewModelProvider(requireActivity()).get(TaskViewModel.class);
        tvm.getAllTasks().observe(getViewLifecycleOwner(), tasks -> {
            // Update the cached copy of the words in the adapter.
            List<Task> upcoming = new ArrayList<Task>();
            List<Task> overdue = new ArrayList<Task>();

            List<Task> notify = dateChecker.whatToNotify(tasks);

            for (Task task: notify){
                buildPopUp(task);

            }

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

    public void buildPopUp(Task task){
        AlertDialog.Builder builder = new AlertDialog.Builder(getView().getContext());

        builder.setCancelable(false);
        builder.setTitle("Due Task");
        builder.setMessage("Your "+ task.getName()+ " is due today");
        builder.setNegativeButton("Ignore", new DialogInterface.OnClickListener(){
            @Override
                    public void onClick(DialogInterface dialogInterface, int i){
                dialogInterface.cancel();

            }
        });

        builder.setPositiveButton("Completed", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                //Action when task has been completed

                task.update();
                tvm.update(task);

                //Attempt at updating the database
                //TaskDatabase db = TaskDatabase.getDatabase(getView().getContext().getApplicationContext());
                //TaskDAO myTaskDAO = db.taskDAO();
                //myTaskDAO.updateTask(task.getName(),task.getFrequency(),task.getDay(),task.getMonth(),task.getYear());
                updateListView();







            }        });

        builder.show();




    }

    public void updateListView(){
        RecyclerView overdueRecyclerView = binding.overdueRecycler;
        final TaskListAdapter overdueAdapter = new TaskListAdapter(new TaskListAdapter.TaskDiff(), 1);
        overdueRecyclerView.setAdapter(overdueAdapter);
        overdueRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        RecyclerView upcomingRecyclerView = binding.upcomingRecycler;
        final TaskListAdapter upcomingAdapter = new TaskListAdapter(new TaskListAdapter.TaskDiff(), 1);
        upcomingRecyclerView.setAdapter(upcomingAdapter);
        upcomingRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        tvm = new ViewModelProvider(requireActivity()).get(TaskViewModel.class);
        tvm.getAllTasks().observe(getViewLifecycleOwner(), tasks -> {
            // Update the cached copy of the words in the adapter.
            List<Task> upcoming = new ArrayList<Task>();
            List<Task> overdue = new ArrayList<Task>();

            List<Task> notify = dateChecker.whatToNotify(tasks);


            for (Task task:tasks) {
                if (task.getDateDue().before(Calendar.getInstance().getTime())) {
                    overdue.add(task);
                } else {
                    upcoming.add(task);
                }
            }

            //Creates a list of tasks that need a notification



            overdueAdapter.submitList(overdue);
            upcomingAdapter.submitList(upcoming);
        });

    }

}