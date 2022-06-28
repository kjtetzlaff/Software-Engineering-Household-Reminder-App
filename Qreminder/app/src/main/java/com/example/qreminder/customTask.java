package com.example.qreminder;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import com.example.qreminder.databinding.FragmentAddtasksBinding;
import com.example.qreminder.databinding.FragmentCustomTaskBinding;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;


public class customTask extends Fragment {

    TaskViewModel tvm = new ViewModelProvider(this).get(TaskViewModel.class);

    private FragmentCustomTaskBinding binding;
    private DatePickerDialog datePickerDialog;
    private Button dateButton;

    public customTask() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentCustomTaskBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initDatePicker();
        dateButton=view.findViewById(R.id.date_Select);
        dateButton.setText(getToday());
        binding.createTaskDoneButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Task newTask = new Task(binding.taskName.getText().toString(),
                        new Date((String)binding.dateSelect.getText()),
                        binding.reminderDropdown.getCount()
                      );


                tvm.insert(newTask);
                NavHostFragment.findNavController(customTask.this)
                        .navigate(R.id.action_customTask_to_addTask);
            }
        });
        binding.createTaskCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(customTask.this)
                        .navigate(R.id.action_customTask_to_addTask);
            }
        });
        binding.dateSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDatePicker(view);
            }
        });
    }

    private String getToday(){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) +1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        String a = month+"/"+day+"/"+year;
        return a;
    }
    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                String date = month+"/"+dayOfMonth+"/"+year;
                dateButton.setText(date);

            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(getView().getContext(), style, dateSetListener, year, month, day);

    }

    public void openDatePicker(View view){
        datePickerDialog.show();

    }

}

