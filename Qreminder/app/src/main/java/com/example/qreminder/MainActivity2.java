package com.example.qreminder;

import static androidx.navigation.NavHostController.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;

import com.example.qreminder.databinding.ActivityMainBinding;

public class MainActivity2 extends AppCompatActivity {

    private ActivityMainBinding binding;
    private TaskViewModel tvm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        tvm = new ViewModelProvider(this).get(TaskViewModel.class);
        int id = 0;

        if (getIntent().hasExtra("Complete")){
            //Figure out how to grab the specific id of the notification
            id = getIntent().getIntExtra("Complete", 0);
            int testIndex = 0;
            int updateIndex = 0;
            while (testIndex<MainActivity.notificationList.size()){
                if (MainActivity.notificationList.get(testIndex).equals(id)){
                    updateIndex = testIndex-1;
                    break;
                }
                testIndex++;
            }
            try {
               Task task = (Task) MainActivity.notificationList.get(updateIndex);
               task.update();
               tvm.update(task);
            } catch(Exception e){System.out.println("Oops");}

        }

        else if (getIntent().hasExtra("Ignore")) {
           id = getIntent().getIntExtra("Ignore", 0);

        }

            manager.cancel(id);


    }


    }
