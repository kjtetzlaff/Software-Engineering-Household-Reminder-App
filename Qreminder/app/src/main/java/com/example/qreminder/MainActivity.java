package com.example.qreminder;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.qreminder.databinding.ActivityMainBinding;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private TaskViewModel tvm;
    public static int id = 1;
    public static Task currentTask = new Task();
    private Boolean con = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        tvm = new ViewModelProvider(this).get(TaskViewModel.class);

        //Creates the channel for the notifications
        createNotificationChannel();

        BroadcastReceiver receiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (!con) collectTasks();
                con = false;
            }
        };
            IntentFilter filter = new IntentFilter(Intent.ACTION_TIME_TICK);
            registerReceiver(receiver,filter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_settings) {
            return true;
        }
        */


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }



//notification channel code here
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("My Notification", "My Notification", NotificationManager.IMPORTANCE_DEFAULT);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(this, MainActivity.class);

    }


    //notification code here
    //Add options to complete or ignore
private void addNotification(Task task) {

        //Sets whatever the current task is
        currentTask = task;

        //Sets the main intent for the notification
        Intent mainIntent = new Intent(this, MainActivity.class);
        mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent mainPIntent = PendingIntent.getActivity(this, 0, mainIntent,PendingIntent.FLAG_IMMUTABLE);

        //Sets the Complete action intent for the notification
        Intent completeIntent = new Intent(this, CompleteActivity.class);
        completeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent completePIntent = PendingIntent.getActivity(this, 0, completeIntent,PendingIntent.FLAG_IMMUTABLE);

        //Sets the ignore intent for the notification
        Intent ignoreIntent = new Intent(this, IgnoreActivity.class);
        ignoreIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent ignorePIntent = PendingIntent.getActivity(this, 0, ignoreIntent,PendingIntent.FLAG_IMMUTABLE);

        //Builds and creates the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "My Notification");

        //possibly change design
        builder.setSmallIcon(R.drawable.ic_launcher_background);
        builder.setContentTitle("QReminder Notification");
        //Sets the text that the notification will display.
        builder.setContentText("Your "+ currentTask.getName()+" is overdue");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        //sets the notification to auto cancel when tapped
        builder.setAutoCancel(true);

        //Sets the intent
        builder.setContentIntent(mainPIntent);

        //Adds the action for complete
        builder.addAction(R.drawable.ic_launcher_background, "Complete", completePIntent);

        //Adds the action for ignore
        builder.addAction(R.drawable.ic_launcher_background, "Ignore", ignorePIntent);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MainActivity.this);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(id, builder.build());
        id++;
}

    public void collectTasks(){
        //Currently only works when completed in myTasks frame, but it does work to notify.
        List<Task> allTasks = tvm.getAllTasks().getValue();

        if (allTasks!=null) {
            for (Task task : allTasks) {
                if (task.getDateDue().before(Calendar.getInstance().getTime())) {
                    addNotification(task);
                }

            }
        }
        con = true;

    }



    public void action(){
        System.out.println("Hello WOrld");
    }




}