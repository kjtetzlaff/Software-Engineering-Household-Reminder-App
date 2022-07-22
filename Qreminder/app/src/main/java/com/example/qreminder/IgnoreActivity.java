package com.example.qreminder;

import android.app.NotificationManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class IgnoreActivity extends AppCompatActivity {

    public IgnoreActivity(){
        System.out.println("Here");
        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancel(MainActivity.id);
    }
}